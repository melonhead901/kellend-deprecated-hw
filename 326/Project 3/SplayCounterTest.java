/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 12, 2009
 * CSE 326 A
 * Project 3 - SplayCounterTest.java
 */

import org.junit.Before;

/**
 * @author Zach
 */
public class SplayCounterTest extends DataCounterTest {

	@Before
	public void setUp() {
		this.dc = new SplayCounter<String>();
	}
}
