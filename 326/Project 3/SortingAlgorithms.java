/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 16, 2009
 * CSE 326 A
 * Project 3 - SortingAlgorithms.java
 */

import java.lang.Comparable;
import java.util.Comparator;

/**
 * A collection of several static sorting methods.
 * 
 * @author Kellen
 * @author Zach
 * 
 */
public class SortingAlgorithms {

	/**
	 * The cutoff value at which to use insertion sort instead of quick sort
	 */
	private static final int CUTOFF = 10;

	/**
	 * Driver method for ascending order insertion sort
	 * 
	 * @param <E>
	 *            Type of data to sort
	 * @param data
	 *            Array containing data to sort
	 * @param comp
	 *            Comparator to use
	 */
	public static <E extends Comparable<E>> void insertionSort(
			Comparable<E>[] data) {
		insertionSort(data, 0, data.length - 1);
	}

	/**
	 * Helper method for insertion sort, does the actual sorting
	 * 
	 * @param <E>
	 *            Type of data to sort
	 * @param data
	 *            Array containing data to sort
	 * @param left
	 *            left boundary inclusive to sort within
	 * @param right
	 *            Right boundary inclusive to sort within
	 * @param comp
	 *            Comparator to use
	 */
	@SuppressWarnings("unchecked")
	private static <E extends Comparable<? super E>> void insertionSort(
			Comparable<E>[] data, int left, int right) {

		int j;
		for (int p = left + 1; p < right + 1; p++) {
			Comparable<E> tmp = (Comparable<E>) data[p];
			for (j = p; j > left && tmp.compareTo((E) data[j - 1]) < 0; j--) {
				data[j] = data[j - 1];
			}
			data[j] = tmp;
		}
	}

	/**
	 * A secret sort method, ooooo what could it be
	 * 
	 * @param data
	 *            The array to sort
	 */
	@SuppressWarnings("unchecked")
	public static <E extends Comparable<? super E>> void secret(
			Comparable<E>[] data) {

		boolean swapPerformed = true;
		while (swapPerformed) {
			swapPerformed = false;
			for (int i = 0; i < data.length - 1; i++) {
				if (data[i + 1].compareTo((E) data[i]) < 0) {
					swap(data, i, i + 1);
					swapPerformed = true;
				}
			}
		}
	}

	/**
	 * Quick sort!
	 * 
	 * @author Professor GoodProf
	 * @param data
	 *            The array to sort
	 */
	public static <E extends Comparable<? super E>> void quickSort(
			Comparable<E>[] data) {
		quickSortHelper(data, 0, data.length - 1);
	}

	/**
	 * Recursive helper for quick sort
	 * 
	 * @param data
	 *            The array to sort
	 * @param left
	 *            The left index to start the sort at
	 * @param right
	 *            The right index to start the sort at
	 */
	private static <E extends Comparable<? super E>> void quickSortHelper(
			Comparable<E>[] data, int left, int right) {
		if (left + CUTOFF <= right) {
			E pivot = median3(data, left, right);
			int partition = partition(data, left, right, pivot);
			quickSortHelper(data, left, partition - 1);
			quickSortHelper(data, partition + 1, right);
		} else {
			insertionSort(data, left, right);
		}
	}

	/**
	 * Partition an array for quick sort
	 * 
	 * @param data
	 *            The array to sort
	 * @param left
	 *            The left boundary to sort from
	 * @param right
	 *            The right boundary to sort to
	 * @param pivot
	 *            The pivot value (not the index of the pivot)
	 * @return The index of the pivot after partitioning has completed
	 */
	private static <E extends Comparable<? super E>> int partition(
			Comparable<E>[] data, int left, int right, E pivot) {
		int i = left;
		int j = right - 1;

		while (true) {
			while (data[++i].compareTo(pivot) < 0) {
			}
			while (data[--j].compareTo(pivot) > 0) {
			}
			if (i < j)
				swap(data, i, j);
			else
				break;
		}

		swap(data, i, right - 1);
		return i;
	}

