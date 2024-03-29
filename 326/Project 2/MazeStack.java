/**
 * @author Kellen Donohue
 * 04-15-08
 * CSE 326 AA
 * Project 2 - ArrayStack.Java
 */

import java.util.EmptyStackException;

public class MazeStack<E> implements Stack<E>{

	// The initial size of the array
	public static final int INITIAL_SIZE = 100;

	// The array that holds the data
	private E[] array;

	// The index of the top element in the array
	private int last;

	/**
	 * Creates a new, empty maze stack
	 */
	public MazeStack() {
		this(INITIAL_SIZE);
	}

	/**
	 * Creates a new ArrayStack with the given initial capacity
	 * 
	 * @param initialCapacity
	 *            The initial capacity to make ArrayStack
	 */
	@SuppressWarnings("unchecked")
	public MazeStack(int initialCapacity) {
		array = (E[]) new Object[initialCapacity];
		last = -1;
	}

	/**
	 * Report whether the stack is empty
	 * 
	 * @return true if the stack has no elements
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
	 * Remove the most recently inserted item from the stack.
	 * 
	 * @exception EmptyStackException
	 *                thrown if stack is empty.
	 */
	public void pop() {
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}

		array[last] = null;
		last--;
	}

	/**
	 * Push a new item onto the stack
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
	public E top() {
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}

		return array[last];
	}

	@SuppressWarnings("unchecked")
	/*
	 * Enlarges the array if necessary
	 */
	private void enlargeArray() {
		E[] newArray = (E[]) new Object[array.length * 2];

		for (int i = 0; i <= last; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
}
