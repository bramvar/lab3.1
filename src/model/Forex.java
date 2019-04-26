package model;

import exceptions.ElementException;
import vRqueue.VrQueue;
import vrRBTree.VrRbTree;

public class Forex {

	private VrRbTree<Currency> currencies;
	private int num;
	
	public Forex() {
		currencies=new VrRbTree<Currency>();
		num=0;
	}
	
	public VrRbTree<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(VrRbTree<Currency> currencies) {
		this.currencies = currencies;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public void addCurrency(Currency c) throws ElementException {
		currencies.insert(c);
		num++;
	}
	
	public void deleteCurrency(Currency c) throws ElementException {
		currencies.delete(c);
		num--;
	}
	
	public void deleteStockByDate(int year,int month,int day) throws ElementException {
		Currency s=new Currency("",2,new Date(day,month,year,0,0));
		//s.setCompareToCondition(Stock.COMPARE_ALL);
		currencies.delete(s);
	}
	
	public Currency searchCurrency(Currency c) {
		return currencies.search(c);
	}
	
	public Currency searchAllCurrenciesByDate(int year,int month,int day) {
		Currency s=new Currency("",2,new Date(day,month,year,0,0));
		//s.setCompareToCondition(Stock.COMPARE_ALL);
		return currencies.search(s);
	}
	
	public double[] getCurrencyPricesByDate(int iDay,int iMonth,int iYear,int fDay,int fMonth,int fYear) throws ElementException {
		int d=iDay;
		int m=iMonth;
		int y=iYear;
		VrQueue<Currency> a =new VrQueue<Currency>();
		
		while(y<3||m<5||d<2) {
			if(d>31) {
				d=1;
				m++;
			}
			if(m>12) {
				m=1;
				y++;
			}
			if(searchAllCurrenciesByDate(y, m, d)==null) {
				d++;
			}
			else {
				a.offer(searchAllCurrenciesByDate(y, m, d));
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
}
