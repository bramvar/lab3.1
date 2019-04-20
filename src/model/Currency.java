package model;

public class Currency implements Comparable<Currency>{
	
	private String code;
	private double value;
	private Date date;
	
	public Currency(String code, double value, Date date) {
		this.code = code;
		this.value = value;
		this.date = date;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int compareTo(Currency a) {
		int result=0;
		if(this.value<a.value) result=-1;
		else if(this.value>a.value) result=1;
		return result;
	}
	
}
