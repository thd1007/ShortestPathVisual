package application;

import java.io.IOException;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class AlgorithmController {
	// For switching to main
		protected Parent root;
		protected Scene scene;
		protected Stage stage;
		
		// For button and label in Dijkstra Screen
		@FXML 
		protected Button backButton, pauseButton, startButton, preButton, nextButton, FirstStateButton, LastStateButton;
		@FXML
		protected AnchorPane MainPane;
		@FXML
		protected Label myLabel;
		// for using css
		// for background image
		protected AnchorPane anchorP;
		protected VBox vbox;
		protected AnchorPane myAnchor;
		
		@FXML
		protected Button helpme;
		
		// For showing to Screen
		protected int index = 1;
		protected Timeline timeline;
		public abstract void BackMain(javafx.event.ActionEvent e) throws IOException;
		public abstract void showMainPane(int idx);
}
