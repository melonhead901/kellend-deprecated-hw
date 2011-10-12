/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 26, 2009
 * CSE 326 A
 * Project 3 - DataCountDataSorter.java
 */

import java.util.Comparator;

/**
 * Compares DataCount strings based on their data, rather than their count
 * 
 * @author kellend
 * @param <E>
 *            Type of data to Compare
 * 
 */
public class DataCountDataSorter<E> implements Comparator<Comparable<E>> {

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Comparable<E> o1, Comparable<E> o2) {
		if (!(o1 instanceof DataCount) || !(o2 instanceof DataCount)) {
			throw new ClassCastException(
					"Can only use DataSorter<E> on types of DataCounts");
		} else {
			DataCount<Comparable<E>> dcObj1 = (DataCount<Comparable<E>>) o1;
			DataCount<Comparable<E>> dcObj2 = (DataCount<Comparable<E>>) o2;
			return dcObj1.data.compareTo((E) dcObj2.data);
		}
	}
}
