/**
 * @author Kellen Donohue 
 * Apr 29, 2009 
 * CSE 326 AA 
 * Project 2 - BestFSMazeRunner.java
 */

public class RandomHeuristicMazeRunner extends GenericMazeRunner {

	protected PriorityQueue<RandomMazeCell> pq;

	public RandomHeuristicMazeRunner(int branchingFactor) throws Exception {
		if (branchingFactor == 2) {
			this.pq = new BinaryHeap<RandomMazeCell>();
		} else if (branchingFactor == 3) {
			this.pq = new ThreeHeap<RandomMazeCell>();
		} else {
			this.pq = new DHeap<RandomMazeCell>(branchingFactor);
		}
		this.name = "Best First Search";
	}

	public RandomHeuristicMazeRunner() {
		this.pq = new SkewHeap<RandomMazeCell>();
		this.name = "Best First Search";
	}

	@Override
	public MazeCell DSGet() {
		return this.pq.deleteMin().innerCell;
	}

	@Override
	public boolean DSEmpty() {
		return this.pq.isEmpty();
	}

	@Override
	public void DSAdd(MazeCell x) {
		this.pq.insert(new RandomMazeCell(x, this.donut));
	}

	/**
	 * The wrapper class for maze cell which adds tracking of the cell's
	 * Manhattan distance
	 */
	protected static class RandomMazeCell implements
			Comparable<RandomMazeCell> {

		/**
		 * This cell's manhattanDistance
		 */
		protected double val;

		/**
		 * The cell being wrapped
		 */
		protected MazeCell innerCell;

		/**
		 * Creates a new Manhattan distance maze cell
		 * 
		 * @param cell
		 *            The cell to wrap
		 */
		public RandomMazeCell(MazeCell cell, MazeCell donut) {
			this.val = Math.random();
			this.innerCell = cell;
		}

		@Override
		public int compareTo(RandomMazeCell o) {

			// Same functionality as standard integer comparison
			if (this.val == o.val) {
				return 0;
			} else
				return this.val < o.val ? -1 : 1;
		}
	}
}
