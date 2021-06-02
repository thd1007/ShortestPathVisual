package Element;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class Configuration {
	
	// text color in vertex
	public static Color text_color = Color.WHITE;
	// font color of text in vertex
	public static Font text_font = Font.font(15);
	// Edge line with
	public static double Linewidth = 3;
	// color Vertex
	public static Paint VertexColor = Color.RED;
	// chose color Vertex
	public static Paint ChoseVertexColor = Color.GREEN;
	// allow using mouse to move Vertex
	public static boolean allowMoveVertex = true;
	// start color is blue
	public static Paint startColor = Color.BLUE;
	
	// start end Vertex 
	public static Vertex startVertex = null;
	public static Vertex endVertex = null;
	// end color is yellow
	public static Paint endColor = Color.web("#ffb42e");
	public static String textColor = "-fx-text-inner-color: red";
	
	public static int maxWeight = 300;
	public static int minWeight = 1;
	
	public static double textWeightwidth = 35, textWeighheight = 10;
	public static double radius = 15;
	public static List<Edge> GraphEdge = new ArrayList<Edge>();
	public static List<Vertex> GraphNode = new ArrayList<Vertex>();
	public static boolean Nodeexist(double x, double y) {
		for(Vertex Vertex: GraphNode) {
			double x1 = Vertex.getCenterX(), y1 = Vertex.getCenterY();
			double temp = Math.sqrt(Math.pow(x-x1, 2) + Math.pow(y-y1, 2));
			if(temp <= 2 * radius) {
				return true;
			}
		}
		return false;
	}
	
	public static Vertex getNode(double x, double y) {
		for(Vertex Vertex: GraphNode) {
			double x1 = Vertex.getCenterX(), y1 = Vertex.getCenterY();
			double temp = Math.sqrt(Math.pow(x-x1, 2) + Math.pow(y-y1, 2));
			if(temp <= 2 * radius) {
				return Vertex;
			}
		}
		return null;
	}
	public static void RemoveNode(int VertexId) {
		int curId = 0;
		GraphNode.remove(VertexId);
		for(Vertex Vertex: GraphNode) {
			Vertex.setId(curId++);
		}
		
	}
	public static void RemoveEdge(Edge edge) {
		int curId = 0;
		GraphEdge.remove(edge);
		for(Edge curedge: GraphEdge) {
			curedge.setId(curId++);
		}
	}
}
