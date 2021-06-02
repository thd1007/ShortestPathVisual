package algorithm;
import  Element.*;
import java.util.Stack;
public class BellmanFordAlgorithm {
	private static Stack<Vertex> Stack1 = new Stack<>();// save path
	private static Stack<Vertex> Stack2 = new Stack<>();// save dip
	public static void findpath() {
		if(Configuration.endVertex == null || Configuration.endVertex.getG() == Double.MAX_VALUE) {
			System.out.println("no path");
			return;
		}
		Stack2.push(Configuration.endVertex);
		while(!Stack2.isEmpty()) {
			Vertex curV = Stack2.pop();
			Stack1.push(curV);
			if(curV.equals(Configuration.startVertex)){
				System.out.println("Finished!!!");
				break;
			}
			boolean flag = false;
			for(Edge e: curV.getNeighbors()) {
				Vertex v1 = (curV.equals(e.getEnd()))? e.getStart(): e.getEnd();
				// check if this node in the path
				if(v1.getG() + e.getWeight() == curV.getG()) {
					Stack2.push(v1);
					flag = true;
				}
			}
			// if can't go far more from this node, pop it out
			if(!flag) {
				Stack1.pop();
			}
		}
		if(Stack1.isEmpty()) {
			System.out.println("There is no path");
		}
		else {
			System.out.println("PATH: ");
			while(!Stack1.isEmpty()) {
				System.out.println(Stack1.pop().getid());
			}
		}
	}
	public static void show() {
		if(Configuration.startVertex == null || Configuration.endVertex == null) {
			System.out.println("start end null");
			return;
		}
		int n = Configuration.GraphNode.size();
		for(Vertex Vertex: Configuration.GraphNode) {
			if(Vertex.equals(Configuration.startVertex)) {
				Vertex.setG(0);
			}
			else {
				Vertex.setG(Double.MAX_VALUE);
			}
		}
		for(int i = 1; i <= n-1; i++) {
			for(Edge e: Configuration.GraphEdge) {
				int weight = e.getWeight();
				Vertex StartV = e.getStart();
				Vertex EndV = e.getEnd();
				StartV.setG(Math.min(StartV.getG(), EndV.getG() + weight));
				EndV.setG(Math.min(EndV.getG(), StartV.getG() + weight));
			}
		}
		System.out.println(Configuration.endVertex.getG());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// set distance from start vertex to all vertex
		
	}

}
