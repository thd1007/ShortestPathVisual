package elements;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Edge extends Line {
	private static int idCounter = 0;
	private int ID;
	private int weight;
	private Vertice start, end;
	private Text text = new Text();
	// constructor Edge without parameter
//	public Edge() {
//		super();
//		this.setStroke(Color.BLUE);
//		this.weight = new Random().nextInt(Configuration.maxWeight - Configuration.minWeight) + Configuration.minWeight;
//	}
	
	// constructor Edge with parameters
	public Edge(Vertice start, Vertice end) {
		super(start.getCenterX(), start.getCenterY(), end.getCenterX(), end.getCenterY());
		ID = idCounter++;
		this.setStart(start);
		this.setEnd(end);
		this.setStroke(Color.BLUE);
		this.weight = new Random().nextInt(Configuration.maxWeight - Configuration.minWeight) + Configuration.minWeight;
		
		this.text.setText(Integer.toString((this.weight)));
		this.text.setX((start.getCenterX() + end.getCenterX())/2);
		this.text.setY((start.getCenterY() + end.getCenterY())/2);
		text.setFont(Font.font("New Courier", 16));
		
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Vertice getStart() {
		return start;
	}
	public void setStart(Vertice start) {
		this.start = start;
	}
	public Vertice getEnd() {
		return end;
	}
	public void setEnd(Vertice end) {
		this.end = end;
	}
	public int getID() {
		return ID;
	}
	public void setId(int id) {
		this.ID = id;
	}	
	public Text getText() {
		return this.text;
	}
}