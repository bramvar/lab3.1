package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.stage.FileChooser;

public class Main {

	private CapitalMarket cm;
	private Forex f;
	
	public Main() {
		
	}
	
	public static void main(String[] args) throws IOException {

	     String line="";
	     String line2="";

			FileReader fr = new FileReader( new File("/home/vardo/Downloads/#US30 prices.txt"));
			 BufferedReader br=new BufferedReader(fr);
			while((line=br.readLine())!=null) {;
				line2+=line+"\n";
			}
			String[] l=line2.split("\n");
			String[] m=l[1].split(",");
			String[] n=m[1].split(" ");
			
			System.out.println(n[1]);
	}
}
