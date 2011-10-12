/**
 * @author Kellen Donohue
 * 04-15-08
 * CSE 326 AA
 * Project 2 - MazeStack.Java
 */

import java.util.EmptyStackException;

public class MazeStack<E> implements Stack<E> {

	// The initial size of the array
	static final int INITIAL_SIZE = 100;
	
	// The array that holds the data
	private E[] array;
	
	// The index of the top element in the array
	int last;
	
	/*
	 * Creates a new, empty maze stack
	 */
	public MazeStack()
	{		
		this.makeNewStack();
	}

	@Override
	/**
	   * Report whether the stack is empty
	   * 
	   * @return true if the stack has no elements
	   */
	public boolean isEmpty() 
	{
		return last==0;
	}

	@Override
	  /**
	   * Remove all elements from the stack.
	   */
	public void makeEmpty() 
	{
		this.makeNewStack();
	}

	@Override
	  /**
	   * Remove the most recently inserted item from the stack.
	   * 
	   * @exception EmptyStackException thrown if stack is empty.
	   */
	public void pop() 
	{
		if(this.isEmpty())
		{
			throw new EmptyStackException();
		}
		
		array[last] = null; 
		last--;
	}

	@Override
	/**
	   * Push a new item onto the stack
	   * 
	   * @param x the item to insert.
	   */
	public void push(E x) 
	{
		last++;
		if(last == array.length)
		{
			this.enlargeArray();
		}

		array[last] = x;
	}

	@Override
	/**
	   * Get the most recently inserted item in the stack. Does not alter the stack.  
	   * 
	   * @return the most recently pushed item on the stack.
	   * @exception EmptyStackException thrown if stack is empty.
	*/	
	public E top() 
	{
		if(this.isEmpty())
		{
			throw new EmptyStackException();
		}
		
		return array[last];
	}
	
	@SuppressWarnings("unchecked")
	/*
	 * Creates a new, empty stack
	 */
	private void makeNewStack()
	{
		array = (E[])new Object[INITIAL_SIZE];
		last=0;
	}
	
	@SuppressWarnings("unchecked")
	/*
	 * Enlarges the array if necessary
	 */
	private void enlargeArray()
	{
		array = (E[])new Object[array.length*2];
	}
}
