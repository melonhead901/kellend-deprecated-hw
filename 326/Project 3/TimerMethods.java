/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * May 26, 2009
 * CSE 326 A
 * Project 3 - WordCount.java
 */

import java.io.IOException;

/**
 * Class to output the time of method calls
 * 
 * @author Kellen
 * 
 */
public class TimerMethods {

	/**
	 * Load
	 * 
	 * @param filename
	 *            File to load words from
	 * @param counter
	 *            DataCounter which holds the words
	 */
	public static void loadFile(String filename, DataCounter<String> counter) {

		long start = System.nanoTime();

		// Try to open the file and process each word in it
		try {
			FileWordReader reader = new FileWordReader(filename);
			String word = reader.nextWord();
			while (word != null) {
				counter.incCount(word);
				word = reader.nextWord();
			}
		} catch (IOException e) {
			System.err.println("Error processing " + filename + e);
			System.exit(1);
		}

		System.out.println("Insert Time: " + (System.nanoTime() - start));
	}

	/**
	 * Gets the frequency array from the given data counter
	 * 
	 * @param counter
	 *            The counter whose frequencies to extract
	 * @return The array of word frequencies
	 */
	public static DataCount<String>[] getCounts(DataCounter<String> counter) {
		long start = System.nanoTime();
		DataCount<String>[] data = counter.getCounts();
		System.out.println("Counts Time: " + (System.nanoTime() - start));
		return data;
	}
}
