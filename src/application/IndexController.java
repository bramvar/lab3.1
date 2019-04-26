package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.ElementException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class IndexController implements Initializable{
	
	  @FXML private Button importDataButton;
	  @FXML private Button infoButton;
	  @FXML private AnchorPane pane;
	  
	  private DatePicker initialDate;
	  private DatePicker finalDate;
	  
	  private Button importB;
	  private Button capMarketInfoBt;
	  private Button forexInfoBt;
	  private Button test;
	  private Button showInfoByDate;
	  
	  private ListView<String> dataView;
	  private Label text,text2,totalStocksTx,totalStocks,text3,text4,highestStockPriceTx,highestStockPrice,lowestStockPriceTx,lowestStockPrice;
	  private Label highestCurrencyPriceTx,highestCurrencyPrice,lowestCurrencyPriceTx,lowestCurrencyPrice;
	  private ComboBox<String> dataType;
	  
	  private ObservableList<String> listData=FXCollections.observableArrayList();
	  private ObservableList<String> options=FXCollections.observableArrayList();
	  
	  private Main main;
	  
	 public void showImportDataPane(ActionEvent e) {
		 pane.getChildren().clear();
		 pane.getChildren().add(importB);
		 pane.getChildren().add(text);
		 pane.getChildren().add(text2);
		 pane.getChildren().add(dataView);
		 pane.getChildren().add(dataType);
		 importDataButton.setDisable(true);
		 infoButton.setDisable(false);
		 importDataButton.setStyle("fx-backgroud-color: #0d0d0d");
		
	 }
	 
	 public void showStockPricesInfoByDate(ActionEvent e) throws NumberFormatException, ElementException {
		 String idate=initialDate.getValue().toString();
		 String fdate=finalDate.getValue().toString();
		 double[] d=main.instance().highestAndLowestStockPriceDate(idate, fdate);
		 highestStockPrice.setText(Double.toString(d[0]));
		 lowestStockPrice.setText(Double.toString(d[1]));
	 }
	 
	 public void showDataInfoPane(ActionEvent e) {
		 pane.getChildren().clear();
		 capMarketInfoBt.setStyle("fx-backgroud-color: #333645");
		 pane.getChildren().add(capMarketInfoBt);
		 pane.getChildren().add(forexInfoBt);
		 
		 pane.getChildren().add(totalStocksTx);
		 pane.getChildren().add(totalStocks);
		 pane.getChildren().add(initialDate);
		 pane.getChildren().add(finalDate);
		 pane.getChildren().add(text3);
		 pane.getChildren().add(text4);
		 pane.getChildren().add(showInfoByDate);
		 pane.getChildren().add(highestCurrencyPriceTx);
		 pane.getChildren().add(highestCurrencyPrice);
		 pane.getChildren().add( lowestCurrencyPriceTx);
		 pane.getChildren().add( lowestCurrencyPrice);
		
		 
		 hideOrShowForexInfo(false);
		 
		 capMarketInfoBt.setDisable(true);
		 forexInfoBt.setDisable(false);
		 
		 pane.getChildren().add(highestStockPriceTx);
		 pane.getChildren().add(highestStockPrice);
		 
		 pane.getChildren().add(lowestStockPriceTx);
		 pane.getChildren().add(lowestStockPrice);
		 
		 importDataButton.setDisable(false);
		 infoButton.setDisable(true);
		 
	 }
	 
	 public void showCapMarketInfoBt(ActionEvent e) {
		 hideOrShowCapMarketInfo(true);
		 capMarketInfoBt.setDisable(true);
		 forexInfoBt.setDisable(false);
		 hideOrShowForexInfo(false);
		// pane.getChildren().add(test);
	 }
	 
	 public void hideOrShowCapMarketInfo(boolean a) {
		 totalStocksTx.setVisible(a);
		 totalStocks.setVisible(a);
		 highestStockPriceTx.setVisible(a);
		 highestStockPrice.setVisible(a);
		 lowestStockPriceTx.setVisible(a);
		 lowestStockPrice.setVisible(a);
		// pane.getChildren().add(test);
	 }
	 
	 
	 public void showForexInfoBt(ActionEvent e) {
		 hideOrShowForexInfo(true);
		 hideOrShowCapMarketInfo(false);
		 capMarketInfoBt.setDisable(false);
		 forexInfoBt.setDisable(true);
		// lowestStockPriceTx.setVisible(true);
		 //lowestStockPrice.setVisible(true);
		 
	 }
	 
	 public void hideOrShowForexInfo(boolean a) {
		 highestCurrencyPriceTx.setVisible(a);
		 highestCurrencyPrice.setVisible(a);
		 lowestCurrencyPriceTx.setVisible(a);
		 lowestCurrencyPrice.setVisible(a);
		 
	 }
	 
	 public void test(ActionEvent e) {
		 System.out.println(initialDate.getValue());
	 }
	 
	 public void showDataContent(ActionEvent e) throws IOException {
		 FileChooser fileChooser = new FileChooser();
	     fileChooser.setTitle("Buscar Imagen");
	     
	     fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("TXT", "*.txt"),
	                new FileChooser.ExtensionFilter("IN", "*.in")
	        );
	     
	     File f = fileChooser.showOpenDialog(null);
	     FileReader fr=new FileReader(f);
	     BufferedReader br=new BufferedReader(fr);
	     
	     String line="";
	     while((line=br.readLine())!=null)
	    	 listData.add(line);
	 }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dataType=new ComboBox<String>();
		dataType.setLayoutX(250);
		dataType.setLayoutY(25);
		
		showInfoByDate=new Button("GO");
		showInfoByDate.setLayoutX(380);
		showInfoByDate.setLayoutY(240);
		
		lowestCurrencyPriceTx=new Label("lowest currency Price: ");
		lowestCurrencyPriceTx.setLayoutX(330);
		lowestCurrencyPriceTx.setLayoutY(310);
		
		lowestCurrencyPrice=new Label("l");
		lowestCurrencyPrice.setLayoutX(490);
		lowestCurrencyPrice.setLayoutY(310);
		
		highestCurrencyPriceTx=new Label("highest currency Price: ");
		highestCurrencyPriceTx.setLayoutX(330);
		highestCurrencyPriceTx.setLayoutY(280);
		
		highestCurrencyPrice=new Label("h");
		highestCurrencyPrice.setLayoutX(490);
		highestCurrencyPrice.setLayoutY(280);
		
		highestStockPriceTx=new Label("highest Stock Price: ");
		highestStockPriceTx.setLayoutX(50);
		highestStockPriceTx.setLayoutY(280);
		
		highestStockPrice=new Label("a");
		highestStockPrice.setLayoutX(180);
		highestStockPrice.setLayoutY(280);
		
		lowestStockPriceTx=new Label("lowest stock price: ");
		lowestStockPriceTx.setLayoutX(50);
		lowestStockPriceTx.setLayoutY(310);
		
		lowestStockPrice=new Label("s");
		lowestStockPrice.setLayoutX(180);
		lowestStockPrice.setLayoutY(310);
		
		text3=new Label("Data from:");
		text3.setLayoutX(220);
		text3.setLayoutY(120);
			
		text4=new Label("to");
		text4.setLayoutX(220);
		text4.setLayoutY(180);

		initialDate=new DatePicker();
		initialDate.setLayoutX(220);
		initialDate.setLayoutY(150);
		
		finalDate=new DatePicker();
		finalDate.setLayoutX(220);
		finalDate.setLayoutY(200);
		
		options.add("Stock");
		options.add("Currency");
		dataType.setItems(options);
		
		totalStocksTx=new Label("Total Stocks: ");
		totalStocksTx.setLayoutX(10);
		totalStocksTx.setLayoutY(100);
		
		totalStocks=new Label();
		totalStocks.setLayoutX(100);
		totalStocks.setLayoutY(100);
		
		test=new Button("test");
		test.setLayoutX(50);
		test.setLayoutY(200);
		
		capMarketInfoBt=new Button("Capital Market");
		capMarketInfoBt.setLayoutX(0);
		capMarketInfoBt.setLayoutY(0);
		capMarketInfoBt.setPrefSize(323,61);
		capMarketInfoBt.setStyle("fx-backgroud-color: #333645");
	
		forexInfoBt=new Button("Forex");
		forexInfoBt.setLayoutX(323);
		forexInfoBt.setLayoutY(0);
		forexInfoBt.setPrefSize(324,61);
		forexInfoBt.setStyle("fx-backgroud-color: #333645");
		
		importB=new Button("add");
		text=new Label();
		text2=new Label();
		dataView=new ListView();
		 importB.setLayoutX(490.0);
		 importB.setLayoutY(50.0);
		 text.setText("press add button and chosse a text file to import it");
		 text.setLayoutX(150);
		 text.setLayoutY(50.0);
		 text2.setText("select the dataType");
		 text2.setLayoutX(100);
		 text2.setLayoutY(25.0);
		 dataView.setLayoutX(60);
		 dataView.setLayoutY(100);
		 dataView.setPrefSize(550, 300);
		 
		 importB.setOnAction(event -> {
			 FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Buscar Imagen");
			 fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("TXT", "*.txt"),
	                new FileChooser.ExtensionFilter("IN", "*.in")
		     	);
			 
			 File f = fileChooser.showOpenDialog(null);
			 String line="";
			 try {
				 System.out.println(f.getAbsolutePath());
				 FileReader fr = new FileReader(f.getAbsolutePath());
				 BufferedReader br=new BufferedReader(fr);
				 String[] m,n;
				 int k=0;
				 while((line=br.readLine())!=null) {
					 listData.add(line);
					 m=line.split(",");
					 n=m[1].split(" ");
					 
					 if(dataType.getValue().equals("Stock")) {
						 main.instance().addELementCapMarket(m[0],m[1],m[2]);
					 }
					 else if(dataType.getValue().equals("Curency")){
						 
					 }
					 k++;
					 System.out.println(k);
				 }
				 totalStocks.setText(Integer.toString(main.instance().getNumStocks()));
			 }
			 catch (FileNotFoundException e) {
				 e.printStackTrace();
			 } catch (IOException e) {
				 e.printStackTrace();
			 } catch (ElementException e) {
				e.printStackTrace();
			}
		dataView.setItems(listData);
		dataView.refresh();
		
		main.instance().setAllCompareConditionStocks();
		});
		 
		 capMarketInfoBt.setOnAction(event ->{
			 showCapMarketInfoBt(event);
		 });
		 
		 showInfoByDate.setOnAction(event ->{
			 try {
				showStockPricesInfoByDate(event);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 });
		 
		 forexInfoBt.setOnAction(event ->{
			 showForexInfoBt(event);
		 });
		 
		 test.setOnAction(event ->{
			 test(event);
		 });
		 
		 
	}
	


}
