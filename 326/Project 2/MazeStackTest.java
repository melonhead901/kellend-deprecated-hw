/**
 * @author Kellen Donohue
 * Apr 16, 2009
 * ArrayStackTest.java
 * CSE 326 AA
 * Project 2 - MazeStack.java
 */

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class MazeStackTest {

	MazeStack<Object> stack;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.stack = new MazeStack<Object>();
	}

	/**
	 * tests whether an empty stack is successfully created on initialization
	 */
	@Test
	public void testMazeStack() {
		assertTrue(stack.isEmpty());
	}

	/**
	 * Tests whether an empty stack is successfully created on initialization
	 * with an alternate default size
	 */
	@Test
	public void testMazeStackAlternateSize() {
		int alternateDefaultSize = 10;
		stack = new MazeStack<Object>(alternateDefaultSize);
		assertTrue(stack.isEmpty());
	}

	/**
	 * Test whether a stack is empty upon creation
	 */
	@Test
	public void testIsEmpty() {
		Assert.assertTrue(stack.isEmpty());
	}

	/**
	 * Tests whether a stack is non-empty after a single value is added
	 */
	@Test
	public void testIsNotEmpty() {
		int value = 6;
		stack.push(value);
		assertFalse(stack.isEmpty());
	}

	/**
	 * Tests whether a stack is empty after values is added and removed
	 */
	@Test
	public void testEmptyAfterAddAndRemove() {
		// Add 2 values
		int value = 6;
		stack.push(value);
		int otherVal = 5;
		stack.push(otherVal);

		// Remove the 2 values
		stack.pop();
		stack.pop();

		// Now do the test
		assertTrue(stack.isEmpty());
	}

	/**
	 * Tests whether an already empty stack is empty
	 */
	@Test
	public void testMakeEmptyEmpty() {
		this.stack.makeEmpty();
		assertTrue(stack.isEmpty());
	}

	/**
	 * Test whether makeEmpty succeeds after a value has been added
	 */
	@Test
	public void testMakeFullEmpty() {
		Object value = 6;
		stack.push(value);
		this.stack.makeEmpty();
		assertTrue(stack.isEmpty());
	}

	/**
	 * Tests whether the pop removes items from the list, and if in the correct
	 * order, and if it leaves the stack empty after all added values are
	 * removed
	 */
	@Test
	public void testPop() {
		Object value = 6;
		stack.push(value);
		Object otherVal = 5;
		stack.push(otherVal);
		Object thirdVal = 4;
		stack.push(thirdVal);
		stack.pop();
		assertEquals(stack.top(), otherVal);
		stack.pop();
		assertEquals(stack.top(), value);
		stack.pop();
		assertTrue(stack.isEmpty());
	}

	/**
	 * Tests pushing a single item on the list, and verifying that it is on the
	 * top
	 */
	@Test
	public void testPushSingleVal() {
		Object value = 6;
		stack.push(value);
		assertEquals(value, stack.top());
	}

	/**
	 * Verifies values in the stack are maintained in the correct order as
	 * multiple are pushed on the stack
	 */
	@Test
	public void testPushMultipleVal() {
		Object value = 6;
		stack.push(value);
		// No need to check here, the single push case is checked by push single
		// val test
		Object otherVal = 5;
		stack.push(otherVal);
		assertEquals(otherVal, stack.top());
		Object thirdVal = 2;
		stack.push(thirdVal);
		assertEquals(thirdVal, stack.top());
	}

	/**
	 * Verifies top is correct after one value is added
	 */
	@Test
	public void testTop() {
		Object value = 6;
		stack.push(value);
		assertEquals(stack.top(), value);
	}

	/**
	 * Verify that a stack is non empty if many values are added, but not all
	 * are removed
	 */
	@Test
	public void testPartialEmpty() {
		Object val1 = 3.0;
		Object val2 = 2.0;
		Object val3 = 1.0;

		// Add values to the stack
		stack.push(val1);
		stack.push(val2);
		stack.push(val3);

		// Remove some, but not all of the values
		stack.pop();
		stack.pop();

		// Make sure the stack does not think its empty
		assertFalse(stack.isEmpty());
	}

	/**
	 * Tests whether the proper exception is thrown when top is called on an
	 * empty stack
	 */
	@Test(expected = java.util.EmptyStackException.class)
	public void testEmptyTop() {
		stack.top();
	}

	/**
	 * Tests whether the proper exception is thrown when pop is called on an
	 * empty stack
	 */
	@Test(expected = java.util.EmptyStackException.class)
	public void testEmptyPop() {
		stack.pop();
	}

	/**
	 * Tests a variety of pushes, pops, and tops
	 */
	@Test
	public void inDepthTest() {
		Object val1 = "Kellen";
		Object val2 = "James";
		Object val3 = "Donohue";
		Object val4 = "kellend";

		stack.push(val1);
		stack.push(val2);
		assertEquals(val2, stack.top());
		stack.pop();
		stack.push(val3);
		assertEquals(val3, stack.top());
		stack.pop();
		assertEquals(val1, stack.top());
		stack.pop();
		stack.push(val4);
		assertEquals(val4, stack.top());
		stack.pop();
		assertTrue(stack.isEmpty());
	}

	/**
	 * Test the the stack can exceed its initial capacity and still maintain
	 * data
	 */
	@Test
	public void testStackResize() {
		// The size to make the new Stack
		int stackSize = 10;
		stack = new MazeStack<Object>(stackSize);

		// Push more elements into the stack than it holds by default
		for (int i = 1; i <= stackSize + 1; i++) {
			stack.push(i);
		}

		// Make sure all the elements got copied over properly
		for (int i = stackSize + 1; i > 0; i--) {
			assertEquals(stack.top(), i);
			stack.pop();
		}
	}
}
