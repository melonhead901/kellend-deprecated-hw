/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * 05/17/2009
 * CSE 326 A
 * Project 3 - BSTCounter.java
 */

/**
 * BSTCounter implements the DataCounter interface using a splay tree to store
 * the data items and counts.
 * 
 * @param <E>
 *            The type of the data elements. E must be Comparable.
 * 
 * @author Zach
 */
public class BSTCounter<E extends Comparable<? super E>> implements
		DataCounter<E> {

	/**
	 * The root of the binary search tree. Root is null if and only if the tree
	 * is empty.
	 */
	protected BSTNode<E> overallRoot;

	/**
	 * The number of nodes in tree.
	 */
	protected int size;

	/**
	 * Generic inner static class representing a node in the tree. Each node
	 * includes a data element and an integer count. The class is protected so
	 * that it may be accessed by subclasses of BSTCounter.
	 * 
	 * @param <E>
	 *            The type of data stored in the node
	 */
	protected static class BSTNode<E extends Comparable<? super E>> {
		/**
		 * The left child of this node.
		 */
		protected BSTNode<E> left;

		/**
		 * The right child of this node.
		 */
		protected BSTNode<E> right;

		/**
		 * The data element stored at this node.
		 */
		protected E data;

		/**
		 * The count for this data element.
		 */
		protected int count;

		/**
		 * Create a new data node.
		 * 
		 * @param data
		 *            The data element to be stored at this node.
		 */
		public BSTNode(E data) {
			this.data = data;
			count = 1;
			left = null;
			right = null;
		}
	}

	/**
	 * Create an empty binary search tree.
	 */
	public BSTCounter() {
		overallRoot = null;
		size = 0;
	}

	/** {@inheritDoc} */
	@Override
	public void incCount(E data) {
		if (overallRoot == null) {
			overallRoot = new BSTNode<E>(data);
			size++;
		} else {
			// traverse the tree
			BSTNode<E> currentNode = overallRoot;
			while (true) {
				// compare the data to be inserted with the data at the current
				// node
				int cmp = data.compareTo(currentNode.data);

				if (cmp == 0) {
					// current node is a match
					currentNode.count++;

					return;
				} else if (cmp < 0) {
					// new data goes to the left of the current node
					if (currentNode.left == null) {
						currentNode.left = new BSTNode<E>(data);
						size++;

						return;
					} else {
						currentNode = currentNode.left;
					}
				} else {
					// new data goes to the right of the current node
					if (currentNode.right == null) {
						currentNode.right = new BSTNode<E>(data);
						size++;

						return;
					} else {
						currentNode = currentNode.right;
					}
				}
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public int getSize() {
		return size;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public DataCount<E>[] getCounts() {
		DataCount<E>[] counts = new DataCount[size];
		if (overallRoot != null)
			traverse(overallRoot, counts, 0);
		return counts;
	}

	/**
	 * Do an inorder traversal of the tree, filling in an array of DataCount
	 * objects with the count of each element. Doing an inorder traversal
	 * guarantees that the result will be sorted by element. We fill in some
	 * contiguous block of array elements, starting at index, and return the
	 * next available index in the array.
	 * 
	 * @param root
	 *            The root of the tree to traverse.
	 * 
	 * @param counts
	 *            The array to populate.
	 * 
	 * @param idx
	 *            The current index to insert an element into.
	 */
	protected int traverse(BSTNode<E> root, DataCount<E>[] counts, int idx) {
		if (root != null) {
			idx = traverse(root.left, counts, idx);
			counts[idx] = new DataCount<E>(root.data, root.count);
			idx = traverse(root.right, counts, idx + 1);
		}
		return idx;
	}

	/**
	 * Rotate the given node with it's left child.
	 * 
	 * @param k2
	 *            The parent node to rotate.
	 * @return The new root node.
	 * 
	 */
	protected BSTNode<E> rotateWithLeftChild(BSTNode<E> k2) {
		BSTNode<E> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;

		return k1;
	}

	/**
	 * Perform a double rotation on the given node, it's left child, and that
	 * node's right child.
	 * 
	 * @param k3
	 *            The parent node to rotate.
	 * @return The new root node.
	 */
	protected BSTNode<E> doubleWithLeftChild(BSTNode<E> k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	/**
	 * Rotate the given node with it's right child.
	 * 
	 * @param k2
	 *            The parent node to rotate.
	 * @return The new root node.
	 * 
	 */
	protected BSTNode<E> rotateWithRightChild(BSTNode<E> k2) {
		BSTNode<E> k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;

		return k1;
	}

	/**
	 * Perform a double rotation on the given node, it's right child, and that
	 * node's left child.
	 * 
	 * @param k3
	 *            The parent node to rotate.
	 * @return The new root node.
	 */
	protected BSTNode<E> doubleWithRightChild(BSTNode<E> k3) {
		k3.right = rotateWithLeftChild(k3.right);
		return rotateWithRightChild(k3);
	}

	/**
	 * 
	 * @return The current height of the tree.
	 */
	public int TreeHeight() {
		return TreeHeightHelper(overallRoot) - 1;
	}

	protected int TreeHeightHelper(BSTNode<E> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + Math.max(TreeHeightHelper(node.left),
					TreeHeightHelper(node.right));
		}
	}

	/**
	 * Dump the contents of the tree to a String (provided for debugging and
	 * unit testing purposes).
	 * 
	 * @return a textual representation of the tree.
	 */
	protected String dump() {
		if (overallRoot != null) {
			return dump(overallRoot);
		} else {
			return "<empty tree>";
		}
	}

	/**
	 * Dump the contents of the subtree rooted at this node to a String
	 * (provided for debugging purposes).
	 * 
	 * @return a textual representation of the subtree rooted at this node.
	 */
	protected String dump(BSTNode<E> root) {
		if (root == null)
			return ".";

		String out = "([" + root.data + "," + root.count + "] ";
		out += dump(root.left);
		out += " ";
		out += dump(root.right);
		out += ")";

		return out;
	}
}
