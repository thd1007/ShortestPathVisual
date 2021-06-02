package algorithm;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.swing.JOptionPane;

import Element.Block;
import Element.Configuration;
import Element.Edge;
import Element.Vertex;
import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Dijkstra {
	private static PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
	public static ArrayList<Block> eachStep = new ArrayList<Block>();
	
	private static void initState() {
		pq.clear();
		eachStep.clear();
		for(Vertex v: Configuration.GraphNode) {
			v.setmyParent(null);
			if(Configuration.startVertex.getCenterX() == v.getCenterX() && Configuration.startVertex.getCenterY() == v.getCenterY()) {
				v.setG(0);
			}
			else {
				v.setG(Double.MAX_VALUE);
			}
		}
		
		pq.add(Configuration.startVertex);
		Block init = new Block(Configuration.GraphEdge, Configuration.GraphNode);
		eachStep.add(init);
	}
	
	private static void step(Vertex curVertex) {
		Color cur = (Color) curVertex.getFill();
		curVertex.setFill(Color.BLACK);
		for(Edge edge: curVertex.getNeighbors()) {
			edge.setStroke(Color.GREEN);
			Vertex v = edge.getStart() == curVertex ? edge.getEnd() : edge.getStart();
			if(curVertex.getG() + edge.getWeight() < v.getG()) {
				v.setG(curVertex.getG() + edge.getWeight());
				v.setmyParent(curVertex);
				pq.add(v);
			}
		}
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
		
		while(!pq.isEmpty()) {
			Vertex u = pq.poll();
			
			step(u);
		}
		showPath(Configuration.endVertex);
		Block final_state = new Block(Configuration.GraphEdge, Configuration.GraphNode);
		eachStep.add(final_state);
		System.out.println(Configuration.endVertex.getG());
	}
}
