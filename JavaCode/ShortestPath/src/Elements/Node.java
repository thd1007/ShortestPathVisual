package Elements;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node>{
	// Coordinate
	private int x;
	private int y;
	// Id for readability of result purposes
	private static int idCounter = 0;
	public int id;
	
	// Parent
	public Node parent = null;
	
	// Evaluation functions
	public double f = Double.MAX_VALUE;
	public double g = Double.MAX_VALUE;
    // Heuristic
	public double h;
    
	public Node(double h) {
		this.h = h;
		this.id = idCounter++;
		this.neighbors = new ArrayList<>();
	}
    
	@Override
	public int compareTo(Node n) {
		return Double.compare(this.f, n.f);
	}
	
	private List<Edge> neighbors;
	
	public static class Edge {
		Edge(int weight, Node node) {
			this.weight = weight;
			this.node = node;
		}
		public int weight;
		public Node node;
	}
	
	public List<Edge> getNeighbors() {
		return neighbors;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	public void addBranch(int weight, Node node)
    {
    	Edge newEdge = new Edge(weight, node);
    	neighbors.add(newEdge);
    }
	
	public double calculateHeuristic(Node target){
        return this.h;
	}
}
