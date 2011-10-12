/**
 * An enumeration type that represents the four cardinal directions in a maze.
 * We use an enumeration because we know in advance that there will be exactly
 * four instances of Direction. We also include some helper methods for moving
 * clockwise and counter-clockwise given a direction.
 * 
 * @author Albert J. Wong (awong@cs)
 * @author Laura Effinger-Dean (effinger@cs)
 */
public enum Direction {

  /** The direction representing north. */
  NORTH, 
  
  /** The direction representing east. */
  EAST, 
  
  /** The direction representing south. */
  SOUTH, 
  
  /** The direction representing west. */
  WEST;

  /**
   * Returns the direction that is 90 degrees clockwise from the current
   * direction.
   * 
   * @return the direction 90 degrees clockwise from the current direction
   */
  public Direction clockwise90() {
    switch (this) {
    case NORTH:
      return EAST;
    case EAST:
      return SOUTH;
    case SOUTH:
      return WEST;
    default: // WEST
      return NORTH;
    }
  }

  /**
   * Returns the direction that is 90 degrees counter-clockwise from the current
   * direction.
   * 
   * @return the direction 90 degrees counterclockwise from the current
   *         direction
   */
  public Direction counterClockwise90() {
    switch (this) {
    case NORTH:
      return WEST;
    case EAST:
      return NORTH;
    case SOUTH:
      return EAST;
    default: // WEST
      return SOUTH;
    }
  }
}
