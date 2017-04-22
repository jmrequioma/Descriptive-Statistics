package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MainFields;

public class MenuController {
	
	@FXML private Button btnUngrouped;
	@FXML private Button btnGrouped;
	@FXML private Button btnQuit;
	
	@FXML
	private void btnClick(ActionEvent event) throws IOException {
		Button src = (Button) event.getSource();
		
		if(src == btnUngrouped) {
			MainFields.setType("Ungrouped");
			System.out.println(MainFields.getType());
		} else if(src == btnGrouped) {
			MainFields.setType("Grouped");
			System.out.println(MainFields.getType());
		} else {
			System.exit(0);
		}
		
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/view/InputDesc.fxml"));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/bloodcrimson.css");
		stage.setTitle("Input Description");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(src.getScene().getWindow());
		stage.showAndWait();
	}
} 
