package algorithm;
import java.util.PriorityQueue;

import Element.Configuration;
import Element.Edge;
import Element.Vertext;

public class Dijkstra {
	public static PriorityQueue<Vertext> pq = new PriorityQueue<Vertext>();
	
	public static void init(Vertext start) {
		pq.clear();
		for(Vertext v: Configuration.GraphNode) {
			if(start.getCenterX() == v.getCenterX() && start.getCenterY() == v.getCenterY()) {
				v.setF(0);
			}
			else {
				v.setF(Double.MAX_VALUE);
			}
		}
	}
	public static void shortestPath(Vertext start, Vertext end) {
		pq.add(start);
		while(!pq.isEmpty()) {
			Vertext u = pq.peek();
			pq.poll();
			
			for(Edge edge: u.getNeighbors()) {
				Vertext v = edge.getStart() == u ? edge.getEnd() : edge.getStart();
				if(edge.getWeight() + u.getF() < v.getF()) {
					v.setF(edge.getWeight() + u.getF());
					pq.add(v);
				}
			}
		}
		System.out.println(end.getF());
	}
	
}
