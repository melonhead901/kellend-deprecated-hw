/**
 * @author steinz
 * 4/29/09
 * CSE326 Project 2
 * JUnit tests for BinaryHeap class
 */


import org.junit.Before;

/**
 * Tests the basic functionality of a binary heap
 * @author Kellen
 *
 */
public class BinaryHeapTest extends PriorityQueueTest{
	
	/**
	 * initialize the priority queue with a binary heap before each test
	 */
	@Before
	public void setUp() {
		this.pq = new BinaryHeap<Integer>();
	}
}