package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import Element.Configuration;
import Element.Edge;
import Element.Vertex;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApplicationController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane myAnchor;
	@FXML
	private AnchorPane pane;
	@FXML
	private VBox myVbox;
	@FXML
	private Button AddVertexrb, AddEdgerb, MoveVertexrb, RemoveVertexrb, 
				RemoveEdgerb, ChooseStartEndVertexrb, helpButton;
	@FXML 
	private AnchorPane MainPane;
	@FXML
	ToggleGroup MyButton;
	@FXML 
	Label myLabel;
	@FXML
	Button ResetButton;
	@FXML
	Button BFAButton;
	private boolean FlagaddEdge = false, FlagremoveEdge = false, FlagStartEndVertex = false;
	private Vertex tempVertexA = null, tempVertexB = null;
	
	private boolean isAddV = false;
	private boolean isAddE = false;
	private boolean isMoveV = false;
	private boolean isRemoveV = false;
	private boolean isRemoveE = false;
	private boolean isChooseStartEndV = false;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		show();
//		MainPane.addEventHandler(MouseEvent.MOUSE_PRESSED, AddVertexHandler);
//		AddVertexrb.setTextFill(Configuration.VertexColor);
//		isAddV = false;
		AddVertexrb.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				AddVertexrb.setTextFill(Configuration.VertexColor);
				AddEdgerb.setTextFill(Color.BLACK);
				MoveVertexrb.setTextFill(Color.BLACK);
				RemoveEdgerb.setTextFill(Color.BLACK);
				RemoveVertexrb.setTextFill(Color.BLACK);
				ChooseStartEndVertexrb.setTextFill(Color.BLACK);
				isAddV = true;
				isAddE = isMoveV = isRemoveV = isRemoveE = isChooseStartEndV = false;
			}
			
		});
		AddEdgerb.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				AddEdgerb.setTextFill(Configuration.VertexColor);
				AddVertexrb.setTextFill(Color.BLACK);
				MoveVertexrb.setTextFill(Color.BLACK);
				RemoveEdgerb.setTextFill(Color.BLACK);
				RemoveVertexrb.setTextFill(Color.BLACK);
				ChooseStartEndVertexrb.setTextFill(Color.BLACK);
				isAddE = true;
				isAddV = isMoveV = isRemoveV = isRemoveE = isChooseStartEndV = false;
			}
			
		});
		MoveVertexrb.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				AddEdgerb.setTextFill(Color.BLACK);
				AddVertexrb.setTextFill(Color.BLACK);
				MoveVertexrb.setTextFill(Configuration.VertexColor);
				RemoveEdgerb.setTextFill(Color.BLACK);
				RemoveVertexrb.setTextFill(Color.BLACK);
				ChooseStartEndVertexrb.setTextFill(Color.BLACK);
				isMoveV = true;
				isAddV = isAddE = isRemoveV = isRemoveE = isChooseStartEndV = false;
			}
			
		});
		RemoveEdgerb.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				AddEdgerb.setTextFill(Color.BLACK);
				AddVertexrb.setTextFill(Color.BLACK);
				MoveVertexrb.setTextFill(Color.BLACK);
				RemoveEdgerb.setTextFill(Configuration.VertexColor);
				RemoveVertexrb.setTextFill(Color.BLACK);
				ChooseStartEndVertexrb.setTextFill(Color.BLACK);
				isRemoveE = true;
				isAddV = isMoveV = isRemoveV = isAddE = isChooseStartEndV = false;
			}
			
		});
		RemoveVertexrb.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				AddEdgerb.setTextFill(Color.BLACK);
				AddVertexrb.setTextFill(Color.BLACK);
				MoveVertexrb.setTextFill(Color.BLACK);
				RemoveEdgerb.setTextFill(Color.BLACK);
				RemoveVertexrb.setTextFill(Configuration.VertexColor);
				ChooseStartEndVertexrb.setTextFill(Color.BLACK);
				isRemoveV = true;
				isAddV = isMoveV = isAddE = isRemoveE = isChooseStartEndV = false;
			}
			
		});
		ChooseStartEndVertexrb.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				AddEdgerb.setTextFill(Color.BLACK);
				AddVertexrb.setTextFill(Color.BLACK);
				MoveVertexrb.setTextFill(Color.BLACK);
				RemoveEdgerb.setTextFill(Color.BLACK);
				RemoveVertexrb.setTextFill(Color.BLACK);
				ChooseStartEndVertexrb.setTextFill(Configuration.VertexColor);
				isChooseStartEndV = true;
				isAddV = isMoveV = isAddE = isRemoveE = isRemoveV = false;
			}
		});
		helpButton.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Help.fxml"));
			try {
				Parent root1;
				root1 = loader.load();
				Stage stage1 = new Stage();
				stage1.setTitle("Help");
				Image icon = new Image("/application/dauhoi.jpg");
				stage1.getIcons().add(icon);
				stage1.setResizable(false);
				stage1.setScene(new Scene(root1));
				stage1.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	// action function for radio button
	public void ActionEventFunc(ActionEvent e) {
		if(isAddV) {
			AddVertexFunction();
		}
		else if (isAddE) {
			AddEdgeFunction();
		}
		else if (isRemoveV) {
			RemoveVertexFunction();
		}
		else if(isMoveV) {
			MoveVertexFunction();
		}
		else if(isRemoveE) {
			RemoveEdgeFunction();
		}
		else if(isChooseStartEndV) {
			ChooseStartEndVertex();
		}
	}
	// BellmanFord Algorithm
	public void BellmanFordAlgorithmFunction(ActionEvent e) throws IOException {
		if(!CheckStartEndVertex()) return;
		System.setProperty("user.dir", "BellmanFordAlgorithm");
		root = FXMLLoader.load(getClass().getResource("/application/BellmanFord/BellmanFordAlgorithm.fxml"));
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/astar.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void DijkstraAlgorithm(ActionEvent event) throws IOException{
		if(!CheckStartEndVertex()) return;
		System.setProperty("user.dir", "Dijkstra");
		root = FXMLLoader.load(getClass().getResource("/application/Dijkstra/DijkstraScreen.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/astar.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void aStarAlgorithm(ActionEvent event) throws IOException{
		if(!CheckStartEndVertex()) return;
		System.setProperty("user.dir", "aStar");
		root = FXMLLoader.load(getClass().getResource("/application/AStar/AStarAlgorithm.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/astar.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}	
	// reset function
	public void ResetFunction(ActionEvent e) {
		MainPane.getChildren().clear();
		Configuration.GraphNode.clear();
		myLabel.setText("");
		AddVertexFunction();
		AddVertexrb.setTextFill(Configuration.VertexColor);
		AddEdgerb.setTextFill(Color.BLACK);
		MoveVertexrb.setTextFill(Color.BLACK);
		RemoveEdgerb.setTextFill(Color.BLACK);
		RemoveVertexrb.setTextFill(Color.BLACK);
		ChooseStartEndVertexrb.setTextFill(Color.BLACK);
		isAddV = true;
		isAddE = isMoveV = isRemoveV = isRemoveE = isChooseStartEndV = false;

	}
	
	// Add Vertex Function 
	public void AddVertexFunction() {
		Configuration.allowMoveVertex = true;
		myLabel.setText("Add Vertex by clicking into white scene ");
		MainPane.addEventHandler(MouseEvent.MOUSE_PRESSED, AddVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, RemoveVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, RemoveEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, ChooseStartEndVertexHandler);
	}
	
	// Add Edge Function
	public void AddEdgeFunction() {
		if(!CheckCanAddEdge()) return;
		Configuration.allowMoveVertex = false;
		myLabel.setText("Please choose an Vertex");
		MainPane.addEventHandler(MouseEvent.MOUSE_PRESSED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, AddVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, RemoveVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, RemoveEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, ChooseStartEndVertexHandler);
	}
	
	// Remove Vertex function
	public void RemoveVertexFunction() {
		if(!CheckAnyVertex()) return;
		Configuration.allowMoveVertex = true;
		myLabel.setText("Please choose an Vertex to remove");
		MainPane.addEventHandler(MouseEvent.MOUSE_PRESSED, RemoveVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, AddVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, RemoveEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, ChooseStartEndVertexHandler);
	}
	
	
	// Move Vertex function
	public void MoveVertexFunction() {
		if(!CheckAnyVertex()) return;
		Configuration.allowMoveVertex = true;
		myLabel.setText("Drag mouse on an Vertex to move");
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, RemoveVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, AddVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, RemoveEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, ChooseStartEndVertexHandler);
	}
	
	// Remove Edge function
	public void RemoveEdgeFunction() {
		if(!CheckCanRemoveEdge()) return;
		Configuration.allowMoveVertex = false;
		myLabel.setText("Please choose an Vertex");
		MainPane.addEventHandler(MouseEvent.MOUSE_PRESSED, RemoveEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, RemoveVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, AddVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, ChooseStartEndVertexHandler);
	}
	
	// Choose Start and End function
	public void ChooseStartEndVertex() {
		if(!CheckAnyVertex()) return;
		if(Configuration.startVertex != null || Configuration.endVertex != null) {
			Configuration.startVertex.setFill(Color.RED);
			Configuration.endVertex.setFill(Color.RED);
			Configuration.startVertex = null;
			Configuration.endVertex = null;
		}
		Configuration.allowMoveVertex = false;
		myLabel.setText("Please choose Start Vertex");
		MainPane.addEventHandler(MouseEvent.MOUSE_PRESSED, ChooseStartEndVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, RemoveVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, AddVertexHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, RemoveEdgeHandler);
	}
	
	
	// add Vertex handler
	EventHandler<MouseEvent> AddVertexHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			double x = e.getX();
			double y = e.getY();
			myLabel.setText("Add Vertex by clicking into white scene ");
			if(e.isPrimaryButtonDown() && !Configuration.Nodeexist(x, y)) {
				System.out.println("new Vertex");
				Vertex newVertex = new Vertex(x, y);
				MainPane.getChildren().add(newVertex.getStack());
				Configuration.GraphNode.add(newVertex);
				myLabel.setText("Add Vertex succefully!!");
//				myLabel.setText("Add Vertex by clicking into white scene ");
			}
		}
	};
	// choose start and end Vertex
	EventHandler<MouseEvent> ChooseStartEndVertexHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			myLabel.setText("Please choose Start Vertex");
			double x = e.getX();
			double y = e.getY();
			for(Vertex Vertex : Configuration.GraphNode) {
				if(Vertex.checkClick(x, y)) {
					if(!FlagStartEndVertex) {
						Vertex.setFill(Configuration.startColor);
						Configuration.startVertex = Vertex;
						myLabel.setText("Please choose end Vertex");
						FlagStartEndVertex = true;
					}
					else {
						Vertex.setFill(Configuration.endColor);
						// if click to start Vertex again, refill both start and end Vertex
						if(Vertex.equals(Configuration.startVertex)) {
							FlagStartEndVertex = false;
							Configuration.startVertex.setFill(Configuration.VertexColor);
							if(Configuration.endVertex != null) {
								Configuration.endVertex.setFill(Configuration.VertexColor);
								Configuration.endVertex = null;
							}
							myLabel.setText("Please choose Start Vertex");
							Configuration.startVertex = null;
							return;
						}
						// if click to end Vertex again, refill only end Vertex
						else if(Configuration.endVertex != null && Vertex.equals(Configuration.endVertex)) {
							FlagStartEndVertex = true;
							Configuration.endVertex.setFill(Configuration.VertexColor);
							Configuration.endVertex = null;
							return;
						}
						Configuration.endVertex = Vertex;
						Configuration.endVertex.setFill(Configuration.endColor);
					}
				}
			}
		}
	};
	
	
	// add edge handler
	EventHandler<MouseEvent> AddEdgeHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			myLabel.setText("Please choose an Vertex");
			double x = e.getX();
			double y = e.getY();
			for(Vertex Vertex : Configuration.GraphNode) {
				if(Vertex.checkClick(x, y)) {
					
					if(!FlagaddEdge) {
						System.out.println("add Edge handler");
						Vertex.setFill(Configuration.ChoseVertexColor);
						tempVertexA = Vertex;
						myLabel.setText("Please choose another Vertex to create edge");
						FlagaddEdge = true;
					}
					else {
						Vertex.setFill(Configuration.ChoseVertexColor);
						if(Vertex.equals(tempVertexA)){
							FlagaddEdge = false;
							tempVertexA.setFill(Configuration.VertexColor);
							myLabel.setText("Please choose an Vertex");
							return;
						}
						if(tempVertexA.isNeighborVertex(Vertex)) {
							System.out.println("neighbor exists");
							Vertex.setFill(Configuration.VertexColor);
							return;
						}
						tempVertexB = Vertex;
						Edge newEdge = new Edge(tempVertexA, tempVertexB);
						TextField curtext = newEdge.getText();
						curtext.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent e1) {
								// TODO Auto-generated method stub
								MainPane.requestFocus();
							}
						});
						MainPane.getChildren().add(0, newEdge);
						MainPane.getChildren().add(curtext);
						tempVertexA.addNeighbor(newEdge);
						tempVertexB.addNeighbor(newEdge);
						Configuration.GraphEdge.add(newEdge);
						myLabel.setText("Add Edge succesfully");
						tempVertexA.setFill(Configuration.VertexColor);
						tempVertexB.setFill(Configuration.VertexColor);
						FlagaddEdge = false;
					}
					break;
				}
			}
			
		}
		
	};
	
	EventHandler<MouseEvent> RemoveVertexHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			// only left click
			if(e.isSecondaryButtonDown()) return;
			myLabel.setText("Please choose an Vertex to remove");
			double x = e.getX();
			double y = e.getY();
			Vertex curVertex = Configuration.getNode(x, y);
			if(curVertex != null) {
				for(Edge edge: curVertex.getNeighbors()) {
					MainPane.getChildren().remove(edge.getText());
					MainPane.getChildren().remove(edge);
					Vertex anotherVertex = (edge.getEnd().equals(curVertex)) ? edge.getStart(): edge.getEnd();
					anotherVertex.removeNeighbor(edge);
					Configuration.RemoveEdge(edge);
				}
//				MainPane.getChildren().removeAll(curVertex.getNeighbors());
				curVertex.removeAllNeighbor();
				MainPane.getChildren().remove(curVertex.getStack());
//				MainPane.getChildren().remove(curVertex.getText());
				int id = curVertex.getid();
				Configuration.RemoveNode(id);
				myLabel.setText("Remove Vertex succefully!!");
			}
			
		}
	};
	// remove Edge
	EventHandler<MouseEvent> RemoveEdgeHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			myLabel.setText("Please choose an Vertex");
			double x = e.getX();
			double y = e.getY();
			for(Vertex Vertex : Configuration.GraphNode) {
				if(Vertex.checkClick(x, y)) {
					Vertex.setFill(Configuration.ChoseVertexColor);
					if(!FlagremoveEdge) {
						tempVertexA = Vertex;
						myLabel.setText("Please choose another Vertex to remove edge");
						FlagremoveEdge = true;
					}
					else {
						// if press on Vertex A again
						if(Vertex.equals(tempVertexA)){
							FlagremoveEdge = false;
							tempVertexA.setFill(Configuration.VertexColor);
							myLabel.setText("Please choose an Vertex");
							return;
						}
						if(!tempVertexA.isNeighborVertex(Vertex)) {
							System.out.println("No neighbor exists");
							Vertex.setFill(Configuration.VertexColor);
							return;
						}
						tempVertexB = Vertex;
						Edge edge = tempVertexB.getEdge(tempVertexA);
						MainPane.getChildren().removeAll(edge, edge.getText());
						tempVertexA.removeNeighbor(tempVertexB);
						tempVertexB.removeNeighbor(tempVertexA);
						Configuration.GraphEdge.remove(edge);
						myLabel.setText("Remove Edge succesfully");
						tempVertexA.setFill(Configuration.VertexColor);
						tempVertexB.setFill(Configuration.VertexColor);
						FlagremoveEdge = false;
					}
					break;
				}
			}
			
		}
		
	};
	private void show() {
		if(Configuration.GraphNode.isEmpty()) return;
		for(Edge edge: Configuration.GraphEdge) {
			TextField curtext = edge.getText();
			curtext.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e1) {
					// TODO Auto-generated method stub
					MainPane.requestFocus();
				}
			});
			MainPane.getChildren().addAll(edge, curtext);
		}
		for(Vertex v: Configuration.GraphNode) {
			MainPane.getChildren().addAll(v.getStack());
		}
	}
	private boolean CheckStartEndVertex() {
		if(Configuration.startVertex == null || Configuration.endVertex == null) {
			JOptionPane.showMessageDialog(null,"Check again start and end Vertex");
			return false;
		}
		return true;
	}
	// use for check move can use or remove can use
	private boolean CheckAnyVertex() {
		if(Configuration.GraphNode.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No vertex here");
			return false;
		}
		return true;
	}
	// use for check add edge
	private boolean CheckCanAddEdge() {
		if(Configuration.GraphNode.size() < 2) {
			JOptionPane.showMessageDialog(null, "Check again the number of Vertex");
			return false;
		}
		return true;
	}
	// use for check can remove edge
	private boolean CheckCanRemoveEdge() {
		if(Configuration.GraphEdge.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No edge to remove");
			return false;
		}
		return true;
	}
}