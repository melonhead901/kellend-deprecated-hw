/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 11, 2009
 * CSE 326 A
 * Project 3 - DataCounterTest.java
 */

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

/**
 * Abstract class which implements test code for data counters
 * 
 * @author Kellen
 * 
 */
public abstract class DataCounterTest {

	/* Test Constants */

	/**
	 * The ascii value of the char a
	 */
	private static final int ASCII_A = (int) 'a';

	/**
	 * The ascii value of the char z
	 */
	private static final int ASCII_Z = (int) 'z';

	/**
	 * The number of values to use in tests that generate the test arrays
	 * dynamically
	 */
	private static final int NUMBER_VALUES_DYNAMIC_TESTS = 20;

	/**
	 * The length of the average word in the variable length test
	 */
	private static final int VARIABLE_LENGTH_AVERAGE_WORD_LENGTH = 5;

	/**
	 * The maximum length of the word to insert
	 */
	private static final int VARIABLE_LENGTH_MAX_WORD_LENGTH = 5;

	/**
	 * Number of repeated inserts to perform for the repeated inserts test
	 */
	private static final int NUM_REPATED_INSERTS = 5;

	/**
	 * The data counter used throughout the test
	 */
	protected DataCounter<String> dc;

	/* Basic Tests */

	/**
	 * Verify that the dc size and counts length is initially 0.
	 */
	@Test
	public void testInitiallyEmpty() {
		assertEquals(dc.getSize(), 0);
		assertEquals(dc.getCounts().length, 0);
	}

	/**
	 * Inserts the characters a-z into the DC, verifies that the correct number
	 * of elements are inserted after each insertion.
	 */
	@Test
	public void testSimpleSeparateInserts() {
		for (int i = ASCII_A; i <= ASCII_Z; i++) {
			dc.incCount(String.valueOf((char) i));
			assertEquals(dc.getSize(), i - ASCII_A + 1);
			assertEquals(dc.getCounts().length, i - ASCII_A + 1);
		}
	}

