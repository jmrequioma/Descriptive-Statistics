package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MainFields;

public class DataController implements Initializable {
	@FXML
	private TextArea txtASampleData;
	@FXML
	private Button btnContinue;
	@FXML
	private Button btnEdit;
	@FXML
	private Label lblTitle;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblTitle.setText(MainFields.getTitle());
		if (MainFields.getDataType() == "Float") {
			for (int i = 0; i < MainFields.getSampleDataFloat().size(); i++) {
				txtASampleData.appendText("{Index: " + (i + 1) + " ; Data: " + MainFields.getSampleDataFloat().get(i) + "} ");
			}
		} else {
			for (int i = 0; i < MainFields.getSampleDataInt().size(); i++) {
				txtASampleData.appendText("{Index: " + (i + 1) + " ; Data: " + MainFields.getSampleDataInt().get(i) + "} ");
			}
		}
		
	}
	
	// Event Listener on Button[#btnContinue].onAction
	@FXML
	public void continueClick(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ChoiceMenu.fxml"));
		Stage stage = (Stage) btnContinue.getScene().getWindow();
		stage.setTitle("Choices");
		stage.setResizable(false);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/bloodcrimson.css");
		stage.setScene(scene);
		stage.show();
	}
	// Event Listener on Button[#btnEdit].onAction
	@FXML
	public void editClick(ActionEvent event) throws IOException {
		Button src = (Button) event.getSource();
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/view/Edit.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/bloodcrimson.css");
		stage.setTitle("Edit Data");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(src.getScene().getWindow());
		stage.showAndWait();
	}
}
