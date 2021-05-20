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
				pq.add(v);
			}
		}
		Block step = new Block(Configuration.GraphEdge, Configuration.GraphNode);
		eachStep.add(step);
		curVertex.setFill(cur);
	}
	
	public static void run() {
		if(Configuration.startVertext == null || Configuration.endVertext == null) 
			return;
		
		initState();
		
		while(!pq.isEmpty()) {
			Vertext u = pq.poll();
			
			step(u);
		}
		System.out.println(Configuration.endVertext.getG());
	}
}
