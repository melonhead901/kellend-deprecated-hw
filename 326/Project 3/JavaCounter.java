/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * June 1, 2009
 * CSE 326 A
 * Project 3 - JavaCounter.java
 */

import java.util.Map;

/**
 * Abstract DataCounter implementation based off of Java's maps.
 * 
 * @author Zach
 * 
 * @param <E>
 *            The type of keys in the counter.
 */
public abstract class JavaCounter<E extends Comparable<? super E>> implements
		DataCounter<E> {

	protected Map<E, Integer> map;

	@SuppressWarnings("unchecked")
	@Override
	public DataCount<E>[] getCounts() {
		DataCount<E>[] counts = new DataCount[map.size()];
		int i = 0;
		for (E key : map.keySet()) {
			counts[i++] = new DataCount<E>(key, map.get(key));
		}
		return counts;
	}

	@Override
	public int getSize() {
		return map.size();
	}

	@Override
	public void incCount(E data) {
		Integer inTree = map.get(data);

		if (inTree == null) {
			map.put(data, 1);
		} else {
			map.put(data, inTree + 1);
		}
	}

}
