/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 12, 2009
 * CSE 326 A
 * Project 3 - CuckooHashTableTest.java 
 */

import org.junit.After;
import org.junit.Before;

/**
 * @author Kellen
 */
public class CuckooHashTableTest extends DataCounterTest {

	@Before
	public void setUp() {
		this.dc = new CuckooHashTable();
	}

	@After
	public void tearDown() {
		// ((CuckooHashTable)this.dc).printHashTable();
	}

}
