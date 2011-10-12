/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * 05/17/2009
 * CSE 326 A
 * Project 3 - SortingAlgorithmsTest.java
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the sorting algorithms we created
 * 
 * @author Kellen
 * @author Zach
 * 
 */
public class SortingAlgorithmsTest {

	/**
	 * The array that will be created, sorted, and tested
	 */
	Integer[] arr;
	
	/**
	 * The size of the array to test
	 */
	public static final int SIZE = 50;

	/**
	 * Initialize the array
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.arr = new Integer[SIZE];

	}

	/**
	 * Ensure that it's been sorted
	 */
	@After
	public void tearDown() {
		for (int i = 0; i < SIZE - 1; i++) {
			assertTrue(arr[i] <= arr[i + 1]);
		}
	}

	/* Random tests */
	
	/**
	 * Tests insertion sort of a Random array
	 */
	@Test
	public void testInsertionRandom() {
		generateRandomArray();
		SortingAlgorithms.insertionSort(arr);
	}

	/**
	 * Tests secret sort of a Random array
	 */
	@Test
	public void testSecretRandom() {
		generateRandomArray();
		SortingAlgorithms.secret(arr);
	}

	/**
	 * Tests quick sort of a Random array
	 */
	@Test
	public void testQuickSortRandom() {
		generateRandomArray();
		SortingAlgorithms.quickSort(arr);
	}

	/**
	 * Tests merge sort of a Random array
	 */
	@Test
	public void testMergeSortRandom() {
		generateRandomArray();
		SortingAlgorithms.mergeSort(arr);
	}
	
	/* Ascending tests */
	
	/**
	 * Tests insertion sort of a Ascending array
	 */
	@Test
	public void testInsertionAscending() {
		generateAscendingArray();
		SortingAlgorithms.insertionSort(arr);
	}

	/**
	 * Tests secret sort of a Ascending array
	 */
	@Test
	public void testSecretAscending() {
		generateAscendingArray();
		SortingAlgorithms.secret(arr);
	}

	/**
	 * Tests quick sort of a Ascending array
	 */
	@Test
	public void testQuickSortAscending() {
		generateAscendingArray();
		SortingAlgorithms.quickSort(arr);
	}

	/**
	 * Tests merge sort of a Ascending array
	 */
	@Test
	public void testMergeSortAscending() {
		generateAscendingArray();
		SortingAlgorithms.mergeSort(arr);
	}
	
	/* Descending tests */
	
	/**
	 * Tests insertion sort of a Descending array
	 */
	@Test
	public void testInsertionDescending() {
		generateDescendingArray();
		SortingAlgorithms.insertionSort(arr);
	}

	/**
	 * Tests secret sort of a Descending array
	 */
	@Test
	public void testSecretDescending() {
		generateDescendingArray();
		SortingAlgorithms.secret(arr);
	}

	/**
	 * Tests quick sort of a Descending array
	 */
	@Test
	public void testQuickSortDescending() {
		generateDescendingArray();
		SortingAlgorithms.quickSort(arr);
	}

	/**
	 * Tests merge sort of a Descending array
	 */
	@Test
	public void testMergeSortDescending() {
		generateDescendingArray();
		SortingAlgorithms.mergeSort(arr);
	}

	/* Duplicates tests */
	
	/**
	 * Tests insertion sort of a Duplicates array
	 */
	@Test
	public void testInsertionDuplicates() {
		generateDuplicatesArray();
		SortingAlgorithms.insertionSort(arr);
	}

	/**
	 * Tests secret sort of a Duplicates array
	 */
	@Test
	public void testSecretDuplicates() {
		generateDuplicatesArray();
		SortingAlgorithms.secret(arr);
	}

	/**
	 * Tests quick sort of a Duplicates array
	 */
	@Test
	public void testQuickSortDuplicates() {
		generateDuplicatesArray();
		SortingAlgorithms.quickSort(arr);
	}

	/**
	 * Tests merge sort of a Duplicates array
	 */
	@Test
	public void testMergeSortDuplicates() {
		generateDuplicatesArray();
		SortingAlgorithms.mergeSort(arr);
	}
	
	/* Array generation methods */

	/**
	 * Generates a psuedo-random array
	 */
	private void generateRandomArray() {
		int insert = 1;
		for (int i = 0; i < SIZE; i++) {

			// Inserts a psuedo-random number
			insert = (insert * 31 + 51) % 47;
			this.arr[i] = new Integer(insert);
		}
	}

	/**
	 * Sets the test array to be in ascending order
	 */
	private void generateAscendingArray() {
		for(int i = 0; i < SIZE; i++){
			this.arr[i] = i;
		}

	}
	
	/**
	 * Sets the test array to be in descending order
	 */
	private void generateDescendingArray() {
		for(int i = 0; i < SIZE; i++){
			this.arr[i] = SIZE-i;
		}
	}
	
	/**
	 * Create an array with many duplicates
	 */
	private void generateDuplicatesArray(){
		int modifier = (int)Math.sqrt(SIZE);
		for(int i = 0; i < SIZE; i++){
			this.arr[i] = i % modifier;
		}
	}
}
