package BackEnd;

import java.util.LinkedList;

import FrontEnd.Node;


public class DFS {
	private static LinkedList<Node> pathSequence;
	
	public static void dfs(Node node, Node startNode, Node endNode,LinkedList<Path> Paths, int gain,Node[] vertices) {
		
		Model.visited[node.getLabel()] = true;
		for (int i = 0; i < vertices[node.getLabel()].getEdges().size(); i++) {

			if (vertices[node.getLabel()].getEdges().get(i).getEndNode().getLabel() == endNode.getLabel()) {
				vertices[node.getLabel()].getEdges().get(i).getEndNode().setParent(node);
				 pathSequence = new LinkedList<>();
				 pathSequence.add(getPathSequence(startNode, node));
				 pathSequence.add(endNode);

				Path path = new Path(pathSequence, gain * vertices[node.getLabel()].getEdges().get(i).getValue());
				Paths.add(path);

			} else if (!Model.visited[vertices[node.getLabel()].getEdges().get(i).getEndNode().getLabel()]) {
				vertices[node.getLabel()].getEdges().get(i).getEndNode().setParent(node);

				gain *= vertices[node.getLabel()].getEdges().get(i).getValue();
				dfs(vertices[node.getLabel()].getEdges().get(i).getEndNode(), startNode, endNode, Paths, gain,vertices);
				gain /= vertices[node.getLabel()].getEdges().get(i).getValue();
			}
		}
		Model.visited[node.getLabel()] = false;		//backtrack
	}
	
	public static Node getPathSequence(Node startNode, Node lastOne) {
		if (lastOne.getLabel() == startNode.getLabel()) {
			return startNode;
		}
		pathSequence.add(getPathSequence(startNode, lastOne.getParent()));
		return lastOne;
	}
	
	
	
	
	
}
