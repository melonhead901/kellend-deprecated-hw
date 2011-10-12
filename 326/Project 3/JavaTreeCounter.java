/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * June 1, 2009
 * CSE 326 A
 * Project 3 - JavaTreeCounter.java
 */

import java.util.TreeMap;

/**
 * DataCounter implementation based off of Java's tree map.
 * 
 * @author Zach
 * 
 * @param <E>
 *            The type of keys in the counter.
 */
public class JavaTreeCounter<E extends Comparable<? super E>> extends
		JavaCounter<E> implements DataCounter<E> {

	public JavaTreeCounter(){
		map = new TreeMap<E, Integer>();
	}
}
