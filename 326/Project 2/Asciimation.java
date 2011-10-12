/**
 * @author Kellen Donohue
 * May 5, 2009
 * CSE 326 AA
 * Project 2 - Asciimation.java
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Kellen Creates an asciimation of the path being solved
 */
public class Asciimation extends MazeGenerator {

	/**
	 * @param rows
	 *            The rows of the ASCII maze
	 * @param cols
	 *            The columns of the ASCII maze
	 * @param randomLocations
	 */
	public Asciimation(int rows, int cols) {
		super(rows, cols, true);
	}

	/**
	 * Creates an ASCIImation of a given maze solution
	 * 
	 * @param args
	 *            maze solution-path output-file
	 */
	public static void main(String[] args) {

		String mazePath = args[0];
		String solutionPath = args[1];
		String outputFile = args[2];

		/**
		 * The reader used to load files
		 */
		BufferedReader f;

		/**
		 * Holds one line of a file
		 */
		String[] line;

		/**
		 * The Asciimation object used to draw the Asciimation
		 */
		Asciimation maze = null;

		// Open maze & load
		try {
			f = new BufferedReader(new FileReader(mazePath));
			line = f.readLine().split(" ");
			maze = new Asciimation(Integer.parseInt(line[1]), Integer
					.parseInt(line[0]));
			maze.overwriteMaze(mazePath);
			f.close();
		} catch (IOException ex) {
			System.out.println("Error opening maze file.");
		}

		// Process string path into cells
		Cell[] cellsOnPath = null;
		try {
			f = new BufferedReader(new FileReader(solutionPath));
			line = f.readLine().split(" ");
			cellsOnPath = maze.getCellArray(line);
			f.close();
		} catch (IOException e) {
			System.out.println("Error opening path file.");
		}

		// Open writer
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(outputFile)));
			// Iterate path, at each step:
			for (Cell c : cellsOnPath) {
				c.setState(Cell.CellState.CURRENT);// Set current cell to
				// current position
				maze.writeMazeProgress(out);// Write maze to file
				c.setState(Cell.CellState.VISITED);// Set current cell to not
				// current position
			}

			out.close(); // Close writer
		} catch (IOException e) {
			System.out.println("Error writing ASCIImation file.");
		}
	}

	/**
	 * Converts a string array of cells on the solution path into an array of
	 * cell
	 * 
	 * @param path
	 *            The string array of cells on the solution path
	 * @return The cells specified in the solution path
	 */
	private Cell[] getCellArray(String[] path) {
		Cell[] results = new Cell[path.length];
		for (int i = 0; i < path.length; i++) {

			// Remove non number characters in cell coordinate description
			String[] locs = path[i].split("\\(|,|\\)");

			int row = Integer.parseInt(locs[1]);
			int col = Integer.parseInt(locs[2]);
			Cell c = this.getCell(row, col);
			results[i] = c;
		}
		return results;
	}

	/**
	 * Writes the current progress of the maze to the file, similar to
	 * writeMazeToFile, but includes progress trackers and does not close the
	 * writer
	 * 
	 * @param writer
	 *            The print writer used to write progress data
	 */
	private void writeMazeProgress(PrintWriter writer) {

		/**
		 * Write the maze
		 */
		for (int i = 0; i < this.height; i++) { // For each row
			for (int j = 0; j < this.width; j++) { // For each column
				writer.print('+'); // Print the divider at left
				Cell c = cells[i][j];
				if (c.hasWall(Direction.NORTH)) { // Print upper wall if it
					// exists
					writer.print('-');
				} else {
					writer.print(' ');
				}
			}

			/**
			 * Print divider and end and new line
			 */
			writer.print('+');
			writer.println();

			/**
			 * Print wall at right if it exists
			 */
			for (int j = 0; j < this.width; j++) {
				Cell c = cells[i][j];
				if (c.hasWall(Direction.WEST)) {
					writer.print('|');
				} else {
					writer.print(' ');
				}

				/**
				 * Print cell contents if it's special
				 */
				if (c.isGoal()) {
					writer.print('O');
				} else if (c.isStart()) {
					writer.print('*');
				} else if (c.getState() == Cell.CellState.CURRENT) {
					writer.print('X');
				} else if (c.getState() == Cell.CellState.VISITED) {
					writer.print('.');
				} else {
					writer.print(' ');
				}
			}

			/**
			 * End the row
			 */
			writer.print('|');
			writer.println();
		}

		/**
		 * Print the bottom line and end character, then close the writer
		 */
		for (int i = 0; i < width; i++) {
			writer.print("+-");
		}
		writer.println("+");
		writer.println("=====");
	}
}
