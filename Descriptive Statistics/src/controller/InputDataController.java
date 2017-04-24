package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import main.MainFields;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;

public class InputDataController {
	@FXML
	private TextField txtFSampleData;
	@FXML
	private Button btnAddItem;
	@FXML
	private Button btnConfirmSampleData;
	private ArrayList<Float> sampleDatasFloat = new ArrayList<Float>();
	private ArrayList<Integer> sampleDatasInt = new ArrayList<Integer>();
	
	// Event Listener on Button[#btnAddItem].onAction
	@FXML
	public void addItemClick(ActionEvent event) {
		String dataType = MainFields.getDataType();
		String input = txtFSampleData.getText();
		if (dataType == "Float") {
			try {
				float inputFloat = Float.valueOf(input);
				sampleDatasFloat.add(inputFloat);
				txtFSampleData.clear();
				System.out.println(inputFloat);
			} catch (java.lang.RuntimeException re) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Critical Error");
				alert.setHeaderText("Invalid Input!!!");
				alert.setContentText("Ooops, input is not allowed! Please change the input.");
				alert.showAndWait();
			}
			MainFields.setSampleDataFloat(sampleDatasFloat);
		} else { 
			try {
				int inputInt = Integer.valueOf(input);
				sampleDatasInt.add(inputInt);
				txtFSampleData.clear();
				System.out.println(inputInt);
			} catch (java.lang.RuntimeException re) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Critical Error");
				alert.setHeaderText("Invalid Input!!!");
				alert.setContentText("Ooops, input is not allowed! Please change the input.");
				alert.showAndWait();
			}
			MainFields.setSampleDataInt(sampleDatasInt);
		}
		
	}
	// Event Listener on Button[#btnConfirmSampleData].onAction
	@FXML
	public void confirmSampleDataClick(ActionEvent event) throws IOException {
		String dataType = MainFields.getDataType();
		if (dataType == "Float") {
			if (MainFields.getSampleDataFloat().size() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Critical Error");
				alert.setHeaderText("Invalid Click!!!");
				alert.setContentText("Please add inputs.");
				alert.showAndWait();
			} else {
				Parent root = FXMLLoader.load(getClass().getResource("/view/Data.fxml"));
				Stage stage = (Stage) btnConfirmSampleData.getScene().getWindow();
				stage.setTitle("Data");
				stage.setResizable(false);
				Scene scene = new Scene(root);
				scene.getStylesheets().add("/theme/bloodcrimson.css");
				stage.setScene(scene);
				stage.show();
			}
		} else {
			if (MainFields.getSampleDataInt().size() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Critical Error");
				alert.setHeaderText("Invalid Click!!!");
				alert.setContentText("Please add inputs.");
				alert.showAndWait();
			} else {
				Parent root = FXMLLoader.load(getClass().getResource("/view/Data.fxml"));
				Stage stage = (Stage) btnConfirmSampleData.getScene().getWindow();
				stage.setTitle("Data");
				stage.setResizable(false);
				Scene scene = new Scene(root);
				scene.getStylesheets().add("/theme/bloodcrimson.css");
				stage.setScene(scene);
				stage.show();
			}
		}
	}
}
