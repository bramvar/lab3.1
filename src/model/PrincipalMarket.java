package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import exceptions.ElementException;

public class PrincipalMarket {

	private CapitalMarket cm;
	private Forex f;
	
	public PrincipalMarket() {
		cm=new CapitalMarket();
		f=new Forex();
	}
	
	public void addELementCapMarket(String market,String date,String value) throws ElementException {
		System.out.println(date);
		String[] date1=date.split(" ");
		String[] date2=date1[1].split("/");
		String[] time=date1[2].split(":");
		System.out.println(time[0]+" "+time[1]);
		Date d=new Date(Integer.parseInt(date2[0]),Integer.parseInt(date2[1]),Integer.parseInt(date2[2]),Integer.parseInt(time[0]),Integer.parseInt(time[1]));
		Stock s=new Stock(market,Double.parseDouble(value),d);
		cm.addStock(s);
	}
	
	public double  highestStockPriceDate(String initialDate, String finalDate) throws NumberFormatException, ElementException {
		double result=0;
		if((initialDate!=null)&&(finalDate!=null)) {
			String[] iDate=initialDate.split("-");
			String[] fDate=finalDate.split("-");
			
			double[] prices=cm.getStockPricesByDate(Integer.parseInt(iDate[3]),Integer.parseInt(iDate[2]),Integer.parseInt(iDate[1]),Integer.parseInt(fDate[3]),Integer.parseInt(fDate[2]),Integer.parseInt(fDate[1]));
			result=cm.highestPriceDate(prices);
		}
		return result;
	}
	
	public void lowestStockPriceDate(String initialDate, String finalDate) {
		
	}
	
	public int getNumStocks() {
		return cm.getNum();
	}

	public Forex getF() {
		return f;
	}

	public static void main(String[] args)throws IOException, ElementException{

	     String line="";
	     String line2="";
	     
	     CapitalMarket c=new CapitalMarket();
/*
			FileReader fr = new FileReader( new File("/home/vardo/Downloads/#US30 prices.txt"));
			 BufferedReader br=new BufferedReader(fr);
			while((line=br.readLine())!=null) {;
				line2+=line+"\n";
			}
			String[] l=line2.split("\n");
			String[] m=l[1].split(",");
			String[] n=m[1].split(" ");
			String[] n1=n[2].split(":");
			String[] p=n[1].split("/");
			
			System.out.println(n1[1]);
			*/
			Stock s1= new Stock("ddd",125.5,new Date(1,1,123,5,3));
			Stock s2= new Stock("ddd",125.5,new Date(1,1,123,5,4));
			System.out.println(s1.compareTo(s2));
			
			c.addStock(s2);
			c.addStock(s1);
	}
}
