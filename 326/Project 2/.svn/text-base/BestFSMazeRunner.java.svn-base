/**
 * @author Kellen Donohue 
 * Apr 29, 2009 
 * CSE 326 AA 
 * Project 2 - BestFSMazeRunner.java
 */

public class BestFSMazeRunner extends GenericMazeRunner {

	protected PriorityQueue<ManhattanDistanceMazeCell> pq;

	public BestFSMazeRunner(int branchingFactor) throws Exception {
		if (branchingFactor == 2) {
			this.pq = new BinaryHeap<ManhattanDistanceMazeCell>();
		} else if (branchingFactor == 3) {
			this.pq = new ThreeHeap<ManhattanDistanceMazeCell>();
		} else {
			this.pq = new DHeap<ManhattanDistanceMazeCell>(branchingFactor);
		}
		this.name = "Best-first search";
	}

	public BestFSMazeRunner() {
		this.pq = new SkewHeap<ManhattanDistanceMazeCell>();
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
		this.pq.insert(new ManhattanDistanceMazeCell(x, this.donut));
	}

	/**
	 * The wrapper class for maze cell which adds tracking of the cell's
	 * Manhattan distance
	 */
	protected static class ManhattanDistanceMazeCell implements
			Comparable<ManhattanDistanceMazeCell> {

		/**
		 * This cell's manhattanDistance
		 */
		protected int manhattanDistance;

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
		public ManhattanDistanceMazeCell(MazeCell cell, MazeCell donut) {

			int distance = 0;

			// Add row and column distance
			distance += Math.abs(donut.getCol() - cell.getCol());
			distance += Math.abs(donut.getRow() - cell.getRow());

			this.manhattanDistance = distance;

			this.innerCell = cell;
		}

		@Override
		public int compareTo(ManhattanDistanceMazeCell o) {

			// Same functionality as standard integer comparison
			if (this.manhattanDistance == o.manhattanDistance) {
				return 0;
			} else
				return this.manhattanDistance < o.manhattanDistance ? -1 : 1;
		}
	}
}
