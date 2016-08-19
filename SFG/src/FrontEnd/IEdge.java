package FrontEnd;

import java.awt.Graphics;


public interface IEdge {

	public Node getEndNode();
	public int getValue();
	public void setValue(int value);
	public void setEndNode(Node endNode);
	public void Draw(Graphics g);
	public int getStartX();
	public int getStartY();
	public SourcePoint getSourcePoint();
	public void setSourcePointPosition(int x, int y);
	
}
