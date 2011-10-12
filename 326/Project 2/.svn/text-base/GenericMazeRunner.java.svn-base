import java.io.PrintWriter;
import java.util.Iterator;

/**
 * @author Kellen Donohue 
 * Apr 28, 2009 
 * CSE 326 AA 
 * Project 2 - GenericMazeRunner.java
 */

/**
 * An abstract generic maze runner, subclasses must specify get set and isEmpty operations
 */
public abstract class GenericMazeRunner implements MazeRunner {

	/**
	 * Gets the next cell off the implementing data structure
	 * @return The next cell on the data structure
	 */
	protected abstract MazeCell DSGet();
	
	/**
	 * Returns whether or not the data structure is empty
	 * @return True if the data structure is empty, otherwise false
	 */
	protected abstract boolean DSEmpty();
	
	/**
	 * Adds the given cell to the data structure
	 * @param start The cell to add
	 */
	protected abstract void DSAdd(MazeCell cell);
		
	/**
	 * The name of the search algorithm being implemented
	 */
	protected String name;
	
	/**
	 * The maze runner's maze
	 */
	protected MazeCell donut;

	public GenericMazeRunner(){
		this.name = "Abstract Search";
	}
	
	@Override
	public void solveMaze(Maze maze, PrintWriter writer) {
		
		this.donut = maze.getDonut();
		
		/**
		 * Count of the cells the solver has touched Set to 1, since the
		 * starting cell is always touched
		 */
		int cellsVisited = 1;
		
		// Add the first cell to the queue
		maze.getStart().setState(MazeCell.CellState.VISIT_IN_PROGRESS);
		this.DSAdd(maze.getStart());
		
		while (!this.DSEmpty()) {
			// Get the next cell to visit
			MazeCell m = this.DSGet();

			if (m.isDonut()) {
				// If the cell being visited is the donut, print the solution
				// and return
				printSolution(m, cellsVisited, writer);
				return;
			} else {
				// Add all neighboring cells to the queue marking them as visit
				// in progress and increment the counter
				Iterator<MazeCell> iter = maze.getNeighbors(m);
				while (iter.hasNext()) {
					MazeCell neighbor = iter.next();
					if (neighbor.getState() == MazeCell.CellState.UNVISITED) {
						neighbor.setState(MazeCell.CellState.VISIT_IN_PROGRESS);
						neighbor.setExtraInfo(new PathInfo(m));
						this.DSAdd(neighbor);
						cellsVisited++;
					}
				}
				// Mark this cell as visited
				m.setState(MazeCell.CellState.VISITED);
			}
		}
		printNoSolutionFound(writer);
	}
	
	/**
	 * Stores the previously visited cell
	 */
	protected static class PathInfo {

		public MazeCell cell;

		public PathInfo(MazeCell previousVisitedCell) {
			cell = previousVisitedCell;
		}
	}

	/**
	 * Print the solution!
	 * 
	 * @param finalCell
	 *            the cell with the donut in it
	 * @param writer
	 */
	protected void printSolution(MazeCell finalCell, int cellsVisited,
			PrintWriter writer) {
		// Print type of search used
		writer.println(this.name +":");

		int pathLength = 0;

		// Trace the solution back to the start
		// Tracer stores the currently viewed cell, starting at the end
		// Solution stores the path to the donut
		// Prepend tracer's cell location to the solution string and set it's
		// state to be on the solution path
		MazeCell tracer = finalCell;
		String solution = tracer.toString();
		tracer.setState(MazeCell.CellState.ON_SOLUTION_PATH);
		// While there is a cell before the current one, process the cell the
		// current one came from
		while (tracer.getExtraInfo() != null) {
			PathInfo previous = (PathInfo) tracer.getExtraInfo();
			solution = previous.cell.toString() + " " + solution;
			previous.cell.setState(MazeCell.CellState.ON_SOLUTION_PATH);
			tracer = previous.cell;
			pathLength++;
		}
		writer.println(solution);

		// Print extra info
		writer.println("Length of path: " + pathLength);
		writer.println("Number of cells visited: " + cellsVisited);
	}

	/**
	 * Print no solution found
	 * 
	 * @param writer
	 */
	protected void printNoSolutionFound(PrintWriter writer) {
		writer.println(this.name + " failed");
	}
}
