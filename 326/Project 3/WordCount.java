

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order.
 * 
 * @author Kellen
 * 
 */
public class WordCount {

	/**
	 * Execute a run of word count
	 * 
	 * @param args
	 *            Command line arguments determining how the program will be run
	 */
	public static void main(String[] args) {

		/**
		 * Whether or not the frequency pairs should be printed at the end of
		 * the file
		 */
		boolean printFrequency = false;

		/**
		 * Whether or not the number of unique words should be printed at the
		 * end of the file
		 */
		boolean printUnique = false;

		/**
		 * The name of the file to be read
		 */
		String filename = "";

		/**
		 * The data counter to use to count words
		 */
		DataCounter<String> counter = null;

		/**
		 * Must have at least a type of word counter and a file name
		 */
		if (args.length < 2) {
			printUsage();
			System.exit(1);
		}

		// Iterate through each argument and take appropriate action
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-b")) {
				counter = new BSTCounter<String>();
			} else if (args[i].equals("-a")) {
				counter = new AVLCounter<String>();
			} else if (args[i].equals("-s")) {
				counter = new SplayCounter<String>();
			} else if (args[i].equals("-h")) {
				counter = new CuckooHashTable();
			} else if (args[i].equals("-jh")) {
				counter = new JavaHashCounter<String>();
			} else if (args[i].equals("-jt")) {
				counter = new JavaTreeCounter<String>();
			} else if (args[i].equals("-frequency")) {
				printFrequency = true;
			} else if (args[i].equals("-num_unique")) {
				printUnique = true;
			} else {
				filename = args[i];
				break;
			}

		}

		// If the filename has not been specified or the counter is null we
		// cannot continue
		if (filename.equals("") || counter == null) {
			printUsage();
			System.exit(1);
		}

		TimerMethods.loadFile(filename, counter);

		if (printFrequency) {
			DataCount<String>[] counts = TimerMethods.getCounts(counter);
			SortingAlgorithms.quickSort(counts);

			// for (DataCount<String> c : counts)
				// System.out.println(c.count + " \t" + c.data);
		}

		if (printUnique) {
			System.out.println(counter.getSize());
		}
	}


	/**
	 * Prints the usage instructions to standard error. You should modify this
	 * method to document whatever command-line arguments you add to the
	 * program.
	 */
	private static void printUsage() {
		System.err
				.println("Usage: java WordCount [ -b | -a | -s | -h ] [ -frequency | -num_unique ] <filename>");
		System.err.println("\t-b\t\t-- Use an Unbalanced BST");
		System.err.println("\t-a\t\t-- Use an AVL Tree");
		System.err.println("\t-s\t\t-- Use a Splay Tree");
		System.err.println("\t-h\t\t-- Use a Hashtable");
		System.err.println("\t-jt\t\t-- Use Java's TreeMap");
		System.err.println("\t-jh\t\t-- Use Java's HashMap");
		System.err
				.println("\t-frequency\t-- Print all the word/frequency pairs, ordered by frequency, and then by the words in lexicographic order");
		System.err
				.println("\t-num_unique\t-- Print the number of unique words in the document.");

		System.err.println();
	}

}
