/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * 05/17/2009
 * CSE 326 A
 * Project 3 - TreeVisualizerLauncher.java
 */

import java.io.IOException;
import javax.swing.JFrame;

/**
 * Launches the tree visualizer for testing purposes.
 * 
 * @author Zach
 */
public class TreeVisualizerLauncher {

	private static final int windowSize = 1000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * The TreeCounter to use.
		 */
		VisualBSTCounter<String> counter = null;
		/**
		 * 
		 */
		String title = "";
		/**
		 * The input file.
		 */
		String filename;
		/**
		 * The wait time between operations.
		 */
		int waitTime = 1000;
		/**
		 * The size of each visual node.
		 */
		int nodeSize = 10;
		/**
		 * Whether or not to dump the contents of the tree to standard out.
		 */
		boolean dump = false;
		/**
		 * Draw the node contents instead of boxes if set to true.
		 */
		boolean drawStrings = true;

		if (args.length < 2) {
			printUsage();
			System.exit(1);
		}

		// Argument handling
		for (int i = 0; i < args.length - 1; i++) {
			if (args[i].equals("-b")) {
				counter = new VisualBSTCounter<String>();
				title = "BST Visualizer";
			} else if (args[i].equals("-a")) {
				counter = new VisualAVLCounter<String>();
				title = "AVL Visualizer";
			} else if (args[i].equals("-s")) {
				counter = new VisualSplayCounter<String>();
				title = "Splay Visualizer";
			} else if (args[i].equals("-w")) {
				// must be followed by an int
				if (i + 1 >= args.length - 1) {
					printUsage();
					System.exit(1);
				} else {
					try {
						waitTime = Integer.parseInt(args[++i]);
						if (waitTime < 1) {
							printUsage();
							System.exit(1);
						}
					} catch (NumberFormatException e) {
						printUsage();
						System.exit(1);
					}
				}
			} else if (args[i].equals("-n")) {
				// must be followed by an int
				if (i + 1 >= args.length - 1) {
					printUsage();
					System.exit(1);
				} else {
					try {
						nodeSize = Integer.parseInt(args[++i]);
					} catch (NumberFormatException e) {
						printUsage();
						System.exit(1);
					}
				}
			} else if (args[i].equals("-d")) {
				dump = true;
			} else if (args[i].equals("-g")) {
				drawStrings = false;
			}
		}

		if (counter == null) {
			printUsage();
			System.exit(1);
		}

		filename = args[args.length - 1];

		// Set up the visualizer
		TreeVisualizer<VisualBSTCounter<String>> visualizer = new TreeVisualizer<VisualBSTCounter<String>>(
				title, counter, nodeSize, drawStrings, windowSize);
		visualizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		visualizer.setSize(windowSize, windowSize);
		visualizer.pack();
		visualizer.setVisible(true);

		counter.addListener(visualizer);

		// main loop
		synchronized (counter) {
			try {
				// read the input
				FileWordReader reader = new FileWordReader(filename);
				String word = reader.nextWord();
				while (word != null) {
					// increment the count of this word
					counter.incCount(word);
					word = reader.nextWord();

					if (dump) {
						// dump the tree contents
						System.out.println(counter.dump() + "\n");
					}

					// pause
					counter.wait(waitTime);
				}
			} catch (IOException e) {
				System.err.println("Error processing " + filename + e);
				printUsage();
				System.exit(1);
			} catch (InterruptedException e) {
				System.err.println("\nThread interrupted " + e);
				printUsage();
				System.exit(1);
			}
		}
	}

	/**
	 * Print program usage.
	 */
	private static void printUsage() {
		System.err
				.println("Usage: java TreeVisualizerLauncher [ -b | -a | -s ]  [ -d ] [ -g ] [ -n n ] [ -w n ] <filename1>");
		System.err.println("\t-b\t\t-- Use an Unbalanced BST");
		System.err.println("\t-a\t\t-- Use an AVL Tree");
		System.err.println("\t-s\t\t-- Use a Splay Tree");
		System.err
				.println("\t-w n\t\t-- Wait n milliseconds between operations (default 1000, minimum is 1)");
		System.err.println("\t-n n\t\t-- Nodes are n pixels wide (default 10)");
		System.err
				.println("\t\t\t\t Smaller nodes are useful for seeing the general structure of large trees");
		System.err
				.println("\t-d\t\t-- Dump the contents of the tree to standard out after each operation");
		System.err
				.println("\t-g\t\t-- Just draw boxes for each node instead of the contents");
		System.err
				.println("\t\t\t\t Nice for seeing the general structure of large trees");
		System.err
				.println("\t\t\t\t Printing the node data is useful for validating the tree structure");
		System.err.println();
		System.err.println("\tExamples:");
		System.err
				.println("\t\tjava TreeVisualizerLauncher -a -d texts/hamlet.txt");
		System.err
				.println("\t\tjava TreeVisualizerLauncher -s -g -w 100 -n 5 texts/hamlet.txt");
		System.err.println();
	}
}
