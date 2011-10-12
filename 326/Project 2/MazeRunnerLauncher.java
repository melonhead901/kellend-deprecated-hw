import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * MazeRunnerLauncher defines our program's main method, which parses the
 * command-line parameters, build the Maze, and starts a MazeRunner.
 * 
 * Some of the code in this file and other files is useful only in conjunction
 * with a maze visualizer. You can mostly ignore it, except that you should take
 * care to mark cells as "VISIT_IN_PROGRESS," "VISITED" and "ON_SOLUTION_PATH"
 * when running your search.
 * 
 * @author Albert J. Wong (awong@cs)
 */
public class MazeRunnerLauncher {

  /**
   * The number of milliseconds to wait between visualizer GUI updates.
   */
  public static final int DEFAULT_UPDATE_INTERVAL = 500;

  public static void main(String[] args) {
    boolean useVisualizer = false;
    boolean useTracer = false;
    long updateInterval = DEFAULT_UPDATE_INTERVAL;
    MazeRunner runner = null;

    // There should be at least one argument (the maze input file).
    if (args.length < 1) {
      printUsage();
      return;
    }

    // Process command-line switches.
    int i = 0;
    while (i < args.length - 1) {
      if (args[i].equals("-r")) {
        runner = new RandomMazeRunner();
      }
      else if(args[i].equals("-DFS"))
      {
    	  runner = new DFSMazeRunner();
      }
      else if (args[i].equals("-v")) {
        useVisualizer = true;
      } else if (args[i].equals("-t")) {
        useTracer = true;
      } else if (args[i].equals("-p")) {
        // The -p option takes a second argument, the pause interval for the
        // visualizer.
        i++;
        if (i >= args.length - 1) {
          printUsage();
          return;
        }

        // Parse the pause interval.
        try {
          updateInterval = Long.parseLong(args[i]);
        } catch (NumberFormatException noe) {
          System.err.println("Bad Pause Interval. Defaulting to "
              + updateInterval);
        }
      } else {
        printUsage();
        return;
      }

      i++;
    }

    /*
     * Default to a random maze runner if no runner option is given
     */
    if (runner == null) {
      runner = new RandomMazeRunner();
    }

    Maze maze;
    try {
      // Create a maze from the given filename.
      maze = Maze.MazeFactory.parseMaze(args[args.length - 1]);
    } catch (FileNotFoundException fnfe) {
      System.err.println("Could not find file " + args[i]);
      return;
    } catch (IOException ioe) {
      System.err.println("Error reading file " + args[i]);
      return;
    }

    // Attach the visualizer if -v flag given
    if (useVisualizer) {
      // add your code here if doing the extra credit
    }

    // Attach the tracer if -t flag given
    if (useTracer) {
      PrintWriter debugWriter = new PrintWriter(new OutputStreamWriter(
          System.err), true);
      maze.addMazeChangeListener(new MazeTracer(debugWriter));
    }

    // Solve the maze
    PrintWriter writer = new PrintWriter(new BufferedWriter(
        new OutputStreamWriter(System.out)), true);
    runner.solveMaze(maze, writer);

    // Ensure the writer is closed so that it flushes the output.
    writer.close();
  }

  /**
   * Prints the usage instructions to standard error. You should modify this
   * method to document whatever command-line arguments you add to the program.
   */
  private static void printUsage() {
    System.err
        .println("Usage: java MazeRunnerLauncher [-r] [-v] [-t] [-p <milliseconds>] <mazefile>");
    System.err.println("\t-r -- Use the Random Maze Runner (default)");
    System.err.println("\t-v -- Visualize maze graphically");
    System.err.println("\t      (not yet implemented - see extra credit)");
    System.err.println("\t-t -- Output tracing information");
    System.err
        .println("\t-p -- Pause time between moving to each cell for visualizer");
    System.err.println("\t      (default " + DEFAULT_UPDATE_INTERVAL
        + "). Use with -v.");

    System.err.println();
  }
}
