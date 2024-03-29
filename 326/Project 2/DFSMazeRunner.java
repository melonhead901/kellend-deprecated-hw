/**
 * @author Kellen Donohue Apr 20, 2009 CSE 326 AA Project 2 - DFSMazeRunner.java
 */

public class DFSMazeRunner extends GenericMazeRunner {

	/**
	 * The inner stack used
	 */
	private Stack<MazeCell> s;

	/**
	 * Creates a new depth first search runner, based on a stack implementation
	 */
	public DFSMazeRunner() {
		this.s = new MazeStack<MazeCell>();
		this.name = "Depth-first search";
	}

	@Override
	public MazeCell DSGet() {
		MazeCell result = this.s.top();
		this.s.pop();
		return result;
	}

	@Override
	public boolean DSEmpty() {
		return this.s.isEmpty();
	}

	@Override
	public void DSAdd(MazeCell x) {
		this.s.push(x);
	}
}