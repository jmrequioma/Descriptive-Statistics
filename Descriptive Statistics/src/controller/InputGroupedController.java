package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.MainFields;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.RadioButton;

public class InputGroupedController implements Initializable {
	@FXML
	private TextField txtFInputK;
	@FXML
	private RadioButton closedRBtn;
	@FXML
	private ToggleGroup groupedChoices;
	@FXML
	private RadioButton fOpenRBtn;
	@FXML
	private RadioButton lOpenRBtn;
	@FXML
	private RadioButton bOpenRBtn;
	@FXML
	private Button btnConfirm;

	// Event Listener on Button[#btnConfirm].onAction
	@FXML
	public void confirmClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		int inputK = -1;
		try {
			inputK = Integer.valueOf(txtFInputK.getText());
			MainFields.setGroupedDataK(inputK);
			
			if (closedRBtn.isSelected()) {
				MainFields.setIntervalCase(0);
			} else if (fOpenRBtn.isSelected()) {
				MainFields.setIntervalCase(1);
			} else if (lOpenRBtn.isSelected()) {
				MainFields.setIntervalCase(2);
			} else {
				MainFields.setIntervalCase(3);
			}
			System.out.println("interval case: " + MainFields.getIntervalCase());
			System.out.println("k: " + inputK);

			Parent root = FXMLLoader.load(getClass().getResource("/view/InputGroupedData.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/theme/bloodcrimson.css");
			Stage stage = (Stage) txtFInputK.getScene().getWindow();
			stage.setTitle("Input grouped data");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (java.lang.RuntimeException re) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Critical Error");
			alert.setHeaderText("Invalid Input!!!");
			alert.setContentText("Ooops, input is not allowed! Please change the input.");
			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		closedRBtn.setToggleGroup(groupedChoices);
		fOpenRBtn.setToggleGroup(groupedChoices);
		lOpenRBtn.setToggleGroup(groupedChoices);
		bOpenRBtn.setToggleGroup(groupedChoices);
	}
}
