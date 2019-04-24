package model;

import java.util.ArrayList;

import exceptions.ElementException;
import vRqueue.VrQueue;
import vrAVLTree.VrAvlTree;

public class CapitalMarket {
	
	private VrAvlTree<Stock> stocks;
	private int num;
	
	public CapitalMarket() {
		stocks=new VrAvlTree<Stock>();
		num=0;
	}
	
	public void addStock(Stock s) throws ElementException {
		stocks.insert(s);
		num++;
	}

	public void deleteStock(Stock s) throws ElementException {
		stocks.delete(s);
		num--;
	}
	
	public void deleteStockByDate(int year,int month,int day) throws ElementException {
		Stock s=new Stock("",2,new Date(day,month,year,0,0));
		//s.setCompareToCondition(Stock.COMPARE_ALL);
		 stocks.delete(s);
	}
	
	public double[] getStockPricesByDate(int iDay,int iMonth,int iYear,int fDay,int fMonth,int fYear) throws ElementException {
		int d=iDay;
		int m=iMonth;
		int y=iYear;
		VrQueue<Stock> a =new VrQueue<Stock>();
		
		while(y<3||m<5||d<2) {
			if(d>31) {
				d=1;
				m++;
			}
			if(m>12) {
				m=1;
				y++;
			}
			if(searchAllStockByDate(y, m, d)==null) {
				d++;
			}
			else {
				a.offer(searchAllStockByDate(y, m, d));
				deleteStockByDate(y,m,d);
			}
			//System.out.println(d+"/"+m+"/"+y);
			//System.out.println(cont);
		}
		double[] prices=new double[a.size()];
		
		for(int i=0;i<prices.length;i++)
			prices[i]=a.poll().getValue();
		
		return prices;
	}
	
	public double highestPriceDate(double[] arr) {
		return highestPriceDate(arr,0,arr.length-1);
	}
	
	public double highestPriceDate(double[] arr, int i, int j){
		if(i==j) {
			return arr[i];
		}
		else {
			int m=(i+j)/2;
			double aux=highestPriceDate(arr,i,m);
			double aux2=highestPriceDate(arr, m+1,j);
			return (aux>aux2)?aux:aux2;
		}
	}
	
	public Stock searchStock(Stock s) {
		return stocks.search(s);
	}
	
	public Stock searchAllStockByDate(int year,int month,int day) {
		Stock s=new Stock("",2,new Date(day,month,year,0,0));
		//s.setCompareToCondition(Stock.COMPARE_ALL);
		return stocks.search(s);
	}
	
	public VrAvlTree<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(VrAvlTree<Stock> stocks) {
		this.stocks = stocks;
	}

	public int getNum() {
		return num;
	}
	
	public static void main(String[] args) throws ElementException {
		CapitalMarket cm=new CapitalMarket();
		Date t=new Date(1,2,2,0,0);
		Stock s=new Stock("test",5,t);;
		Stock s1=new Stock("test1",3,new Date(2,2,2,0,0));
		Stock s2=new Stock("test2",1,new Date(2,4,3,0,0));
		Stock s3=new Stock("test3",1,new Date(1,5,3,0,0));
		Stock s4=new Stock("test4",1,new Date(2,5,3,0,0));
		Stock s5=new Stock("test5",1,new Date(2,4,5,0,0));
		
		cm.addStock(s);
		cm.addStock(s1);
		cm.addStock(s2);
		cm.addStock(s3);
		cm.addStock(s4);
		cm.addStock(s5);
		
		
		int d=1;
		int m=2;
		int y=2;
		
		int cont=0;
		
		ArrayList<Stock> a =new ArrayList<Stock>();
		
		while(y<3||m<5||d<2) {
			if(d>31) {
				d=1;
				m++;
			}
				
			if(m>12) {
				m=1;
				y++;
			}

			if(cm.searchAllStockByDate(y, m, d)==null) {
				d++;
			}
			else {
				a.add(cm.searchAllStockByDate(y, m, d));
				cm.deleteStockByDate(y,m,d);
			}
			
			//System.out.println(d+"/"+m+"/"+y);
			//System.out.println(cont);
			
		}
		
		
	//	cm.deleteStockByDate(2,2,2);
		
		
		System.out.println(a.size());
		
		for(Stock q:a) {
			System.out.println(q.getMarket());
		}
		
		
	}
}
