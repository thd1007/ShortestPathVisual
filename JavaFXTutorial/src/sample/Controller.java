package sample;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
public class Controller {

	@FXML
	AnchorPane graph;
	Vertex vertexTemp;
	Vertex vertexDelete;
	//graph events
	public void onGraphPressed(MouseEvent mouseEvent) {
		if (mouseEvent.isPrimaryButtonDown()) {
			createAndAddVertex(mouseEvent.getX(), mouseEvent.getY());
		}
	}
	public void onGraphDragDetected(MouseEvent mouseEvent) {
		if (mouseEvent.isPrimaryButtonDown()) {
			vertexTemp = (Vertex) createAndAddVertex(mouseEvent.getX(), mouseEvent.getY());
		}
	}
	public void onGraphDragged(MouseEvent mouseEvent) {
		if (vertexTemp != null) {
			vertexTemp.setLayoutX(mouseEvent.getX());
			vertexTemp.setLayoutY(mouseEvent.getY());
		}
	}
	
	public void onGraphReleased(MouseEvent mouseEvent) {
		vertexTemp = null;
	}
	
	//vertex events
	private void onVertexPressed(MouseEvent mouseEvent, Vertex vertex) {
		vertexDelete = vertex;
	}
	private void onVertexDragDetected(MouseEvent mouseEvent, Button vertex) {
		vertex.toFront();
		vertexDelete = null;
		if (mouseEvent.isPrimaryButtonDown()) {
			vertexTemp = (Vertex) createAndAddVertex(vertex.getLayoutX() + mouseEvent.getX() + vertex.getTranslateX(), 
													vertex.getLayoutY() + mouseEvent.getY() + vertex.getTranslateY());
		}
	}
	
	private void onVertexDragged(MouseEvent mouseEvent, Button vertex) {
		if (vertexTemp != null) {
			vertexTemp.setLayoutX(vertex.getLayoutX() + mouseEvent.getX() + vertex.getTranslateX());
			vertexTemp.setLayoutY(vertex.getLayoutY() + mouseEvent.getY() + vertex.getTranslateY());
		}
		if (mouseEvent.isSecondaryButtonDown()) {
			vertex.setLayoutX(vertex.getLayoutX() + mouseEvent.getX() + vertex.getTranslateX());
			vertex.setLayoutY(vertex.getLayoutY() + mouseEvent.getY() + vertex.getTranslateY());
		}

	}
	
	private void onVertexReleased(MouseEvent mouseEvent, Vertex vertex) {
		if (vertexDelete != null) {
			graph.getChildren().remove(vertexDelete);
		}
		vertexTemp = null;
		vertexDelete = null;
	}
	//helper methods
	private Node createAndAddVertex(Double x, Double y) {
		Vertex vertex = new Vertex(x, y);
		
		vertex.setOnMousePressed(mouseEvent -> onVertexPressed(mouseEvent, vertex));
		vertex.setOnDragDetected(mouseEvent -> onVertexDragDetected(mouseEvent, vertex));
		vertex.setOnMouseDragged(mouseEvent -> onVertexDragged(mouseEvent, vertex));
		vertex.setOnMouseReleased(mouseEvent -> onVertexReleased(mouseEvent, vertex));
		graph.getChildren().add(vertex);
		return vertex;
	}
	
}
