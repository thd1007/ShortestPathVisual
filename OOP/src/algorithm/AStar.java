package algorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;

import Element.Block;
import Element.Configuration;
import Element.Edge;
import Element.Vertex;
import javafx.scene.paint.Color;

public class AStar {

	private static PriorityQueue<Vertex> closedList = new PriorityQueue<>();
	private static PriorityQueue<Vertex> openList = new PriorityQueue<>();
	public static ArrayList<Block> eachStep = new ArrayList<Block>();
	
	private static void initState() {
		openList.clear();
		closedList.clear();
		eachStep.clear();
		for(Vertex v: Configuration.GraphNode) {
			v.setmyParent(null);
			if(Configuration.startVertex.getCenterX() == v.getCenterX() && Configuration.startVertex.getCenterY() == v.getCenterY()) {
				v.setG(0);
				v.setF(0);
				v.setH(v.calculateHeuristic(Configuration.endVertex));
			}
			else {
				v.setG(Double.MAX_VALUE);
				v.setF(0);
				v.setH(v.calculateHeuristic(Configuration.endVertex));
			}
		}
		
		openList.add(Configuration.startVertex);
		Block init = new Block(Configuration.GraphEdge, Configuration.GraphNode);
		eachStep.add(init);
	}
	
	private static void step(Vertex curVertex) {
		Color cur = (Color) curVertex.getFill();
		curVertex.setFill(Color.BLACK);
		for(Edge edge: curVertex.getNeighbors()) {
			edge.setStroke(Color.GREEN);
			Vertex v = edge.getStart() == curVertex ? edge.getEnd() : edge.getStart();
			double totalWeight = curVertex.getG() + edge.getWeight();
			if (!openList.contains(v) && !closedList.contains(v)) {
				v.setG(totalWeight);
				v.setF(totalWeight + v.getH());
				v.setmyParent(curVertex);
				openList.add(v);
			}
			else {
				if (totalWeight < v.getG()) {
					v.setG(totalWeight);
					v.setF(totalWeight + v.getH());
					v.setmyParent(curVertex);
					if(closedList.contains(v))
					{
						closedList.remove(v);
						openList.add(v);
					}
				}
			}
		}
		openList.remove(curVertex);
		closedList.add(curVertex);
		Block step = new Block(Configuration.GraphEdge, Configuration.GraphNode);
		eachStep.add(step);
		curVertex.setFill(cur);
	}
	private static void showPath(Vertex v) {
		if(v.myParent() == null) return;
		for(Edge edge: Configuration.GraphEdge) {
			Vertex start = edge.getStart();
			Vertex end = edge.getEnd();
			if((start.equals(v) && end.equals(v.myParent())) || (start.equals(v.myParent()) && end.equals(v))){
				edge.setStroke(Color.DEEPPINK);
			}
		}
		showPath(v.myParent());
	}
	public static void run() {
		if(Configuration.startVertex == null || Configuration.endVertex == null) 
			return;
		
		initState();
		
		while(!openList.isEmpty()) {
			Vertex u = openList.peek();
			
			step(u);
		}
		showPath(Configuration.endVertex);
		Block final_state = new Block(Configuration.GraphEdge, Configuration.GraphNode);
		eachStep.add(final_state);
		System.out.println(Configuration.endVertex.getF());
	}

}
