package model;

import exceptions.ElementException;
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
	
	public void searchStock(Stock s) {
		stocks.search(s);
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
}
