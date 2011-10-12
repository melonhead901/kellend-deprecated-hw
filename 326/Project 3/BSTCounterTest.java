/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 12, 2009
 * CSE 326 A
 * Project 3 - BSTCounterTest.java 
 */

import org.junit.Before;

/**
 * @author Zach
 */
public class BSTCounterTest extends DataCounterTest {

	@Before
	public void setUp() {
		this.dc = new BSTCounter<String>();
	}
}
