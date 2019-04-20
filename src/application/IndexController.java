package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class IndexController implements Initializable{
	
	  @FXML private Button importDataButton;
	  @FXML private Button infoButton;
	  @FXML private AnchorPane pane;
	  
	  private Button importB;
	  private ListView<String> dataView;
	  private Label text,text2;
	  private ComboBox<String> dataType;
	  
	  private ObservableList<String> listData=FXCollections.observableArrayList();
	  private ObservableList<String> options=FXCollections.observableArrayList();
	  
	 public void showImportDataPane(ActionEvent e) {
		 pane.getChildren().add(importB);
		 pane.getChildren().add(text);
		 pane.getChildren().add(text2);
		 pane.getChildren().add(dataView);
		 pane.getChildren().add(dataType);
		 importDataButton.setDisable(true);
		 importDataButton.setStyle("fx-backgroud-color: #0d0d0d");
		
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
		
		options.add("Stock");
		options.add("Currency");
		dataType.setItems(options);
		
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
			 while((line=br.readLine())!=null) {
				 listData.add(line);
				 System.out.println(line);
			 }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataView.setItems(listData);
		dataView.refresh();
		 });
		 
		 
	}
	
	public void handleClicks(ActionEvent e) {
		if(e.getSource()==importDataButton) {
			
		}
		else if(e.getSource()==infoButton) {
			
		}
	}

}
