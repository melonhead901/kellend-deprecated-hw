/**
 * Kellen Donohue
 * 04/06/2009
 * CSE 326 AA
 * Project 1 - Reverse.java
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Read a .dat file and reverse it.
 * 
 * @version CSE326, Wi09
 */
public class Reverse {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println(" Incorrect number of arguments");
			System.err.println(" Usage: ");
			System.err
					.println("\tjava Reverse <stack type> <input file> <output file>");

			System.exit(1);
		}

		boolean useList = true;
		if (args[0].equals("list")) {
			useList = true;
		} else if (args[0].equals("array")) {
			useList = false;
		} else {
			System.err.println("\tSaw " + args[0]
					+ " instead of list or array as first argument");
			System.exit(1);
		}

		try {
			//
			// Set up the input file to be read, and the output file to be
			// written
			//
			BufferedReader fileIn = new BufferedReader(new FileReader(args[1]));
			PrintWriter fileOut = new PrintWriter(new BufferedWriter(
					new FileWriter(args[2])));

			//
			// Read the first line of the .dat file. We want to store the
			// "sample rate" in a variable, but we can ignore the rest
			// of the line. We step through the first line one token (word)
			// at a time using the StringTokenizer. The fourth token
			// is the one we want (the sample rate).
			//
			String oneLine = fileIn.readLine();
			StringTokenizer str = new StringTokenizer(oneLine);
			str.nextToken(); // Read in semicolon
			str.nextToken(); // Read in "Sample"
			str.nextToken(); // Read in "Rate"

			int sampleRate;
			sampleRate = Integer.parseInt(str.nextToken()); // Read in sample
															// rate

			//
			// Read in the file and place values from the second column
			// in the stack. The first column values are thrown away.
			// We stop reading if we reach the end of the file.
			//
			DStack s;
			if (useList) {
				s = new ListStack();

			} else {
				s = new ArrayStack();
			}

			int count = 0;
			while (fileIn.ready()) {
				oneLine = fileIn.readLine();
				if (oneLine.charAt(0) == ';') {
					continue;
				}
				str = new StringTokenizer(oneLine);
				str.nextToken(); // Read in time step value from
									// first column
				double data = Double.parseDouble(str.nextToken()); // Read in
																	// data
				// value from second
				// column
				s.push(data);
				count++;
			}

			System.out.println(count + " samples in file");

			//
			// Now we are ready to output the data values to output file.
			// But first, we need to output the header line
			// "; Sample Rate <sample rate>"
			//
			fileOut.println("; Sample Rate " + sampleRate);

			// Since the first column consists of numbers which start
			// at 0 and increase by 1/sampleRate every time slice, we'll
			// just use numSteps to recalculate these numbers.
			int numSteps = 0;

			// Finally, we print the values in reverse order (by popping
			// them off the stack). The first column consists of numbers
			// which start at 0 and increase by 1/sampleRate per row, so
			// we'll use numSteps/sampleRate to recalculate the appropriate
			// values. Uniform spacing will be accomplished by printing a tab.

			while (!s.isEmpty()) {
				fileOut.println((double) numSteps / sampleRate + "\t"
						+ s.peek());
				s.pop();
				numSteps++;
			}

			//
			// Close the files
			//
			fileIn.close();
			fileOut.close();

		} catch (IOException ioe) {
			System.err
					.println("Error opening/reading/writing input or output file.");
			System.exit(1);
		} catch (NumberFormatException nfe) {
			System.err.println(nfe.toString());
			System.err.println("Error in file format");
			System.exit(1);
		}
	}
}
