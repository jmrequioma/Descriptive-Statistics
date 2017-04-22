package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.MainFields;
import main.Mean;
import javafx.scene.control.TableColumn;

public class OutputController implements Initializable {
	@FXML
	private TableView<Mean> meanTable;
	@FXML
	private TableColumn<Mean, String> meanCol;
	@FXML
	private TableColumn<Mean, String> varCol;
	@FXML
	private TableColumn<Mean, String> sdCol;
	@FXML
	private TableView medianTable;
	@FXML
	private TableColumn medCol;
	@FXML
	private TableColumn rangeCol;
	@FXML
	private TableView modeTable;
	@FXML
	private TableColumn modeCol;
	@FXML
	private TableColumn charCol;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (MainFields.isMean()) {
			double meanInt = calculateMeanInt(MainFields.getSampleDataInt());
			for (int i = 0; i < MainFields.getSampleDataInt().size(); i++) {
				System.out.println("hi " + MainFields.getSampleDataInt().get(i));
			}
			double meanVar = calculateVariance(MainFields.getSampleDataInt(), meanInt);
			double meanStanDev = Math.sqrt(meanVar);
			presentMean(meanInt, meanVar, meanStanDev);
			meanCol.setCellValueFactory(new PropertyValueFactory
					<Mean, String>("mean"));
			varCol.setCellValueFactory(new PropertyValueFactory
					<Mean, String>("variance"));
			sdCol.setCellValueFactory(new PropertyValueFactory
					<Mean, String>("stanDev"));
		}
	}
	
	private double calculateMeanInt(ArrayList<Integer> data) {
		float totalVal = 0;
		for (int i = 0; i < data.size(); i++) {
			totalVal += data.get(i);
		}
		return totalVal / data.size();
	}
	
	private double calculateVariance(ArrayList<Integer> data, double mean) {
		float totalVal = 0;
		for (int i = 0; i < data.size(); i++) {
			totalVal += (Math.pow((data.get(i) - mean), 2));
		}
		return totalVal / (data.size() - 1);
	}
	
	private void presentMean(double mean, double variance, double stanDev) {
		meanTable.getItems().add(
				new Mean(String.valueOf(mean), String.valueOf(variance), String.valueOf(stanDev)));
	}
}