	/**
	 * Sorts left, right, and the (left+right)/2 based on their values in data
	 * 
	 * @param data
	 *            The array to sort
	 * @param left
	 *            The left boundary to look at
	 * @param right
	 *            The right boundary to look at
	 * @return The value of the median
	 */
	@SuppressWarnings("unchecked")
	private static <E extends Comparable<? super E>> E median3(
			Comparable<E>[] data, int left, int right) {

		// Put the left, middle and right values in their correct relative
		// positions
		int center = (left + right) / 2;
		if (data[left].compareTo((E) data[center]) > 0) {
			swap(data, left, center);
		}
		if (data[left].compareTo((E) data[right]) > 0) {
			swap(data, left, right);
		}
		if (data[center].compareTo((E) data[right]) > 0) {
			swap(data, center, right);
		}

		// Median is in the middle, put it at the right
		swap(data, center, right - 1);
		return (E) data[right - 1];
	}

	/**
	 * Reverse an array
	 * 
	 * @param data
	 *            The array to reverse
	 */
	public static <E extends Comparable<? super E>> void reverse(
			Comparable<E>[] data) {
		Comparable<E> temp;
		for (int i = 0; i < data.length / 2; i++) {
			temp = data[i];
			data[i] = data[data.length - i - 1];
			data[data.length - i - 1] = temp;
		}
	}

