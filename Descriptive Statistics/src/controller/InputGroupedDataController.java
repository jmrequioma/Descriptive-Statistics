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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.GroupedData;
import main.MainFields;

public class InputGroupedDataController implements Initializable {

	@FXML private TableView<GroupedData> groupedData;
	@FXML private TableColumn<GroupedData, String> lowerClassLimit;
	@FXML private TableColumn<GroupedData, String> upperClassLimit;
	@FXML private TableColumn<GroupedData, String> frequency;
	@FXML private ComboBox<Integer> classInterval;
	@FXML private TextField lowerClassLimitTxtF;
	@FXML private TextField upperClassLimitTxtF;
	@FXML private TextField frequencyTxtF;
	@FXML private Button continueBtn;
	private ArrayList<String> lowerClassLimitsList = new ArrayList<String>();
	private ArrayList<String> upperClassLimitsList = new ArrayList<String>();
	private ArrayList<String> frequencyList = new ArrayList<String>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initLists();
		initCombox();
		populateTable();
		
		lowerClassLimit.setCellValueFactory(new PropertyValueFactory
				<GroupedData, String>("lowerClassLimit"));
		upperClassLimit.setCellValueFactory(new PropertyValueFactory
				<GroupedData, String>("upperClassLimit"));
		frequency.setCellValueFactory(new PropertyValueFactory
				<GroupedData, String>("frequency"));
	}
	
	private void initLists() {
		System.out.println("Interval casexx: " + MainFields.getIntervalCase());
		for(int i = 0; i < MainFields.getGroupedDataK(); i++) {
			if(i == 0 && (MainFields.getIntervalCase() == 1 || 
						  MainFields.getIntervalCase() == 3)) 
			{
				lowerClassLimitsList.add("<=");
			} else {
				lowerClassLimitsList.add("");	
			}
			
			if(i == MainFields.getGroupedDataK() - 1 && (MainFields.getIntervalCase() == 2 || 
					  MainFields.getIntervalCase() == 3)) 
			{
				upperClassLimitsList.add(">=");
			} else {
				upperClassLimitsList.add("");	
			}
			
			frequencyList.add("");
		}
	}
	
	private void initCombox() {
		for(int i = 0; i < MainFields.getGroupedDataK() ; i++) {
			classInterval.getItems().add(i + 1);
		}
	}
	
	private void populateTable() {
		for(int i = 0; i < MainFields.getGroupedDataK(); i++) {
			groupedData.getItems().add(new GroupedData(lowerClassLimitsList.get(i), 
					upperClassLimitsList.get(i), frequencyList.get(i)));
		}
	}
	
	@FXML
	private void editClick() {
		int classIntervalVal = classInterval.getValue();
		String lowerClassLimitVal = lowerClassLimitTxtF.getText();
		String upperClassLimitVal = upperClassLimitTxtF.getText();
		String frequencyVal = frequencyTxtF.getText();
		
		if(MainFields.getDataType().equals("Integer")) {
			if((isNumber(lowerClassLimitVal) || classInterval.getValue() == 1) && (isNumber(upperClassLimitVal) || classInterval.getValue() == MainFields.getGroupedDataK()) && 
			   isInteger(lowerClassLimitVal) && isInteger(upperClassLimitVal)) {
				updateLists(classIntervalVal, lowerClassLimitVal, upperClassLimitVal, frequencyVal);
				updateTable();
				checkContinueEnable();
			} else {
				showError();
			}
		} else {
			if((isNumber(lowerClassLimitVal) || classInterval.getValue() == 1) && (isNumber(upperClassLimitVal) || classInterval.getValue() == MainFields.getGroupedDataK())) {
				updateLists(classIntervalVal, lowerClassLimitVal, upperClassLimitVal, frequencyVal);
				updateTable();
				checkContinueEnable();
			} else {
				showError();
			}
		}
		
	}
	
	private boolean isNumber(String data) {
		return data.matches("[-+]?\\d*\\.?\\d+");
	}
	
	private boolean isInteger(String data) {
		return !data.contains(".");
	}
	
	private void showError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Critical Error");
		alert.setHeaderText("Invalid Click!!!");
		alert.setContentText("Please add inputs.");
		alert.showAndWait();
	}
	
	private void updateLists(int classIntervalVal, String lowerClassLimit, String upperClassLimit, 
			String frequency) 
	{
		if(classInterval.getValue() != 1 || (MainFields.getIntervalCase() != 1 && 
				  MainFields.getIntervalCase() != 3)) 
		{
			lowerClassLimitsList.set(classIntervalVal - 1, lowerClassLimit);
		} 
		
		if(classInterval.getValue() != MainFields.getGroupedDataK() || (MainFields.getIntervalCase() != 2 && 
				  MainFields.getIntervalCase() != 3)) 
		{
			upperClassLimitsList.set(classIntervalVal - 1, upperClassLimit);
		}
		
		frequencyList.set(classIntervalVal - 1, frequency);
	}
	
	private void updateTable() {
		groupedData.getItems().removeAll(groupedData.getItems());
		populateTable();
	}
	
	private void checkContinueEnable() {
		if(allIntervalsValid()) {
			continueBtn.setDisable(false);
		} else {
			continueBtn.setDisable(true);
		}
	}
	
	private boolean allIntervalsValid() {
		for(int i = 0; i < MainFields.getGroupedDataK(); i++) {
			if(lowerClassLimitsList.get(i).equals("") || upperClassLimitsList.get(i).equals("") ||
					frequencyList.get(i).equals(""))
			{
				return false;
			}
		}
		return true;
	}
	
	@FXML
	private void continueClick() throws IOException {
		MainFields.setLowerClassLimitsList(lowerClassLimitsList);
		MainFields.setUpperClassLimitsList(upperClassLimitsList);
		MainFields.setFrequencyList(frequencyList);
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/ComputationTable.fxml"));
		Stage stage = (Stage) groupedData.getScene().getWindow();
		stage.setTitle("Computation Table");
		stage.setResizable(false);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/bloodcrimson.css");
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void intervalComboxAction() {
		if(classInterval.getValue() == 1 && (MainFields.getIntervalCase() == 1 || 
				  MainFields.getIntervalCase() == 3)) 
		{
			lowerClassLimitTxtF.setDisable(true);
		} else {
			lowerClassLimitTxtF.setDisable(false);
		}
		
		if(classInterval.getValue() == MainFields.getGroupedDataK() && (MainFields.getIntervalCase() == 2 || 
				  MainFields.getIntervalCase() == 3)) 
		{
			upperClassLimitTxtF.setDisable(true);
		} else {
			upperClassLimitTxtF.setDisable(false);	
		}
		
		clearTextFields();
	}
	
	@FXML
	private void clearTextFields() {
		lowerClassLimitTxtF.clear();
		upperClassLimitTxtF.clear();;
		frequencyTxtF.clear();;
	}
	
}
