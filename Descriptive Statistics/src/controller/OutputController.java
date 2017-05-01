package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.MainFields;
import main.Mean;
import main.Median;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	@FXML
	private Button returnBtn;
	@FXML
	private Button execBtn;
	
	private ArrayList<Float> copy = new ArrayList<Float>();
	List<List<Integer>> modes2 = new ArrayList<List<Integer>>();
	List<List<Float>> dummy = new ArrayList<List<Float>>();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (MainFields.getType() == "Ungrouped") {
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
					List<List<Float>> modes = new ArrayList<List<Float>>();
					ArrayList<Integer> indices = new ArrayList<Integer>();
					
					//modes = mode(copy);
					indices = lastOccurencesIndex(copy, MainFields.getSampleDataInt());
					modes = groupData(indices, copy);
					System.out.println("Checking: ");
					for (int i = 0; i < modes.size(); i++) {
						for (int j = 0; j < modes.get(i).size(); j++) {
							System.out.print(modes.get(i).get(j) + " ");
						}
						System.out.println();
					}
					String strModes = getModes(modes, modes2);
					int modeCtr = countModes(strModes);
					System.out.println("strModes: " + strModes);
					String chara = "";
					if (modeCtr == 0 || checkEqualSize(0, modes)) {
						chara = "No mode";
					} else if (modeCtr == 1) {
						chara = "Unimodal";
					} else if (modeCtr == 2) {
						chara = "Bimodal";
					} else {
						chara = "Multi-modal";
					}
					if (chara == "No mode") {
						strModes = "";
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
			if (MainFields.isMode()) {
				List<List<Integer>> modes = new ArrayList<List<Integer>>();
				ArrayList<Integer> copy = new ArrayList<Integer>();
				for (int i = 0; i < MainFields.getSampleDataInt().size(); i++) {
					copy.add(MainFields.getSampleDataInt().get(i));
				}
				Collections.sort(copy);
				ArrayList<Integer> indices = new ArrayList<Integer>();
				
				//modes = mode(copy);
				indices = lastOccurencesIndex(MainFields.getSampleDataFloat(), copy);
				modes = groupData(copy, indices);
				System.out.println("Checking: ");
				for (int i = 0; i < modes.size(); i++) {
					for (int j = 0; j < modes.get(i).size(); j++) {
						System.out.print(modes.get(i).get(j) + " ");
					}
					System.out.println();
				}
				String strModes = getModes(dummy, modes);
				int modeCtr = countModes(strModes);
				System.out.println("strModes: " + strModes);
				String chara = "";
				if (modeCtr == 0 || checkEqualSize(modes, 0)) {
					chara = "No mode";
				} else if (modeCtr == 1) {
					chara = "Unimodal";
				} else if (modeCtr == 2) {
					chara = "Bimodal";
				} else {
					chara = "Multi-modal";
				}
				if (chara == "No mode") {
					strModes = "";
				}
				presentMode(strModes, chara);
				modeCol.setCellValueFactory(new PropertyValueFactory
						<Median, String>("median"));
				charCol.setCellValueFactory(new PropertyValueFactory
						<Median, String>("range"));
			}
		} else {
			
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
	
	private ArrayList<Integer> lastOccurencesIndex(ArrayList<Float> data1, ArrayList<Integer> data2) {
		if (MainFields.getDataType() == "Float") {
			ArrayList<Integer> index = new ArrayList<Integer>();
			for (int i = 1; i < data1.size(); i++) {
				float dataPointed = data1.get(i);
				float prevData = data1.get(i - 1);
				if (dataPointed != prevData) {
					index.add(i - 1);
				}
			}
			index.add(data1.size() - 1);
			return index;
		} else {
			ArrayList<Integer> index = new ArrayList<Integer>();
			for (int i = 1; i < data2.size(); i++) {
				int dataPointed = data2.get(i);
				int prevData = data2.get(i - 1);
				if (dataPointed != prevData) {
					index.add(i - 1);
				}
			}
			index.add(data2.size() - 1);
			return index;
		}
	}
	// for float
	private List<List<Float>> groupData(ArrayList<Integer> index, List<Float> sortedSamplingFrameCopy ) {
		List<List<Float>> groupedData = new ArrayList<List<Float>>();
		for (int i = 0; i < index.size(); i++) {
			ArrayList<Float> bucket = new ArrayList<Float>();
			int start;
			if (i == 0) {
				start = 0;
			} else {
				start = index.get(i - 1) + 1;
			}
			for (int j = start; j <= index.get(i); j++) {
				bucket.add(sortedSamplingFrameCopy.get(j));
			}
			groupedData.add(bucket);
		}
		
		return groupedData;
	}
	// for int
	private List<List<Integer>> groupData(List<Integer> sortedSamplingFrameCopy, ArrayList<Integer> index) {
		List<List<Integer>> groupedData = new ArrayList<List<Integer>>();
		for (int i = 0; i < index.size(); i++) {
			ArrayList<Integer> bucket = new ArrayList<Integer>();
			int start;
			if (i == 0) {
				start = 0;
			} else {
				start = index.get(i - 1) + 1;
			}
			for (int j = start; j <= index.get(i); j++) {
				bucket.add(sortedSamplingFrameCopy.get(j));
			}
			groupedData.add(bucket);
		}
		
		return groupedData;
	}
	// for float
	private String getModes(List<List<Float>> groupedData, List<List<Integer>> groupedData2) {
		for (int i = 0; i < groupedData.size(); i++) {
			for (int j = 0; j < groupedData.get(i).size(); j++) {
				System.out.println(groupedData.get(i).get(j));
			}
		}
		if (MainFields.getDataType() == "Float") {
			String strModes = "";
			int size = 2;
			for (int i = 0; i < groupedData.size(); i++) {
				if (groupedData.get(i).size() >= size) {
					size = groupedData.get(i).size();
					System.out.println("hello: " + groupedData.get(i));
				}
			}
			// we already know the largest size
			for (int i = 0; i < groupedData.size(); i++) {
				if (groupedData.get(i).size() == size) {
					
					strModes += (String.valueOf(groupedData.get(i).get(0)) + ", ");
				}
			}
			return strModes;
		} else {
			String strModes = "";
			int size = 1;
			for (int i = 0; i < groupedData2.size(); i++) {
				if (groupedData2.get(i).size() > size) {
					size = groupedData2.get(i).size();
				}
			}
			// we already know the largest size
			for (int i = 0; i < groupedData2.size(); i++) {
				if (groupedData2.get(i).size() == size) {
					strModes += (String.valueOf(groupedData2.get(i).get(0).toString() + ", "));
				}
			}
			return strModes;
		}
	}
	
	private int countModes(String strMode) {
		int counter = 0;
		for(int i = 0; i < strMode.length(); i++ ) {
		    if(strMode.charAt(i) == ',' ) {
		        counter++;
		    } 
		}
		return counter;
	}
	
	private boolean checkEqualSize(List<List<Integer>> modes, int dummy) {
		int ctr = 0;
		for (int i = 1; i < modes.size(); i++) {
			if (modes.get(i).size() == modes.get(i - 1).size()) {
				ctr++; 
			}
		}
		if (ctr == (modes.size() - 1)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkEqualSize(int dummy, List<List<Float>> modes) {
		int ctr = 0;
		for (int i = 1; i < modes.size(); i++) {
			if (modes.get(i).size() == modes.get(i - 1).size()) {
				ctr++; 
			}
		}
		if (ctr == (modes.size() - 1)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void returnClick(ActionEvent e) throws IOException {
		MainFields.reset();
		Parent root = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Stage stage = (Stage) returnBtn.getScene().getWindow();
		stage.setTitle("Menu");
		stage.setResizable(false);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/bloodcrimson.css");
		stage.setScene(scene);
		stage.show();
	}
	
	public void execClick(ActionEvent e) throws IOException {
		MainFields.resetChoices();
		Parent root = FXMLLoader.load(getClass().getResource("/view/ChoiceMenu.fxml"));
		Stage stage = (Stage) execBtn.getScene().getWindow();
		stage.setTitle("Choices");
		stage.setResizable(false);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/bloodcrimson.css");
		stage.setScene(scene);
		stage.show();
	}
}
