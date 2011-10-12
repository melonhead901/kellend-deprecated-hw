/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 14, 2009
 * CSE 326 A
 * Project 3 - VisualSplayCounter.java
 */

/**
 * Clone of SplayCounter implementing listeners. Separate so that benchmarks
 * aren't slowed down by alerts.
 * 
 * @author Zach
 */
public class VisualSplayCounter<E extends Comparable<? super E>> extends
		VisualBSTCounter<E> implements DataCounter<E> {

	/**
	 * Enumerable used to represent steps to the right or left.
	 * 
	 * @author steinz
	 * 
	 */
	private enum direction {
		left, right
	};

	/**
	 * Stores a node and the direction traveled from that node.
	 * 
	 * @author steinz
	 * 
	 */
	private class step {

		/**
		 * The direction traveled from this node.
		 */
		private direction step_direction;

		/**
		 * The node at this step.
		 */
		private VisualBSTNode<E> node;

		/**
		 * Constructs a step.
		 * 
		 * @param dir
		 *            The direction traveled at this step.
		 * @param node
		 *            The node at this step.
		 */
		public step(direction dir, VisualBSTNode<E> node) {
			this.step_direction = dir;
			this.node = node;
		}
	}

	/**
	 * Create an empty splay tree.
	 */
	public VisualSplayCounter() {
		super();
	}

	/** {@inheritDoc} */
	public void incCount(E data) {
		// Iterative insertion

		// If overallRoot is null, overallRoot becomes a new node containing the
		// data
		if (overallRoot == null) {
			size++;
			overallRoot = new VisualBSTNode<E>(data);

			alertListeners(overallRoot);

			return;
		}

		// search the tree to find where data should go
		VisualBSTNode<E> root = overallRoot;
		int cmp = data.compareTo(root.data);
		ArrayStack<step> path = new ArrayStack<step>();

		// stop looking if you've found data
		while (cmp != 0) {
			if (cmp < 0) {
				// data goes to the left
				if (root.left != null) {
					// move to the left
					path.push(new step(direction.left, root));

					root = root.left;
				} else {
					// make a new node on the left
					root.left = new VisualBSTNode<E>(data);
					size++;

					path.push(new step(direction.left, root));

					root = root.left;
					splay(path);

					alertListeners(overallRoot);

					return;
				}
			} else if (cmp > 0) {
				// data goes to the right
				if (root.right != null) {
					// move to the right
					path.push(new step(direction.right, root));

					root = root.right;
				} else {
					// make a new node on the right
					root.right = new VisualBSTNode<E>(data);
					size++;

					path.push(new step(direction.right, root));

					root = root.right;
					splay(path);

					alertListeners(overallRoot);

					return;
				}
			}

			cmp = data.compareTo(root.data);
		}

		// you found the data you're looking for, increment it's count
		root.count++;

		splay(path);

		alertListeners(root);
	}

	private void splay(ArrayStack<step> path) {
		// return;
		// step back through the ArrayStack
		while (path.size() > 0) {
			// zig
			if (path.size() == 1) {
				step s = path.pop();
				if (s.step_direction == direction.left) {
					overallRoot = rotateWithLeftChild(s.node);
				} else {
					overallRoot = rotateWithRightChild(s.node);
				}
			} else if (path.size() == 2) {
				step s2 = path.pop();
				step s1 = path.pop();
				if (s1.step_direction == direction.left
						&& s2.step_direction == direction.left) {
					// zig-zig left
					overallRoot = rotateWithLeftChild(s1.node);
					overallRoot = rotateWithLeftChild(s2.node);

				} else if (s1.step_direction == direction.right
						&& s2.step_direction == direction.right) {
					// zig-zig right
					overallRoot = rotateWithRightChild(s1.node);
					overallRoot = rotateWithRightChild(s2.node);
				} else if (s1.step_direction == direction.left
						&& s2.step_direction == direction.right) {
					// zig-zag left right
					overallRoot = doubleWithLeftChild(s1.node);
				} else {
					// zig-zag right left
					overallRoot = doubleWithRightChild(s1.node);
				}
			} else {
				step s3 = path.pop();
				step s2 = path.pop();
				step s1 = path.peek();
				if (s2.step_direction == direction.left
						&& s3.step_direction == direction.left) {
					// zig-zig left
					if (s1.step_direction == direction.left) {
						s1.node.left = rotateWithLeftChild(s2.node);
						s1.node.left = rotateWithLeftChild(s3.node);
					} else {
						s1.node.right = rotateWithLeftChild(s2.node);
						s1.node.right = rotateWithLeftChild(s3.node);
					}
				} else if (s2.step_direction == direction.right
						&& s3.step_direction == direction.right) {
					// zig-zig right
					if (s1.step_direction == direction.left) {
						s1.node.left = rotateWithRightChild(s2.node);
						s1.node.left = rotateWithRightChild(s3.node);
					} else {
						s1.node.right = rotateWithRightChild(s2.node);
						s1.node.right = rotateWithRightChild(s3.node);
					}
				} else if (s2.step_direction == direction.left
						&& s3.step_direction == direction.right) {
					// zig-zag left right
					if (s1.step_direction == direction.left) {
						s1.node.left = doubleWithLeftChild(s2.node);
					} else {
						s1.node.right = doubleWithLeftChild(s2.node);
					}
				} else {
					// zig-zag right left
					if (s1.step_direction == direction.left) {
						s1.node.left = doubleWithRightChild(s2.node);
					} else {
						s1.node.right = doubleWithRightChild(s2.node);
					}
				}
			}
		}
	}
}
