package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.MainFields;
import main.Mean;
import main.Median;
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
	private TableView<Median> medianTable;
	@FXML
	private TableColumn<Median, String> medCol;
	@FXML
	private TableColumn<Median, String> rangeCol;
	@FXML
	private TableView modeTable;
	@FXML
	private TableColumn modeCol;
	@FXML
	private TableColumn charCol;
	
	private ArrayList<Float> copy = new ArrayList<Float>();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (MainFields.getDataType() == "Float") {
			if (MainFields.isMean()) {
				double meanInt = calculateMean(MainFields.getSampleDataFloat());
				for (int i = 0; i < MainFields.getSampleDataFloat().size(); i++) {
					System.out.println("hi " + MainFields.getSampleDataFloat().get(i));
				}
				double meanVar = calculateVariance(meanInt, MainFields.getSampleDataFloat());
				double meanStanDev = Math.sqrt(meanVar);
				presentMean(meanInt, meanVar, meanStanDev);
				meanCol.setCellValueFactory(new PropertyValueFactory
						<Mean, String>("mean"));
				varCol.setCellValueFactory(new PropertyValueFactory
						<Mean, String>("variance"));
				sdCol.setCellValueFactory(new PropertyValueFactory
						<Mean, String>("stanDev"));
			}
			if (MainFields.isMedian()) {
				for (int i = 0; i < MainFields.getSampleDataFloat().size(); i++) {
					copy.add(MainFields.getSampleDataFloat().get(i));
				}
				Collections.sort(copy);
				double range = (copy.get(copy.size() - 1) - copy.get(0));
				//double median = 
			}
		} else {
			if (MainFields.isMean()) {
				float totalVal = 0;
				for (int i = 0; i < MainFields.getSampleDataInt().size(); i++) {
					totalVal += MainFields.getSampleDataInt().get(i);
				}
				double meanInt = totalVal / MainFields.getSampleDataInt().size();
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
	}
	
	private double calculateMean(ArrayList<Float> data) {
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
	
	private double calculateVariance(double mean, ArrayList<Float> data) {
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
