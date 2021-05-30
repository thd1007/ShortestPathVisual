package algorithm;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.PriorityQueue;

import Element.Block;
import Element.Configuration;
import Element.Edge;
import Element.Vertext;
import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Dijkstra {
	private static PriorityQueue<Vertext> pq = new PriorityQueue<Vertext>();
	public static ArrayList<Block> eachStep = new ArrayList<Block>();
	
	private static void initState() {
		pq.clear();
		eachStep.clear();
		for(Vertext v: Configuration.GraphNode) {
			v.setmyParent(null);
			if(Configuration.startVertext.getCenterX() == v.getCenterX() && Configuration.startVertext.getCenterY() == v.getCenterY()) {
				v.setG(0);
			}
			else {
				v.setG(Double.MAX_VALUE);
			}
		}
		
		pq.add(Configuration.startVertext);
		Block init = new Block(Configuration.GraphEdge, Configuration.GraphNode);
		eachStep.add(init);
	}
	
	private static void step(Vertext curVertex) {
		Color cur = (Color) curVertex.getFill();
		curVertex.setFill(Color.BLACK);
		for(Edge edge: curVertex.getNeighbors()) {
			edge.setStroke(Color.GREEN);
			Vertext v = edge.getStart() == curVertex ? edge.getEnd() : edge.getStart();
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
	private static void showPath(Vertext v) {
		if(v.myParent() == null) return;
		for(Edge edge: Configuration.GraphEdge) {
			Vertext start = edge.getStart();
			Vertext end = edge.getEnd();
			if((start.equals(v) && end.equals(v.myParent())) || (start.equals(v.myParent()) && end.equals(v))){
				edge.setStroke(Color.PINK);
			}
		}
		showPath(v.myParent());
	}
	public static void run() {
		if(Configuration.startVertext == null || Configuration.endVertext == null) 
			return;
		
		initState();
		
		while(!pq.isEmpty()) {
			Vertext u = pq.poll();
			
			step(u);
		}
		showPath(Configuration.endVertext);
		Block final_state = new Block(Configuration.GraphEdge, Configuration.GraphNode);
		eachStep.add(final_state);
		System.out.println(Configuration.endVertext.getG());
	}
}
