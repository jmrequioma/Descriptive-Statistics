package main;

public class Mean {
	private String mean;
	private String variance;
	private String stanDev;
	
	public Mean(String mean, String variance, String stanDev) {
		this.mean = mean;
		this.variance = variance;
		this.stanDev = stanDev;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

	public String getVariance() {
		return variance;
	}

	public void setVariance(String variance) {
		this.variance = variance;
	}

	public String getStanDev() {
		return stanDev;
	}

	public void setStanDev(String stanDev) {
		this.stanDev = stanDev;
	}
}
