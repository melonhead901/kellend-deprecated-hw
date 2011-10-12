import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Random;

/**
 * The RandomMazeRunner implements a naive search algorithm. At each location in
 * the maze, we randomly pick a direction to go, until we find the donut. In all
 * likelihood, if a path exists, the runner will find it, but it could take a
 * very long time! Your implementations of BFS, DFS and BestFS will (hopefully!)
 * be far more efficient.
 * 
 * @author Albert J. Wong (awong@cs)
 * @author Hannah C. Tang (hctang@cs)
 */
public class RandomMazeRunner implements MazeRunner {

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
   * Tries to find a solution to the given maze. This function is the core of
   * the random maze runner. It chooses a random neighboring node to expand and
   * goes that way. Think of it as a lost child in the woods looking for the way
   * home stumbling around from spot to spot until it runs out of enery...err
   * maybe not.
   * 
   * @param maze the maze to solve.
   * @param writer the PrintWriter on which to output the solution.
   */
  public void solveMaze(Maze maze, PrintWriter writer, long updateInterval) {
    int cellsVisited;
    MazeCell curCell;

    // This is the actual solving engine. Beginning from the
    // start cell, a random path to a neighboring cell is chosen.
    // If the neighboring cell is the donut, we solved the maze.
    // If not, repeat.
    cellsVisited = 0;
    curCell = maze.getStart();
    while (!curCell.isDonut()) {
      // Mark the cell visited.
      curCell.setState(MazeCell.CellState.VISITED);

      // Tally the total number of cells visited. Note that a cell may be
      // visited more than once by the random runner, but BFS/DFS/BestFS should
      // visit each cell at most once.
      cellsVisited++;

      // Choose a random neighbor of the current cell
      MazeCell nextCell = getRandomNeighbor(maze, curCell);

      // Update the current cell's "next" pointer to be the random cell we just
      // picked. Note that we might have already seen this cell, so we could
      // overwrite part of the solution path, but that's OK.
      SolutionPathInfo info = getSolutionPathInfo(curCell);
      info.nextInSolution = nextCell;

      // move on to the next cell
      curCell = nextCell;
    }

    // We've solved the maze, so now we output the solution.
    printSolution(maze, cellsVisited, writer);
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
    writer.println(curCell);

    // Print the path length and the number of cells visited by the runner.
    writer.println("Length of path: " + pathLength);
    writer.println("Number of cells visited: " + cellsVisited);
  }

  /**
   * A helper method that chooses a random neighboring node of the current cell.
   * 
   * @param maze The maze that we are solving
   * @param curCell The current cell we are on. We choose a random neighbor of
   *        this cell in the previous maze.
   */
  private MazeCell getRandomNeighbor(Maze maze, MazeCell curCell) {
    Iterator<MazeCell> it = maze.getNeighbors(curCell);
    int pickNum = randSeq.nextInt(curCell.getMaxNumWalls()
        - curCell.getNumWalls());
    MazeCell nextCell = null;

    // Find the randomly picked neighbor in the neighbors list. pickNum is never
    // more than the number of neighbors, so we do not need to check
    // it.hasNext().
    for (int i = 0; i <= pickNum; i++) {
      nextCell = (MazeCell) it.next();
    }

    return nextCell;
  }

}
