package OOPLab.Element;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Configuration {
	
	// Edge line with
	public static double Linewidth = 3;
	// color vertext
	public static Paint VertextColor = Color.RED;
	// chose color vertext
	public static Paint ChoseVertextColor = Color.GREEN;
	// allow using mouse to move vertext
	public static boolean allowMoveVertext = true;
	// start color is blue
	public static Paint startColor = Color.BLUE;
	
	// start end Vertext 
	public static Vertext startVertext = null;
	public static Vertext endVertext = null;
	// end color is yellow
	public static Paint endColor = Color.YELLOW;
	public static String textColor = "-fx-text-inner-color: red";
	
	public static int maxWeight = 300;
	public static int minWeight = 1;
	
	public static double textWeightwidth = 35, textWeighheight = 10;
	public static double radius = 15;
	public static List<Edge> GraphEdge = new ArrayList<Edge>();
	public static List<Vertext> GraphNode = new ArrayList<Vertext>();
	public static boolean Nodeexist(double x, double y) {
		for(Vertext vertext: GraphNode) {
			double x1 = vertext.getCenterX(), y1 = vertext.getCenterY();
			double temp = Math.sqrt(Math.pow(x-x1, 2) + Math.pow(y-y1, 2));
			if(temp <= 2 * radius) {
				return true;
			}
		}
		return false;
	}
	
	public static Vertext getNode(double x, double y) {
		for(Vertext vertext: GraphNode) {
			double x1 = vertext.getCenterX(), y1 = vertext.getCenterY();
			double temp = Math.sqrt(Math.pow(x-x1, 2) + Math.pow(y-y1, 2));
			if(temp <= 2 * radius) {
				return vertext;
			}
		}
		return null;
	}
	public static void RemoveNode(int VertextId) {
		int curId = 0;
		GraphNode.remove(VertextId);
		for(Vertext vertext: GraphNode) {
			vertext.setId(curId++);
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
