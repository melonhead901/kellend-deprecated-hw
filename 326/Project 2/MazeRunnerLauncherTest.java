/**
 * @author Kellen Donohue
 * May 1, 2009
 * CSE 326 AA
 * Project 2 - MazeRunnerLauncherTest.java
 */

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests to ensure that MazeRunnerLauncher doesn't break Not an exhaustive test
 * really, doesn't check all failure conditions
 * 
 * @author Kellen
 * 
 */
public class MazeRunnerLauncherTest {
	
	/**
	 * The base directory where map inputs are contained 
	 */
	private final String mazesBaseDir = "sample-inputs/";
	
	/**
	 * The extension at the end of every maze file
	 */
	private final String mazesExt = ".txt";
	
	/**
	 * The maze used for small maze tests
	 */
	private final String smallMaze = "maze1";
	
	/**
	 * The maze used for big maze tests
	 */
	private final String largeMaze = "bigmaze1";

	/**
	 * Tests whether DFS can solve a small maze
	 */
	@Test
	public void testDFSSmall() {

		String search = MazeRunnerLauncher.DFS_ARG;
		String testMaze = smallMaze;

		String args[] = buildTest(search, testMaze);
		assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(args,
				true));
	}

	/**
	 * Tests whether DFS can solve a large maze
	 */
	@Test
	public void testDFSBig() {

		String search = MazeRunnerLauncher.DFS_ARG;
		String testMaze = largeMaze;

		String args[] = buildTest(search, testMaze);
		assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(args,
				true));
	}

	/**
	 * Tests whether BFS can solve a small maze
	 */
	@Test
	public void testBFSSmall() {

		String search = MazeRunnerLauncher.BFS_ARG;
		String testMaze = smallMaze;

		String args[] = buildTest(search, testMaze);
		assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(args,
				true));
	}

	/**
	 * Tests whether BFS can solve a large maze
	 */
	@Test
	public void testBFSBig() {

		String search = MazeRunnerLauncher.BFS_ARG;
		String testMaze = largeMaze;

		String args[] = buildTest(search, testMaze);
		assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(args,
				true));
	}

	/**
	 * Tests whether the random solver can solve a small maze
	 */
	@Test
	public void testRandomSmall() {

		String search = MazeRunnerLauncher.RAND_ARG;
		String testMaze = smallMaze;

		String args[] = buildTest(search, testMaze);
		assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(args,
				true));
	}

	/**
	 * Tests whether the random can solve a large maze
	 */
	@Test
	public void testRandomBig() {

		String search = MazeRunnerLauncher.RAND_ARG;
		String testMaze = largeMaze;

		String args[] = buildTest(search, testMaze);
		assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(args,
				true));
	}

	/**
	 * Tests where the skew heap can solve a small maze
	 */
	@Test
	public void testSkewHeapSmall() {

		String search = MazeRunnerLauncher.PTR_ARG;
		String testMaze = smallMaze;

		String args[] = buildBestFSTest(search, testMaze);
		assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(args,
				true));

	}

	/**
	 * Tests whether a binary heap can solve a small maze
	 */
	@Test
	public void testBinaryHeapSmall() {

		String search = MazeRunnerLauncher.BIN_ARG;
		String testMaze = smallMaze;

		String args[] = buildBestFSTest(search, testMaze);
		assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(args,
				true));

	}

	/**
	 * Tests whether a three heap can solve a small maze
	 */
	@Test
	public void testThreeHeapSmall() {

		String search = MazeRunnerLauncher.THREE_ARG;
		String testMaze = smallMaze;

		String args[] = buildBestFSTest(search, testMaze);
		assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(args,
				true));

	}

	/**
	 * Tests whether d heaps of varying sizes can solve a small maze
	 */
	@Test
	public void testDHeapSmall() {

		// Fibonacci sequence (2 and 3 already tested), also test 64
		int dHeapSizes[] = { 1, 5, 8, 13, 64 };
		String testMaze = smallMaze;

		for (int i = 0; i < dHeapSizes.length; i++) {
			String args[] = buildTest(dHeapSizes[i], testMaze);
			assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(
					args, true));
		}

	}

	/**
	 * Tests where the skew heap can solve a large maze
	 */
	@Test
	public void testSkewHeapLarge() {

		String search = MazeRunnerLauncher.PTR_ARG;
		String testMaze = largeMaze;

		String args[] = buildBestFSTest(search, testMaze);
		assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(args,
				true));

	}

	/**
	 * Tests whether a binary heap can solve a large maze
	 */
	@Test
	public void testBinaryHeapLarge() {

		String search = MazeRunnerLauncher.BIN_ARG;
		String testMaze = largeMaze;

		String args[] = buildBestFSTest(search, testMaze);
		assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(args,
				true));

	}

	/**
	 * Tests whether a three heap can solve a large maze
	 */
	@Test
	public void testThreeHeapLarge() {

		String search = MazeRunnerLauncher.THREE_ARG;
		String testMaze = largeMaze;

		String args[] = buildBestFSTest(search, testMaze);
		assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(args,
				true));

	}

	/**
	 * Tests whether d heaps of varying sizes can solve a large maze
	 */
	@Test
	public void testDHeapLarge() {

		// Fibonacci sequence (2 and 3 already tested), also test 64
		int dHeapSizes[] = { 1, 5, 8, 13, 64 };
		String testMaze = largeMaze;

		for (int i = 0; i < dHeapSizes.length; i++) {
			String args[] = buildTest(dHeapSizes[i], testMaze);
			assertEquals(MazeRunnerLauncher.SUCCESS, MazeRunnerLauncher.main(
					args, true));
		}

	}	/**
	 * Generates an arg[] for test string, with visualizer off
	 * 
	 * @param search
	 *            The search argument to use
	 * @param testMaze
	 *            The testMaze to use
	 * @return The arg[] array with the given choices
	 */
	private String[] buildTest(String search, String testMaze) {
		String[] args = new String[2];
		args[0] = search;
		args[1] = mazesBaseDir + testMaze + mazesExt;
		return args;
	}

	/**
	 * Generates an args string for a best first search test, with no visualizer
	 * @param search The search to use
	 * @param testMaze The testMaze to ues
	 */
	private String[] buildBestFSTest(String search, String testMaze){
		String[] args = new String[3]; 
		args[0] = MazeRunnerLauncher.BESTFS_ARG;
		args[1] = search;
		args[2] = mazesBaseDir + testMaze + mazesExt;
		return args;
	}
	/**
	 * Build a d heap arg string
	 * 
	 * @param branchingFactor
	 *            Branching factor
	 * @param testMaze
	 *            Maze to test
	 * @return The test string
	 */
	private String[] buildTest(int branchingFactor, String testMaze) {
		String[] args = new String[4];
		args[0] = MazeRunnerLauncher.BESTFS_ARG;
		args[1] = MazeRunnerLauncher.D_ARG;
		args[2] = String.valueOf(branchingFactor);
		args[3] = mazesBaseDir + testMaze + mazesExt;
		return args;

	}
}
