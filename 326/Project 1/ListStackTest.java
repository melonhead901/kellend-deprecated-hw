/**
 * Kellen Donohue
 * 04/06/2009
 * CSE 326 AA
 * Eric McCambridge
 * Project1 - ListStackTest.java
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

/**
 * @author Kellen
 *
 */
public class ListStackTest {

	ListStack list;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new ListStack();
	}

	/**
	 * Test method for {@link ListStack#ListStack()}.
	 */
	@Test
	public void testListStack() {
		list = new ListStack();
		assertTrue(list.isEmpty());
	}

	/**
	 * Test method for {@link ListStack#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		list = new ListStack();
		assertTrue(list.isEmpty());
		list.push(2);
		list.push(3);
		assertFalse(list.isEmpty());
		list.pop();
		list.pop();
		assertTrue(list.isEmpty());
	}

	/**
	 * Test method for {@link ListStack#peek()}.
	 */
	@Test
	public void testPeek() {
		list = new ListStack();

		// Check an empty list, test should fail
		try
		{
			list.peek();
			fail("Empty stack peeked at.");
		}
		catch (EmptyStackException e)
		{
		}

		double test = 2;
		list.push(test);
		assertEquals(list.peek(), test, 0.01);

		double testB = 3;
		list.push(testB);
		assertEquals(list.peek(), testB, 0.01);

		list.pop();
		assertEquals(list.peek(), test, 0.01);
	}

	/**
	 * Test method for {@link ListStack#pop()}.
	 */
	@Test
	public void testPop() {list = new ListStack();

	// Check an empty list, test should fail
	try
	{
		list.pop();
		fail("Empty stack popped at.");
	}
	catch (EmptyStackException e)
	{
	}

	double test = 2;
	list.push(test);

	double testB = 3;
	list.push(testB);

	assertEquals(list.pop(), testB, 0.01);
	assertEquals(list.pop(), test, 0.01);

	}

	/**
	 * Test method for {@link ListStack#push(double)}.
	 */
	@Test
	public void testPush() {
		list = new ListStack();

		double test = 2;
		list.push(test);

		assertEquals(test, list.peek(), 0.01);

		double testB = 3;
		list.push(testB);

		assertEquals(testB, list.peek(), 0.01);

		double testC = 7;
		list.push(testC);

		assertEquals(testC, list.peek(), 0.01);
	}
}
