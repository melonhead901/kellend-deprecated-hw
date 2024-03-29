import java.io.PrintWriter;

/**
 * Abstract base class for MazeRunners. It has a solveMaze method that, given a
 * maze, will attempt to find a path from the start to the goal in the maze.
 */
public interface MazeRunner {
  /**
   * Attempts to find a path from the start to the donut node of the maze given
   * in <code>maze</code>.
   * 
   * @param maze The maze to solve.
   * @param writer The PrintWriter on which to output the maze solution.
   * @param updateInterval The interval to wait between steps.
   */
  public void solveMaze(Maze maze, PrintWriter writer, long updateInterval);
}
