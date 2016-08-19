package FrontEnd;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;




public class Node implements INode{
	
	private int label;
	private Node parent;
	private int yPosition;
	private int width;
	private int height;
	private Color color;
	private LinkedList<Edge> edges;
	private boolean visited;
	private String name;
	private int xPosition;
	
	public Node() {
		edges = new LinkedList<Edge>();
		this.setHeight(35);
		this.setWidth(35);
	}
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;
	}

	@Override
	public int getxPosition() {
		// TODO Auto-generated method stub
		return this.xPosition;
	}

	@Override
	public void setxPosition(int xPos) {
		// TODO Auto-generated method stub
		this.xPosition=xPos;
	}

	@Override
	public int getyPosition() {
		// TODO Auto-generated method stub
		return this.yPosition;
	}

	@Override
	public void setyPosition(int yPos) {
		// TODO Auto-generated method stub
		this.yPosition=yPos;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color=color;
	}

	@Override
	public LinkedList<Edge> getEdges() {
		// TODO Auto-generated method stub
		return this.edges;
	}

	@Override
	public void addEdges(Edge edge) {
		// TODO Auto-generated method stub
		this.edges.add(edge);
	}

	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub
	//	g.setColor(Color.BLACK);
		g.drawOval(xPosition, yPosition, getWidth(), getHeight());

		Iterator<Edge> iterator = edges.iterator();
		Edge edge;
		while (iterator.hasNext()) {
			edge = iterator.next();
			edge.Draw(g);
		}
		g.setColor(color);
		g.fillOval(xPosition, yPosition, getWidth(), getHeight());
	}

	@Override
	public boolean isVisited() {
		// TODO Auto-generated method stub
		return this.visited;
	}

	@Override
	public void setVisited(boolean visited) {
		// TODO Auto-generated method stub
		this.visited=visited;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		this.width=width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		this.height=height;
	}

	@Override
	public Node getParent() {
		// TODO Auto-generated method stub
		return this.parent;
	}

	@Override
	public void setParent(Node parent) {
		// TODO Auto-generated method stub
		this.parent=parent;
	}

	@Override
	public int getLabel() {
		// TODO Auto-generated method stub
		return this.label;
	}

	@Override
	public void setLabel(int label) {
		// TODO Auto-generated method stub
		this.label=label;
	}

}
