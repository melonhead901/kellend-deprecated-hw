/**
 * Base interface for queue implementations CSE326 project 2.
 * 
 * @version Sp08
 */
public interface Queue<E> {
  /**
   * Report whether this queue is empty
   * 
   * @return true if the queue has no elements
   */
  public boolean isEmpty();

  /**
   * Enqueue a new object to the queue
   * 
   * @param x Object to be enqueued.
   */
  public void enqueue(E x);

  /**
   * Get the least recently inserted item in the queue. Does not alter the
   * queue.
   * 
   * @return The front of the queue.
   * @exception NoSuchElementException thrown if queue is empty.
   */
  public E peek();

  /**
   * Remove and return the element in the front of the queue.
   * 
   * @return The front of the queue.
   * @exception NoSuchElementException thrown if queue is empty.
   */
  public E dequeue();

  /**
   * Delete all elements from the queue.
   */
  public void makeEmpty();

}
