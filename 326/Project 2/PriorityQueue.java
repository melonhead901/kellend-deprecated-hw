/**
 * Base interface for priority queue implementations. CSE326 project 2.
 * 
 * @version Sp08
 */
public interface PriorityQueue<E extends Comparable<? super E>> {
  /**
   * Report whether this queue is empty
   * 
   * @return true if the priority queue has no elements
   */
  public boolean isEmpty();

  /**
   * Locate the mimimun element in this priority queue
   * 
   * @return reference to the minimum element in the priority queue.
   */
  public E findMin();

  /**
   * Insert a new object to the priority queue
   * 
   * @param x Object to be inserted into the priority queue.
   */
  public void insert(E x);

  /**
   * Remove the minimum element from the priority queue.
   * 
   * @return reference to the minimum element that was removed.
   */
  public E deleteMin();

  /**
   * Delete all elements from the priority queue.
   */
  public void makeEmpty();

}
