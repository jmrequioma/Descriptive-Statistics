package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.GroupedData;
import main.MainFields;

public class ComputationTableController implements Initializable {

	@FXML private TableView<GroupedData> groupedData;
	@FXML private TableColumn<GroupedData, String> lowerClassLimit;
	@FXML private TableColumn<GroupedData, String> upperClassLimit;
	@FXML private TableColumn<GroupedData, String> frequency;
	@FXML private TableColumn<GroupedData, String> classMarks;
	@FXML private TableColumn<GroupedData, String> fixi;
	@FXML private TableColumn<GroupedData, String> fixi2;
	@FXML private Button btnContinue;
	private ArrayList<String> classMarksList = new ArrayList<String>();
	private ArrayList<String> fixiList = new ArrayList<String>();
	private ArrayList<String> fixi2List = new ArrayList<String>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initFourthToLastColumns();
		populateTable();
		
		lowerClassLimit.setCellValueFactory(new PropertyValueFactory
				<GroupedData, String>("lowerClassLimit"));
		upperClassLimit.setCellValueFactory(new PropertyValueFactory
				<GroupedData, String>("upperClassLimit"));
		frequency.setCellValueFactory(new PropertyValueFactory
				<GroupedData, String>("frequency"));
		classMarks.setCellValueFactory(new PropertyValueFactory
				<GroupedData, String>("classMarks"));
		fixi.setCellValueFactory(new PropertyValueFactory
				<GroupedData, String>("fixi"));
		fixi2.setCellValueFactory(new PropertyValueFactory
				<GroupedData, String>("fixi2"));
		
	}
	
	private void initFourthToLastColumns() {
		String classMark;
		String fixi;
		String fixi2;
		
		for(int i = 0; i < MainFields.getGroupedDataK(); i++) {
			if(i == 0 && (MainFields.getIntervalCase() == 1 || 
					      MainFields.getIntervalCase() == 3) ||
			  (i == MainFields.getGroupedDataK() - 1 && 
			  	   (MainFields.getIntervalCase() == 2 || 
					MainFields.getIntervalCase() == 3))) 
			{
				classMark = "-";
				fixi = "-";
				fixi2 = "-";
			} else {
				classMark = String.valueOf((Float.valueOf(MainFields.getLowerClassLimitsList().get(i)) +
						    Float.valueOf(MainFields.getUpperClassLimitsList().get(i))) / 2);
				fixi = String.valueOf(Float.valueOf(classMark) * 
				       Float.valueOf(MainFields.getFrequencyList().get(i)));
				fixi2 = String.valueOf(Float.valueOf(MainFields.getFrequencyList().get(i)) * 
			      	    Math.pow(Float.valueOf(classMark), 2));
			}
			
			classMarksList.add(classMark);
			fixiList.add(fixi);
			fixi2List.add(fixi2);
		}
	}
	
	private void populateTable() {
		for(int i = 0; i < MainFields.getGroupedDataK(); i++) {
			groupedData.getItems().add(new GroupedData(MainFields.getLowerClassLimitsList().get(i), 
					MainFields.getUpperClassLimitsList().get(i), MainFields.getFrequencyList().get(i),
					classMarksList.get(i), fixiList.get(i), fixi2List.get(i)));
		}
		
		populateTotals();	
	}
	
	private void populateTotals() {
		String frequencyTotal = getTotalInt(MainFields.getFrequencyList());
		String fixiTotal = "-";
		String fixi2Total = "-";
		MainFields.setFrequencyTotal(Integer.valueOf(frequencyTotal));
		
		if(MainFields.getIntervalCase() == 0) {
			fixiTotal = getTotalFloat(fixiList);
			MainFields.setFixiTotal(Float.valueOf(fixiTotal));
			fixi2Total = getTotalFloat(fixi2List);
			MainFields.setFixi2Total(Float.valueOf(fixi2Total));
		}

		groupedData.getItems().add(new GroupedData("", "", "total: " + frequencyTotal, "", 
				"total: " + fixiTotal, "total: " + fixi2Total));
	}
	
	private String getTotalInt(ArrayList<String> dataList) {
		int total = 0;
		for(String data : dataList) {
			int dataInt = Integer.valueOf(data);
			total += dataInt;
		}
		return String.valueOf(total);
	}
	
	private String getTotalFloat(ArrayList<String> dataList) {
		float total = 0;
		for(String data : dataList) {
			float dataFloat = Float.valueOf(data);
			total += dataFloat;
		}
		return String.valueOf(total);
	}
	
	@FXML
	private void continueClick() throws IOException {
		// Computation table ends here
		Parent root = FXMLLoader.load(getClass().getResource("/view/ChoiceMenu.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/bloodcrimson.css");
		Stage stage = (Stage) btnContinue.getScene().getWindow();
		stage.setTitle("Choices");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