	/**
	 * Swaps two elements in an array
	 * 
	 * @param data
	 *            The array with elements to swap
	 * @param i
	 *            The first index
	 * @param j
	 *            The second index
	 */
	private static <E extends Comparable<? super E>> void swap(
			Comparable<E>[] data, int i, int j) {
		Comparable<E> temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	/**
	 * merge sort
	 * 
	 * @param <E>
	 *            The type of data in the array.
	 * @param data
	 *            The array to sort.
	 */
	public static <E extends Comparable<? super E>> void mergeSort(
			Comparable<E>[] data) {
		// the auxillary array
		Comparable<E>[] aux = data.clone();

		// number of elements to merge, starting with 2
		int mergeBy = 2;

		// true if you data sorted so far is in the auxiliary array
		boolean in_aux = false;

		// merge halves until the left half contains the whole array
		while (mergeBy / 2 < data.length) {
			if (in_aux) {
				// merge from aux to data
				for (int i = 0; i < data.length; i += mergeBy) {
					merge(aux, i, i + mergeBy / 2, mergeBy / 2, data);
				}
			} else {
				// merge from data to aux
				for (int i = 0; i < data.length; i += mergeBy) {
					merge(data, i, i + mergeBy / 2, mergeBy / 2, aux);
				}
			}
			// switch used array, double merged size
			in_aux = !in_aux;
			mergeBy *= 2;
		}
		if (in_aux) {
			// copy back from aux to data if necessary
			for (int i = 0; i < data.length; i++) {
				data[i] = aux[i];
			}
		}
	}

	/**
	 * Merge method for merge sort.
	 * 
	 * @param <E>
	 *            The type of data in the array.
	 * @param data
	 *            The array of data.
	 * @param first
	 *            The index of the first sub-array.
	 * @param second
	 *            The index of the second sub-array.
	 * @param len
	 *            The length of each sub-array.
	 * @param target
	 *            The array to merge in to.
	 */
	@SuppressWarnings("unchecked")
	private static <E extends Comparable<? super E>> void merge(
			Comparable<E>[] data, int first, int second, int len,
			Comparable<E>[] target) {
		// indices from first
		int i = 0;
		// indices from second
		int j = 0;
		// index in target to copy to
		int t = first;

		// main copy smaller element into target loop
		// stop if the right pointer runs off the array
		while (i < len && j < len && second + j < data.length) {
			int cmp = data[first + i].compareTo((E) data[second + j]);
			if (cmp > 0) {
				target[t] = data[second + j];
				j++;
			} else {
				target[t] = data[first + i];
				i++;
			}
			t++;
		}

		// finish copying from the array that hasn't finished, making sure you
		// don't run off the end of the array
		if (i < len) {
			// finish copying first array
			for (int k = i; k < len && first + k < data.length; k++) {
				target[t] = data[first + k];
				t++;
			}
		} else {
			// finish copying second array
			for (int k = j; k < len && second + k < data.length; k++) {
				target[t] = data[second + k];
				t++;
			}
		}
	}

	/* Comparator Methods */
	/**
	 * Quick sort!
	 * 
	 * @author Professor GoodProf
	 * @param E
	 *            Type of data to sort
	 * @param comparator
	 *            Comparator to use
	 * @param data
	 *            The array to sort
	 */
	public static <E extends Comparable<? super E>> void quickSort(
			Comparable<E>[] data, Comparator<Comparable<E>> comparator) {
		quickSortHelper(data, 0, data.length - 1, comparator);
	}

	/**
	 * Recursive helper for quick sort
	 * 
	 * @param data
	 *            The array to sort
	 * @param left
	 *            The left index to start the sort at
	 * @param right
	 *            The right index to start the sort at
	 */
	private static <E extends Comparable<? super E>> void quickSortHelper(
			Comparable<E>[] data, int left, int right,
			Comparator<Comparable<E>> comparator) {

		// Pick a pivot, partition around that pivot, then recursively sort the
		// left and right sides
		if (left + CUTOFF <= right) {
			E pivot = median3(data, left, right, comparator);
			int partition = partition(data, left, right, pivot, comparator);
			quickSortHelper(data, left, partition - 1, comparator);
			quickSortHelper(data, partition + 1, right, comparator);
		} else {
			insertionSort(data, left, right, comparator);
		}
	}

	/**
	 * Sorts left, right, and the (left+right)/2 based on their values in data
	 * 
	 * @param data
	 *            The array to sort
	 * @param left
	 *            The left boundary to look at
	 * @param right
	 *            The right boundary to look at
	 * @param
	 * @return The value of the median
	 */
	@SuppressWarnings("unchecked")
	private static <E extends Comparable<? super E>> E median3(
			Comparable<E>[] data, int left, int right,
			Comparator<Comparable<E>> comp) {

		// Put the left, middle and right values in their correct relative
		// positions
		int center = (left + right) / 2;
		if (comp.compare(data[left], data[center]) > 0) {
			swap(data, left, center);
		}
		if (comp.compare(data[left], data[right]) > 0) {
			swap(data, left, right);
		}
		if (comp.compare(data[center], data[right]) > 0) {
			swap(data, center, right);
		}

		// Median is in the middle, put it at the right
		swap(data, center, right - 1);
		return (E) data[right - 1];
	}

	/**
	 * Partition an array for quick sort
	 * 
	 * @param data
	 *            The array to sort
	 * @param left
	 *            The left boundary to sort from
	 * @param right
	 *            The right boundary to sort to
	 * @param pivot
	 *            The pivot value (not the index of the pivot)
	 * @return The index of the pivot after partitioning has completed
	 */
	@SuppressWarnings("unchecked")
	private static <E extends Comparable<? super E>> int partition(
			Comparable<E>[] data, int left, int right, E pivot,
			Comparator<Comparable<E>> comp) {

		// Partition the array starting from the outside and moving in
		int i = left;
		int j = right - 1;

		// Move all data <= the pivot to the left, and all data >= the pivot to
		// the right
		while (true) {
			while (comp.compare(data[++i], (Comparable) pivot) < 0) {
			}
			while (comp.compare(data[--j], (Comparable) pivot) > 0) {
			}
			if (i < j)
				swap(data, i, j);
			else
				break;
		}

		// Position the pivot
		swap(data, i, right - 1);
		return i;
	}

	/**
	 * Driver method for ascending order insertion sort
	 * 
	 * @param <E>
	 *            Type of data to sort
	 * @param data
	 *            Array containing data to sort
	 * @param comp
	 *            Comparator to use
	 */
	public static <E extends Comparable<? super E>> void insertionSort(
			Comparable<E>[] data, Comparator<Comparable<E>> comp) {
		insertionSort(data, 0, data.length - 1, comp);
	}

	/**
	 * Helper method for insertion sort, does the actual sorting
	 * 
	 * @param <E>
	 *            Type of data to sort
	 * @param data
	 *            Array containing data to sort
	 * @param left
	 *            left boundary inclusive to sort within
	 * @param right
	 *            Right boundary inclusive to sort within
	 * @param comp
	 *            Comparator to use
	 */
	private static <E extends Comparable<? super E>> void insertionSort(
			Comparable<E>[] data, int left, int right,
			Comparator<Comparable<E>> comp) {
		int j;
		for (int p = left + 1; p < right + 1; p++) {
			Comparable<E> tmp = (Comparable<E>) data[p];
			for (j = p; j > left && comp.compare(tmp, data[j - 1]) < 0; j--) {
				data[j] = data[j - 1];
			}
			data[j] = tmp;
		}
	}
}
