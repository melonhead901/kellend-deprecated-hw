/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * 05/17/2009
 * CSE 326 A
 * Project 3 - TreeVisualizerV2.java
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * 
 * @author Zach
 */
public class TreeVisualizerV2<E extends VisualBSTCounter<String>> extends
		JFrame implements TreeListener<String>, ActionListener {

	private JPanel treeArea;
	private JButton performStep;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6031352215844898620L;

	public TreeVisualizerV2(String title) {
		super(title);

		// set up the main content pane
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

		// add components to main
		treeArea = new JPanel();
		main.add(treeArea);

		performStep = new JButton("Step");
		performStep.setActionCommand("step");
		performStep.addActionListener(this);
		main.add(performStep);

		// add the main content pane to the frame
		this.setContentPane(main);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("step".equals(e.getActionCommand())) {
			// perform a step

		}
	}

	@Override
	public void nodeChangeEvent(VisualBSTCounter.VisualBSTNode<String> node) {

	}
}
