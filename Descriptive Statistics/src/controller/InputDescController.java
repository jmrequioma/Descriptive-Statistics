package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.MainFields;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class InputDescController implements Initializable {
	@FXML
	private TextField txtFInputTitle;
	@FXML
	private Button btnBack;
	@FXML
	private RadioButton rBtnInt;
	@FXML
	private RadioButton rBtnFlt;
	@FXML
	private ToggleGroup data;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		rBtnInt.setToggleGroup(data);
		rBtnFlt.setToggleGroup(data);
		
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void confirm(ActionEvent event) throws IOException {
		String title = txtFInputTitle.getText();
		Stage stage = (Stage) btnBack.getScene().getWindow();
		if (title.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Critical Error");
			alert.setHeaderText("Invalid Input!!!");
			alert.setContentText("Ooops, input is not allowed! Please change the input.");
			alert.showAndWait();
		} else {
			if (rBtnInt.isSelected()) {
				MainFields.setDataType("Integer");
			} else {
				MainFields.setDataType("Float");
			}
			if (MainFields.getType() == "Ungrouped") {
				MainFields.setTitle(title);
				Stage ownerStage = (Stage) stage.getOwner();
				Parent root = FXMLLoader.load(getClass().getResource("/view/InputData.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add("/theme/bloodcrimson.css");
				ownerStage.setScene(scene);
				ownerStage.setResizable(false);
				ownerStage.show();
				stage.close();
			} else {
				MainFields.setTitle(title);
				Stage ownerStage = (Stage) stage.getOwner();
				Parent root = FXMLLoader.load(getClass().getResource("/view/InputGrouped.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add("/theme/bloodcrimson.css");
				ownerStage.setScene(scene);
				ownerStage.setResizable(false);
				ownerStage.show();
				stage.close();
			}
		}
	}
	// Event Listener on Button[#btnBack].onAction
	@FXML
	public void back(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
	}
}
