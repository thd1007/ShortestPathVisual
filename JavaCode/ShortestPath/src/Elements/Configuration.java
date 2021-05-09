package OOPLab.Element;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Circle;

public class Configuration {
	public static int maxWeight = 1000;
	public static int minWeight = 1;
	public static List<Edge> GraphEdge = new ArrayList<Edge>();
	public static List<Vertext> GraphNode = new ArrayList<Vertext>();
	public static boolean Nodeexist(double x, double y) {
		for(Vertext vertext: GraphNode) {
			if(x == vertext.getCenterX() && y == vertext.getCenterY()) {
				return true;
			}
		}
		return false;
	}
}
