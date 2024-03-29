import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.JFrame;

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
	 * Command Line Arguments
	 */

	/**
	 * Argument used by command line to refer to depth-first search
	 */
	public static final String DFS_ARG = "-DFS";

	/**
	 * Argument used by command line to refer to breadth-first search
	 */
	public static final String BFS_ARG = "-BFS";

	/**
	 * Argument used by command line to refer to random search
	 */
	public static final String RAND_ARG = "-r";

	/**
	 * Argument used by command line to refer to visualizer
	 */
	public static final String VISUALIZER_ARG = "-v";

	/**
	 * Argument used by command line to refer to pause interval
	 */
	public static final String PAUSE_INTERVAL_ARG = "-p";

	/**
	 * Argument used by command lie to refer to tracer
	 */
	public static final String TRACER_ARG = "-t";

	/**
	 * Argument used by command lie to refer to tracer
	 */
	public static final String PTR_ARG = "-ptr";

	/**
	 * Argument used to specify binary heap
	 */
	public static final String BIN_ARG = "-bin";

	/**
	 * Argument used to specify binary heap
	 */
	public static final String THREE_ARG = "-three";

	/**
	 * Argument used to specify binary heap
	 */
	public static final String D_ARG = "-dheap";

	/**
	 * Argument used to specify best first search
	 */
	public static final String BESTFS_ARG = "-BestFS";

	/**
	 * Argument used to specify best first search
	 */
	public static final String ASTAR_ARG = "-AStar";

	/**
	 * Argument used to specify random heursitic maze runner
	 */
	public static final String RAND_HEU_ARG = "-Rand";
	/**
	 * Error Exit Codes
	 */

	/**
	 * Success code for Maze successfully loaded and solved
	 */
	public static final int SUCCESS = 0;

	/**
	 * Error code for Maze file not found
	 */
	public static final int MISSING_FILE = 1;

	/**
	 * Error code for improper arguments
	 */
	public static final int BAD_ARGS = 2;

	/**
	 * Error code for IO error when loading maze file
	 */
	public static final int FILE_ERROR = 3;

	/**
	 * The number of milliseconds to wait between visualizer GUI updates.
	 */
	public static final int DEFAULT_UPDATE_INTERVAL = 500;

	public static void main(String[] args) {
		main(args, false);
	}

	public static int main(String[] args, boolean useTest) {
		boolean useVisualizer = false;
		boolean useTracer = false;
		long updateInterval = DEFAULT_UPDATE_INTERVAL;
		MazeRunner runner = null;
		boolean useBestFS = false;
		boolean useAStar = false;

		// There should be at least one argument (the maze input file).
		if (args.length < 1) {
			printUsage();
			return BAD_ARGS;
		}

		// Process command-line switches.
		int i = 0;
		try {
			while (i < args.length - 1) {
				if (args[i].equals(RAND_ARG)) {
					runner = new RandomMazeRunner();
				} else if (args[i].equals(BFS_ARG)) {
					runner = new BFSMazeRunner();
				} else if (args[i].equals(DFS_ARG)) {
					runner = new DFSMazeRunner();
				} else if (args[i].equals(BESTFS_ARG)) {
					useBestFS = true;
				} else if (args[i].equals(ASTAR_ARG)) {
					useAStar = true;
				} else if (args[i].equals(PTR_ARG)) {
					if (useBestFS) {
						runner = new BestFSMazeRunner();
					} else if (useAStar) {
						runner = new AStarBestFSMazeRunner();
					} else {
						printUsage();
						return BAD_ARGS;
					}
				} else if (args[i].equals(BIN_ARG)) {
					if (useBestFS) {
						runner = new BestFSMazeRunner(2);
					} else if (useAStar) {
						runner = new AStarBestFSMazeRunner(2);
					} else {
						printUsage();
						return BAD_ARGS;
					}
				} else if(args[i].equals(RAND_HEU_ARG)){
					runner = new RandomHeuristicMazeRunner();
				}
				else if (args[i].equals(THREE_ARG)) {
					if (useBestFS) {
						runner = new BestFSMazeRunner(3);
					} else if (useAStar) {
						runner = new AStarBestFSMazeRunner(3);
					} else {
						printUsage();
						return BAD_ARGS;
					}
				} else if (args[i].equals(D_ARG)) {
					// Make sure there's an argument after i
					if (i >= args.length - 1) {
						printUsage();
						return BAD_ARGS;
					}
					i++;

					// Make a d heap with the specified branching factor
					int branchingFactor = Integer.parseInt(args[i]);
					if (useBestFS) {
						runner = new BestFSMazeRunner(branchingFactor);
					} else if (useAStar) {
						runner = new AStarBestFSMazeRunner(branchingFactor);
					} else {
						printUsage();
						return BAD_ARGS;
					}
				} else if (args[i].equals(VISUALIZER_ARG)) {
					useVisualizer = true;
				} else if (args[i].equals(TRACER_ARG)) {
					useTracer = true;
				} else if (args[i].equals(PAUSE_INTERVAL_ARG)) {
					// The -p option takes a second argument, the pause interval
					// for
					// the
					// visualizer.
					i++;
					if (i >= args.length - 1) {
						printUsage();
						return BAD_ARGS;
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
					return BAD_ARGS;
				}

				i++;
			}
		} catch (Exception e) {
			printUsage();
			return BAD_ARGS;
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
			return MISSING_FILE;
		} catch (IOException ioe) {
			System.err.println("Error reading file " + args[i]);
			return FILE_ERROR;
		}

		// Attach the visualizer if -v flag given
		if (useVisualizer) {
			// create a new visualizer
			Visualizer v = new Visualizer("Maze Visualizer", maze);
			// add the visualizer to maze's list of listeners
			maze.addMazeChangeListener(v);
			// set up the visualizer and make it visible
			v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			v.stateChangeEvent();
			v.pack();
			v.setVisible(true);
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

		if (useVisualizer) {
			synchronized (runner) {
				runner.solveMaze(maze, writer, updateInterval);
			}
		} else {
			runner.solveMaze(maze, writer, 0);
		}

		// Ensure the writer is closed so that it flushes the output.
		writer.close();
		return SUCCESS;
	}

	/**
	 * Prints the usage instructions to standard error. You should modify this
	 * method to document whatever command-line arguments you add to the
	 * program.
	 */
	private static void printUsage() {
		System.err
				.println("Usage: java MazeRunnerLauncher [-r] [-v] [-t] [-p <milliseconds>] <mazefile>");
		System.err.println("\t-r   -- Use the Random Maze Runner (default)");
		System.err.println("\t-BFS -- Use the BFS Maze Runner");
		System.err.println("\t-DFS -- Use the DFS Maze Runner");
		System.err
				.println("\t-Best -- Use the BestFS Maze Runner with the Manhattan Distance Heuristic, followed by a priority queue implementation");
		System.err
				.println("\t-AStar -- Use the BestFS Maze Runner with the A* heuristic, followed by a priority queue implementation");
		System.err.println("\t-v   -- Visualize maze graphically");
		System.err
				.println("\t        (not yet implemented - see extra credit)");
		System.err.println("\t-t   -- Output tracing information");
		System.err
				.println("\t-p   -- Pause time between moving to each cell for visualizer");
		System.err.println("\t        (default " + DEFAULT_UPDATE_INTERVAL
				+ "). Use with -v.");
		
		System.err.println("\nPriority Queue implementations:");
		System.err.println("\t-ptr -- Use SkewHeap implementation");
		System.err.println("\t-bin -- Use Binary0heap implementation");
		System.err.println("\t-three -- Use Three-heap implementation");
		System.err
				.println("\t-dheap # -- Use a d-heap implementation, where # is d");

		System.err.println();
	}
}
