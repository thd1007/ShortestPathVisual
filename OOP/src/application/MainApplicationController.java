package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import Element.Configuration;
import Element.Edge;
import Element.Vertext;
import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import algorithm.Dijkstra;

public class MainApplicationController implements Initializable {
	@FXML
	private Button AddVertextrb, AddEdgerb, MoveVertextrb, RemoveVertextrb, RemoveEdgerb;
	@FXML
	private Button DijkstraAlgo; // add by Minh
	@FXML 
	private AnchorPane MainPane;
	@FXML
	ToggleGroup MyButton;
	@FXML 
	Label myLabel;
	@FXML
	Button ResetButton;
	private boolean FlagaddEdge = false, FlagremoveEdge = false, FlagD = false;
	private Vertext tempVertextA = null, tempVertextB = null;
	
	private boolean isAddV = false;
	private boolean isAddE = false;
	private boolean isMoveV = false;
	private boolean isRemoveV = false;
	private boolean isRemoveE = false;
	private boolean isD = false; // add by Minh
	private int count = 0; // add by Minh
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		MainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		AddVertextrb.setTextFill(Color.RED);
		isAddV = true;
		AddVertextrb.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				AddVertextrb.setTextFill(Color.RED);
				AddEdgerb.setTextFill(Color.BLACK);
				MoveVertextrb.setTextFill(Color.BLACK);
				RemoveEdgerb.setTextFill(Color.BLACK);
				RemoveVertextrb.setTextFill(Color.BLACK);
				DijkstraAlgo.setTextFill(Color.BLACK);
				
