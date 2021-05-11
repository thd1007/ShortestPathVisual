package elements;

public class Vector {
	private Vertice start;
	private Vertice end;
	
	private double x;
	private double y;
	
	public Vector(Vertice start, Vertice end){
		this.start = start;
		this.end = end;
	}
	
	// method
	public void calcIndex() {
		this.x = start.getCenterX() - end.getCenterX();
		this.y = start.getCenterY() - end.getCenterY();
	}
	public boolean Inline(Vector other) {
		if(Math.abs((this.x / other.x) - (this.y/other.y)) < 0.3) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
