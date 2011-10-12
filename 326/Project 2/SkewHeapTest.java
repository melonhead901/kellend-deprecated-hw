/**
 * @author Kellen Donohue
 * Apr 29, 2009
 * CSE 326 AA
 * Project 2 - MazePriorityQueueTest.java
 */

import org.junit.Before;

/**
 * A class to test the basic functionality of skew heaps
 * @author Kellen
 *
 */
public class SkewHeapTest extends PriorityQueueTest{

	/**
	 * Set up the test suite with a skew hwap
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.pq = new SkewHeap<Integer>();		
	}
}
