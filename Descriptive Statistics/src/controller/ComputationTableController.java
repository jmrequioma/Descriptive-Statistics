package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
		for(int i = 0; i < MainFields.getGroupedDataK(); i++) {
			String classMark = String.valueOf((Float.valueOf(MainFields.getLowerClassLimitsList().get(i)) +
							   Float.valueOf(MainFields.getUpperClassLimitsList().get(i))) / 2);
			String fixi = String.valueOf(Float.valueOf(classMark) * 
					      Float.valueOf(MainFields.getFrequencyList().get(i)));
			String fixi2 = String.valueOf(Float.valueOf(classMark) * 
				      	   Math.pow(Float.valueOf(MainFields.getFrequencyList().get(i)), 2));
			
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
		
		String frequencyTotal = getTotalInt(MainFields.getFrequencyList());
		String fixiTotal = getTotalFloat(fixiList);
		String fixi2Total = getTotalFloat(fixi2List);
		
		MainFields.setFrequencyTotal(Integer.valueOf(frequencyTotal));
		MainFields.setFixiTotal(Float.valueOf(fixiTotal));
		
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
	private void continueClick() {
		// Computation table ends here
	}
}
