package OOPLab.Element;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge extends Line {
	private static int idCounter = 0;
	private int ID;
	private int weight;
	private Vertext start, end;
	// constructor Edge without parameter
//	public Edge() {
//		super();
//		this.setStroke(Color.BLUE);
//		this.weight = new Random().nextInt(Configuration.maxWeight - Configuration.minWeight) + Configuration.minWeight;
//	}
	
	// constructor Edge with parameters
	public Edge(Vertext start, Vertext end) {
		super(start.getCenterX(), start.getCenterY(), end.getCenterX(), end.getCenterY());
		ID = idCounter++;
		this.setStart(start);
		this.setEnd(end);
		this.setStroke(Color.BLUE);
		this.weight = new Random().nextInt(Configuration.maxWeight - Configuration.minWeight) + Configuration.minWeight;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Vertext getStart() {
		return start;
	}
	public void setStart(Vertext start) {
		this.start = start;
	}
	public Vertext getEnd() {
		return end;
	}
	public void setEnd(Vertext end) {
		this.end = end;
	}
	public int getID() {
		return ID;
	}
	public void setId(int id) {
		this.ID = id;
	}
	
}
