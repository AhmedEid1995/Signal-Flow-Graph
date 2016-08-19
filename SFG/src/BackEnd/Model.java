package BackEnd;

import java.util.ArrayList;
import java.util.LinkedList;

import FrontEnd.Node;


public class Model {

	private Node[] vertices;
	private LinkedList<Path> forwardPaths;
	private LinkedList<Path> loops;
	static boolean[] visited;
	private ArrayList<int[]> listOfCombinationsOfLoops;
	private ArrayList<ArrayList<int[]>> allCompinationsOfNonTouchingLoops;
	private ArrayList<Integer> SmallDeltaValues;


	
	public Model(Node[] x) {
		this.vertices = x;
	}
	
	public LinkedList<Path> getForwardPaths(Node startNode, Node endNode) {
		if (forwardPaths == null) {
			visited = new boolean[vertices.length];
			forwardPaths = new LinkedList<Path>();

			DFS.dfs(startNode,  startNode, endNode, forwardPaths, 1,vertices);
		}
		return forwardPaths;
	}
	
	
	public LinkedList<Path> getSingleNonTouchedLoops() {
		if (loops == null) {
			loops = new LinkedList<>();
			for (int i = 0; i < vertices.length; i++) {
				visited = new boolean[vertices.length];
				for (int j = 0; j < i; j++)
					visited[j] = true;

				DFS.dfs(vertices[i], vertices[i], vertices[i], loops, 1,vertices);
			}
		}
		return loops;
	}
	
	
	
	

	private void compinations(int[] listOfLoops, int[] loopsToCheck, int counter, int counter2) {
		if (counter2 == loopsToCheck.length) {
			listOfCombinationsOfLoops.add(loopsToCheck.clone());
		} else {
			if (loopsToCheck.length - counter2 > listOfLoops.length - counter)
				return;
			loopsToCheck[counter2] = listOfLoops[counter];
			compinations(listOfLoops, loopsToCheck, counter + 1, counter2 + 1);

			compinations(listOfLoops, loopsToCheck, counter + 1, counter2);
		}
	}

	
	// BitMap
	private boolean isNotTouchedLoops(int[] allCombinations) {
		for (int i = 0; i < allCombinations.length; i++) {
			for (int j = i+1; j < allCombinations.length; j++) {
				if((loops.get(allCombinations[i]).getBitMap() & loops.get(allCombinations[j]).getBitMap()) != 0)
					return false;
			}
		}
		
		return true;
	}

	
	

	private ArrayList<int[]> getCombinantionLoops(int numOfCombinations) {
		listOfCombinationsOfLoops = new ArrayList<>();
		int[] question = new int[loops.size()];
		for (int i = 0; i < question.length; i++)
			question[i] = i;
		int[] sol = new int[numOfCombinations];
		compinations(question, sol, 0, 0);

		ArrayList<int[]> CombinantionLoops = new ArrayList<>();

		for (int i = 0; i < listOfCombinationsOfLoops.size(); i++) {
			if (isNotTouchedLoops(listOfCombinationsOfLoops.get(i))) {
				CombinantionLoops.add(listOfCombinationsOfLoops.get(i));
			}
		}

		if (CombinantionLoops.size() == 0)
			return null;

		allCompinationsOfNonTouchingLoops.add(CombinantionLoops);
		return CombinantionLoops;
	}

	
	/**
	 * 
	 * @return the value of big delta.
	 */
	private int getBigDelta() {
		int res = 1;

		int numOfCombinations = 0;
		while (true) {
			numOfCombinations++;
			ArrayList<int[]> CombinantionLoops = getCombinantionLoops(numOfCombinations);
			if (CombinantionLoops == null)
				break;

			int sum = 0;
			for (int i = 0; i < CombinantionLoops.size(); i++) {
				int mult = 1;
				for (int j = 0; j < CombinantionLoops.get(i).length; j++) {
					mult *= loops.get(CombinantionLoops.get(i)[j]).getGain();
				}
				sum += mult;
			}

			if (numOfCombinations % 2 != 0)
				res -= sum;
			else
				res += sum;
		}

		return res;
	}

	
	
	
	private int getSmallDelta(Path forwardPath) {
		int res = 1;

		for (int k = 0; k < allCompinationsOfNonTouchingLoops.size(); k++) {
			ArrayList<int[]> CombinantionLoops = allCompinationsOfNonTouchingLoops.get(k);

			int sum = 0;
			for (int i = 0; i < CombinantionLoops.size(); i++) {
				int mult = 1;
				for (int j = 0; j < CombinantionLoops.get(i).length; j++) {
					if (isTouchingPathWithLoop(forwardPath,	loops.get(CombinantionLoops.get(i)[j]))) {
						mult = 0;
						break;
					}
					mult *= loops.get(CombinantionLoops.get(i)[j]).getGain();
				}
				sum += mult;
			}

			if (k % 2 == 0)
				res -= sum;
			else
				res += sum;
		}
		
		SmallDeltaValues.add(res);
		return res;
	}

	private boolean isTouchingPathWithLoop(Path forwardPath, Path loop) {
		return (forwardPath.getBitMap() & loop.getBitMap()) != 0;
	}

	/**
	 * 
	 * @param inputNode
	 * @param outPutNode
	 * @return the value of over all transfer function.
	 */
	public double getOverAllGain(Node inputNode, Node outPutNode) {

		getForwardPaths(inputNode, outPutNode);
		getSingleNonTouchedLoops();

		allCompinationsOfNonTouchingLoops = new ArrayList<>();
		SmallDeltaValues = new ArrayList<>();

		int bigDelta = getBigDelta();
		int sum = 0;
		for (int i = 0; i < forwardPaths.size(); i++) {
			sum += forwardPaths.get(i).getGain()
					* getSmallDelta(forwardPaths.get(i));
		}
		
		
		

		return sum * 1.0 / bigDelta;
	}

	
	
	
	public LinkedList<Path> getForwardPaths() {
		return forwardPaths;
	}
	
	public ArrayList<Integer> getAllSmallDeltaValues(){
		return SmallDeltaValues;
	}

	
	
	
	
}
