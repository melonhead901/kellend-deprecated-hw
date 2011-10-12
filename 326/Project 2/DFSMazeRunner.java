import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Kellen Donohue
 * Apr 15, 2009
 * CSE 326 AA
 * Project 2 - DFSMazeRunner.java
 */

public class DFSMazeRunner implements MazeRunner {
	
	MazeStack<MazeCell> set;

	/**
	 * Creates a new DFS MazeRunner
	 */
	public DFSMazeRunner() {
		set = new MazeStack<MazeCell>();
	}

	/* (non-Javadoc)
	 * @see MazeRunner#solveMaze(Maze, java.io.PrintWriter)
	 */
	@Override
	public void solveMaze(Maze maze, PrintWriter writer) {
		set.push(maze.getStart());
		maze.getStart().setState(MazeCell.CellState.VISIT_IN_PROGRESS);
		
		while(!set.isEmpty())
		{
			MazeCell c = set.top();
			if(maze.getDonut() == c)
			{
				return;				
			}
			else
			{
				Iterator<MazeCell> i = maze.getNeighbors(c);
				while(i.hasNext())
				{
					MazeCell n = i.next();
					if(n.getState() == MazeCell.CellState.UNVISITED)
					{
						n.setState(MazeCell.CellState.VISIT_IN_PROGRESS);
						set.push(n);
					}
				}				
				c.setState(MazeCell.CellState.VISITED);
			}
			set.pop();
		}
	}

	  /**
	   * A random number generator, which we use to choose the next direction at
	   * each step in our random search.
	   */
	  private static Random randSeq = new Random();

	  /**
	   * We can store some information in each cell of the maze to keep track of our
	   * solution path. For the random maze runner, we'll store the next cell on the
	   * solution path. You may need to do something different for more
	   * sophisticated search algorithms.
	   */
	  private static class SolutionPathInfo {
	    /**
	     * Link to the next cell in the solution path.
	     */
	    public MazeCell nextInSolution;
	  }

	  /**
	   * A helper method that returns a pointer to the SolutionPathInfo associated
	   * with a given cell. This method takes care of initializing the cell's
	   * extraInfo field if it is null.
	   * 
	   * @param curCell The cell for which to get the SolutionPathInfo
	   */
	  private static SolutionPathInfo getSolutionPathInfo(MazeCell curCell) {
	    if (curCell.getExtraInfo() == null) {
	      curCell.setExtraInfo(new SolutionPathInfo());
	    }

	    return (SolutionPathInfo) curCell.getExtraInfo();
	  }


	/**
	   * Print the solution path and mark each cell on the path as ON_SOLUTION_PATH.
	   * 
	   * @param maze the Maze being solved.
	   * @param cellsVisited the total number of cells visited while searching.
	   * @param writer the PrintWriter on which to output the solution.
	   */
	  private void printSolution(Maze maze, int cellsVisited, PrintWriter writer) {
	    writer.println("Random search:");

	    // Loop through the solution list starting from the first cell
	    // and print the path to "writer"
	    MazeCell curCell = maze.getStart();
	    int pathLength = 0;
	    while (!curCell.isDonut()) {

	      pathLength++;
	      writer.print(curCell + " ");

	      // Mark the cell as "ON_SOLUTION_PATH" so any listeners know that this
	      // cell is part of the solution.
	      curCell.setState(MazeCell.CellState.ON_SOLUTION_PATH);

	      // Get the next cell on the solution path from the current cell's solution
	      // info.
	      SolutionPathInfo info = getSolutionPathInfo(curCell);
	      curCell = info.nextInSolution;
	    }

	    // Print the last cell.
	    writer.println(curCell + "\n");

	    // Print the path length and the number of cells visited by the runner.
	    writer.println("Length of path: " + pathLength);
	    writer.println("Number of cells visited: " + cellsVisited);
	  }


}