	/**
	 * Inserts many elements in order, verify the data structure.
	 */
	@Test
	public void testRigorousSingleInserts() {

		int size = (ASCII_Z - ASCII_A) / 2;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					StringBuilder builder = new StringBuilder();
					builder.append((char) (i + ASCII_A));
					builder.append((char) (j + ASCII_A));
					builder.append((char) (k + ASCII_A));
					assertEquals(dc.getCounts().length, size * size * i + size
							* j + k);
					assertEquals(dc.getSize(), size * size * i + size * j + k);
					dc.incCount(builder.toString());
				}
			}
		}
	}

	/**
	 * Inserts the characters a-z in order into the DC multiple times, verifies
	 * that each item has been inserted the correct number of times at end
	 */
	@Test
	public void testEqualMultipleInserts() {
		int maxInserts = 5;

		for (int count = 0; count < maxInserts; count++) {
			for (int i = ASCII_A; i <= ASCII_Z; i++) {
				dc.incCount(String.valueOf((char) i));
			}
		}

		DataCount<String>[] results = dc.getCounts();
		for (int i = 0; i < results.length; i++) {
			assertEquals(results[i].count, maxInserts);
		}
	}

	/* Comprehensive Tests */

	/**
	 * Inserts many elements out of order, verify the data structure.
	 */
	@Test
	public void testDynamicInserts() {

		// The positive number of possible input values, preferably in [1,26]
		String[] inputs = new String[NUMBER_VALUES_DYNAMIC_TESTS];
		int[] freq = new int[NUMBER_VALUES_DYNAMIC_TESTS];

		// Create the data table
		for (int i = 0; i < NUMBER_VALUES_DYNAMIC_TESTS; i++) {
			inputs[i] = String.valueOf((char) (ASCII_A + i));
		}

		Random rand = new Random(6); // For consistency

		// Do insertions
		int numToInsert = 1000;
		for (int i = 0; i < numToInsert; i++) {
			// Pick a value to insert
			int index = rand.nextInt(NUMBER_VALUES_DYNAMIC_TESTS);
			// Increment it
			this.dc.incCount(inputs[index]);
			// Update it's frequency
			freq[index]++;
		}

		DataCount<String>[] results = this.dc.getCounts();

		// Check that each element in the results array was inserted the correct
		// number of times.
		for (int i = 0; i < results.length; i++) {
			assertEquals(results[i].count, freq[results[i].data.charAt(0)
					- ASCII_A]);
		}

		// Check that each element we inserted is in the results array the
		// correct number of times.
		for (int i = 0; i < NUMBER_VALUES_DYNAMIC_TESTS; i++) {
			// The string we're looking for
			String search = inputs[i];

			// The index of that string in the results array
			int index = arrayContains(results, search);

			assertEquals(freq[i], results[index].count);
		}
	}

	/**
	 * Executes a tests which randomly generates words of variable length,
	 * inserts each word variable number of times, then check that the word has
	 * been correctly inserted
	 * 
	 * @param seed
	 *            The random used used to test
	 */
	public void variableLengthComprehensiveTest(Random rand) {

		// The number of values to test
		String[] data = new String[NUMBER_VALUES_DYNAMIC_TESTS];
		int[] freq = new int[NUMBER_VALUES_DYNAMIC_TESTS];

		// Create the data table
		for (int i = 0; i < NUMBER_VALUES_DYNAMIC_TESTS; i++) {
			StringBuilder word = new StringBuilder();
			double prob = 1.0 - (double) VARIABLE_LENGTH_AVERAGE_WORD_LENGTH
					/ (double) VARIABLE_LENGTH_MAX_WORD_LENGTH;

			// Generate a word of the appropriate length
			for (int j = 0; j < VARIABLE_LENGTH_AVERAGE_WORD_LENGTH; j++) {
				if (rand.nextDouble() >= prob) {
					word.append((char) (rand.nextInt(26) + ASCII_A));
				}
			}

			// We have to make sure each of the words we give it are unique
			// If we have a unique word add it, otherwise we must keep the
			// counter in the same position
			if (arrayContains(data, word.toString()) != -1) {
				i--;
			} else {
				data[i] = word.toString();
			}
		}

		// Do insertions
		int numToInsert = 1000;
		for (int i = 0; i < numToInsert; i++) {
			// Pick a value to insert
			int index = rand.nextInt(NUMBER_VALUES_DYNAMIC_TESTS);
			// Increment it
			this.dc.incCount(data[index]);
			// Update it's frequency
			freq[index]++;
		}

		// Make sure the right number of values are in the array
		assertEquals(this.dc.getSize(), NUMBER_VALUES_DYNAMIC_TESTS);

		DataCount<String>[] results = this.dc.getCounts();

		// For each val we inserted
		for (int i = 0; i < NUMBER_VALUES_DYNAMIC_TESTS; i++) {
			// The string we're looking for
			String search = data[i];

			// The index of that string in the results array
			int index = arrayContains(results, search);
			assertEquals(freq[i], results[index].count);
		}
	}

	/**
	 * Runs an execution of the comprehensive test
	 */
	@Test
	public void variableLengthComprehensiveTestRun1() {
		int startSeed = 6;
		this.variableLengthComprehensiveTest(new Random(startSeed));
	}

	/**
	 * Runs an execution of the comprehensive test
	 */
	@Test
	public void variableLengthComprehensiveTestRun2() {
		int startSeed = 42;
		this.variableLengthComprehensiveTest(new Random(startSeed));
	}

	/**
	 * Runs an execution of the comprehensive test
	 */
	@Test
	public void variableLengthComprehensiveTestRun3() {
		int startSeed = 0;
		this.variableLengthComprehensiveTest(new Random(startSeed));
	}

	/**
	 * Runs an execution of the comprehensive test
	 */
	@Test
	public void variableLengthComprehensiveTestRun4() {
		int startSeed = 2012; // Yay graduating
		this.variableLengthComprehensiveTest(new Random(startSeed));
	}

	/**
	 * Runs an execution of the comprehensive test
	 */
	@Test
	public void variableLengthComprehensiveTestRun5() {
		int startSeed = 90189; // Happy Birthday
		this.variableLengthComprehensiveTest(new Random(startSeed));
	}

	/* Helper Methods */

	/**
	 * Search the given array for the given string
	 * 
	 * @param array
	 *            The array to search
	 * @param search
	 *            The string to search for
	 * @return The index of the searchString if it exists, -1 otherwise
	 */
	private int arrayContains(DataCount<String>[] array, String search) {

		int index = NUM_REPATED_INSERTS;

		// Find the value in the array
		for (int j = 0; j < array.length; j++) {
			if (array[j].data.equals(search)) {
				index = j;
				break;
			}
		}
		return index;
	}

	/**
	 * Search the given array for the given string
	 * 
	 * @param array
	 *            The array to search
	 * @param search
	 *            The string to search for
	 * @return The index of the searchString if it exists, -1 otherwise
	 */
	private int arrayContains(String[] array, String search) {

		int index = -1;

		// Find the value in the array
		for (int j = 0; j < array.length; j++) {
			if (array[j] != null && array[j].equals(search)) {
				index = j;
				break;
			}
		}
		return index;

	}
}
