/**
 * @author Kellen Donohue, Zach Stein
 * @author kellend, steinz
 * 05/17/2009
 * CSE 326 A
 * Project 3 - TreeVisualizer.java
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * Tree Visualizer Swing App
 * 
 * Drawing isn't optimal, but this is still a useful tool for debugging and
 * visualization.
 * 
 * @author Zach
 */
public class TreeVisualizer<E extends VisualBSTCounter<String>> extends JFrame
		implements TreeListener<String> {

	/**
	 * The tree to draw.
	 */
	private E tree;

	/**
	 * Draw strings instead of boxes if true.
	 */
	private boolean drawStrings;
	/**
	 * The size of ecah node in the tree.
	 */
	private int nodeSize;
	/**
	 * Padding at the top of the window.
	 */
	private static final int topPadding = 50;

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a TreeVisualizer object.
	 * 
	 * @param title
	 *            The frame title.
	 * @param tree
	 *            The tree to draw.
	 * @param nodeSize
	 *            The size of each node.
	 */
	public TreeVisualizer(String title, E tree, int nodeSize,
			boolean drawStrings, int windowSize) {
		super(title);
		this.tree = tree;
		this.nodeSize = nodeSize;
		this.drawStrings = drawStrings;

		// In the lab the visualizer worked fine without this, but on my Vista
		// machine the window has minimal initial size without this line
		this.setPreferredSize(new Dimension(windowSize, windowSize));
	}

	/**
	 * Redraw the tree when a node changes.
	 */
	@Override
	public void nodeChangeEvent(VisualBSTCounter.VisualBSTNode<String> node) {
		repaint();
	}

	/**
	 * Clear the screen and redraw the tree
	 */
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		// use a monospace font
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, nodeSize));

		// If the tree is empty, height doesn't matter so just use 0
		// Otherwise, scale the height of each level based on the height of the
		// tree
		int treeHeight = tree.TreeHeight();
		int height;
		if (treeHeight != -1)
			height = (this.getHeight() - 50) / (tree.TreeHeight() + 1);
		else
			height = 0;

		// Draw the tree
		drawLevel(g, tree.overallRoot, 0, this.getWidth() / 2,
				this.getWidth() / 2, height);
	}

	/**
	 * Recursive drawing method.
	 * 
	 * @param g
	 *            The graphics object to draw with.
	 * @param node
	 *            The current node to draw.
	 * @param level
	 *            The current node's depth.
	 * @param xPos
	 *            The current node's xPosition.
	 * @param width
	 *            The width to the left/right of the current node available for
	 *            the next level.
	 * @param height
	 *            The height of each level.
	 */
	private void drawLevel(Graphics g,
			VisualBSTCounter.VisualBSTNode<String> node, int level, int xPos,
			int width, int height) {
		if (node != null) {
			if (!drawStrings) {
				// Draw the Box
				g.drawRect(xPos - nodeSize / 2, topPadding + level * height
						- nodeSize / 2, nodeSize, nodeSize);
			}

			// Draw Lines to Children if they exist
			if (node.left != null) {
				g.drawLine(xPos - nodeSize / 2, topPadding + level * height
						+ nodeSize / 2, xPos - width / 2, topPadding
						+ (level + 1) * height - nodeSize / 2);
			}
			if (node.right != null) {
				g.drawLine(xPos + nodeSize / 2, topPadding + level * height
						+ nodeSize / 2, xPos + width / 2, topPadding
						+ (level + 1) * height - nodeSize / 2);
			}

			if (drawStrings) {
				// Draw the text
				g.setColor(Color.BLUE);
				g.drawString(node.data + " - " + node.count, xPos
						- node.data.length() * nodeSize / 2, topPadding + level
						* height + nodeSize / 2);
				g.setColor(Color.BLACK);
			}

			// Draw the children recursively
			drawLevel(g, node.left, level + 1, xPos - width / 2, width / 2,
					height);
			drawLevel(g, node.right, level + 1, xPos + width / 2, width / 2,
					height);
		}
	}
}
