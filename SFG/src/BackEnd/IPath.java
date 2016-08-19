package BackEnd;

import java.util.LinkedList;

import FrontEnd.Node;

public interface IPath {
	public int getCombinationBitMask();
	public LinkedList<Node> getPath();

	public int getGain();

	public int getBitMap();
	
	public void setBitMap(int x);
	
	public void setGain(int x);
	
}
