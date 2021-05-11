package elements;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Vertice extends Circle implements Comparable<Vertice> {
	private static int idCounter = 0;
	private int id; 
	
	// x, y is center coordinate in Circle class
	
	// A* variable by Dang
		private double f = Double.MAX_VALUE;
		private double g = Double.MAX_VALUE;
		// Heuristic
		private double h;
	
	private ArrayList<Edge> neighbors = new ArrayList<Edge>();
	public Vertice(double x, double y, double radius) {
		super(x, y, radius, Color.RED);
		id = idCounter++;
	}
	public Vertice(double x, double y) {
		super(x, y, 10.0, Color.RED);
		id = idCounter++;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getid() {
		return id;
	}
	// check if mouse click to vertex
	public boolean checkClick(double x, double y) {
		double a = Math.pow((this.getCenterX() - x), 2);
		double b = Math.pow((this.getCenterY() - y), 2);
		return (a + b <= Math.pow(this.getRadius(), 2));
	}
	public boolean equals(Vertice b) {
		// TODO Auto-generated method stub
		return (b.getCenterX() == this.getCenterX() && b.getCenterY() == this.getCenterY());
	}
	@Override
	public int compareTo(Vertice n) {
		// TODO Auto-generated method stub
		return Double.compare(this.f, n.getF());
	}
	// remove neighbor function
	public void removeNeighbor(Edge e) {
		this.neighbors.remove(e);
	}
	// add neighbor function
	public void addNeighbor(Edge e) {
		this.neighbors.add(e);
	}
	
	public double getF() {
		return f;
	}
	public void setF(double f) {
		this.f = f;
	}
	public double getG() {
		return g;
	}
	public void setG(double g) {
		this.g = g;
	}
	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}
	public ArrayList<Edge> getNeighbors() {
		return this.neighbors;
	}

}