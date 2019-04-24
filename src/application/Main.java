package application;
	
import java.io.IOException;

import exceptions.ElementException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.PrincipalMarket;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	private static Main instanceMain;
	private PrincipalMarket pm;
	
	public Main(){
        Main.instanceMain=this;
        pm=new PrincipalMarket();
    }
	
	 public static Main instance(){
	        return instanceMain;
	  }
	 
	 public int getNumStocks() {
		 return pm.getNumStocks();
	 }
	 
	 public void addELementCapMarket(String market,String date,String value) throws ElementException {
		 pm.addELementCapMarket(market, date, value);
	 }
	 
	 public double highestStockPriceDate(String initialDate, String finalDate) throws NumberFormatException, ElementException {
		return  pm.highestStockPriceDate(initialDate, finalDate);
	}
	
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Index.fxml"));
        
        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
	}
	
	
	public static void main(String[] args) {
 		launch(args);
	}
}
