import java.util.ArrayList;
import java.util.Iterator;

/**
 * A MazeCell represents a single cell in a <code>Maze</code>. The class
 * includes a number of methods to get information about the cell's location,
 * neighbors, visitation state and so on. MazeRunners may also store arbitrary
 * "extra info" with each MazeCell using the get/setExtraInfo methods, which may
 * be useful for keeping track of paths to the donut.
 * 
 * @author Albert J. Wong (awong@cs)
 */
public class MazeCell {
  /**
   * CellState is an enumeration that represents the state of a given cell in
   * the current search.
   * 
   * @author Albert J. Wong (awong@cs)
   */
  public enum CellState {
    /** The state when a cell has never been visited */
    UNVISITED,

    /** The state when a cell has been traversed fully. */
    VISITED,

    /**
     * The state when a cell has been visited, but hasn't been fully processed
     * (e.g., if its neighbors haven't been visited yet in BFS or DFS).
     */
    VISIT_IN_PROGRESS,

    /**
     * The state when a cell is on the solution path (the path from the start to
     * the donut).
     */
    ON_SOLUTION_PATH;
  }

  /**
   * A piece of state that may be used to hold information about the current
   * search, if useful.
   */
  private Object info;

  /**
   * The current visitation state of this cell.
   */
  private CellState state;

  /**
   * A list of the <code>MazeChangeListener</code> that will be notified on a
   * visitation state change.
   */
  private ArrayList<MazeChangeListener> mazeChangeListeners;

  /**
   * An array to keep track of which walls exist on this cell.
   */
  private boolean[] walls;

  /**
   * The number of walls this cell has.
   */
  private int numWalls;

  /**
   * Whether or not this cell is the start cell.
   */
  private boolean isStartCell;

  /**
   * Whether or not this cell is the donut cell.
   */
  private boolean isDonutCell;

  /**
   * The row this cell is on.
   */
  private int row;

  /**
   * The column this cell is on.
   */
  private int col;

  /**
   * Creates a new MazeCell with the given parameters.
   * 
   * @param cellRow The row this cell is on.
   * @param cellCol The column this cell is on.
   * @param isStart <code>true</code> if this cell is the start cell.
   * @param isDonut <code>true</code> if this cell is the donut cell.
   * @param northWall <code>true</code> if this cell has a wall to the North.
   * @param eastWall <code>true</code> if this cell has a wall to the East.
   * @param southWall <code>true</code> if this cell has a wall to the South.
   * @param westWall <code>true</code> if this cell has a wall to the West.
   */
  public MazeCell(int cellRow, int cellCol, boolean isStart, boolean isDonut,
      boolean northWall, boolean eastWall, boolean southWall, boolean westWall) {
    info = null;
    state = CellState.UNVISITED;
    mazeChangeListeners = new ArrayList<MazeChangeListener>();

    walls = new boolean[] { northWall, eastWall, southWall, westWall };

    numWalls = 0;
    for (int i = 0; i < 4; i++) {
      if (walls[i]) {
        numWalls++;
      }
    }

    isStartCell = isStart;
    isDonutCell = isDonut;

    row = cellRow;
    col = cellCol;
  }

  /**
   * Returns the current visitation state of the Cell. A cell can be marked as
   * any value in <code>CellState</code>.
   * 
   * @return Returns the current visitation state of the cell.
   */
  public CellState getState() {
    return state;
  }

  /**
   * Sets the current visitation state of the Cell. A cell can be marked as any
   * value in <code>CellState</code>.
   * 
   * @param s Sets the visitation state to the state in <code>s</code>
   */
  public void setState(CellState s) {
    state = s;
    broadcastChange();
  }

  /**
   * Gets the object that was previously stored in this cell with
   * <code>setExtraInfo(Object newInfo)</code>. This can be used to implement
   * a form of closures on operations with this cell.
   * 
   * @return The object that was last stored in this node with
   *         <code>setExtraInfo(Object newInfo)</code>. If there was no
   *         previous call to <code>setExtraInfo</code>, <code>null</code>
   *         is returned.
   */
  public Object getExtraInfo() {
    return info;
  }

  /**
   * Stores an object in this cell that can be later retrieved with a call to
   * <code>getExtraInfo()</code>. This can be used to implement a form of
   * closures on operations with this cell.
   * 
   * @param newInfo The object to store in this cell. This object may later be
   *        retrieved with a call to <code>getExtraInfo()</code>.
   *        <code>newInfo</code> may be <code>null</code>. This will remove
   *        any previously stored object from this cell.
   */
  public void setExtraInfo(Object newInfo) {
    info = newInfo;
  }

  /**
   * Adds a new MazeChangeListener to listen to changes in this cell.
   * 
   * @param listener Adds <code>listener</code> to the list of
   *        <code>MazeChangeListener</code> that will be notified when the
   *        visitation state of this cell is changed via
   *        <code>setState(CellState s)</code>
   */
  public void addMazeChangeListener(MazeChangeListener listener) {
    mazeChangeListeners.add(listener);
  }

  /**
   * Notifies all <code>MazeChangeListener</code> that have been previously
   * registered via <code>addMazeChangeListener</code> of a change in this
   * cell's visitation state.
   */
  protected void broadcastChange() {
    Iterator<MazeChangeListener> it = mazeChangeListeners.iterator();

    while (it.hasNext()) {
      ((MazeChangeListener) it.next()).cellStateChangeEvent(this);
    }
  }

  /**
   * Returns true if this cell is a start cell.
   * 
   * @return <code>true</code> if this cell is a start cell.
   *         <code>false</code> otherwise.
   */
  public boolean isStart() {
    return isStartCell;
  }

  /**
   * Returns true if this cell is a donut cell.
   * 
   * @return <code>true</code> if this cell is a donut cell.
   *         <code>false</code> otherwise.
   */
  public boolean isDonut() {
    return isDonutCell;
  }

  /**
   * A defined toString function that will output a nicely formated string that
   * specifies the location (row,col) of this cell.
   * 
   * @return a string of the form "(row,col)".
   */
  public String toString() {
    return "(" + row + "," + col + ")";
  }

  /**
   * Returns <code>true</code> if there is a wall in the direction
   * <code>d</code>.
   * 
   * @param d The direction in which to check for a wall.
   * @return <code>true</code> if there is a wall in the direction
   *         <code>d</code>.
   */
  public boolean isWall(Direction d) {
    return walls[d.ordinal()];
  }

  /**
   * Returns the number of walls that this cell has
   * 
   * @return the number of walls this cell has
   */
  public int getNumWalls() {
    return numWalls;
  }

  /**
   * Returns the total possible number of walls this cell has. This can be
   * though of as the number of sides the cell has.
   * 
   * @return the maximum number of wall this cell can have.
   */
  public int getMaxNumWalls() {
    return 4;
  }

  /**
   * Returns the row this cell is in.
   * 
   * @return the row this cell is in.
   */
  public int getRow() {
    return row;
  }

  /**
   * Returns the column this cell is in.
   * 
   * @return the column this cell is in.
   */
  public int getCol() {
    return col;
  }
}
