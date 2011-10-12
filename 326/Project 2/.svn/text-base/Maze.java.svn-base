import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Maze class represents a maze constructed as a rectangle of square cells
 * (each of which is represented by a MazeCell class). We create Mazes using the
 * nested class MazeFactory and solve Mazes using instances of the MazeRunner
 * abstract class.
 * 
 * @author Albert J. Wong (awong@cs)
 */
public class Maze {

  /** The start cell of this maze. */
  private MazeCell startCell;

  /** The donut cell of this maze. */
  private MazeCell donutCell;

  /**
   * A 2-dimensional array that holds/represents all the cells in this maze.
   */
  private MazeCell[][] cells;

  /** The width of the maze */
  private int width;

  /** The height of the maze */
  private int height;

  /**
   * A list of the <code>MazeChangeListener</code>s that will be notified on
   * a visitation state change.
   */
  private ArrayList<MazeChangeListener> mazeChangeListeners;

  /**
   * Create an incomplete Maze. This constructor is private because it should be
   * called only by the MazeFactory class.
   */
  private Maze() {
    mazeChangeListeners = new ArrayList<MazeChangeListener>();
  }

  /**
   * Adds a new MazeChangeListener to listen to changes in this maze.
   * 
   * @param listener listener to add to the maze's set of change listeners
   */
  public void addMazeChangeListener(MazeChangeListener listener) {
    mazeChangeListeners.add(listener);

    Iterator<MazeCell> it = getCells();

    while (it.hasNext()) {
      ((MazeCell) it.next()).addMazeChangeListener(listener);
    }
  }

  /**
   * Return the maze's starting cell.
   * 
   * @return the start cell of this maze.
   */
  public MazeCell getStart() {
    return startCell;
  }

  /**
   * Return the maze cell that contains the donut.
   * 
   * @return the donut cell of this maze.
   */
  public MazeCell getDonut() {
    return donutCell;
  }

  /**
   * Returns an iterator over all cells in this maze.
   * 
   * @return an iterator over all cells in this maze.
   */
  public Iterator<MazeCell> getCells() {
    return new MazeIterator();
  }

  /**
   * Returns an iterator over the neighbors of the given cell.
   * 
   * @param cell the cell whose neighbors should be returned.
   * @return an iterator over the given cell's neighbors.
   */
  public Iterator<MazeCell> getNeighbors(MazeCell cell) {
    return new NeighborIterator(cell);
  }

  /**
   * Returns the cell located at (row,col).
   * 
   * @param row The row of the cell to retrieve.
   * @param col The column of the cell to retrieve.
   * @return the cell located at (row,col).
   */
  public MazeCell getCell(int row, int col) {
    return cells[row][col];
  }

  /**
   * Change the cell located at (row,col).
   * 
   * @param row The row of the cell to set.
   * @param col The column of the cell to set.
   */
  private void setCell(int row, int col, MazeCell cell) {
    cells[row][col] = cell;
  }

  /**
   * Get the width, in number of cells, of the maze.
   * 
   * @return the width of the maze in number of cells.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Get the height, in number of cells, of the maze.
   * 
   * @return the height of the maze in number of cells.
   */
  public int getHeight() {
    return height;
  }

  /**
   * An iterator class that iterates over all the cells in a Maze. This class is
   * implemented using the inner class language feature in Java which allows an
   * inner (as opposed to nested) class to have an implicit reference to the
   * containing class. Thus, any MazeIterator instance implicitly knows with
   * which maze it is associated, and may access the maze's private fields.
   * 
   * @author Albert J. Wong (awong@cs)
   */
  private class MazeIterator implements Iterator<MazeCell> {

    /** Keeps track of the current row over which we are iterating. */
    private int curRow;

    /** Keeps track of the current column over which we are iterating. */
    private int curCol;

    /**
     * Construct a new MazeIterator.
     */
    private MazeIterator() {
      curRow = 0;
      curCol = 0;
    }

