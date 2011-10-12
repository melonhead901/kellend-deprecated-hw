/**
 * @author Kellen Donohue, Zach Stein 
 * @author kellend, steinz
 * May 11, 2009 
 * CSE 326 A 
 * Project 3 - CuckooHashTable.java
 */

/**
 * A Hash Table based on Cuckoo Hashing
 * 
 * @author Kellen
 */
public class CuckooHashTable implements DataCounter<String> {

	/**
	 * String array that holds the data
	 */
	private StringCount[] data;

	/**
	 * Number of unique elements in the hashTable
	 */
	private int uniqEle;

	/**
	 * Default starting size of the hashTable
	 */
	public static final int INITIAL_SIZE = 19;

	/**
	 * Creates a new, empty hash table
	 */
	public CuckooHashTable() {
		this.data = new StringCount[INITIAL_SIZE];
		this.uniqEle = 0;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	public DataCount<String>[] getCounts() {
		DataCount<String>[] results = new DataCount[this.uniqEle];
		int j = 0;
		for (int i = 0; i < this.data.length; i++) {
			if (this.data[i] != null) {
				results[j++] = this.data[i];
			}
		}
		return results;
	}

	/** {@inheritDoc} */
	public int getSize() {
		return this.uniqEle;
	}

	/** {@inheritDoc} */
	public void incCount(String data) {
		// Check if element exists
		int loc = find(data);

		// If it does not insert it
		if (loc == -1) {
			insertNew(data);
		}

		// If it does increment count
		else {
			this.data[loc].count++;
		}
	}

	/**
	 * Create a new entry for the HashTable, and insert into the table
	 * 
	 * @param newVal
	 *            The new value to insert into the Hashtable
	 */
	private void insertNew(String newVal) {
		StringCount val = new StringCount(newVal, 1);
		this.insert(val);
		this.uniqEle++;
	}

	/**
	 * Inserts the given value into the hash table
	 * 
	 * @param val
	 *            The value to insert
	 */
	private void insert(StringCount val) {

		// Try to insert StringCounter to each of its first first locations
		boolean success = basicInsert(val);

		// If that fails, start cuckooing
		if (!success) {
			// While we aren't done, do the following
			boolean finished = false;

			int h1 = val.hash1() % this.data.length;
			StringCount current = val;
			StringCount temp = this.data[h1];

			ArrayList<StringCount> alreadySeen = new ArrayList<StringCount>();
			alreadySeen.add(val);

			while (!finished) {
				// Check that we haven't already seen current
				if (alreadySeen.contains(current)) {
					// If we have rehash and try insert again
					this.manifestDestiny();
					insert(val);
					break;
				}
				// Push the current value into its h1
				this.data[h1] = val;
				// The temp value, which was in h1, now becomes current
				current = temp;
				// Try to insert current, if it succeeds we're finished
				finished = basicInsert(current);
				// Otherwise temp becomes current's h1
				h1 = current.hash1() % this.data.length;
				temp = this.data[h1];
				// Remember that we've already seen this one
				alreadySeen.add(current);
			}
		}
	}

	/**
	 * Attempt a basic insert. i.e. check the two values the parameter hashes
	 * too
	 * 
	 * @param val
	 *            The object to insert if possible
	 * @return True if the insert succeeds, otherwise false
	 */
	private boolean basicInsert(StringCount val) {
		int h1 = val.hash1() % this.data.length;
		int h2 = val.hash2() % this.data.length;

		if (this.data[h1] == null) {
			// Check the first hash
			this.data[h1] = val;
		} else if (this.data[h2] == null) {
			// If that fails try the second hash
			this.data[h2] = val;
		} else {
			// Didn't successfully insert at either the first or second location
			return false;
		}
		return true;
	}

	/**
	 * Searches the hash table for the given data
	 * 
	 * @param data
	 *            The string to search for
	 * @return The index of that value in the hash table if it exists, -1
	 *         otherwise
	 */
	private int find(String data) {
		StringCount checker = new StringCount(data, 0);

		int h1 = checker.hash1() % this.data.length;
		int h2 = checker.hash2() % this.data.length;
		int result = -1;

		// If the requested value is in the hash table it will exist at either
		// the first or second hash locations
		if (this.data[h1] != null && this.data[h1].equals(checker)) {
			result = h1;
		} else if (this.data[h2] != null && this.data[h2].equals(checker)) {
			result = h2;
		}

		return result;
	}

	/**
	 * Enlarge the hash table and rehash each value The hashtable will expand
	 * across memory, taking over areas divinely alloted to it
	 */
	private void manifestDestiny() {

		// New hash table size should be prime, and at least twice as big as
		// current size
		int newSize = findNextPrime(2 * this.data.length);

		StringCount[] temp = this.data;
		this.data = new StringCount[newSize];
		;

		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != null) {
				insert(temp[i]);
			}
		}
	}

	/**
	 * Find the next prime, at least at large as num
	 * 
	 * @param num
	 *            The lowest value that can be returned
	 * @return The next prime at least as large as num
	 */
	private int findNextPrime(int num) {
		int current = num;
		while (!isPrime(current)) {
			current++;
		}
		return current;
	}

	/**
	 * Returns whether or not the given number is prime
	 * 
	 * @param num
	 *            To number whose primacy to check
	 * @return True if the number is prime, false if it is 0, 1 , negative or
	 *         composite
	 */
	private boolean isPrime(int num) {
		// Negative numbers, 0, or 1 fail
		if (num < 2) {
			return false;
		}

		// Check every factor 2 up to sqrt(num)
		for (int i = 2; i <= (int) Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Debugging test of tree
	 */
	public void printHashTable() {
		for (int i = 0; i < this.data.length; i++) {
			System.out.print(i + ":\t");
			if (this.data[i] != null) {
				System.out.print(this.data[i]);
			}
			System.out.println();
		}
	}
}
