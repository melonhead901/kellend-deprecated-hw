/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 12, 2009
 * CSE 326 A
 * Project 3 - AVLCounter.java
 */

/**
 * AVLcounter implements the DataCounter interface using an AVL tree to store
 * the data items and counts.
 * 
 * @author Zach
 */
public class AVLCounter<E extends Comparable<? super E>> extends BSTCounter<E>
		implements DataCounter<E> {

	/**
	 * @author Zach
	 * 
	 *         Extends BSTNode, also storing the node's height.
	 * 
	 * @param <E>
	 *            The type of data stored in the node.
	 */
	protected static class AVLNode<E extends Comparable<? super E>> extends
			BSTNode<E> {

		/**
		 * The height of this node.
		 */
		private int height;

		/**
		 * Create a new data node.
		 * 
		 * @param data
		 */
		public AVLNode(E data) {
			super(data);
			height = 0;
		}
	}

	/**
	 * Create an empty AVL tree.
	 */
	public AVLCounter() {
		super();
	}

	/** {@inheritDoc} */
	@Override
	public void incCount(E data) {
		overallRoot = insert(data, (AVLNode<E>) overallRoot);
	}

	/**
	 * Recursive insertion method
	 * 
	 * @param data
	 *            The data element to insert into the subtree.
	 * @param root
	 *            The root of the subtree to insert the element into.
	 * @return The new root of the tree.
	 */
	private AVLNode<E> insert(E data, AVLNode<E> root) {
		// if root is null return a new node containing data
		if (root == null) {
			size++;
			return new AVLNode<E>(data);
		}

		// compare the data to be inserted with the data at root
		int cmp = data.compareTo(root.data);

		if (cmp < 0) {
			// insert in the left subtree
			root.left = insert(data, (AVLNode<E>) root.left);
			// rotate if necessary
			if (Height((AVLNode<E>) root.left)
					- Height((AVLNode<E>) root.right) == 2) {
				if (data.compareTo(root.left.data) < 0) {
					root = (AVLNode<E>) rotateWithLeftChild(root);
				} else {
					root = (AVLNode<E>) doubleWithLeftChild(root);
				}
			}
		} else if (cmp > 0) {
			// insert in the right subtree
			root.right = insert(data, (AVLNode<E>) root.right);
			// rotate if necessary
			if (Height((AVLNode<E>) root.right)
					- Height((AVLNode<E>) root.left) == 2) {
				if (data.compareTo(root.right.data) > 0) {
					root = (AVLNode<E>) rotateWithRightChild(root);
				} else {
					root = (AVLNode<E>) doubleWithRightChild(root);
				}
			}
		} else {
			// duplicate
			root.count++;
		}

		// update root height
		root.height = Math.max(Height((AVLNode<E>) root.left),
				Height((AVLNode<E>) root.right)) + 1;

		// return the root of the subtree
		return root;
	}

	/** {@inheritDoc} */
	@Override
	protected BSTNode<E> rotateWithLeftChild(BSTNode<E> k2) {
		BSTNode<E> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		((AVLNode<E>) k2).height = Math.max(Height((AVLNode<E>) k2.left),
				Height((AVLNode<E>) k2.right)) + 1;
		((AVLNode<E>) k1).height = Math.max(Height((AVLNode<E>) k1.left),
				((AVLNode<E>) k2).height) + 1;

		return k1;
	}

	/** {@inheritDoc} */
	@Override
	protected BSTNode<E> rotateWithRightChild(BSTNode<E> k2) {
		BSTNode<E> k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;
		((AVLNode<E>) k2).height = Math.max(Height((AVLNode<E>) k2.right),
				Height((AVLNode<E>) k2.left)) + 1;
		((AVLNode<E>) k1).height = Math.max(Height((AVLNode<E>) k1.right),
				((AVLNode<E>) k2).height) + 1;

		return k1;
	}

	/**
	 * Return the height of the indicated node or -1 if it is null.
	 */
	private int Height(AVLNode<E> node) {
		return node == null ? -1 : node.height;
	}
}