				isAddV = true;
				isAddE = isMoveV = isRemoveV = isRemoveE = isD = false;
			}
			
		});
		AddEdgerb.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				AddEdgerb.setTextFill(Color.RED);
				AddVertextrb.setTextFill(Color.BLACK);
				MoveVertextrb.setTextFill(Color.BLACK);
				RemoveEdgerb.setTextFill(Color.BLACK);
				RemoveVertextrb.setTextFill(Color.BLACK);
				DijkstraAlgo.setTextFill(Color.BLACK);
				
				isAddE = true;
				isAddV = isMoveV = isRemoveV = isRemoveE = isD = false;
			}
			
		});
		MoveVertextrb.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				AddEdgerb.setTextFill(Color.BLACK);
				AddVertextrb.setTextFill(Color.BLACK);
				MoveVertextrb.setTextFill(Color.RED);
				RemoveEdgerb.setTextFill(Color.BLACK);
				RemoveVertextrb.setTextFill(Color.BLACK);
				DijkstraAlgo.setTextFill(Color.BLACK);
				
				isMoveV = true;
				isAddV = isAddE = isRemoveV = isRemoveE = isD = false;
			}
			
		});
		RemoveEdgerb.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				AddEdgerb.setTextFill(Color.BLACK);
				AddVertextrb.setTextFill(Color.BLACK);
				MoveVertextrb.setTextFill(Color.BLACK);
				RemoveEdgerb.setTextFill(Color.RED);
				RemoveVertextrb.setTextFill(Color.BLACK);
				DijkstraAlgo.setTextFill(Color.BLACK);
				
				isRemoveE = true;
				isAddV = isMoveV = isRemoveV = isAddE = isD = false;
			}
			
		});
		RemoveVertextrb.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				AddEdgerb.setTextFill(Color.BLACK);
				AddVertextrb.setTextFill(Color.BLACK);
				MoveVertextrb.setTextFill(Color.BLACK);
				RemoveEdgerb.setTextFill(Color.BLACK);
				RemoveVertextrb.setTextFill(Color.RED);
				DijkstraAlgo.setTextFill(Color.BLACK);
				
				isRemoveV = true;
				isAddV = isMoveV = isAddE = isRemoveE = isD = false;
			}
			
		});
		DijkstraAlgo.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				AddEdgerb.setTextFill(Color.BLACK);
				AddVertextrb.setTextFill(Color.BLACK);
				MoveVertextrb.setTextFill(Color.BLACK);
				RemoveEdgerb.setTextFill(Color.BLACK);
				RemoveVertextrb.setTextFill(Color.BLACK);
				DijkstraAlgo.setTextFill(Color.RED);
				
				isD = true;
				isRemoveV = isAddV = isMoveV = isAddE = isRemoveE = false;
			}
			
		});
	}
	// action function for radio button
	public void ActionEventFunc(ActionEvent e) {
		if(isAddV) {
			AddVertextFunction();
		}
		else if (isAddE) {
			AddEdgeFunction();
		}
		else if (isRemoveV) {
			RemoveVertextFunction();
		}
		else if(isMoveV) {
			MoveVertextFunction();
		}
		else if(isRemoveE) {
			RemoveEdgeFunction();
		}
		else if(isD) {
			DAlgo();
		}
	}
	
	// reset function
	public void ResetFunction(ActionEvent e) {
		MainPane.getChildren().clear();
		Configuration.GraphNode.clear();
		myLabel.setText("");
	}
	
	// Add Vertext Function 
	public void AddVertextFunction() {
		Configuration.allowMoveVertext = true;
		myLabel.setText("Add vertext by clicking into white scene ");
		MainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveEdgeHandler);
	}
	
	// Add Edge Function
	public void AddEdgeFunction() {
		Configuration.allowMoveVertext = false;
		myLabel.setText("Please choose an Vertext");
		MainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveEdgeHandler);
	}
	
	// Remove Vertext function
	public void RemoveVertextFunction() {
		Configuration.allowMoveVertext = true;
		myLabel.setText("Please choose an vertext to remove");
		MainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, RemoveVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveEdgeHandler);
	}
	
	
	// Move Vertext function
	public void MoveVertextFunction() {
		Configuration.allowMoveVertext = true;
		myLabel.setText("Drag mouse on an vertext to move");
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveEdgeHandler);
	}
	
	// Remove Edge function
	public void RemoveEdgeFunction() {
		Configuration.allowMoveVertext = true;
		myLabel.setText("Please choose an Vertext");
		MainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, RemoveEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddEdgeHandler);
	}
	
	// add vertext handler
	EventHandler<MouseEvent> AddVertextHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			double x = e.getX();
			double y = e.getY();
			myLabel.setText("Add vertext by clicking into white scene ");
			if(!Configuration.Nodeexist(x, y)) {
				Vertext newVertext = new Vertext(x, y);
				MainPane.getChildren().addAll(newVertext, newVertext.getText());
				Configuration.GraphNode.add(newVertext);
				myLabel.setText("Add Vertext succefully!!");
//				myLabel.setText("Add vertext by clicking into white scene ");
			}
		}
	};
	// add edge handler
	EventHandler<MouseEvent> AddEdgeHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.isSecondaryButtonDown()) return;
			myLabel.setText("Please choose an Vertext");
			double x = e.getX();
			double y = e.getY();
			for(Vertext vertext : Configuration.GraphNode) {
				if(vertext.checkClick(x, y)) {
					
					if(!FlagaddEdge) {
						System.out.println("add Edge handler");
						vertext.setFill(Color.GREEN);
						tempVertextA = vertext;
						myLabel.setText("Please choose another vertext to create edge");
						FlagaddEdge = true;
					}
					else {
						vertext.setFill(Color.GREEN);
						if(vertext.equals(tempVertextA)){
							FlagaddEdge = false;
							tempVertextA.setFill(Color.RED);
							myLabel.setText("Please choose an Vertext");
							return;
						}
						if(tempVertextA.isNeighborVertex(vertext)) {
							System.out.println("neighbor exists");
							vertext.setFill(Color.RED);
							return;
						}
						tempVertextB = vertext;
						Edge newEdge = new Edge(tempVertextA, tempVertextB);
						TextField curtext = newEdge.getText();
						curtext.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent e1) {
								// TODO Auto-generated method stub
								MainPane.requestFocus();
							}
						});
						MainPane.getChildren().addAll(newEdge, curtext);
						tempVertextA.addNeighbor(newEdge);
						tempVertextB.addNeighbor(newEdge);
						Configuration.GraphEdge.add(newEdge);
						myLabel.setText("Add Edge succesfully");
						tempVertextA.setFill(Color.RED);
						tempVertextB.setFill(Color.RED);
						FlagaddEdge = false;
					}
					break;
				}
			}
			
		}
		
	};
	
	EventHandler<MouseEvent> RemoveVertextHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			myLabel.setText("Please choose an vertext to remove");
			double x = e.getX();
			double y = e.getY();
			Vertext curVertext = Configuration.getNode(x, y);
			if(curVertext != null) {
				for(Edge edge: curVertext.getNeighbors()) {
					MainPane.getChildren().remove(edge.getText());
					Vertext anotherVertext = (edge.getEnd().equals(curVertext)) ? edge.getStart(): edge.getEnd();
					anotherVertext.removeNeighbor(edge);
					Configuration.RemoveEdge(edge);
				}
				MainPane.getChildren().removeAll(curVertext.getNeighbors());
				curVertext.removeAllNeighbor();
				MainPane.getChildren().remove(curVertext);
				MainPane.getChildren().remove(curVertext.getText());
				int id = curVertext.getid();
				Configuration.RemoveNode(id);
				myLabel.setText("Remove Vertext succefully!!");
			}
			
		}
	};
	// remove Edge
	EventHandler<MouseEvent> RemoveEdgeHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			myLabel.setText("Please choose an Vertext");
			double x = e.getX();
			double y = e.getY();
			for(Vertext vertext : Configuration.GraphNode) {
				if(vertext.checkClick(x, y)) {
					vertext.setFill(Color.GREEN);
					if(!FlagremoveEdge) {
						tempVertextA = vertext;
						myLabel.setText("Please choose another vertext to remove edge");
						FlagremoveEdge = true;
					}
					else {
						if(vertext.equals(tempVertextA)){
							FlagremoveEdge = false;
							tempVertextA.setFill(Color.RED);
							myLabel.setText("Please choose an Vertext");
							return;
						}
						if(!tempVertextA.isNeighborVertex(vertext)) {
							System.out.println("No neighbor exists");
							vertext.setFill(Color.RED);
							return;
						}
						tempVertextB = vertext;
						Edge edge = tempVertextB.getEdge(tempVertextA);
						MainPane.getChildren().removeAll(edge, edge.getText());
						tempVertextA.removeNeighbor(tempVertextB);
						tempVertextB.removeNeighbor(tempVertextA);
						Configuration.GraphEdge.remove(edge);
						myLabel.setText("Remove Edge succesfully");
						tempVertextA.setFill(Color.RED);
						tempVertextB.setFill(Color.RED);
						FlagremoveEdge = false;
					}
					break;
				}
			}
			
		}
		
	};
	
	public void DAlgo() {
		Configuration.allowMoveVertext = false;
		myLabel.setText("Dijkstra running. Press again for turn off");
		MainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, DAlgoHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddEdgeHandler);
	}
	
	EventHandler<MouseEvent>DAlgoHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			myLabel.setText("Please choose an start vertext");
			double x = e.getX();
			double y = e.getY();
			for(Vertext vertext : Configuration.GraphNode) {
				if(vertext.checkClick(x, y)) {
					vertext.setFill(Color.YELLOW);
					if(!FlagD) {
						tempVertextA = vertext;
						myLabel.setText("Please choose end vertext.");
						count++;
						FlagD = true;
					}
					else {
						if(vertext.equals(tempVertextA)){
							FlagD = false;
							tempVertextA.setFill(Color.RED);
							myLabel.setText("Please choose an start vertext");
							count = 0;
							return;
						}
						tempVertextB = vertext;
						count++;
						FlagD = false;
					}
					break;
				}
			}
			if(count == 2) {
				count = 0;
				SequentialTransition st = new SequentialTransition();
				Dijkstra.init(tempVertextA);
				
				// Dijkstra
				Dijkstra.pq.add(tempVertextA);
				while(!Dijkstra.pq.isEmpty()) {
					Vertext u = Dijkstra.pq.poll();
					FillTransition ft = new FillTransition(Duration.millis(5000), u);
                    ft.setToValue(Color.BLACK);
                    ft.play();
                    //st.getChildren().add(ft);
					
					for(Edge edge: u.getNeighbors()) {
						StrokeTransition ftEdge = new StrokeTransition(Duration.millis(1000), edge);
                        ftEdge.setToValue(Color.ORANGE);
                        ftEdge.play();
                        //st.getChildren().add(ftEdge);
                        
						Vertext v = edge.getStart() == u ? edge.getEnd() : edge.getStart();
						FillTransition ft1 = new FillTransition(Duration.millis(5000), v);
                        ft1.setToValue(Color.FORESTGREEN);
                        ft1.play();
                        //st.getChildren().add(ft1);
						if(edge.getWeight() + u.getF() < v.getF()) {
							v.setF(edge.getWeight() + u.getF());
							Dijkstra.pq.add(v);
						}
						//v.setFill(Color.RED);
						//edge.setFill(Color.BLUE);
					}
					//st.onFinishedProperty();
	                //st.play();
				}
				System.out.println(tempVertextB.getF());
			}
		}
	};
}
