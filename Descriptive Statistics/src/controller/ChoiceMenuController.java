package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.CheckBox;

public class ChoiceMenuController implements Initializable {
	@FXML
	private CheckBox mean;
	@FXML
	private CheckBox all;
	@FXML
	private CheckBox median;
	@FXML
	private CheckBox mode;
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
}
