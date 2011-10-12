/**
 * Kellen Donohue
 * 04/06/2009
 * CSE 326 AA
 * Eric McCambridge
 * Project1 - ArrayStackTest.java
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

/**
 * @author Kellen
 *
 */
public class ArrayStackTest {

	ArrayStack list;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new ArrayStack();
	}

	/**
	 * Test method for {@link ArrayStack#ArrayStack()}.
	 */
	@Test
	public void testArrayStack() {
		list = new ArrayStack();
		assertTrue(list.isEmpty());
	}

	/**
	 * Test method for {@link ArrayStack#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		list = new ArrayStack();
		assertTrue(list.isEmpty());
		list.push(2);
		list.push(3);
		assertFalse(list.isEmpty());
		list.pop();
		list.pop();
		assertTrue(list.isEmpty());
	}

	/**
	 * Test method for {@link ArrayStack#peek()}.
	 */
	@Test
	public void testPeek() {
		list = new ArrayStack();

		// Check an empty list, test should fail
		try
		{
			list.peek();
			fail("Empty stack peeked at.");
		}
		catch (EmptyStackException e)
		{
		}

		int test = 2;
		list.push(test);
		assertEquals(list.peek(), test, 0.01);

		int testB = 3;
		list.push(testB);
		assertEquals(list.peek(), testB, 0.01);

		list.pop();
		assertEquals(list.peek(), test, 0.01);
	}

	/**
	 * Test method for {@link ArrayStack#pop()}.
	 */
	@Test
	public void testPop() {list = new ArrayStack();

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
	 * Test method for {@link ArrayStack#push(double)}.
	 */
	@Test
	public void testPush() {
		list = new ArrayStack();

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
