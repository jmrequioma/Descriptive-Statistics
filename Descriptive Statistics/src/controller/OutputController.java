package controller;

import java.net.URL;
import java.text.DecimalFormat;
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
	private TableView<Median> modeTable;
	@FXML
	private TableColumn<Median, String> modeCol;
	@FXML
	private TableColumn<Median, String> charCol;
	
	private ArrayList<Float> copy = new ArrayList<Float>();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (MainFields.getDataType() == "Float") {
			for (int i = 0; i < MainFields.getSampleDataFloat().size(); i++) {
				copy.add(MainFields.getSampleDataFloat().get(i));
			}
			Collections.sort(copy);
			if (MainFields.isMean()) {
				double meanInt = calculateMean(MainFields.getSampleDataFloat());
				for (int i = 0; i < MainFields.getSampleDataFloat().size(); i++) {
					System.out.println("hi " + MainFields.getSampleDataFloat().get(i));
				}
				double meanVar = calculateVariance(meanInt, MainFields.getSampleDataFloat());
				double meanStanDev = Math.sqrt(meanVar);
				DecimalFormat df = new DecimalFormat("#.###");  
				meanVar = Double.valueOf(df.format(meanVar));
				meanStanDev = Double.valueOf(df.format(meanStanDev));
				meanInt = Double.valueOf(df.format(meanStanDev));
				presentMean(meanInt, meanVar, meanStanDev);
				meanCol.setCellValueFactory(new PropertyValueFactory
						<Mean, String>("mean"));
				varCol.setCellValueFactory(new PropertyValueFactory
						<Mean, String>("variance"));
				sdCol.setCellValueFactory(new PropertyValueFactory
						<Mean, String>("stanDev"));
			}
			if (MainFields.isMedian()) {
				double range = (copy.get(copy.size() - 1) - copy.get(0));
				double median;
				if (copy.size() % 2 > 0) {
					// odd
					median = copy.get(((copy.size() + 1) / 2) - 1);
				} else {
					// even
					double firstMed = copy.get((copy.size() / 2) - 1);
					double secondMed = copy.get(copy.size() / 2);
					System.out.println("firstMed: " + firstMed);
					System.out.println("secondMed: " + secondMed);
					median = ((firstMed + secondMed) / 2);
				}
				presentMedian(median, range);
				medCol.setCellValueFactory(new PropertyValueFactory
						<Median, String>("median"));
				rangeCol.setCellValueFactory(new PropertyValueFactory
						<Median, String>("range"));
			}
			if (MainFields.isMode()) {
				ArrayList<Float> modes = new ArrayList<Float>();
				modes = mode(copy);
				String strModes = "";
				String chara = "";
				for (int i = 0; i < modes.size(); i++) {
					strModes += modes.get(i).toString();
				}
				if (modes.size() == 0) {
					chara = "No mode";
				} else if (modes.size() == 1) {
					chara = "Unimodal";
				} else if (modes.size() == 2) {
					chara = "Bimodal";
				} else {
					chara = "Multi-modal";
				}
				presentMode(strModes, chara);
				modeCol.setCellValueFactory(new PropertyValueFactory
						<Median, String>("median"));
				charCol.setCellValueFactory(new PropertyValueFactory
						<Median, String>("range"));
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
				DecimalFormat df = new DecimalFormat("#.###");  
				meanVar = Double.valueOf(df.format(meanVar));
				meanStanDev = Double.valueOf(df.format(meanStanDev));
				meanInt = Double.valueOf(df.format(meanStanDev));
				presentMean(meanInt, meanVar, meanStanDev);
				meanCol.setCellValueFactory(new PropertyValueFactory
						<Mean, String>("mean"));
				varCol.setCellValueFactory(new PropertyValueFactory
						<Mean, String>("variance"));
				sdCol.setCellValueFactory(new PropertyValueFactory
						<Mean, String>("stanDev"));
			}
			if (MainFields.isMedian()) {
				ArrayList<Integer> copy = new ArrayList<Integer>();
				for (int i = 0; i < MainFields.getSampleDataInt().size(); i++) {
					copy.add(MainFields.getSampleDataInt().get(i));
				}
				Collections.sort(copy);
				double range = (copy.get(copy.size() - 1) - copy.get(0));
				double median;
				if (copy.size() % 2 > 0) {
					// odd
					median = copy.get(((copy.size() + 1) / 2) - 1);
				} else {
					// even
					double firstMed = copy.get((copy.size() / 2) - 1);
					double secondMed = copy.get(copy.size() / 2);
					System.out.println("firstMed: " + firstMed);
					System.out.println("secondMed: " + secondMed);
					median = ((firstMed + secondMed) / 2);
				}
				presentMedian(median, range);
				medCol.setCellValueFactory(new PropertyValueFactory
						<Median, String>("median"));
				rangeCol.setCellValueFactory(new PropertyValueFactory
						<Median, String>("range"));
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
	
	private void presentMedian(double median, double range) {
		medianTable.getItems().add(
				new Median(String.valueOf(median), String.valueOf(range)));
	}
	
	private void presentMode(String modes, String chara) {
		modeTable.getItems().add(
				new Median(modes, chara));
	}
	
	private ArrayList<Float> mode(ArrayList<Float> a){
		  ArrayList<Float> modes = new ArrayList<Float>();
		  int maxCount=0;
		  for (int i = 0; i < a.size(); ++i){
		    int count = 0;
		    for (int j = 0; j < a.size(); ++j){
		      if (a.get(j).equals(a.get(i))) {
		    	  ++count;
		      }
		    }
		    if (count > maxCount){
		      maxCount = count;
		      modes.clear();
		      modes.add(a.get(i));
		    } else if ( count == maxCount ){
		      modes.add(a.get(i));
		    }
		  }
		  return modes;
		}
}
