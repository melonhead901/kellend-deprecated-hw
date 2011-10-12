/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 18, 2009
 * CSE 326 AA
 * Project 3 - Correlator.java
 */

import java.io.IOException;

/**
 * computes and outputs the correlation metric of two documents
 * 
 * @author Kellen
 * 
 */
public class Correlator {

	/**
	 * Smallest frequency of words to compare
	 */
	private static final double MIN_FREQ = 0.0001;

	/**
	 * Highest frequency of words to compare
	 */
	private static final double MAX_FREQ = 0.01;

	/**
	 * Compares two documents, outputs their comparison metric
	 * 
	 * @param args
	 *            Arguments specifying type of dictionary to use, and the names
	 *            of two files to compare
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		DataCounter<String> counter1 = null;
		DataCounter<String> counter2 = null;

		String file1 = "";
		String file2 = "";

		if (args.length != 3) {
			printUsage();
			System.exit(1);
		}

		// Handle the arguments
		for (int i = 0; i < args.length - 2; i++) {
			if (args[i].equals("-b")) {
				counter1 = new BSTCounter<String>();
				counter2 = new BSTCounter<String>();
			} else if (args[i].equals("-a")) {
				counter1 = new AVLCounter<String>();
				counter2 = new AVLCounter<String>();
			} else if (args[i].equals("-s")) {
				counter1 = new SplayCounter<String>();
				counter2 = new SplayCounter<String>();
			} else if (args[i].equals("-h")) {
				counter1 = new CuckooHashTable();
				counter2 = new CuckooHashTable();
			} else if (args[i].equals("-jt")) {
				counter1 = new JavaTreeCounter<String>();
				counter2 = new JavaTreeCounter<String>();
			} else if (args[i].equals("-jh")) {
				counter1 = new JavaHashCounter<String>();
				counter2 = new JavaHashCounter<String>();
			} else {
				printUsage();
				System.exit(1);
			}
		}

		file1 = args[args.length - 2];
		file2 = args[args.length - 1];

		// Process file 1
		int file1Words = openFile(counter1, file1);
		DataCount<String>[] array1 = counter1.getCounts();

		// Process file 2
		int file2Words = openFile(counter2, file2);
		DataCount<String>[] array2 = counter2.getCounts();

		SortingAlgorithms.quickSort(array1, new DataCountDataSorter());
		SortingAlgorithms.quickSort(array2, new DataCountDataSorter());

		// Running total of the comparison metric
		double total = 0;

		int a2Pos = 0;

		// Count number of words common to the two documents
		// int wordsInMetric = 0;

		// Iterate through each word in array 1
		for (int a1Pos = 0; a1Pos < array1.length && a2Pos < array2.length;) {
			if (array1[a1Pos].data.compareTo(array2[a2Pos].data) > 0) {
				a2Pos++;
			} else if (array1[a1Pos].data.compareTo(array2[a2Pos].data) < 0) {
				a1Pos++;
			} else {
				// If the normalized frequency is in the right range in the
				// second document perform the comparison
				double freq1 = array1[a1Pos].count / (double) file1Words;
				if (freq1 >= MIN_FREQ && freq1 <= MAX_FREQ) {
					double freq2 = array2[a2Pos].count / (double) file2Words;
					if (freq2 >= MIN_FREQ && freq2 <= MAX_FREQ) {
						total += Math.pow(freq1 - freq2, 2);
						// wordsInMetric++;
					}
				}
				a1Pos++;
				a2Pos++;
			}
		}

		// Output final result
		System.out.println(total);

		/*
		 * Other metric information System.out.println("Metric: " + total +
		 * " * 10**4 = " + (total 10000));
		 * 
		 * System.out.println("Words used to compute metric: " + wordsInMetric);
		 * 
		 * double scaled_metric = total / wordsInMetric 10000000;
		 * 
		 * if (Double.isNaN(scaled_metric)) {
		 * System.out.println("No common words."); } else {
		 * System.out.println("Alternate Metric: " + scaled_metric); }
		 */
	}

	/**
	 * Prints the usage instructions to standard error. You should modify this
	 * method to document whatever command-line arguments you add to the
	 * program.
	 */
	private static void printUsage() {
		System.err
				.println("Usage: java Correlator [ -b | -a | -s | -h ] <filename1> <filename2>");
		System.err.println("\t-b\t\t-- Use an Unbalanced BST");
		System.err.println("\t-a\t\t-- Use an AVL Tree");
		System.err.println("\t-s\t\t-- Use a Splay Tree");
		System.err.println("\t-h\t\t-- Use a Hashtable");
		System.err.println("\t-jt\t\t-- Use Java's TreeMap");
		System.err.println("\t-jh\t\t-- Use Java's HashMap");
		System.err.println();
	}

	/**
	 * Opens the file, counts the words inside it and stores that information
	 * 
	 * @param counter
	 *            The counter to store the word frequencies in
	 * @param filename
	 *            The name of the file to read
	 * @return The number of unique words in the file, -1 if the file load fails
	 */
	private static int openFile(DataCounter<String> counter, String filename) {
		try { // Try to open the file and process each word in it
			int numWords = 0;
			FileWordReader reader = new FileWordReader(filename);
			String word = reader.nextWord();

			// While there is still a word to read, read the next word
			while (word != null) {
				counter.incCount(word);
				word = reader.nextWord();
				numWords++;
			}
			return numWords;
		} catch (IOException e) {
			// Print a descriptive error
			System.err.println("Error processing " + filename + e);
			System.exit(1);
			return -1;
		}
	}
}
