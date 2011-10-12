/**
 * @author steinz
 * 4/17/09
 * CSE326 Project 2
 * LinkedListQueue pointer based queue used for BFS search
 */

import java.util.NoSuchElementException;

public class MazeQueue<E> implements Queue<E> {
	/**
	 * Simple node holding a value and a pointer to the next node
	 */
	private class Node<T> {
		/**
		 * Pointer to the next node
		 */
		public Node<T> next;
		/**
		 * Value in this node
		 */
		public T value;

		/**
		 * @param i_value
		 *            value to initialize the node with
		 */
		public Node(T i_value) {
			value = i_value;
			next = null;
		}
	}

	/**
	 * The front of the queue
	 */
	private Node<E> front;
	/**
	 * The back of the queue
	 */
	private Node<E> back;

	/**
	 * Constructs an empty list
	 */
	public MazeQueue() {
		makeEmpty();
	}

	/**
	 * @return the first element in the queue
	 * @throws NoSuchElementException
	 *             if the queue is empty
	 */
	public E dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Queue is empty");
		E result = front.value;
		front = front.next;
		return result;
	}

	/**
	 * add an element to the queue
	 */
	public void enqueue(E x) {
		if (isEmpty()) {
			front = back = new Node<E>(x);
		} else {
			back.next = new Node<E>(x);
			back = back.next;
		}
	}

	/**
	 * @return the first element in the queue
	 * @throws NoSuchElementException
	 *             if the queue is empty
	 */
	public E peek() {
		if (isEmpty())
			throw new NoSuchElementException("Queue is empty");
		return front.value;
	}

	/**
	 * @return true if the queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		return front == null;
	}

	/**
	 * Empty the queue
	 */
	public void makeEmpty() {
		front = null;
		back = null;
	}
}
