/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 12, 2009
 * CSE 326 A
 * Project 3 - VisualAVLCounter.java
 */

/**
 * Clone of AVLCounter implementing listeners. Separate so that benchmarks
 * aren't slowed down by alerts.
 * 
 * @author Zach
 */
public class VisualAVLCounter<E extends Comparable<? super E>> extends
		VisualBSTCounter<E> implements DataCounter<E> {

	/**
	 * @author Zach
	 * 
	 *         Extends VisualBSTNode, also storing the node's height.
	 * 
	 * @param <E>
	 *            The type of data stored in the node.
	 */
	protected static class VisualAVLNode<E extends Comparable<? super E>>
			extends VisualBSTNode<E> {

		/**
		 * The height of this node.
		 */
		private int height;

		/**
		 * Create a new data node.
		 * 
		 * @param data
		 */
		public VisualAVLNode(E data) {
			super(data);
			height = 0;
		}
	}

	/**
	 * Create an empty AVL tree.
	 */
	public VisualAVLCounter() {
		super();
	}

	/** {@inheritDoc} */
	@Override
	public void incCount(E data) {
		overallRoot = insert(data, (VisualAVLNode<E>) overallRoot);
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
	private VisualAVLNode<E> insert(E data, VisualAVLNode<E> root) {
		// if root is null return a new node containing data
		if (root == null) {
			size++;
			return new VisualAVLNode<E>(data);
		}

		// compare the data to be inserted with the data at root
		int cmp = data.compareTo(root.data);

		if (cmp < 0) {
			// insert in the left subtree
			root.left = insert(data, (VisualAVLNode<E>) root.left);
			// rotate if necessary
			if (Height((VisualAVLNode<E>) root.left)
					- Height((VisualAVLNode<E>) root.right) == 2) {
				if (data.compareTo(root.left.data) < 0) {
					root = (VisualAVLNode<E>) rotateWithLeftChild(root);
				} else {
					root = (VisualAVLNode<E>) doubleWithLeftChild(root);
				}

				alertListeners(root);
			} else {
				alertListeners(root.left);
			}
		} else if (cmp > 0) {
			// insert in the right subtree
			root.right = insert(data, (VisualAVLNode<E>) root.right);
			// rotate if necessary
			if (Height((VisualAVLNode<E>) root.right)
					- Height((VisualAVLNode<E>) root.left) == 2) {
				if (data.compareTo(root.right.data) > 0) {
					root = (VisualAVLNode<E>) rotateWithRightChild(root);
				} else {
					root = (VisualAVLNode<E>) doubleWithRightChild(root);
				}

				alertListeners(root);
			} else {
				alertListeners(root);
			}
		} else {
			// duplicate
			root.count++;

			alertListeners(root);
		}

		// update root height
		root.height = Math.max(Height((VisualAVLNode<E>) root.left),
				Height((VisualAVLNode<E>) root.right)) + 1;

		// return the root of the subtree
		return root;
	}

	/** {@inheritDoc} */
	@Override
	protected VisualBSTNode<E> rotateWithLeftChild(VisualBSTNode<E> k2) {
		VisualBSTNode<E> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		((VisualAVLNode<E>) k2).height = Math.max(
				Height((VisualAVLNode<E>) k2.left),
				Height((VisualAVLNode<E>) k2.right)) + 1;
		((VisualAVLNode<E>) k1).height = Math.max(
				Height((VisualAVLNode<E>) k1.left),
				((VisualAVLNode<E>) k2).height) + 1;

		// alertListeners(k1);

		return k1;
	}

	/** {@inheritDoc} */
	@Override
	protected VisualBSTNode<E> rotateWithRightChild(VisualBSTNode<E> k2) {
		VisualBSTNode<E> k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;
		((VisualAVLNode<E>) k2).height = Math.max(
				Height((VisualAVLNode<E>) k2.right),
				Height((VisualAVLNode<E>) k2.left)) + 1;
		((VisualAVLNode<E>) k1).height = Math.max(
				Height((VisualAVLNode<E>) k1.right),
				((VisualAVLNode<E>) k2).height) + 1;

		// alertListeners(k1);

		return k1;
	}

	/**
	 * Return the height of the indicated node or -1 if it is null.
	 */
	private int Height(VisualAVLNode<E> node) {
		return node == null ? -1 : node.height;
	}
}
