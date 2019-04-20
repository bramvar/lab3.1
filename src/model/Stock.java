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
		if(this.market.compareTo(s.market)<0) result=-1;
		else if(this.market.compareTo(s.market)>0) result=1;
		else {
			if(this.date.getYear()<s.date.getYear()) result=-1;
			else if(this.date.getYear()>s.date.getYear()) result=1;
			else {
				if(this.date.getMonth()<s.date.getMonth())result=-1;
				else if(this.date.getMonth()>s.date.getMonth())result=1;
				else {
					if(this.date.getDay()<s.date.getDay())result=-1;
					else if(this.date.getDay()>s.date.getDay())result=1;
					else {
						if(this.date.getHour()<s.date.getHour())result=-1;
						else if(this.date.getHour()>s.date.getHour())result=1;
						else {
							if(this.date.getMin()<s.date.getMin())result=-1;
							else if(this.date.getMin()>s.date.getMin())result=1;
						}
					}
				}
			}
		}
		return result;
	}
}
