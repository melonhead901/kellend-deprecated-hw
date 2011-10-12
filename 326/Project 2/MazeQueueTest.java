/**
 * @author steinz
 * 4/17/09
 * CSE326 Project 2
 * JUnit tests for LinkedListQueue class
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class MazeQueueTest {
	MazeQueue<Integer> q;

	/**
	 * initialize the queue before each test
	 */
	@Before
	public void setUp() {
		q = new MazeQueue<Integer>();
	}

	// enqueue and dequeue tests
	
	/**
	 * enqueue 100 elements, check that they dequeue in order
	 */
	@Test
	public void testEnqueDeque() {
		for (int i = 0; i < 100; i++)
			q.enqueue(i);
		for (int i = 0; i < 100; i++)
			assertEquals(i, q.dequeue());
	}

	/**
	 * enqueue 20 elements, check that they dequeue in order,
	 * enqueue 10 elements, check that they dequeue in order
	 */
	@Test
	public void testEnqueDequeTwice() {
		for (int i = 0; i < 20; i++)
			q.enqueue(i);
		for (int i = 0; i < 20; i++)
			assertEquals(i, q.dequeue());
		for (int i = 0; i < 10; i++)
			q.enqueue(i);
		for (int i = 0; i < 10; i++)
			assertEquals(i, q.dequeue());
	}

	/**
	 * enqueue 10 elements, check that 5 dequeue in order,
	 * enqueue 10 elements, check that 15 dequeue in order
	 */
	@Test
	public void testEnqueDequeSplit() {
		for (int i = 0; i < 10; i++)
			q.enqueue(i);
		for (int i = 0; i < 5; i++)
			assertEquals(i, q.dequeue());
		for (int i = 10; i < 20; i++)
			q.enqueue(i);
		for (int i = 5; i < 20; i++)
			assertEquals(i, q.dequeue());
	}

	// peek tests
	
	/**
	 * enqueue 100 elements, check that peek returns those elements in order, dequeuing between peeks
	 */
	@Test
	public void testPeek() {
		for (int i = 0; i < 100; i++)
			q.enqueue(i);
		for (int i = 0; i < 100; i++) {
			assertEquals(i, q.peek());
			q.dequeue();
		}
	}

	/**
	 * enqueue 20 elements, check that peek returns those elements in order, dequeuing between peeks
	 * enqueue 10 elements, check that peek returns those elements in order, dequeuing between peeks
	 */
	@Test
	public void testPeekTwice() {
		for (int i = 0; i < 20; i++)
			q.enqueue(i);
		for (int i = 0; i < 20; i++) {
			assertEquals(i, q.peek());
			q.dequeue();
		}
		for (int i = 0; i < 10; i++)
			q.enqueue(i);
		for (int i = 0; i < 10; i++) {
			assertEquals(i, q.peek());
			q.dequeue();
		}
	}

	// isEmpty tests
	
	/**
	 * check that the queue is empty
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(q.isEmpty());
	}

	/**
	 * enqueue 100 elements, check that the queue is not empty
	 */
	@Test
	public void testIsEmptyEnqued() {
		for (int i = 0; i < 100; i++)
			q.enqueue(i);
		assertFalse(q.isEmpty());
	}

	/**
	 * enqueue 100 elements, dequeue those 100 elements, check that the queue is empty
	 */
	@Test
	public void testIsEmptyEnquedDequed() {
		for (int i = 0; i < 100; i++)
			q.enqueue(i);
		for (int i = 0; i < 100; i++)
			q.dequeue();
		assertTrue(q.isEmpty());

	}
	
	/**
	 * enqueue 100 elements, dequeue 50 elements, check that the queue is not empty
	 */
	@Test
	public void testIsEmptyEnquedPartialDequed(){
		for (int i = 0; i < 100; i++)
			q.enqueue(i);
		for (int i = 0; i < 50; i++)
			q.dequeue();
		assertFalse(q.isEmpty());
	}

	/**
	 * enqueue 20 elements, dequeue 20 elements, enqueue 10 elements, dequeue 10 elements, check that the queue is empty
	 */
	@Test
	public void testIsEmptyEnquedDequedTwice() {
		for (int i = 0; i < 20; i++)
			q.enqueue(i);
		for (int i = 0; i < 20; i++)
			q.dequeue();
		for (int i = 0; i < 10; i++)
			q.enqueue(i);
		for (int i = 0; i < 10; i++)
			q.dequeue();
		assertTrue(q.isEmpty());
	}

	// Exception tests
	
	/**
	 * dequeue an element - fail if no exception thrown
	 */
	@Test
	public void testDequeException() {
		try {
			q.dequeue();
			fail();
		} catch (NoSuchElementException e) {
		}
	}

	/**
	 * enqueue 100 elements, dequeue 100 elements, dequeue an element - fail if no exception thrown
	 */
	@Test
	public void testDequeExceptionEnquedDequed() {
		for (int i = 0; i < 100; i++)
			q.enqueue(i);
		for (int i = 0; i < 100; i++)
			q.dequeue();
		try {
			q.dequeue();
			fail();
		} catch (NoSuchElementException e) {
		}
	}

	/**
	 * enqueue 20 elements, dequeue 20 elements, enqueue 10 elements, dequeue 10 elements, dequeue an element - fail if no exception thrown
	 */
	@Test
	public void testDequeExceptionEnquedDequedTwice() {
		for (int i = 0; i < 20; i++)
			q.enqueue(i);
		for (int i = 0; i < 20; i++)
			q.dequeue();
		for (int i = 0; i < 10; i++)
			q.enqueue(i);
		for (int i = 0; i < 10; i++)
			q.dequeue();
		try {
			q.dequeue();
			fail();
		} catch (NoSuchElementException e) {
		}
	}

	/**
	 * peek - fail if no exception thrown
	 */
	@Test
	public void testPeekException() {
		try {
			q.peek();
			fail();
		} catch (NoSuchElementException e) {
		}
	}

	/**
	 * enqueue 100 elements, dequeue 100 elements, peek - fail if no exception thrown
	 */
	@Test
	public void testPeekExceptionEnquedDequed() {
		for (int i = 0; i < 100; i++)
			q.enqueue(i);
		for (int i = 0; i < 100; i++)
			q.dequeue();
		try {
			q.peek();
			fail();
		} catch (NoSuchElementException e) {
		}
	}

	/**
	 * enqueue 20 elements, dequeue 20 elements, enqueue 10 elements, dequeue 10 elements, peek - fail if no exception thrown
	 */
	@Test
	public void testPeekExceptionEnquedDequedTwice() {
		for (int i = 0; i < 20; i++)
			q.enqueue(i);
		for (int i = 0; i < 20; i++)
			q.dequeue();
		for (int i = 0; i < 10; i++)
			q.enqueue(i);
		for (int i = 0; i < 10; i++)
			q.dequeue();
		try {
			q.peek();
			fail();
		} catch (NoSuchElementException e) {
		}
	}

	// makeEmpty tests
	
	/**
	 * make empty, check that the queue is empty
	 */
	@Test
	public void testMakeEmpty() {
		q.makeEmpty();
		assertTrue(q.isEmpty());
	}

	/**
	 * enqueue 100 elements, make empty, check that the queue is empty
	 */
	@Test
	public void testMakeEmptyEnque() {
		for (int i = 0; i < 100; i++)
			q.enqueue(i);
		q.makeEmpty();
		assertTrue(q.isEmpty());
	}

	/**
	 * enqueue 100 elements, dequeue 100 elements, make empty, check that the queue is empty
	 */
	@Test
	public void testMakeEmptyEnqueDeque() {
		for (int i = 0; i < 100; i++)
			q.enqueue(i);
		for (int i = 0; i < 100; i++)
			q.dequeue();
		q.makeEmpty();
		assertTrue(q.isEmpty());
	}
}
