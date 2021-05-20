package Element;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Vertext extends Circle implements Comparable<Vertext> {
	private int id; 
	private double radius = Configuration.radius;
	private Text text;
	private Label textg;
	
	// A* variable by Dang
	private double f = Double.MAX_VALUE;
	private double g = Double.MAX_VALUE;
		// Heuristic
	private double h;
	
	private ArrayList<Edge> neighbors = new ArrayList<>();
	public Vertext(double x, double y) {
		super(x, y, Configuration.radius, Configuration.VertextColor);
		id = Configuration.GraphNode.size();
		text= new Text(String.valueOf(id));
		textg = new Label("INF");
		textg.setTextFill(Color.RED);
		text.setLayoutX(this.getCenterX() + radius);
		text.setLayoutY(this.getCenterY());
		// mouse dragged event for Vertext
		this.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				if(Configuration.allowMoveVertext) {
					double x = e.getX(), y = e.getY();
					System.out.println("mouse dragged");
					setFill(Color.GREEN);
					setCenterX(x);
					setCenterY(y);
					text.setLayoutX(getCenterX() + radius);
					text.setLayoutY(getCenterY());
				}
				
			}
		});
		this.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				if(Configuration.allowMoveVertext) {
					System.out.println("Mouse release");
					setFill(Configuration.VertextColor);
				}
			}
		});

	}
	public Vertext(double x, double y, int id, double h, double g, double f, String text, String textg, Paint color) {
		super(x, y, Configuration.radius, color);
		this.id = id;
		this.h = h;
		this.g = g;
		this.f = f;
		this.text = new Text(text);
		this.textg = new Label(textg);
		this.textg.setTextFill(Color.RED);
		this.textg.setStyle(Configuration.textColor);
		this.text.setLayoutX(this.getCenterX() + radius);
		this.text.setLayoutY(this.getCenterY());
		this.textg.setLayoutX(this.getCenterX());
		this.textg.setLayoutY(this.getCenterY() + radius);
	}
	// copy vertext
	public Vertext CopyVertext() {
		return new Vertext(this.getCenterX(), this.getCenterY(), this.getid(), this.h, this.g, this.f, text.getText(), textg.getText(), this.getFill());
	}
	public void setId(int id) {
		text.setText(String.valueOf(id));
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
	public boolean equals(Vertext b) {
		// TODO Auto-generated method stub
		return (b.getCenterX() == this.getCenterX() && b.getCenterY() == this.getCenterY());
	}
	@Override
	public int compareTo(Vertext n) {
		// TODO Auto-generated method stub
		return Double.compare(this.f, n.getF());
	}
	// remove neighbors function
	public void removeNeighbor(Edge... es) {
		for(Edge e: es) {
			this.neighbors.remove(e);
		}
	}
	// add neighbor function
	public void addNeighbor(Edge ... es) {
		for(Edge e: es) {
			this.neighbors.add(e);
		}
	}
	
	/// remove neighbor by Vertext
	public void removeNeighbor(Vertext vertext) {
		for(Edge edge: neighbors) {
			if(edge.getStart().equals(vertext) || edge.getEnd().equals(vertext)) {
				this.neighbors.remove(edge);
				break;
			}
		}
	}
	// check if neighbor vertex has already exist
	public boolean isNeighborVertex(Vertext vertext) {
		for(Edge edge: neighbors) {
			if(edge.getStart().equals(vertext) || edge.getEnd().equals(vertext)) {
				return true;
			}
		}
		return false;
	}
	// remove all neighbors of this vertext
	public void removeAllNeighbor() {
		for(Edge edge: neighbors) {
			Vertext curVertext = (edge.getEnd().equals(this)) ? edge.getStart():edge.getEnd();
			curVertext.removeNeighbor(this);
		}
	}
	
	
	// get all neighbors
	public ArrayList<Edge> getNeighbors(){
		return neighbors;
	}
	
	// get edge corresponding vertext
	public Edge getEdge(Vertext vertext) {
		for(Edge edge: neighbors) {
			if(edge.getStart().equals(vertext) || edge.getEnd().equals(vertext)) {
				return edge;
			}
		}
		return null;
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
		if(g == Double.MAX_VALUE) textg.setText("INF");
		else textg.setText(String.valueOf(g));
		this.g = g;
	}
	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}
	public Text getText() {
		return text;
	}
	public Label getTextg() {
		return textg;
	}

}
