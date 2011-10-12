/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * 05/17/2009
 * CSE 326 A
 * Project 3 - TreeVisualizerV2Launcher.java
 */

import java.io.IOException;
import javax.swing.JFrame;

/**
 * 
 * 
 * @author Zach
 */
public class TreeVisualizerV2Launcher {

	private static final String filename = "texts/hamlet.txt";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VisualBSTCounter<String> counter = new VisualBSTCounter<String>();

		TreeVisualizerV2<VisualBSTCounter<String>> visualizer = new TreeVisualizerV2<VisualBSTCounter<String>>(
				"BST Visualizer");
		visualizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		visualizer.pack();
		visualizer.setVisible(true);

		counter.addListener(visualizer);

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
	}

}
