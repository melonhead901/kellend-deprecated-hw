/**
 * @author steinz
 * 4/17/09
 * CSE326 Project 2
 * BFSMazeRunner employing a MazeQueue
 */

public class BFSMazeRunner extends GenericMazeRunner {
	
	/**
	 * The queue that holds the MazeRunner cells
	 */
	private Queue<MazeCell> q;

	/**
	 * Constructor
	 * Uses a MazeQueue
	 * Search type is Breadth-first
	 */
	public BFSMazeRunner() {
		this.q = new MazeQueue<MazeCell>();
		this.name = "Breadth-first search";
	}

	@Override
	public MazeCell DSGet() {
		return this.q.dequeue();
	}

	@Override
	public boolean DSEmpty() {
		return this.q.isEmpty();
	}

	@Override
	public void DSAdd(MazeCell x) {
		this.q.enqueue(x);
	}
}