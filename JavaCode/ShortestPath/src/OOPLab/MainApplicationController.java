package OOPLab;

import java.net.URL;
import java.util.ResourceBundle;

import OOPLab.Element.Configuration;
import OOPLab.Element.Edge;
import OOPLab.Element.Vertext;
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

public class MainApplicationController implements Initializable {
	@FXML
	private Button AddVertextrb, AddEdgerb, MoveVertextrb, RemoveVertextrb, RemoveEdgerb, ChooseStartEndVertextrb;
	@FXML 
	private AnchorPane MainPane;
	@FXML
	ToggleGroup MyButton;
	@FXML 
	Label myLabel;
	@FXML
	Button ResetButton;
	private boolean FlagaddEdge = false, FlagremoveEdge = false, FlagStartEndVertext = false;
	private Vertext tempVertextA = null, tempVertextB = null;
	
	private boolean isAddV = false;
	private boolean isAddE = false;
	private boolean isMoveV = false;
	private boolean isRemoveV = false;
	private boolean isRemoveE = false;
	private boolean isChooseStartEndV = false;
	
	
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
				ChooseStartEndVertextrb.setTextFill(Color.BLACK);
				isAddV = true;
				isAddE = isMoveV = isRemoveV = isRemoveE = isChooseStartEndV = false;
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
				ChooseStartEndVertextrb.setTextFill(Color.BLACK);
				isAddE = true;
				isAddV = isMoveV = isRemoveV = isRemoveE = isChooseStartEndV = false;
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
				ChooseStartEndVertextrb.setTextFill(Color.BLACK);
				isMoveV = true;
				isAddV = isAddE = isRemoveV = isRemoveE = isChooseStartEndV = false;
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
				ChooseStartEndVertextrb.setTextFill(Color.BLACK);
				isRemoveE = true;
				isAddV = isMoveV = isRemoveV = isAddE = isChooseStartEndV = false;
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
				ChooseStartEndVertextrb.setTextFill(Color.BLACK);
				isRemoveV = true;
				isAddV = isMoveV = isAddE = isRemoveE = isChooseStartEndV = false;
			}
			
		});
		ChooseStartEndVertextrb.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				AddEdgerb.setTextFill(Color.BLACK);
				AddVertextrb.setTextFill(Color.BLACK);
				MoveVertextrb.setTextFill(Color.BLACK);
				RemoveEdgerb.setTextFill(Color.BLACK);
				RemoveVertextrb.setTextFill(Color.BLACK);
				ChooseStartEndVertextrb.setTextFill(Color.RED);
				isChooseStartEndV = true;
				isAddV = isMoveV = isAddE = isRemoveE = isRemoveV = false;
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
		else if(isChooseStartEndV) {
			ChooseStartEndVertext();
		}
	}
	
	// reset function
	public void ResetFunction(ActionEvent e) {
		MainPane.getChildren().clear();
		Configuration.GraphNode.clear();
		myLabel.setText("");
		AddVertextFunction();
		AddVertextrb.setTextFill(Color.RED);
		AddEdgerb.setTextFill(Color.BLACK);
		MoveVertextrb.setTextFill(Color.BLACK);
		RemoveEdgerb.setTextFill(Color.BLACK);
		RemoveVertextrb.setTextFill(Color.BLACK);
		ChooseStartEndVertextrb.setTextFill(Color.BLACK);
		isAddV = true;
		isAddE = isMoveV = isRemoveV = isRemoveE = isChooseStartEndV = false;

	}
	
	// Add Vertext Function 
	public void AddVertextFunction() {
		Configuration.allowMoveVertext = true;
		myLabel.setText("Add vertext by clicking into white scene ");
		MainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, ChooseStartEndVertextHandler);
	}
	
	// Add Edge Function
	public void AddEdgeFunction() {
		Configuration.allowMoveVertext = false;
		myLabel.setText("Please choose an Vertext");
		MainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, ChooseStartEndVertextHandler);
	}
	
	// Remove Vertext function
	public void RemoveVertextFunction() {
		Configuration.allowMoveVertext = true;
		myLabel.setText("Please choose an vertext to remove");
		MainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, RemoveVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, ChooseStartEndVertextHandler);
	}
	
	
	// Move Vertext function
	public void MoveVertextFunction() {
		Configuration.allowMoveVertext = true;
		myLabel.setText("Drag mouse on an vertext to move");
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, ChooseStartEndVertextHandler);
	}
	
	// Remove Edge function
	public void RemoveEdgeFunction() {
		Configuration.allowMoveVertext = true;
		myLabel.setText("Please choose an Vertext");
		MainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, RemoveEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, ChooseStartEndVertextHandler);
	}
	
	// Choose Start and End function
	public void ChooseStartEndVertext() {
		Configuration.allowMoveVertext = true;
		myLabel.setText("Please choose Start Vertext");
		MainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, ChooseStartEndVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddVertextHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, AddEdgeHandler);
		MainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, RemoveEdgeHandler);
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
	// choose start and end vertext
	EventHandler<MouseEvent> ChooseStartEndVertextHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			myLabel.setText("Please choose Start Vertext");
			double x = e.getX();
			double y = e.getY();
			for(Vertext vertext : Configuration.GraphNode) {
				if(vertext.checkClick(x, y)) {
					if(!FlagStartEndVertext) {
						vertext.setFill(Configuration.startColor);
						Configuration.startVertext = vertext;
						myLabel.setText("Please choose end Vertext");
						FlagStartEndVertext = true;
					}
					else {
						vertext.setFill(Configuration.endColor);
						if(vertext.equals(Configuration.startVertext)) {
							FlagStartEndVertext = false;
							Configuration.startVertext.setFill(Color.RED);
							myLabel.setText("Please choose Start Vertext");
							Configuration.startVertext = null;
							return;
						}
						Configuration.endVertext = vertext;
						Configuration.endVertext.setFill(Configuration.endColor);
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
}
