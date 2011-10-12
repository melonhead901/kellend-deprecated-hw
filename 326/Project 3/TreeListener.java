/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * 05/17/2009
 * CSE 326 A
 * Project 3 - TreeListener.java
 */

/**
 * Interface used to listen to tree change events.
 * 
 * @author Zach
 */
public interface TreeListener<E extends Comparable<? super E>> {
	/**
	 * Called when a node in the tree has changed.
	 * 
	 * @param node
	 *            The highest node that changed.
	 */
	public void nodeChangeEvent(VisualBSTCounter.VisualBSTNode<E> node);
}
