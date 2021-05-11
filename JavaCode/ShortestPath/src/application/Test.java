package application;

import elements.Configuration;
import elements.Edge;
import elements.Vector;
import elements.Vertice;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application{
	
	@Override
	public void start(Stage stage){
		
		Scene scene = new Scene(root, 800, 600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		stage.setScene(scene);
		stage.setTitle("PathFinding");
		stage.getIcons().add(new Image("icon.jpg"));
		stage.setResizable(false);
		
		initSetUp();
		funcWork();
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	private SplitPane root = new SplitPane();
	private SplitPane menu = new SplitPane();
	private AnchorPane functions = new AnchorPane();
	private AnchorPane algorithms = new AnchorPane();
	private AnchorPane graph = new AnchorPane();
	private VBox vbox1, vbox2;
	private Button addV;
	private Button addE;
	private Button addW;
	private Button removeV;
	private Button removeE;
	private Button reset;
	private Button dijkstra;
	private Button bellman;
	private Button aStar;
	private boolean isAddV = false;
	private boolean isAddE = false;
	private boolean isAddW = false;
	private boolean isRemoveV = false;
	private boolean isRemoveE = false;
	private Text text;
	private int count = 0;
	
	public Button createButton(String title, double width, double height) {
		Button button = new Button(title);
		button.setPrefSize(width, height);
		return button;
	}
	private void initSetUp() {
		// Button for functions
		addV = createButton("Add Vertex", 100, 30);
		addE = createButton("Add Edge", 100, 30);
		addW = createButton("Add Weight", 100, 30);
		removeV = createButton("Remove Vertex", 100, 30);
		removeE = createButton("Remove Edge", 100, 30);
		reset = createButton("Reset", 100, 30);
		
     	// Button for algorithm
		dijkstra = createButton("Dijkstra", 100, 30);
		bellman = createButton("Bellman - Ford", 100, 30);
		aStar = createButton("A*", 100, 30);
				
		// Vbox1
		vbox1 = new VBox(30);
		vbox1.getChildren().addAll(addV, addE, addW, removeV, removeE, reset);
		AnchorPane.setTopAnchor(vbox1, 30.0);
		AnchorPane.setLeftAnchor(vbox1, 60.0);
			
		functions.getChildren().add(vbox1);
			
		// Vbox2
		vbox2 = new VBox(30);
		vbox2.getChildren().addAll(dijkstra, bellman, aStar);
		AnchorPane.setTopAnchor(vbox2, 30.0);
		AnchorPane.setLeftAnchor(vbox2, 60.0);
		
		// Text
		text = new Text();
		text.setFont(Font.font("New Courier", 14));
		text.setFill(Color.RED);
		AnchorPane.setTopAnchor(text, 30.0);
		AnchorPane.setLeftAnchor(text, 60.0);
		
		graph.getChildren().add(text);
				
		algorithms.getChildren().add(vbox2);
						
		menu.setOrientation(Orientation.VERTICAL);
		menu.getItems().addAll(functions, algorithms);
		menu.setDividerPositions(0.65, 0.35);
				
		root.getItems().add(menu);
		root.getItems().add(graph);
		root.setDividerPositions(0.3, 0.7);
	}
	private void funcWork() {
		Vertice vertice[] = new Vertice[2];
		addV.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(!isAddV) {
					addV.setTextFill(Color.RED);
					isAddV = true;
					
					addE.setTextFill(Color.BLACK);
					isAddE = false;
					removeV.setTextFill(Color.BLACK);
					isRemoveV = false;
					removeE.setTextFill(Color.BLACK);
					isRemoveE = false;
					isAddW = false;
					addW.setTextFill(Color.BLACK);
					
					text.setText("Click to this screen to insert Vertex.");
					text.setVisible(isAddV);
				}
				else {
					addV.setTextFill(Color.BLACK);
					isAddV = false;
					text.setVisible(isAddV);
				}
			}
		});
		graph.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if(isAddV) {
					if(!Configuration.Nodeexist(e.getX(), e.getY())) {
						Vertice v = new Vertice(e.getX(), e.getY());
						v.setFill(Color.RED);
						graph.getChildren().add(v);
						Configuration.GraphNode.add(v);
					}
				}
				if(isAddE) {
					for(Vertice v: Configuration.GraphNode) {
						if(v.checkClick(e.getX(), e.getY())) {
							v.setFill(Color.YELLOW);
							vertice[count] = v;
							count++;
						}
					}
					if(count == 2) {
						Edge line = new Edge(vertice[0], vertice[1]);
						line.setStrokeWidth(3);
						graph.getChildren().addAll(line, line.getText());
						Configuration.GraphEdge.add(line);
						vertice[0].setFill(Color.RED);
						vertice[1].setFill(Color.RED);
						vertice[0].addNeighbor(line);
						vertice[1].addNeighbor(line);
						count = 0;
					}
				}
				if(isRemoveV) {
					int idx = 0, re = -1;
					for(Vertice v: Configuration.GraphNode) {
						if(v.checkClick(e.getX(), e.getY())) {
							for(Edge edge: v.getNeighbors()) {
								graph.getChildren().removeAll(edge, edge.getText());
								Configuration.GraphEdge.remove(edge);
								if(v.equals(edge.getStart())) {
									edge.getEnd().removeNeighbor(edge);
								}
								else if(v.equals(edge.getEnd())){
									edge.getStart().removeNeighbor(edge);
								}
							}
							v.getNeighbors().clear();
							graph.getChildren().remove(v);
							re = idx;
						}
						idx++;
					}
					if(re != -1) {
						Configuration.GraphNode.remove(re);
					}
					if(Configuration.GraphNode.isEmpty()) {
						text.setText("No vertex to remove.");
					}
				}
				if(isRemoveE) {
					int idx = 0, re = -1;
					for(Edge edge: Configuration.GraphEdge) {
						Vector v1 = new Vector(new Vertice(e.getX(), e.getY()), edge.getStart());
						Vector v2 = new Vector(edge.getEnd(), edge.getStart());
						v1.calcIndex();
						v2.calcIndex();
						if(v1.Inline(v2)) {
							graph.getChildren().removeAll(edge, edge.getText());
							re = idx;
						}
						idx++;
					}
					if(re != -1) {
						Configuration.GraphEdge.remove(re);
					}
					if(Configuration.GraphEdge.isEmpty()) {
						text.setText("No edge to remove");
					}
				}
				if(isAddW) {
					TextInputDialog textDialog = new TextInputDialog();
					textDialog.setHeaderText("Weight: ");
					for(Edge edge: Configuration.GraphEdge) {
						Vector v1 = new Vector(new Vertice(e.getX(), e.getY()), edge.getStart());
						Vector v2 = new Vector(edge.getEnd(), edge.getStart());
						v1.calcIndex();
						v2.calcIndex();
						if(v1.Inline(v2)) {
							textDialog.showAndWait();
							edge.setWeight(Integer.parseInt(textDialog.getEditor().getText()));
							edge.getText().setText(textDialog.getEditor().getText());
						}
					}
				}
			}
		});
		addE.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(!isAddE) {
					addE.setTextFill(Color.RED);
					isAddE = true;
					
					addV.setTextFill(Color.BLACK);
					isAddV = false;
					removeV.setTextFill(Color.BLACK);
					isRemoveV = false;
					removeE.setTextFill(Color.BLACK);
					isRemoveE = false;
					isAddW = false;
					addW.setTextFill(Color.BLACK);
					
					if(!Configuration.GraphNode.isEmpty()) {
						text.setText("Click to two vertex to insert Edge.");
						text.setVisible(isAddE);
					}
					else {
						text.setText("No vertex to create Edge.");
						text.setVisible(isAddE);
					}
				}
				else {
					addE.setTextFill(Color.BLACK);
					isAddE = false;
					text.setVisible(isAddE);
				}
			}
		});
		reset.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				isAddE = false;
				isAddV = false;
				isRemoveV = false;
				isRemoveE = false;
				isAddW = false;
				
				addW.setTextFill(Color.BLACK);
				addV.setTextFill(Color.BLACK);
				addE.setTextFill(Color.BLACK);
				removeV.setTextFill(Color.BLACK);
				removeE.setTextFill(Color.BLACK);
				
				Configuration.GraphEdge.clear();
				Configuration.GraphNode.clear();
				graph.getChildren().clear();
				text.setVisible(false);
				graph.getChildren().add(text);
			}
			
		});
		removeV.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if(!isRemoveV) {
					isRemoveV = true;
					removeV.setTextFill(Color.RED);
					
					isAddE = false;
					addE.setTextFill(Color.BLACK);
					isAddV = false;
					addV.setTextFill(Color.BLACK);
					isRemoveE = false;
					removeE.setTextFill(Color.BLACK);
					isAddW = false;
					addW.setTextFill(Color.BLACK);
					
					if(Configuration.GraphNode.isEmpty()) {
						text.setText("No vertex to remove.");
						text.setVisible(isRemoveV);
					}
					else {
						text.setText("Click vertex to remove it.");
						text.setVisible(isRemoveV);
					}
				}
				else {
					removeV.setTextFill(Color.BLACK);
					isRemoveV = false;
					text.setVisible(isRemoveV);
				}
			}
			
		});
		removeE.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				if(!isRemoveE) {
					isRemoveE = true;
					removeE.setTextFill(Color.RED);
					
					isAddE = false;
					addE.setTextFill(Color.BLACK);
					isAddV = false;
					addV.setTextFill(Color.BLACK);
					isRemoveV = false;
					removeV.setTextFill(Color.BLACK);
					isAddW = false;
					addW.setTextFill(Color.BLACK);
					
					if(Configuration.GraphEdge.isEmpty()) {
						text.setText("No edge to remove.");
						text.setVisible(isRemoveE);
					}
					else {
						text.setText("Click edge to remove it.");
						text.setVisible(isRemoveE);
					}
				}
				else {
					removeE.setTextFill(Color.BLACK);
					isRemoveE = false;
					text.setVisible(isRemoveE);
				}
			}
			
		});
		addW.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(!isAddW) {
					isAddW = true;
					addW.setTextFill(Color.RED);
					
					isAddV = false;
					addV.setTextFill(Color.BLACK);
					isAddE = false;
					addE.setTextFill(Color.BLACK);
					isRemoveE = false;
					removeE.setTextFill(Color.BLACK);
					isRemoveV = false;
					removeV.setTextFill(Color.BLACK);
					
					if(Configuration.GraphEdge.isEmpty()) {
						text.setText("No edge to add weight.");
						text.setVisible(isAddW);
					}
					else {
						text.setText("Click edge to add weight.");
						text.setVisible(isAddW);
					}
				}
				else {
					isAddW = false;
					addW.setTextFill(Color.BLACK);
					text.setVisible(isAddW);
				}
			}
			
		});
	}
}
