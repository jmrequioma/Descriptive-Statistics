package main;

public class GroupedData {
	
	private String lowerClassLimit;
	private String upperClassLimit;
	private String frequency;
	private String classMarks;
	private String fixi;
	private String fixi2;
	
	public GroupedData(String lowerClassLimit, String upperClassLimit, String frequency) {
		this.lowerClassLimit = lowerClassLimit;
		this.upperClassLimit = upperClassLimit;
		this.frequency = frequency;
	}
	
	public GroupedData(String lowerClassLimit, String upperClassLimit, String frequency, String classMarks, String fixi,
			String fixi2) {
		this.lowerClassLimit = lowerClassLimit;
		this.upperClassLimit = upperClassLimit;
		this.frequency = frequency;
		this.classMarks = classMarks;
		this.fixi = fixi;
		this.fixi2 = fixi2;
	}
	
	public String getLowerClassLimit() {
		return lowerClassLimit;
	}
	
	public void setLowerClassLimit(String lowerClassLimit) {
		this.lowerClassLimit = lowerClassLimit;
	}
	
	public String getUpperClassLimit() {
		return upperClassLimit;
	}
	
	public void setUpperClassLimit(String upperClassLimit) {
		this.upperClassLimit = upperClassLimit;
	}
	
	public String getFrequency() {
		return frequency;
	}
	
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getClassMarks() {
		return classMarks;
	}

	public void setClassMarks(String classMarks) {
		this.classMarks = classMarks;
	}

	public String getFixi() {
		return fixi;
	}

	public void setFixi(String fixi) {
		this.fixi = fixi;
	}

	public String getFixi2() {
		return fixi2;
	}

	public void setFixi2(String fixi2) {
		this.fixi2 = fixi2;
	}
}
