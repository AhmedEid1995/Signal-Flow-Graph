package FrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;

public class Edge implements IEdge {
	private Node startNode;
	private Node endNode;
	private int value;
	private int label;
	private SourcePoint sourcePoint;

	public Edge(Node startNode, int label) {
		this.startNode = startNode;
		this.label = label;

		this.sourcePoint = new SourcePoint(this.startNode.getxPosition(),
				this.startNode.getyPosition());
	}

	@Override
	public Node getEndNode() {
		// TODO Auto-generated method stub
		return this.endNode;
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public void setValue(int value) {
		// TODO Auto-generated method stub
		this.value = value;
	}

	@Override
	public void setEndNode(Node endNode) {
		// TODO Auto-generated method stub
		this.endNode = endNode;
	}

	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub
		if (startNode != null && endNode != null) {
			Graphics2D graphics = (Graphics2D) g;

			g.setColor(Color.black);
			graphics.setFont(new Font("Traditional Arabic", Font.BOLD, 18));
			graphics.draw(new QuadCurve2D.Double(startNode.getxPosition()
					+ startNode.getWidth() / 2, startNode.getyPosition()
					+ startNode.getHeight() / 2, sourcePoint.getX(),
					sourcePoint.getY(), endNode.getxPosition()
							+ endNode.getWidth() / 2, endNode.getyPosition()
							+ endNode.getHeight() / 2));

			g.drawString("Edge" + label + "," + value, sourcePoint.getX(),
					sourcePoint.getY() + 10);

		}
	}

	@Override
	public int getStartX() {
		return startNode.getxPosition() + (startNode.getWidth() / 2);
	}

	@Override
	public int getStartY() {
		return startNode.getyPosition() + (startNode.getHeight() / 2);
	}

	@Override
	public SourcePoint getSourcePoint() {
		return this.sourcePoint;
	}

	@Override
	public void setSourcePointPosition(int x, int y) {
		// TODO Auto-generated method stub
		this.sourcePoint.setX(x);
		this.sourcePoint.setY(y);
	}

}
