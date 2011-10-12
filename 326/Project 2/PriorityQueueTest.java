/**
 * @author Kellen Donohue
 * May 6, 2009
 * CSE 326 AA
 * Project 2 - PriorityQueueTest.java
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Kellen
 * Tests a priority queue implementaton
 */
public abstract class PriorityQueueTest {
	
	/**
	 * The priority queue used in every test
	 */
	protected PriorityQueue<Integer> pq;

	/**
	 * Each class must declare their own priority queue
	 * @throws java.lang.Exception
	 */
	@Before
	public abstract void setUp() throws Exception;

	// insert, delete, find tests
	
	/**
	 * insert 1 element, check that delete min returns it
	 */
	@Test
	public void testInsert1Delete(){
		pq.insert(10);
		assertEquals((int)pq.deleteMin(), 10);
	}
	
	/**
	 * insert 1 element, check that find min returns it
	 */
	@Test
	public void testInsert1Find(){
		pq.insert(10);
		assertEquals((int)pq.findMin(), 10);
	}
	
	/**
	 * insert 100 elements, check that delete min returns them in min to max order
	 */
	@Test
	public void testInsert1to100Delete(){
		for (int i = 1; i < 101; i++){
			pq.insert(i);
		}
		for (int i = 1; i < 101; i++){
			assertEquals((int)pq.deleteMin(), i);
		}
	}
	
	/**
	 * insert 100 elements, check that find min returns them in min to max order, deleting after each find
	 */
	@Test
	public void testInsert1to100Find(){
		for (int i = 1; i < 101; i++){
			pq.insert(i);
		}
		for (int i = 1; i < 101; i++){
			assertEquals((int)pq.findMin(), i);
			pq.deleteMin();
		}
	}
	
	/**
	 * insert 100 elements in reverse order, check that delete min returns them in min to max order
	 */
	@Test
	public void testInsert100to1Delete(){
		for (int i = 100; i > 0; i--){
			pq.insert(i);
		}
		for (int i = 1; i < 101; i++){
			assertEquals((int)pq.deleteMin(), i);
		}
	}
	
	/**
	 * insert 100 elements in reverse order, check that find min returns them in min to max order, deleting after each find
	 */
	@Test
	public void testInsert100to1Find(){
		for (int i = 100; i > 0; i--){
			pq.insert(i);
		}
		for (int i = 1; i < 101; i++) {
			assertEquals((int)pq.findMin(), i);
			pq.deleteMin();
		}
	}
	
	/**
	 * insert many unordered elements into the heap, check that delete min returns them in min to max order
	 */
	@Test
	public void testInsertManyDelete(){
		int[] insert = {13,9,3,8,5,6,14,1,12,10,2};
		int[] sorted = {1,2,3,5,6,8,9,10,12,13,14};
		    
		for (int i : insert){
			pq.insert(i);
		}
		
		for (int i : sorted){
			assertEquals((int)pq.deleteMin(), i);
		}
	}
	
	/**
	 * insert many unordered elements into the heap, check that find min returns them in min to max order, deleting after each find
	 */
	@Test
	public void testInsertManyFind(){
		int[] insert = {13,9,3,8,5,6,14,1,12,10,2};
		int[] sorted = {1,2,3,5,6,8,9,10,12,13,14};
		    
		for (int i : insert){
			pq.insert(i);
		}
		
		for (int i : sorted){
			assertEquals((int)pq.findMin(), i);
			pq.deleteMin();
		}
	}

	// is empty, make empty tests
	
	/**
	 * check that a newly created heap is empty
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(pq.isEmpty());
	}

	/**
	 * check that the heap is not empty after elements are inserted into it
	 */
	@Test
	public void testInsertIsEmpty() {
		for (int i = 0; i < 100; i++) {
			pq.insert(i);
		}
		assertFalse(pq.isEmpty());
	}
	
	/**
	 * test that the heap is empty after calling makeEmpty
	 */
	@Test
	public void testEmptyIsEmpty(){
		pq.makeEmpty();
		assertTrue(pq.isEmpty());
	}
	
	/**
	 * test that the heap is empty after inserting elements and then calling makeEmpty
	 */
	@Test
	public void testInsertEmptyIsEmpty(){
		for (int i = 0; i < 100; i++) {
			pq.insert(i);
		}
		pq.makeEmpty();
		assertTrue(pq.isEmpty());
	}
	
	// exception tests
	
	/**
	 * test that delete throws an exception when called on a newly created heap
	 */
	@Test
	public void testDeleteException(){
		try {
			pq.deleteMin();
			fail();
		} catch(NoSuchElementException ne) {}
	}
	
	/**
	 * test that inserting elements, deleting them all, and then trying to delete another element throws an exception
	 */
	@Test
	public void testInsertDeleteException(){
		for (int i = 0; i < 100; i++){
			pq.insert(i);
		}
		for (int i = 0 ; i < 100; i++){
			pq.deleteMin();
		}
		try{
			pq.deleteMin();
			fail();
		} catch(NoSuchElementException ne) {}
	}
	
	/**
	 * test that inserting elements, deleting them all, inserting more, deleting them all again, and then trying to delete another throws an exception
	 */
	@Test
	public void testInsertDeleteTwiceException(){
		for (int i = 0 ; i < 20; i++){
			pq.insert(i);
		}
		for (int i = 0 ; i < 20; i++){
			pq.deleteMin();
		}
		for (int i = 0; i < 10; i++){
			pq.insert(i);
		}
		for (int i = 0; i < 10; i++){
			pq.deleteMin();
		}
		try{
			pq.deleteMin();
			fail();
		} catch(NoSuchElementException ne) {}
	}
	
	/**
	 * test that find throws an exception when called on a newly created heap
	 */
	@Test
	public void testFindException(){
		try {
			pq.findMin();
			fail();
		} catch(NoSuchElementException ne) {}
	}
	
	/**
	 * test that inserting, deleting, finding throws an exception
	 */
	@Test
	public void testInsertFindException(){
		for (int i = 0; i < 100; i++){
			pq.insert(i);
		}
		for (int i = 0 ; i < 100; i++){
			pq.deleteMin();
		}
		try{
			pq.findMin();
			fail();
		} catch(NoSuchElementException ne) {}
	}
	
	/**
	 * test that inserting, deleting, inserting, deleting, finding throws an exception (deletes between finds)
	 */
	@Test
	public void testInsertDeleteTwiceFindException(){
		for (int i = 0 ; i < 20; i++){
			pq.insert(i);
		}
		for (int i = 0 ; i < 20; i++){
			pq.deleteMin();
		}
		for (int i = 0; i < 10; i++){
			pq.insert(i);
		}
		for (int i = 0; i < 10; i++){
			pq.deleteMin();
		}
		try{
			pq.findMin();
			fail();
		} catch(NoSuchElementException ne) {}
	}
}
