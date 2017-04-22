package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.MainFields;

import java.io.IOException;

import javafx.event.ActionEvent;

public class EditController {
	@FXML
	private TextField indexField;
	@FXML
	private TextField valueField;
	@FXML
	private Button btnConfirm;
	// Event Listener on Button.onAction
	@FXML
	public void editClick(ActionEvent event) {
		String index = indexField.getText();
		String value = valueField.getText();
		
		if (MainFields.getDataType() == "Float") {
			int indexInt = Integer.valueOf(index); 
			try {
				float valFloat = Float.valueOf(value);
				MainFields.getSampleDataFloat().set(indexInt - 1, valFloat);
			} catch (java.lang.NumberFormatException nfe) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Critical Error");
				alert.setHeaderText("Invalid Input!!!");
				alert.setContentText("Ooops, input is not allowed! Please change the input.");
				alert.showAndWait();
			} catch (java.lang.RuntimeException re) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Critical Error");
				alert.setHeaderText("Invalid Input!!!");
				alert.setContentText("Ooops, input is not allowed! Please change the input.");
				alert.showAndWait();
			}
		} else {
			try {
				int indexInt = Integer.valueOf(index); 
				int valInt = Integer.valueOf(value);
				MainFields.getSampleDataInt().set(indexInt - 1, valInt);
			} catch (java.lang.NumberFormatException nfe) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Critical Error");
				alert.setHeaderText("Invalid Input!!!");
				alert.setContentText("Ooops, input is not allowed! Please change the input.");
				alert.showAndWait();
			} catch (java.lang.RuntimeException re) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Critical Error");
				alert.setHeaderText("Invalid Input!!!");
				alert.setContentText("Ooops, input is not allowed! Please change the input.");
				alert.showAndWait();
			} 
		}
		indexField.clear();
		valueField.clear();
	}
	// Event Listener on Button.onAction
	@FXML
	public void confirmClick(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnConfirm.getScene().getWindow();
		
		Stage ownerStage = (Stage) stage.getOwner();
		Parent root = FXMLLoader.load(getClass().getResource("/view/Data.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/bloodcrimson.css");
		ownerStage.setScene(scene);
		ownerStage.setResizable(false);
		ownerStage.show();
		stage.close();
	}
}
