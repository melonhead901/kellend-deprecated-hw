import java.io.PrintWriter;

/**
 * MazeTracer implements the MazeChangeListener interface and outputs a textual
 * trace of the progress of a MazeRunner.
 * 
 * @author Albert J. Wong (awong@cs)
 */
public class MazeTracer implements MazeChangeListener {
  /**
   * The print writer the tracer will output its trace onto.
   */
  private PrintWriter writer;

  /**
   * Creates a new <code>MazeTracer</code> that outputs its trace onto the
   * <code>PrintWriter w</code>. Clients should in a new print writer
   * instance because this class will close the print writer when it finishes.
   * 
   * @param writer A new print writer that this object can use to output its
   *        trace onto.
   */
  public MazeTracer(PrintWriter writer) {
    this.writer = writer;
  }

  /**
   * This function prints out the state of the cell that has changed.
   * 
   * @param cell the cell whose state change is being broadcast
   */
  public void cellStateChangeEvent(MazeCell cell) {
    writer.print("** Cell " + cell + " Accessed.  ");

    if (cell.getState() == MazeCell.CellState.VISITED) {
      writer.println("Marked Visited");
    } else if (cell.getState() == MazeCell.CellState.VISIT_IN_PROGRESS) {
      writer.println("Marked Visit In Progress");
    } else if (cell.getState() == MazeCell.CellState.UNVISITED) {
      writer.println("Marked Not Visited");
    } else if (cell.getState() == MazeCell.CellState.ON_SOLUTION_PATH) {
      writer.println("Mark Solution Path");
    }
  }

  /**
   * Print outs an error if this method is called. This tracer only handles cell
   * visitation state changes.
   */
  public void stateChangeEvent() {
    writer.println("** Unknown Maze State Change Event");
  }

  /**
   * Ensure the writer is properly closed when this object is shutdown.
   */
  protected void finalize() {
    writer.close();
  }
}
