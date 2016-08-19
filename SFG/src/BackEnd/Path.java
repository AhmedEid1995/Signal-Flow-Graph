package BackEnd;

import java.util.LinkedList;

import FrontEnd.Node;


public class Path implements IPath{
	private LinkedList<Node> path;
	public int gain;
	private int combinations;
	
	
	public Path(LinkedList<Node> path, int gain) {
		this.path = path;
		this.gain = gain;
		this.combinations = getCombinationBitMask();
	}
	
	
	public int getCombinationBitMask() {
		int bitMask = 0;
		Node node;

		for (int i = 0; i < path.size(); i++) {
			node = path.get(i);
			bitMask |= (1 << node.getLabel());
		}

		return bitMask;
	}

	public LinkedList<Node> getPath() {
		return this.path;
	}

	public int getGain() {
		return this.gain;
	}

	public int getBitMap() {
		return this.combinations;
	}
	
	public void setBitMap(int x){
		this.combinations=x;
	}
	
	public void setGain(int x){
		this.gain=x;
	}
	
	
}
