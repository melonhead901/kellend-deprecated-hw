/**
 * @author Kellen Donohue 
 * May 4, 2009 
 * CSE 326 AA 
 * Project 2 - MazeGenerator.java
 */

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Generates a random maze and saves it to file
 */
public class MazeGenerator {

	/**
	 * The row of the start cell
	 */
	private final int startRow;

	/**
	 * The column of the start cell
	 */
	private final int startCol;

	/**
	 * The row of the goal cell
	 */
	private final int goalRow;

	/**
	 * The column of the goal cell
	 */
	private final int goalCol;

	/**
	 * The width of this maze
	 */
	protected int width;

	/**
	 * The height of the maze
	 */
	protected int height;

	/**
	 * The collection of cells
	 */
	protected Cell[][] cells;

	/**
	 * The random device used by this maze
	 */
	private Random rand;

	/**
	 * Stack used during maze generation process
	 */
	private MazeStack<Cell> stack;
	

	/**
	 * Creates a new maze generator, ready for creating
	 * 
	 * @param rows
	 *            The number of rows in the new maze
	 * @param cols
	 *            The number of columns in the new maze
	 * @param randomLocations
	 *            If true random start and finish locations will be used,
	 *            otherwise they will be the upper left and lower right
	 *            respectively
	 */
	public MazeGenerator(int rows, int cols, boolean randomLocations) {
		this.height = rows;
		this.width = cols;
		this.cells = new Cell[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.cells[i][j] = new Cell(i, j);
			}
		}
		this.rand = new Random();

		if (randomLocations) {
			// Create random starting and ending locations
			this.startCol = rand.nextInt(cols);
			this.startRow = rand.nextInt(rows);
			this.goalRow = rand.nextInt(rows);
			this.goalCol = rand.nextInt(cols);
		} else {
			this.startCol = 0;
			this.startRow = 0;
			this.goalRow = rows - 1;
			this.goalCol = cols - 1;
		}