    /**
     * Returns true if there are still cells to be iterated over, false
     * otherwise.
     * 
     * @return true if there are still cells to be iterated over, false
     *         otherwise.
     */
    public boolean hasNext() {
      return (curRow + 1 != height || curCol != width);
    }

    /**
     * If hasNext() is true, this returns the next cell in the iteration
     * sequence. Otherwise a NoSuchElementException is thrown.
     * 
     * @return the next cell in the iteration sequence
     * @exception NoSuchElementException Thrown if next() is called when there
     *            are no more elements to be iterated over.
     */
    public MazeCell next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      if (curCol == width) {
        curCol = 0;
        curRow++;
      }

      return cells[curRow][curCol++];
    }

    /**
     * The remove function is not implemented in this iterator.
     * 
     * @exception UnsupportedOperationException Thrown when remove() is called.
     */
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * An iterator class that iterates over all neighbors that can be reached from
   * the cell the iterator was created for. As for MazeIterator, we use an inner
   * class so that NeighborIterator is associated with a particular instance of
   * Maze.
   * 
   * @author Albert J. Wong (awong@cs)
   */
  private class NeighborIterator implements Iterator<MazeCell> {

    /** The cell over whose neighbors we are iterating. */
    private MazeCell refCell;

    /** The current wall we are examining. */
    private Direction curWall;

    /** The number of neighbors we have found for this cell so far. */
    private int numNeighborsFound = 0;

    /**
     * Construct a new NeighborIterator that will iterator over the neighbors of
     * <code>cell</code>
     * 
     * @param cell The cell whose neighbors this iterator will iterate over.
     */
    public NeighborIterator(MazeCell cell) {
      refCell = cell;
      curWall = Direction.NORTH;
    }

    /**
     * Returns true if there are still cells to be iterated over, false
     * otherwise.
     * 
     * @return true if there are still cells to be iterated over, false
     *         otherwise.
     */
    public boolean hasNext() {
      return numNeighborsFound < (refCell.getMaxNumWalls() - refCell
          .getNumWalls());
    }

    /**
     * If hasNext() is true, this returns the next cell in the iteration
     * sequence. Otherwise a NoSuchElementException is thrown.
     * 
     * @return the next cell in the iteration sequence
     * @exception NoSuchElementException Thrown if next() is called when there
     *            are no more elements to be iterated over.
     */
    public MazeCell next() {
      int nextRow = refCell.getRow();
      int nextCol = refCell.getCol();

      while (hasNext()) {
        if (!refCell.isWall(curWall)) {
          if (curWall == Direction.NORTH) {
            nextRow--;
          } else if (curWall == Direction.EAST) {
            nextCol++;
          } else if (curWall == Direction.SOUTH) {
            nextRow++;
          } else if (curWall == Direction.WEST) {
            nextCol--;
          }

          numNeighborsFound++;
          curWall = curWall.clockwise90();
          return getCell(nextRow, nextCol);
        }

        curWall = curWall.clockwise90();
      }

      throw new NoSuchElementException();
    }

