package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Element.Block;
import Element.Configuration;
import Element.Edge;
import Element.Vertext;
import algorithm.Dijkstra;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DijkstraAlgorithmController implements Initializable {

	// For switching to main
	private Parent root;
	private Scene scene;
	private Stage stage;
	
	// For button and label in Dijkstra Screen
	@FXML 
	private Button backButton, pauseButton, startButton, preButton, nextButton, FirstStateButton, LastStateButton;
	@FXML
	private AnchorPane MainPane;
	@FXML
	private Label myLabel;
	
	// For showing to Screen
	private int index = 1;
	private Timeline timeline;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// run program
		if(Configuration.startVertext == null || Configuration.endVertext == null) {
			System.out.println("start end null");
			return;
		}
		Dijkstra.run();
		showMainPane(0);
		
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		Duration duration = Duration.millis(3000);
		KeyFrame keyframe = new KeyFrame(duration, event -> {
			if(index == Dijkstra.eachStep.size()) {
				myLabel.setText("Algorithm Finished !!!");
				timeline.stop();
				return;
			}
			showMainPane(index++);
		});
		timeline.getKeyFrames().add(keyframe);
		startButton.setOnAction(event -> {
			startButton.setTextFill(Color.RED);
			pauseButton.setTextFill(Color.BLACK);
			preButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.BLACK);
			nextButton.setTextFill(Color.BLACK);
			FirstStateButton.setTextFill(Color.BLACK);
			LastStateButton.setTextFill(Color.BLACK);
			myLabel.setText("Algorithm running !!!");
			timeline.play();
		});
		pauseButton.setOnAction(event -> {
			startButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.RED);
			preButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.BLACK);
			nextButton.setTextFill(Color.BLACK);
			FirstStateButton.setTextFill(Color.BLACK);
			LastStateButton.setTextFill(Color.BLACK);
			myLabel.setText("Algorithm paused");
			timeline.pause();
		});
		preButton.setOnAction(event -> {
			startButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.BLACK);
			preButton.setTextFill(Color.RED);
			pauseButton.setTextFill(Color.BLACK);
			nextButton.setTextFill(Color.BLACK);
			FirstStateButton.setTextFill(Color.BLACK);
			LastStateButton.setTextFill(Color.BLACK);
			if(index == 0) {
				myLabel.setText("There is no previous step");
				return;
			}
			timeline.stop();
			showMainPane(--index);
		});
		nextButton.setOnAction(event -> {
			startButton.setTextFill(Color.RED);
			pauseButton.setTextFill(Color.BLACK);
			preButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.BLACK);
			nextButton.setTextFill(Color.RED);
			FirstStateButton.setTextFill(Color.BLACK);
			LastStateButton.setTextFill(Color.BLACK);
			myLabel.setText("Next Step");
			if(index == Dijkstra.eachStep.size()) {
				myLabel.setText("There is no more step");
				return;
			}
			timeline.stop();
			showMainPane(index++);
		});
		FirstStateButton.setOnAction(event -> {
			startButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.BLACK);
			preButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.BLACK);
			nextButton.setTextFill(Color.BLACK);
			FirstStateButton.setTextFill(Color.RED);
			LastStateButton.setTextFill(Color.BLACK);
			timeline.stop();
			showMainPane(0);
			index = 1;
		});
		LastStateButton.setOnAction(event -> {
			startButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.BLACK);
			preButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.BLACK);
			nextButton.setTextFill(Color.BLACK);
			FirstStateButton.setTextFill(Color.BLACK);
			LastStateButton.setTextFill(Color.RED);
			timeline.stop();
			index = Dijkstra.eachStep.size()-1;
			showMainPane(index);
		});
	}
	
	public void BackMain(ActionEvent e) throws IOException {
		// Draw again in Main
		for(Edge edge: Configuration.GraphEdge) {
			edge.setStroke(Color.BLUE);
		}
		for(Vertext v: Configuration.GraphNode) {
			v.setFill(Color.RED);
			v.setG(Double.MAX_VALUE);
		}
		
		Configuration.startVertext = null;
		Configuration.endVertext = null;
		
		String dir = "MainApplication.fxml";
		root = FXMLLoader.load(getClass().getResource(dir));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void showMainPane(int idx) {
		MainPane.getChildren().clear();
		Block block = Dijkstra.eachStep.get(idx);
		for(Edge edge: block.listEdge) {
			MainPane.getChildren().addAll(edge, edge.getLabel());
		}
		for(Vertext vertext: block.listNode) {
			MainPane.getChildren().addAll(vertext, vertext.getText(), vertext.getTextg());
		}
	}
}
