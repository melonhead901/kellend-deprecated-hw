/**
 * @author Kellen Donohue
 * May 1, 2009
 * CSE 326 AA
 * Project 2 - AStarBestFSMazeRunner.java
 */

import java.io.PrintWriter;
import java.util.Iterator;

/**
 * A MazeRunner that uses a priority queue that operates on the A* heuristic
 * 
 * @author Kellen
 * 
 */
public class AStarBestFSMazeRunner extends BestFSMazeRunner {

	/**
	 * Distance from the c ell currently being looked at back to the start
	 */
	int startDistance;
	
	/**
	 * We need to keep track of the start distance to cell's we've already seen
	 * Since the aStarPQ just holds a regular cell this can't be done that way
	 * So we will do it using a multi dimensional array
	 */
	int[][] storedCells;

	PriorityQueue<AStarMazeCell> aStarPQ;

	public AStarBestFSMazeRunner(int branchingFactor) throws Exception {
		if (branchingFactor == 2) {
			this.aStarPQ = new BinaryHeap<AStarMazeCell>();
		} else if (branchingFactor == 3) {
			this.aStarPQ = new ThreeHeap<AStarMazeCell>();
		} else {
			this.aStarPQ = new DHeap<AStarMazeCell>(branchingFactor);
		}
		this.name = "Best First Search";
	}

	public AStarBestFSMazeRunner() {
		this.aStarPQ = new SkewHeap<AStarMazeCell>();
		this.name = "A Star Best First Search: ";
	}

	@Override
	public MazeCell DSGet() {
		this.startDistance = this.aStarPQ.findMin().getDistanceToStart();
		return this.aStarPQ.deleteMin().innerCell;
	}	

	@Override
	public void DSAdd(MazeCell x) {
		this.aStarPQ.insert(new AStarMazeCell(x, this.donut, this.startDistance));
	}
	
	@Override
	public boolean DSEmpty(){
		return this.aStarPQ.isEmpty();
	}

	@Override
	public void solveMaze(Maze maze, PrintWriter writer, long updateInterval) {

		this.donut = maze.getDonut();

		/**
		 * Count of the cells the solver has touched Set to 1, since the
		 * starting cell is always touched
		 */
		int cellsVisited = 1;

		/**
		 * Real Distance from the start
		 */
		this.startDistance = 0;
		
		this.storedCells = new int[maze.getHeight()][maze.getWidth()];		

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
				this.startDistance++;
				while (iter.hasNext()) {
					MazeCell neighbor = iter.next();
					if (neighbor.getState() == MazeCell.CellState.UNVISITED || 
							storedCells[neighbor.getRow()][neighbor.getCol()] > this.startDistance) {
						neighbor.setState(MazeCell.CellState.VISIT_IN_PROGRESS);
						neighbor.setExtraInfo(new PathInfo(m));
						this.DSAdd(neighbor);
						storedCells[neighbor.getRow()][neighbor.getCol()] = this.startDistance;
						cellsVisited++;
					}
				}
				// Mark this cell as visited
				m.setState(MazeCell.CellState.VISITED);
			}

			if (updateInterval != 0) {
				try {
					wait(updateInterval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		printNoSolutionFound(writer);
	}

	/**
	 * An MazeCell abstraction that operates on the A* heuristic
	 * 
	 * @author Kellen
	 * 
	 */
	protected static class AStarMazeCell extends ManhattanDistanceMazeCell {

		/**
		 * Real distance from the start cell to this cell
		 */
		private int distanceToStart;

		public AStarMazeCell(MazeCell cell, MazeCell donut, int startDist) {
			super(cell, donut);
			this.distanceToStart = startDist;
		}

		@Override
		public int compareTo(ManhattanDistanceMazeCell o) {
			
			if(!(o instanceof AStarMazeCell)){
				throw new ClassCastException("Can't compare two different types of cells yo!");
			}
			
			AStarMazeCell aObj = (AStarMazeCell)o;

			int thisDist = this.distanceToStart + this.manhattanDistance;
			int oDist = aObj.distanceToStart + aObj.manhattanDistance;

			// Same functionality as standard integer comparison
			if (thisDist == oDist) {
				return 0;
			} else
				return thisDist < oDist ? -1 : 1;
		}

		/**
		 * Get this distance to start
		 * 
		 * @return The distance to start
		 */
		public int getDistanceToStart() {
			return this.distanceToStart;
		}

		/**
		 * Sets a new start distance
		 */
		public void setStartDistanceToStart(int newDist) {
			this.distanceToStart = newDist;
		}
	}
}
