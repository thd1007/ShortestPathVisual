package elements;

import java.util.ArrayList;
import java.util.List;

//import javafx.scene.shape.Circle;

public class Configuration {
	public static int maxWeight = 100;
	public static int minWeight = 1;
	public static List<Edge> GraphEdge = new ArrayList<Edge>();
	public static List<Vertice> GraphNode = new ArrayList<Vertice>();
	public static boolean Nodeexist(double x, double y) {
		for(Vertice vertext: GraphNode) {
			double a = Math.pow((vertext.getCenterX() - x), 2);
			double b = Math.pow((vertext.getCenterY() - y), 2);
			if(a + b <= Math.pow(vertext.getRadius()*3.5, 2)) {
				return true;
			}
		}
		return false;
	}
}

