package main;

public class Median {
	private String median;
	private String range;
	
	public Median(String median, String range) {
		this.setMedian(median);
		this.setRange(range);
	}

	public String getMedian() {
		return median;
	}

	public void setMedian(String median) {
		this.median = median;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}
}
