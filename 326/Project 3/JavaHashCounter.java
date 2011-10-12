/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * June 1, 2009
 * CSE 326 A
 * Project 3 - JavaHashCounter.java
 */

import java.util.HashMap;

/**
 * DataCounter implementation based off of Java's hash map.
 * 
 * @author Zach
 * 
 * @param <E>
 *            The type of keys in the counter.
 */
public class JavaHashCounter<E extends Comparable<? super E>> extends
		JavaCounter<E> implements DataCounter<E> {

	public JavaHashCounter() {
		map = new HashMap<E, Integer>();
	}

}
