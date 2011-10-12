/**
 * Kellen Donohue
 * 04/06/2009
 * CSE 326 AA
 * Eric McCambridge
 * Project1  - ListStack.java
 */

import java.util.EmptyStackException;;

public class ListStack implements DStack {

	ListNode last; // The last node inserted into the array

	/**
     * @return Returns whether or not the stack is empty
     */
	@Override
	public boolean isEmpty() {
		return last == null;
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

		return last.getValue();
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

		double value = last.getValue();
		last = last.getNext(); // Advance what we're tracking the last node
		return value;
	}

	/**
     * Puts the given double on the stack
     * @param d The double to place on the stack
     */
	@Override
	public void push(double d) {
		// Put the new value in the proper place in the linked list
		ListNode newNode = new ListNode(d, last);
		last = newNode;
	}

	private class ListNode
	{
		private double value; // The value held by this listnode
		private ListNode next; // The next value in the list

		/**
		 * Create a new ListNode
		 * @param val The value contained in the listNode
		 * @param nextNode The node to set as this node's Next
		 */
		public ListNode(double val, ListNode nextNode)
		{
			this.value = val;
			this.next = nextNode;
		}

		/**
		 * Returns this node's nextNode
		 * @return The node's nextNode
		 */
		public ListNode getNext()
		{
			return this.next;
		}

		/**
		 * The value of this node
		 * @return The node's value
		 */
		public double getValue()
		{
			return this.value;
		}
	}
}


