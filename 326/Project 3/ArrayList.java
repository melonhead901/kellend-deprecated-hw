/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 26, 2009
 * CSE 326 A
 * Project 3 - ArrayList.java
 */

import java.util.Arrays;

/**
 * List implementation based off an array
 * 
 * @author kellend
 */
public class ArrayList<E> {

	/**
	 * The data contained within the list
	 */
	private E[] data;

	/**
	 * The size of the array at start
	 */
	private static final int INITIAL_SIZE = 10;

	/**
	 * The last position a value was inserted at
	 */
	private int lastInsertPos;

	/**
	 * Creates a new blank array list
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		this.data = (E[]) new Object[INITIAL_SIZE];
		this.lastInsertPos = -1;
	}

	/**
	 * Adds a value to the list
	 * 
	 * @param val
	 *            The value to add
	 */
	public void add(E val) {
		this.lastInsertPos++;

		// If the new list will be too large enlarge it
		if (this.lastInsertPos == this.data.length) {
			this.enlargeList();
		}

		this.data[this.lastInsertPos] = val;
	}
	
	/**
	 * Returns whether or not the value is in the list
	 * 
	 * @param current
	 *            The value to check
	 * @return True if the item is in the list, otherwise false
	 */
	public boolean contains(E val) {

		// Look at each value in the array
		for (int i = 0; i <= this.lastInsertPos; i++) {
			if (this.data[i].equals(val)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Enlarge the array list if it would otherwise overflow
	 */
	@SuppressWarnings("unchecked")
	private void enlargeList() {

		// Create a new array of double size
		int newSize = this.data.length * 2;

		E[] newArray = (E[]) new Object[newSize];
		for (int i = 0; i < newSize / 2; i++) {
			newArray[i] = this.data[i];
		}

		this.data = newArray;
	}

	@Override
	public String toString() {
		return Arrays.toString(this.data);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrayList other = (ArrayList) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		return true;
	}
}
