package application.BellmanFord;

import  Element.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import Element.Configuration.*;
import application.AlgorithmController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
public class BellmanFordAlgorithmController extends AlgorithmController implements Initializable {
//	private Stage stage;
//	private Scene scene;
//	private Parent root;
	private static Stack<Vertext> Stack1 = new Stack<>();// save path
	private static Stack<Vertext> Stack2 = new Stack<>();// save dfp
	private int n, m;
//	
//	@FXML 
//	Button backButton, pauseButton, startButton, preButton, nextButton, FirstStateButton, LastStateButton;
//	@FXML
//	AnchorPane MainPane;
//	@FXML
//	Label myLabel;
//	
//	
//	private int index = 1;
//	private Timeline timeline;
	private void findpath(Block curBlock) {
		Vertext endVertext = curBlock.endVertext();
		Vertext startVertext = curBlock.startVertext();
		if(endVertext == null || endVertext.getG() == Double.MAX_VALUE) {
			System.out.println("no path");
			return;
		}
		Stack2.push(endVertext);
		while(!Stack2.isEmpty()) {
			Vertext curV = Stack2.pop();
			Stack1.push(curV);
			if(curV.equals(startVertext)){
				System.out.println("Finished!!!");
				break;
			}
			boolean flag = false;
			for(Edge e: curBlock.listEdge) {
				// if curV not in edge then skip
				if(!curV.equals(e.getEnd()) && !curV.equals(e.getStart())) continue;
				Vertext v1 = (curV.equals(e.getEnd()))? e.getStart(): e.getEnd();
				// check if this node in the path
				if(v1.getG() + e.getWeight() == curV.getG()) {
					Stack2.push(v1);
					flag = true;
				}
			}
			// if can t go far more from this node, pop it out
			if(!flag) {
				Stack1.pop();
			}
		}
		int count = 0;
		Vertext ver1 = null;
		while(!Stack1.isEmpty())
		{	
			Vertext ver = Stack1.pop();
			
			if(ver.getFill().equals(Color.RED)) {
				ver.setFill(Color.GREEN);
			}
			if(ver1 != null) {
				for(Edge edge: curBlock.listEdge) {
					if((edge.getStart().equals(ver1) && edge.getEnd().equals(ver)) || 
							(edge.getStart().equals(ver) && edge.getEnd().equals(ver1))){
						edge.setStroke(Color.DEEPPINK);
					}
				}
			}
			ver1 = ver;
		}
		ConfigurationBFA.listBFA.add(curBlock);
	}
	private void show() {
		ConfigurationBFA.listBFA.clear();
		// initialize start vertext g
		Configuration.startVertext.setG(0);
		// create block
		Block cur = new Block(Configuration.GraphEdge, Configuration.GraphNode);
		ConfigurationBFA.listBFA.add(cur.CopyBlock());

		for(int i = 1; i <= n-1; i++) {
			for(Edge e: cur.listEdge) {
				int weight = e.getWeight();
				Vertext StartV = e.getStart();
				Vertext EndV = e.getEnd();
				StartV.setG(Math.min(StartV.getG(), EndV.getG() + weight));
				EndV.setG(Math.min(EndV.getG(), StartV.getG() + weight));
				e.setStroke(Color.GREEN);;
				ConfigurationBFA.listBFA.add(cur.CopyBlock());
				e.setStroke(Configuration.color_edge); //Color.BLUE
			}
		}
		System.out.println(cur.endVertext().getG());
		findpath(cur);
		//Block final_state = new Block(Configuration.GraphEdge, Configuration.GraphNode);
		//ConfigurationBFA.listBFA.add(final_state);
		Configuration.startVertext.setG(Double.MAX_VALUE);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// run program
		// set color of progress bar
		progressbar.setStyle("-fx-accent: #00ff00;");
		if(Configuration.startVertext == null || Configuration.endVertext == null) {
			System.out.println("start end null");
			return;
		}
		n = Configuration.GraphNode.size();
		m = Configuration.GraphEdge.size();
		show();
		showMainPane(0);
		step_number.setText("0 / " + String.valueOf(ConfigurationBFA.listBFA.size()-1));
		index = 0;
		helpme.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/BellmanFord/HelpBell.fxml"));
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
		
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		Duration duration = Duration.millis(3000);
		KeyFrame keyframe = new KeyFrame(duration, e -> {
			if(index == ConfigurationBFA.listBFA.size()-1) {
				myLabel.setText("Algorithm Finished !!!");
				timeline.stop();
				return;
			}
			progressbar.setProgress((double)(index+1)/(ConfigurationBFA.listBFA.size()-1));
			step_number.setText(String.format("%d / %d", index+1, ConfigurationBFA.listBFA.size()-1));
			showMainPane(++index);
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
			myLabel.setText("Previous Step");
			if(index == 0) {
				myLabel.setText("There is no previous step");
				return;
			}
			progressbar.setProgress((double)(index-1)/(ConfigurationBFA.listBFA.size()-1));
			step_number.setText(String.format("%d / %d", index-1, ConfigurationBFA.listBFA.size()-1));
			showMainPane(--index);
			timeline.stop();
		});
		nextButton.setOnAction(event -> {
			startButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.BLACK);
			preButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.BLACK);
			nextButton.setTextFill(Color.RED);
			FirstStateButton.setTextFill(Color.BLACK);
			LastStateButton.setTextFill(Color.BLACK);
			myLabel.setText("Next Step");
			if(index == ConfigurationBFA.listBFA.size()-1) {
				myLabel.setText("There is no more step");
				return;
			}
			progressbar.setProgress((double)(index+1)/(ConfigurationBFA.listBFA.size()-1));
			step_number.setText(String.format("%d / %d", index+1, ConfigurationBFA.listBFA.size()-1));
			showMainPane(++index);
			timeline.stop();
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
			progressbar.setProgress(0);
			step_number.setText(String.format("%d / %d", 0, ConfigurationBFA.listBFA.size()-1));
			showMainPane(0);
			index = 0;
		});
		LastStateButton.setOnAction(e -> {
			startButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.BLACK);
			preButton.setTextFill(Color.BLACK);
			pauseButton.setTextFill(Color.BLACK);
			nextButton.setTextFill(Color.BLACK);
			FirstStateButton.setTextFill(Color.BLACK);
			LastStateButton.setTextFill(Color.RED);
			timeline.stop();
			index = ConfigurationBFA.listBFA.size()-1;
			step_number.setText(String.format("%d / %d", index, ConfigurationBFA.listBFA.size()-1));
			progressbar.setProgress(1.0);
			showMainPane(index);
		});
	}
	
	@Override
	public void BackMain(ActionEvent e) throws IOException {
		// Draw again in Main
				for(Edge edge: Configuration.GraphEdge) {
					edge.setStroke(Configuration.color_edge); // Color.BLUE
				}
				for(Vertext v: Configuration.GraphNode) {
					v.setFill(Color.RED);
					v.setG(Double.MAX_VALUE);
				}
				Configuration.startVertext.setFill(Configuration.startColor);;
				Configuration.endVertext.setFill(Configuration.endColor);
				
		String dir = "/application/MainApplication.fxml";
		root = FXMLLoader.load(getClass().getResource(dir));
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	@Override
	public void showMainPane(int idx) {
		MainPane.getChildren().clear();
		Block block = ConfigurationBFA.listBFA.get(idx);
		for(Edge edge: block.listEdge) {
			MainPane.getChildren().addAll(edge, edge.getLabel());
		}
		for(Vertext vertext: block.listNode) {
			MainPane.getChildren().addAll(vertext.getStack(), vertext.getTextg());
		}
	}
	
}