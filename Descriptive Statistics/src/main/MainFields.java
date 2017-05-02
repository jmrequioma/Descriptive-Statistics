package main;


import java.util.ArrayList;


public class MainFields {
	//private static EventBus eventBus;
	//private static CollapseListener collapseListener;
	private static String type;
	private static String dataType;
	private static String title;
	private static boolean valid;
	private static boolean intExists;
	private static boolean mean;
	private static boolean mode;
	private static boolean median;
	private static boolean floatExists;
	private static ArrayList<Integer> sampleDataInt;
	private static ArrayList<String> fusedData;
	private static ArrayList<Float> sampleDataFloat;
	private static int intervalCase;   // 0 for closed, 1 for open first, 2 for open last, 3 for open both
	private static int groupedDataK;
	private static ArrayList<String> lowerClassLimitsList; // newly added
	private static ArrayList<String> upperClassLimitsList; // newly added
	private static ArrayList<String> frequencyList; // newly added
	private static float fixiTotal;
	private static float fixi2Total;
	private static int frequencyTotal;
	
	public static String getType() {
		return type;
	}
	
	public static String getDataType() {
		return dataType;
	}

	public static void setDataType(String dataType) {
		MainFields.dataType = dataType;
	}

	public static void setType(String type) {
		MainFields.type = type;
	}
	
	public static String getTitle() {
		return title;
	}
	
	public static void setTitle(String title) {
		MainFields.title = title;
	}
	
	public static boolean getValid() {
		return valid;
	}
	
	public static void setValid(boolean valid) {
		MainFields.valid = valid;
	}
	
	public static boolean isMean() {
		return mean;
	}

	public static void setMean(boolean mean) {
		MainFields.mean = mean;
	}

	public static boolean isMode() {
		return mode;
	}

	public static void setMode(boolean mode) {
		MainFields.mode = mode;
	}

	public static boolean isMedian() {
		return median;
	}

	public static void setMedian(boolean median) {
		MainFields.median = median;
	}

	public static ArrayList<Integer> getSampleDataInt() {
		return sampleDataInt;
	}
	
	public static void setSampleDataInt(ArrayList<Integer> sampleDataString) {
		MainFields.sampleDataInt = sampleDataString;
	}

	public static ArrayList<String> getFusedData() {
		return fusedData;
	}

	public static void setFusedData(ArrayList<String> fusedData) {
		MainFields.fusedData = fusedData;
	}

	public static ArrayList<Float> getSampleDataFloat() {
		return sampleDataFloat;
	}

	public static void setSampleDataFloat(ArrayList<Float> sampleDataFloat) {
		MainFields.sampleDataFloat = sampleDataFloat;
	}

	public static boolean getIntExists() {
		return intExists;
	}

	public static void setIntExists(boolean stringExists) {
		MainFields.intExists = stringExists;
	}

	public static boolean getFloatExists() {
		return floatExists;
	}

	public static void setFloatExists(boolean floatExists) {
		MainFields.floatExists = floatExists;
	}
	
	public static int getGroupedDataK() {
		return groupedDataK;
	}
	
	public static void setGroupedDataK(int groupedDataK) {
		MainFields.groupedDataK = groupedDataK;
	}
	
	public static int getIntervalCase() {
		return intervalCase;
	}
	
	public static void setIntervalCase(int intervalCase) {
		MainFields.intervalCase = intervalCase;
	}
	
	public static ArrayList<String> getLowerClassLimitsList() {
		return lowerClassLimitsList;
	}

	public static void setLowerClassLimitsList(ArrayList<String> lowerClassLimitsList) {
		MainFields.lowerClassLimitsList = lowerClassLimitsList;
	}

	public static ArrayList<String> getUpperClassLimitsList() {
		return upperClassLimitsList;
	}

	public static void setUpperClassLimitsList(ArrayList<String> upperClassLimitsList) {
		MainFields.upperClassLimitsList = upperClassLimitsList;
	}

	public static ArrayList<String> getFrequencyList() {
		return frequencyList;
	}

	public static void setFrequencyList(ArrayList<String> frequencyList) {
		MainFields.frequencyList = frequencyList;
	}

	public static float getFixiTotal() {
		return fixiTotal;
	}

	public static void setFixiTotal(float fixiTotal) {
		MainFields.fixiTotal = fixiTotal;
	}

	public static float getFixi2Total() {
		return fixi2Total;
	}

	public static void setFixi2Total(float fixi2Total) {
		MainFields.fixi2Total = fixi2Total;
	}

	public static int getFrequencyTotal() {
		return frequencyTotal;
	}

	public static void setFrequencyTotal(int frequencyTotal) {
		MainFields.frequencyTotal = frequencyTotal;
	}

	public static void reset() {
		//eventBus.unregister(collapseListener);
		if(type.equals("Grouped")) {
			lowerClassLimitsList.clear();
			upperClassLimitsList.clear();
			frequencyList.clear();
		}
		type = "";
		title = "";
		valid = false;
		if(type.equals("Ungrouped")) {
			if(dataType.equals("Integer")) {
				sampleDataInt.clear();	
			} else {
				sampleDataFloat.clear();	
			}
		}
	}
	
	public static void resetChoices() {
		mean = false;
		median = false;
		mode = false;
	}
}

