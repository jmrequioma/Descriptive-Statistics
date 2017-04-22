package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import main.MainFields;

public class ChoiceMenuController implements Initializable {
	@FXML
	private CheckBox mean;
	@FXML
	private CheckBox all;
	@FXML
	private CheckBox median;
	@FXML
	private CheckBox mode;
	@FXML
	private Button btnConfirm;
	private ArrayList<CheckBox> choices = new ArrayList<CheckBox>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initChoices(choices);
		
	}
	
	private void initChoices(ArrayList<CheckBox> choices) {
		choices.add(mean);
		choices.add(median);
		choices.add(mode);
	}
	// Event Listener on CheckBox[#mean].onAction
	@FXML
	public void hasChoice(ActionEvent event) {
		all.setSelected(false);
	}
	// Event Listener on CheckBox[#all].onAction
	@FXML
	public void allClicked(ActionEvent event) {
		if(all.isSelected()) {
			for(CheckBox choice : choices) {
				choice.setSelected(false);
			}
		} else {
			all.setSelected(true);
		}
	}
	@FXML
	public void confirmClick(ActionEvent event) throws IOException {
		if (all.isSelected()) {
			MainFields.setMean(true);
			MainFields.setMedian(true);
			MainFields.setMode(true);
		} else {
			if (mean.isSelected()) {
				MainFields.setMean(true);
			}
			if (median.isSelected()) {
				MainFields.setMedian(true);
			}
			if (mode.isSelected()) {
				MainFields.setMode(true);
			}
		}
		Parent root = FXMLLoader.load(getClass().getResource("/view/Output.fxml"));
		Stage stage = (Stage) btnConfirm.getScene().getWindow();
		stage.setTitle("Output");
		stage.setResizable(false);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/bloodcrimson.css");
		stage.setScene(scene);
		stage.show();
	}
}