    /**
     * The remove function is not implemented in this iterator.
     * 
     * @exception UnsupportedOperationException Thrown when remove() is called.
     */
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * MazeFactory is a Factory Design Pattern that generates a new Maze. It
   * should throw a malformed input exception, but I didn't have time to go
   * through and fix that. It currently exits if it hits a parse error.
   * 
   * @author Albert J. Wong
   */
  public static class MazeFactory {
    /**
     * parseMaze takes a file in and tries to generate a Maze from that file.
     * 
     * @param filename Name of file with data to generate a Maze.
     * @return Returns a new Maze based on the given filename.
     * 
     * @exception FileNotFoundException Thrown if the file cannot be found.
     * @exception IOException Thrown on a generic IO Exception
     */
    static Maze parseMaze(String filename) throws FileNotFoundException,
        IOException {
      Maze maze = new Maze();

      BufferedReader br = new BufferedReader(new FileReader(filename));

      String topLine;
      String midLine;
      String bottomLine;

      try {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        maze.width = Integer.parseInt(st.nextToken());
        maze.height = Integer.parseInt(st.nextToken());
        maze.cells = new MazeCell[maze.height][maze.width];

      } catch (NoSuchElementException nsee) {
        System.err.println("Error in Maze: line 1");
        System.err.println(nsee);
        System.exit(1);

      } catch (NumberFormatException nfe) {
        System.err.println("Error in Maze: line 1");
        System.err.println(nfe);
        System.exit(1);
      }

      topLine = br.readLine();
      for (int y = 0; y < maze.height; y++) {
        midLine = br.readLine();
        bottomLine = br.readLine();

        if (topLine.length() != maze.width * 2 + 1
            || midLine.length() != maze.width * 2 + 1
            || bottomLine.length() != maze.width * 2 + 1) {
          System.err.println("Error in Maze" + "near line " + y * 2);
          System.exit(1);
        }

        if (topLine.charAt(0) != '+' || midLine.charAt(0) != '|'
            || bottomLine.charAt(0) != '+') {
          System.err.println("Error in Maze (Corrupt Border) near line " + y
              * 2 + " col 0");
          System.exit(1);
        }

        if (topLine.charAt(maze.width * 2) != '+'
            || midLine.charAt(maze.width * 2) != '|'
            || bottomLine.charAt(maze.width * 2) != '+') {
          System.err.println("Error in Maze (Corrupt Border) " + "near line "
              + y * 2 + " col " + topLine.length());
          System.exit(1);
        }

        for (int x = 0; x < maze.width; x++) {
          boolean isStart = false;
          boolean isDonut = false;
          boolean northWall = false;
          boolean eastWall = false;
          boolean southWall = false;
          boolean westWall = false;

          int mid = x * 2 + 1;
          int right = mid + 1;
          int left = mid - 1;

          if (y == 0) {
            if (topLine.charAt(left) != '+' || topLine.charAt(mid) != '-'
                || topLine.charAt(right) != '+') {
              System.err.println("Error in Maze "
                  + "(Corrupt Border) near line " + y * 2 + " col " + mid);
              System.exit(1);
            }
          }

          // bug fix, 2/07/06 changed width to height
          if (y == maze.height - 1) {
            if (bottomLine.charAt(left) != '+' || bottomLine.charAt(mid) != '-'
                || bottomLine.charAt(right) != '+') {
              System.err.println("Error in Maze "
                  + "(Corrupt Border) near line " + y * 2 + " col " + mid);
              System.exit(1);
            }
          }

          if (topLine.charAt(mid) == '-') {
            northWall = true;
          }

          if (bottomLine.charAt(mid) == '-') {
            southWall = true;
          }

          if (midLine.charAt(left) == '|') {
            westWall = true;
          }

          if (midLine.charAt(right) == '|') {
            eastWall = true;
          }

          switch (midLine.charAt(mid)) {
          case '*':
            isStart = true;
            break;

          case 'O':
            isDonut = true;
            break;

          case ' ':
            // Do nothing special.
            break;

          default:
            System.err.println("Error in Maze near line " + y * 2 + " col "
                + mid);
            System.exit(1);
          }

          // Create the cell here.
          maze.setCell(y, x, new MazeCell(y, x, isStart, isDonut, northWall,
              eastWall, southWall, westWall));

          if (isStart) {
            if (maze.startCell != null) {
              System.err.println("Multiple start cells " + "near line " + y * 2
                  + " col " + topLine.length());
              System.exit(1);
            }

            maze.startCell = maze.getCell(y, x);
          }

          if (isDonut) {
            if (maze.donutCell != null) {
              System.err.println("Multiple exit cells " + "near line " + y * 2
                  + " col " + topLine.length());
              System.exit(1);
            }

            maze.donutCell = maze.getCell(y, x);
          }
        }

        topLine = bottomLine;
      }

      if (maze.startCell == null) {
        System.err.println("No start cell");
        System.exit(1);
      }

      if (maze.donutCell == null) {
        System.err.println("No donut cell");
        System.exit(1);
      }

      return maze;
    }
  }
}
