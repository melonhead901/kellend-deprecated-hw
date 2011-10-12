/**
 * @author Kellen Donohue
 * Apr 29, 2009
 * CSE 326 AA
 * Project 2 - SkewHeap.java
 */

import java.util.NoSuchElementException;

/**
 * @author Kellen Donohue Create a priority queue, based on a skew heap
 *         implementation
 * @param <E>
 */
public class SkewHeap<E extends Comparable<? super E>> implements PriorityQueue<E> {

	/**
	 * The root node of the priority queue;
	 */
	private Node<E> root;

	/**
	 * Create an empty priority queue
	 */
	public SkewHeap() {
		this.root = null;
	}

	@Override
	public boolean isEmpty() {
		return this.root == null;
	}

	@Override
	public void makeEmpty() {
		this.root = null;
	}	

	@Override
	public E deleteMin() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		E result = this.root.data;
		this.root = this.merge(this.root.left, this.root.right);
		return result;
	}

	@Override
	public E findMin() {
		if(this.isEmpty()){
			throw new NoSuchElementException();
		}
		return this.root.data;
	}

	@Override
	public void insert(E x) {
		this.root = merge(new Node<E>(x), root);
	}

	/**
	 * Merge the two provided nodes
	 * 
	 * @param node1
	 *            The first node to merge
	 * @param node2
	 *            The second node to merge
	 * @return The result of the merge
	 */
	private Node<E> merge(Node<E> node1, Node<E> node2) {

		// If one node is null, simply return the other
		if (node1 == null) {
			return node2;
		}
		if (node2 == null) {
			return node1;
		}

		// Otherwise determine which node has a smaller root and process the
		// merge from there
		if (node1.data.compareTo(node2.data) < 0) {
			return executeMerge(node1, node2);
		} else {
			return executeMerge(node2, node1);
		}
	}

	/**
	 * Complete the merge, once it has been determined that both nodes are
	 * non-null, and whose head is lesser
	 * 
	 * @param smaller
	 *            The smaller of the two nodes
	 * @param larger
	 *            The larger of the two nodes
	 * @return The head of the resulting merge
	 */
	private Node<E> executeMerge(Node<E> smaller, Node<E> larger) {

		assert (smaller.data.compareTo(larger.data) < 0);

		// Swap the children and recursively call merge
		Node<E> temp = smaller.right;
		smaller.right = smaller.left;
		smaller.left = merge(temp, larger);

		return smaller;
	}

	/**
	 * A node, which contains data and point to a left and right child
	 */
	private static class Node<T> {

		/**
		 * The data contained by this node
		 */
		public T data;

		/**
		 * This node's left child
		 */
		public Node<T> left;

		/**
		 * This node's right child
		 */
		public Node<T> right;

		/**
		 * Create a new node with the specified data
		 * 
		 * @param x
		 *            The value of the new node
		 */
		public Node(T x) {
			this.data = x;
			this.left = null;
			this.right = null;
		}
	}
}
