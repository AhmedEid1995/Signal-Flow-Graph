package FrontEnd;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;


public interface INode {
	public String getName();
	public void setName(String name);
	public int getxPosition();
	public void setxPosition(int xPos);
	public int getyPosition();
	public void setyPosition(int yPos);
	public Color getColor();
	public void setColor(Color color);
	public LinkedList<Edge> getEdges();
	public void addEdges(Edge edge);
	public void Draw(Graphics g);
	public boolean isVisited();
	public void setVisited(boolean visited);
	public int getWidth();
	public void setWidth(int width);
	public int getHeight();
	public void setHeight(int hieght);
	public Node getParent();
	public void setParent(Node parent);
	public int getLabel();
	public void setLabel(int label);
	
	
}
