/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * June 1, 2009
 * CSE 326 A
 * Project 3 - JavaTreeCounterTest.java
 */

import org.junit.Before;

/**
 * @author Zach
 */
public class JavaTreeCounterTest extends DataCounterTest {

	@Before
	public void setUp() {
		this.dc = new JavaTreeCounter<String>();
	}
}
