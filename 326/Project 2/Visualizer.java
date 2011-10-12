import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.GridLayout;

public class Visualizer extends JFrame implements MazeChangeListener {

	/**
	 * Warning told me to - LOOK IT UP LATER
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * each label represents a maze cell
	 */
	private JLabel[][] labels;
	
	/**
	 * constructor
	 * @param title the title of the frame
	 * @param maze the maze this frame renders
	 */
	public Visualizer(String title, Maze maze){
		super(title);
		
		// create the content pane laid out in a grid
		this.setContentPane(new JPanel(new GridLayout(maze.getHeight(), maze.getWidth())));
		
		// create the array of labels to represent the maze cells
		this.labels = new JLabel[maze.getHeight()][maze.getWidth()];
		
		// populate labels
		for (int i = 0; i < maze.getHeight(); i++){
			for (int j = 0; j < maze.getWidth(); j++){
				// get this cell
				MazeCell cell = maze.getCell(i, j);
				
				// create the label
				if (cell.isStart())
					labels[i][j] = new JLabel("  S  ");
				else if (cell.isDonut())
					labels[i][j] = new JLabel("  D  ");
				else
					labels[i][j] = new JLabel("     ");
				
				// center any internal components
				labels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				labels[i][j].setVerticalAlignment(SwingConstants.CENTER);
				
				// set the background color
				labels[i][j].setOpaque(true);
				labels[i][j].setBackground(Color.BLACK);
				/*if (cell.isStart()) {
					labels[i][j].setBackground(Color.GREEN);
				} else if (cell.isDonut()) {
					labels[i][j].setBackground(Color.RED);
				} else {
					labels[i][j].setBackground(Color.BLACK);
				}*/
				
				// create the border / walls
				int top = cell.isWall(Direction.NORTH)? 1 : 0;
				int left = cell.isWall(Direction.WEST)? 1 : 0;
				int bottom = cell.isWall(Direction.SOUTH)? 1 : 0;
				int right = cell.isWall(Direction.EAST)? 1 : 0;
				
				labels[i][j].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.WHITE));
				
				// add the label to the frame
				this.getContentPane().add(labels[i][j]);
			}
		}
	}
	
	/**
	 * update the state of the given cell
	 * @param cell the cell to update
	 */
	@Override
	public void cellStateChangeEvent(MazeCell cell) {
		// get this cell's label
		JLabel label = labels[cell.getRow()][cell.getCol()];

		// update the background color
		if (cell.getState() == MazeCell.CellState.UNVISITED){
			label.setBackground(Color.BLACK);
		} else if (cell.getState() == MazeCell.CellState.VISIT_IN_PROGRESS){
			label.setBackground(Color.GRAY);
		} else if (cell.getState() == MazeCell.CellState.VISITED){
			label.setBackground(Color.YELLOW);
		}  else if (cell.getState() == MazeCell.CellState.ON_SOLUTION_PATH){
			label.setBackground(Color.GREEN);
		}
	}

	/**
	 * unused
	 */
	@Override
	public void stateChangeEvent() {
		return;
	}
}
