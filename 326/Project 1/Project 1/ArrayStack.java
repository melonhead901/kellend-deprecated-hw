/**
 * Kellen Donohue
 * 04/06/2009
 * CSE 326 AA
 * Eric McCambridge
 * Project1 - ArrayStack.java
 */

import java.util.EmptyStackException;

public class ArrayStack implements DStack {

	// The maximum amount of elements the array can contain
	private static final int ARRAY_SIZE = 1000000;

	// The location of the last element used
	private int last;

	// The array used to store the data
	private double[] array;

	/**
	 * Creates a new, empty ArrayStack
	 *
	 */
	public ArrayStack()
	{
		array = new double[ARRAY_SIZE];
	}

	/**
	 * Returns whether or not the stack is empty
	 * @return True if the stack is empty, otherwise false
	 */
	@Override
	public boolean isEmpty() {
		return last == 0;
	}

	/**
     * Looks at the value on the top of the stack, without returning it
     * @throws EmptyStackException if stack is empty
     * @return The value on the top of the stack
     */
	@Override
	public double peek() {
		if(this.isEmpty())
		{
			throw new EmptyStackException();
		}
		else return array[last];
	}

	/**
     * Returns the last value put on the stack
     * @return the deleted value
     * @throws EmptyStackException if stack is empty
     */
	@Override
	public double pop() {
		if(this.isEmpty())
		{
			throw new EmptyStackException();
		}
		last--;
		return array[last+1];
	}

	/**
     * Puts the given double on the stack
     * @param d The double to place on the stack
     */
	@Override
	public void push(double d) {
		// Put the new value in the proper place in the linked list
		last++;
		array[last] = d;
	}

}
