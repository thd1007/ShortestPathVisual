package elements;

public class Edge {
	private static int counterId = 0;
	private Vertice source;
	private Vertice target;
	private int id;
	private double weight;
	
	Edge(Vertice source, Vertice target){
		this.source = source;
		this.target = target;
		this.id = counterId++;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Vertice getSource() {
		return source;
	}

	public Vertice getTarget() {
		return target;
	}

	public int getId() {
		return id;
	}
	
	
}
