/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 26, 2009
 * CSE 326 A
 * Project 3 - ArrayStack.java
 */

import java.util.EmptyStackException;

/**
 * Stack implementation adapted from Kellen's Project 2 MazeStack.
 * 
 * @author Zach
 * 
 * @param <E>
 *            The type of data in the stack
 */
public class ArrayStack<E> {

	/**
	 * The initial size of the array.
	 */
	public static final int INITIAL_SIZE = 30;

	/**
	 * The array that holds the data.
	 */
	private E[] array;

	/**
	 * The index of the top element in the array.
	 */
	private int last;

	/**
	 * Creates a new empty stack.
	 */
	public ArrayStack() {
		this(INITIAL_SIZE);
	}

	/**
	 * Creates a new ArrayStack with the given initial capacity.
	 * 
	 * @param initialCapacity
	 *            The initial capacity of the array.
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack(int initialCapacity) {
		array = (E[]) new Object[initialCapacity];
		last = -1;
	}

	/**
	 * Report whether the stack is empty.
	 * 
	 * @return true if the stack has no elements.
	 */
	public boolean isEmpty() {
		return last == -1;
	}

	/**
	 * Remove all elements from the stack.
	 */
	@SuppressWarnings("unchecked")
	public void makeEmpty() {
		array = (E[]) new Object[INITIAL_SIZE];
		last = -1;
	}

	/**
	 * Returns the number of elements in the stack.
	 */
	public int size() {
		return last + 1;
	}

	/**
	 * Removes and returns the most recently inserted item from the stack.
	 * 
	 * @exception EmptyStackException
	 *                thrown if stack is empty.
	 */
	public E pop() {
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}

		E result = array[last];

		array[last] = null;
		last--;

		return result;
	}

	/**
	 * Push a new item onto the stack.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	public void push(E x) {
		if (last + 1 == array.length) {
			this.enlargeArray();
		}

		last++;
		array[last] = x;
	}

	/**
	 * Get the most recently inserted item in the stack. Does not alter the
	 * stack.
	 * 
	 * @return the most recently pushed item on the stack.
	 * @exception EmptyStackException
	 *                thrown if stack is empty.
	 */
	public E peek() {
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}

		return array[last];
	}

	/**
	 * Enlarges the data array.
	 */
	@SuppressWarnings("unchecked")
	private void enlargeArray() {
		E[] newArray = (E[]) new Object[array.length * 2];

		for (int i = 0; i <= last; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
}
