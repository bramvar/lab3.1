package model;

public class Stock implements Comparable<Stock> {
	
	private String market;
	private double value;
	private Date date;
	
	public Stock(String market, double value, Date date) {
		this.market = market;
		this.value = value;
		this.date = date;
	}
	
	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
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
	public int compareTo(Stock s) {
		int result=0;
		if(this.value<s.value) result=-1;
		else if(this.value>s.value) result=1;
		return result;
	}
}
