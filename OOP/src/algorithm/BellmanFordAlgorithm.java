package algorithm;
import  Element.*;
import java.util.Stack;
public class BellmanFordAlgorithm {
	private static Stack<Vertext> Stack1 = new Stack<>();// save path
	private static Stack<Vertext> Stack2 = new Stack<>();// save dfp
	public static void findpath() {
		if(Configuration.endVertext == null || Configuration.endVertext.getG() == Double.MAX_VALUE) {
			System.out.println("no path");
			return;
		}
		Stack2.push(Configuration.endVertext);
		while(!Stack2.isEmpty()) {
			Vertext curV = Stack2.pop();
			Stack1.push(curV);
			if(curV.equals(Configuration.startVertext)){
				System.out.println("Finished!!!");
				break;
			}
			boolean flag = false;
			for(Edge e: curV.getNeighbors()) {
				Vertext v1 = (curV.equals(e.getEnd()))? e.getStart(): e.getEnd();
				// check if this node in the path
				if(v1.getG() + e.getWeight() == curV.getG()) {
					Stack2.push(v1);
					flag = true;
				}
			}
			// if cant go far more from this node, pop it out
			if(!flag) {
				Stack1.pop();
			}
		}
		if(Stack1.isEmpty()) {
			System.out.println("There is no path");
		}
		else {
			System.out.println("PATH: ");
			while(!Stack1.isEmpty()) {
				System.out.println(Stack1.pop().getid());
			}
		}
	}
	public static void show() {
		if(Configuration.startVertext == null || Configuration.endVertext == null) {
			System.out.println("start end null");
			return;
		}
		int n = Configuration.GraphNode.size();
		for(Vertext vertext: Configuration.GraphNode) {
			if(vertext.equals(Configuration.startVertext)) {
				vertext.setG(0);
			}
			else {
				vertext.setG(Double.MAX_VALUE);
			}
		}
		for(int i = 1; i <= n-1; i++) {
			for(Edge e: Configuration.GraphEdge) {
				Vertext StartV = e.getStart();
				Vertext EndV = e.getEnd();
				int weight = e.getWeight();
				StartV.setG(Math.min(StartV.getG(), EndV.getG() + weight));
				EndV.setG(Math.min(EndV.getG(), StartV.getG() + weight));
			}
		}
		System.out.println(Configuration.endVertext.getG());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// set distance from start vertext to all vertext
		
	}

}