		this.getCell(startRow, startCol).setStart(true);
		this.getCell(goalRow, goalCol).setGoal(true);
	}


	/**
	 * Create a new maze
	 * 
	 * @param args
	 *            An arg array in the following order: width height
	 *            output-filename [-r] -r is for random start and finish
	 *            locations
	 */
	public static void main(String[] args) {
		try {
			boolean randomLocations = false;
			if (args.length > 3 && args[3] == "-r") {
				randomLocations = true;
			}
			Random rand = new Random();
			MazeGenerator maze = new MazeGenerator(Integer.parseInt(args[0]),
					Integer.parseInt(args[1]), randomLocations);
			maze.generateMaze(rand);
			maze.writeMazeToFile(args[2]);
			System.out.println("All done");
		} catch (IOException ex) {
			System.out.println("YOU fail!");
		}
	}
	
	/**
	 * Write the stored maze to file, in maze format
	 * 
	 * @param file
	 *            The file to write to
	 * @throws IOException
	 *             If the file cannot be written
	 */
	private void writeMazeToFile(String file) throws IOException {

		/**
		 * The file writer
		 */
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				file)));

		/**
		 * Print the line at the top of the maze
		 */
		out.println(String.valueOf(this.width) + " "
				+ String.valueOf(this.height));

		/**
		 * Write the maze
		 */
		for (int i = 0; i < this.height; i++) { // For each row
			for (int j = 0; j < this.width; j++) { // For each column
				out.print('+'); // Print the divider at left
				Cell c = cells[i][j];
				if (c.hasWall(Direction.NORTH)) { // Print upper wall if it
					// exists
					out.print('-');
				} else {
					out.print(' ');
				}
			}

			/**
			 * Print divider and end and new line
			 */
			out.print('+');
			out.println();

			/**
			 * Print wall at right if it exists
			 */
			for (int j = 0; j < this.width; j++) {
				Cell c = cells[i][j];
				if (c.hasWall(Direction.WEST)) {
					out.print('|');
				} else {
					out.print(' ');
				}

				/**
				 * Print cell contents if it's special
				 */
				if (c.isGoal()) {
					out.print('O');
				} else if (c.isStart()) {
					out.print('*');
				} else {
					out.print(' ');
				}
			}

			/**
			 * End the row
			 */
			out.print('|');
			out.println();
		}

		/**
		 * Print the bottom line and end character, then close the writer
		 */
		for (int i = 0; i < width; i++) {
			out.print("+-");
		}
		out.print("+");

		out.close();
	}

	@SuppressWarnings("unused")
	// This method used only to test file writing procedure
	protected void overwriteMaze(String file) throws FileNotFoundException,
			IOException {
		Maze maze = Maze.MazeFactory.parseMaze(file);

		Iterator<MazeCell> mIterator = maze.getCells();
		for (int row = 0; row < maze.getHeight(); row++) {
			for (int col = 0; col < maze.getWidth(); col++) {

				MazeCell m = maze.getCell(row, col);
				Cell c = getCell(m.getRow(), m.getCol());
				try {
					c.setStart(m.isStart());
					c.setGoal(m.isDonut());
					c.setWall(Direction.EAST, m.isWall(Direction.EAST));
					c.setWall(Direction.WEST, m.isWall(Direction.WEST));
					c.setWall(Direction.SOUTH, m.isWall(Direction.SOUTH));
					c.setWall(Direction.NORTH, m.isWall(Direction.NORTH));
				} catch (java.lang.NullPointerException ex) {
					System.out.println(row + " " + col);
				}

			}
		}
	}

	/**
	 * Generate a randomly created maze
	 * 
	 * @param rand
	 *            The random device to use in the maze
	 */
	private void generateMaze(Random rand) {

		stack = new MazeStack<Cell>();
		stack.push(getCell(this.startRow, this.startCol));
		while (!stack.isEmpty()) {
			processCell(stack.top());
		}
	}

	/**
	 * Process a cell during maze generation
	 * 
	 * @param c
	 *            Cell to process
	 */
	private void processCell(Cell c) {
		c.setState(Cell.CellState.VISITED);
		List<Cell> neighbors = this.getUnvisitedNeighbors(c);
		if (neighbors.size() > 0) { // Randomly select and operate on an
									// unvisited neighbor
			int index = this.rand.nextInt(neighbors.size());
			this.stack.push(c);
			Cell newCell = neighbors.get(index);
			this.removeConnectingWall(c, newCell);
			processCell(newCell);
		} else { // Cell has no unvisited neighbors, remove it from the stack
			this.stack.pop();
		}
	}

	/**
	 * Removes the walls connecting two cells
	 * 
	 * @param cell1
	 *            The first cell
	 * @param cell2
	 *            The second cell
	 */
	private void removeConnectingWall(Cell cell1, Cell cell2) {
		// Will become the direction relating the first wall to the second wall
		Direction dir;
		int colChange = cell2.col - cell1.col;
		int rowChange = cell2.row - cell1.row;

		// Makes sure you are comparing two cells only one off, then compare the
		// directions
		assert (Math.abs(colChange) + Math.abs(rowChange) == 1);

		if (colChange == 1) {
			dir = Direction.EAST;
		} else if (colChange == -1) {
			dir = Direction.WEST;
		} else if (rowChange == 1) {
			dir = Direction.SOUTH;
		} else {
			assert (rowChange == -1);
			dir = Direction.NORTH;
		}

		// The diving wall needs to be removed from each cell
		cell1.setWall(dir, false);
		cell2.setWall(dir.clockwise90().clockwise90(), false);
	}

	/**
	 * Get the cell if it is in the maze
	 * 
	 * @param row
	 *            The row # of the cell to get
	 * @param col
	 *            The col # of the cell to get
	 * @return
	 */
	protected Cell getCell(int row, int col) {

		// Make sure the cell is in the maze
		if (row < 0 || row >= this.height || col < 0 || col >= this.width) {
			return null;
		}

		return cells[row][col];
	}

	/**
	 * Gets a list of cells neighboring this one which have not been visited
	 * 
	 * @param cell
	 *            The cell whose neighbors to find
	 * @return
	 */
	protected List<Cell> getUnvisitedNeighbors(Cell cell) {
		List<Cell> neighbors = new java.util.ArrayList<Cell>();

		// The default direction
		Direction nextDir = Direction.NORTH;

		// A temporary cell
		Cell toAdd;

		// For each direction get the next cell in that direction
		for (int i = 0; i < 4; i++) {
			switch (nextDir) {
			case EAST:
				toAdd = getCell(cell.row, cell.col + 1);
				break;
			case NORTH:
				toAdd = getCell(cell.row - 1, cell.col);
				break;
			case SOUTH:
				toAdd = getCell(cell.row + 1, cell.col);
				break;
			default:
				assert (nextDir == Direction.WEST);
				toAdd = getCell(cell.row, cell.col - 1);
				break;
			}

			// If the cell is in the array and hasn't already been visited add
			// it to the neightbor's list
			if (toAdd != null
					&& toAdd.getState().equals(Cell.CellState.UNVISITED)) {
				neighbors.add(toAdd);
			}
			nextDir = nextDir.clockwise90();
		}
		return neighbors;
	}

	/**
	 * A cell used for the purposes of our maze generator
	 * 
	 * @author Kellen
	 * 
	 */
	protected static class Cell {

		/**
		 * A list of possible cell states
		 * 
		 * @author Kellen
		 * 
		 */
		public static enum CellState {
			UNVISITED, VISITED, CURRENT // Current used for Asciimation
		}

		/**
		 * Whether or not this cell is the start cell
		 */
		private boolean isStart;

		/**
		 * Whether or not this cell is the goal cell
		 */
		private boolean isGoal;

		/**
		 * The state of this cell
		 */
		private CellState state;

		/**
		 * An array holding the walls of this cell
		 */
		private boolean[] walls;

		/**
		 * This cell's row location
		 */
		private int row;

		/**
		 * This cell's column location
		 */
		private int col;

		/**
		 * Create a blank new cell
		 * 
		 * @param row
		 *            The cell's row
		 * @param col
		 *            The cell's column
		 */
		public Cell(int row, int col) {
			this.row = row;
			this.col = col;

			// Each cell starts with every wall activated
			this.walls = new boolean[] { true, true, true, true };
			this.state = CellState.UNVISITED;
			this.isStart = false;
			this.isGoal = false;
		}

		/**
		 * Get this cell's state
		 * 
		 * @return The cell's state
		 */
		public CellState getState() {
			return this.state;
		}

		/**
		 * Set the state of this cell
		 * 
		 * @param newState
		 *            The new state of this cell
		 */
		public void setState(CellState newState) {
			this.state = newState;
		}

		/**
		 * Get this cell's row position
		 * 
		 * @return The cell's 0-based row position
		 */
		public int getRow() {
			return this.row;
		}

		/**
		 * Get this cell's column position
		 * 
		 * @return The cell's 0-based column position
		 */
		public int getCol() {
			return this.col;
		}

		/**
		 * Sets the state of the wall in the given direction
		 * 
		 * @param dir
		 *            The given direction to change
		 * @param wall
		 *            The wall's new state
		 */
		public void setWall(Direction dir, boolean wall) {
			this.walls[dir.ordinal()] = wall;
		}

		/**
		 * Return whether this call has a wall in the given direction
		 * 
		 * @param dir
		 *            The direction to check for a wall
		 * @return True if the wall exists, false otherwise
		 */
		public boolean hasWall(Direction dir) {
			return this.walls[dir.ordinal()];
		}

		/**
		 * Returns whether or not this cell is the start cell
		 * 
		 * @return True if it is the start cell, false otherwise
		 */
		public boolean isStart() {
			return isStart;
		}

		/**
		 * Sets this cell as the start cell of the maze
		 * 
		 * @param isStart
		 *            True if this cell should become the start, false otherwise
		 */
		public void setStart(boolean isStart) {
			this.isStart = isStart;
		}

		/**
		 * Returns whether or not this is the goal cell
		 * 
		 * @return True if this cell is the goal, false otherwise
		 */
		public boolean isGoal() {
			return isGoal;
		}

		/**
		 * Sets the goal state of this cell
		 * 
		 * @param isGoal
		 *            What the goal state of this cell becomes
		 */
		public void setGoal(boolean isGoal) {
			this.isGoal = isGoal;
		}

		@Override
		public String toString() {
			return "(" + this.row + "," + this.col + ")";
		}
	}
}
