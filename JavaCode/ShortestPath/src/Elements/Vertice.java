package elements;

import java.util.ArrayList;

public class Vertice implements Comparable<Vertice>{
	private static int counterId = 0;
	private double x; // index x on screen
	private double y; // index y on screen
	private double id; // id of vertice on screen
	private ArrayList<Edge> neighbors = new ArrayList<Edge>();
	
	// A* variable by Dang
	private double f = Double.MAX_VALUE;
	private double g = Double.MAX_VALUE;
	// Heuristic
	private double h;
	
	Vertice(double x, double y){
		this.x = x;
		this.y = y;
		this.id = counterId++;
	};
	
	public double getX(){
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public double getId() {
		return this.id;
	}
	public void setG(double g) {
		this.g = g;
	}
	public void setF(double f) {
		this.f = f;
	}
	public void setH(double h) {
		this.h = h;
	}
	public double getG() {
		return g;
	}
	public double getF() {
		return f;
	}
	public double getH() {
		return h;
	}
	// method
	@Override
	public int compareTo(Vertice n) {
		return Double.compare(this.f, n.getF());
	}
	public void removeNeighbor(Edge e) {
		this.neighbors.remove(e);
	}
	public void addNeighbor(Edge e) {
		this.neighbors.add(e);
	}
}
