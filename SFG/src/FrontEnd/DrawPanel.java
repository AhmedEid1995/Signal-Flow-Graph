package FrontEnd;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private Color color;
	private Color selectedColor;
	private ArrayList<Node> nodes;
	private Node newNode;
	private Node startNode;
	private Node endNode;
	private final Image backGround = new ImageIcon("graphPaper.png").getImage();

	public DrawPanel() {
		nodes = new ArrayList<>();
		color = Color.red;
		selectedColor = Color.yellow;

		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(backGround, 0, 0, getWidth(), getHeight(), this);

		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).Draw(g);
		}

		if (newNode != null)
			newNode.Draw(g);

	}

	public void creatNewNode() {
		newNode = new Node();
		newNode.setColor(selectedColor);
	}

	public void setNewNode(int x, int y) {
		newNode.setxPosition(x);
		newNode.setyPosition(y);
	}

	public boolean isThereNewNode() {
		return newNode != null;
	}

	public void addNode() {
		if (newNode != null) {
			newNode.setColor(color);
			newNode.setxPosition(newNode.getxPosition() + newNode.getWidth()
					- (newNode.getxPosition() % newNode.getWidth()));
			newNode.setyPosition(newNode.getyPosition() + newNode.getHeight()
					- (newNode.getyPosition() % newNode.getHeight()));
			nodes.add(newNode);
			newNode = null;

			repaint();
		}
	}

	public void cancelAddinNode() {
		newNode = null;
		repaint();
	}

	public Node selectNode(int x, int y) {
		for (int i = 0; i < nodes.size(); i++) {
			if (isInXRange(x, nodes.get(i)) && isInYRange(y, nodes.get(i))) {
				nodes.get(i).setColor(selectedColor);

				if (startNode == null)
					return startNode = nodes.get(i);

				return endNode = nodes.get(i);
			}

		}
		return null;
	}

	private boolean isInYRange(int y, Node s) {
		// TODO Auto-generated method stub
		return (s.getyPosition() <= y && s.getyPosition() + s.getHeight() >= y);
	}

	private boolean isInXRange(int x, Node s) {
		// TODO Auto-generated method stub
		return (s.getxPosition() <= x && s.getxPosition() + s.getWidth() >= x);
	}

	public boolean isThereSelectedNode() {
		return startNode != null;
	}

	public Edge addEdge(int value) {
		Edge edge = startNode.getEdges().getLast();
		edge.setValue(value);
		edge.setEndNode(endNode);

		startNode.setColor(color);
		endNode.setColor(color);

		startNode = null;
		endNode = null;

		return edge;
	}

	public void setSelectedNodeToNull() {
		if (startNode != null) {
			startNode.setColor(color);
			startNode = null;
		}

		if (endNode != null) {
			endNode.setColor(color);
			endNode = null;
		}
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public Node getSelectedNode() {
		return startNode;
	}

	public Node getSecondSelectedNode() {
		return endNode;
	}

	public Color getDefaultNodeColor() {
		return color;
	}

}
