package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Einkaufsliste {
	
	private static int arraySize;
	private static ArrayList<String> arrayList;
	
	
	public static void BuyList(Stage mainStage) {
		//Create the windows and all other elements
		Stage stage = new Stage();
		stage.setTitle("Einkaufsliste");
		VBox vb = new VBox();
		vb.setPadding(new Insets(10,10,10,10));
		
		
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.BOTTOM_CENTER);
		hbox.setSpacing(150.0);
		hbox.setPadding(new Insets(10,10,10,10));
		
		ScrollPane scrollP = new ScrollPane(vb);
		scrollP.setFitToHeight(true);
		scrollP.setPadding(new Insets(10,10,10,10));
		
		
		BorderPane bp = new BorderPane(scrollP);
		bp.setPadding(new Insets(10,10,10,10));
		bp.setBottom(hbox);
		
		
		Button btn = new Button("OK");
		btn.setMinWidth(75);
		
		
		Button btn2 = new Button("Löschen");
		btn2.setMinWidth(75);
		
		hbox.getChildren().addAll(btn, btn2);
		
		Text text = new Text("Muss noch gekauft werden");
		text.setFont(Font.font("Bauhaus 93",FontWeight.BOLD, 30));
		BorderPane.setAlignment(text,Pos.CENTER);
		bp.setTop(text);
		
		////////////////////////////////////////////////////////////////////////////////////
		
		//Read in all Data from DB and create radio Buttons for the products
		try {
			arrayList = new ArrayList<>(Datei.readAllOutDB());
			arraySize = arrayList.size()/3;
		} 
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
		
		//Create Products in a ProductArray
		Produkt[] produkte = new Produkt[arraySize];
		produkte = Datei.createProduct(arrayList, arraySize);
		
		//Create the Radio Buttons for deleting and read all Products in the Vbox 
		CheckBox[] cbs = new CheckBox[arraySize];
		int i=0;
		String product = "";
		String amount="";
		for(Produkt element : produkte) {
			product = "\n"+element.getName();
			amount = element.getAmount()+"";
			
			Text listing = new Text(product);
			listing.setFont(Font.font("Arial",FontWeight.NORMAL,15));
			vb.getChildren().add(listing);
			
			Text listing2 = new Text(amount);
			listing2.setFont(Font.font("Arial",FontWeight.NORMAL,15));
			vb.getChildren().add(listing2);
			
			cbs[i] = new CheckBox(element.getId() + ". " + element.getName()+" löschen"+"\n");
			
			vb.getChildren().add(cbs[i]);
			i++;
		}
		////////////////////////////////////////////////////////////////////////////////
		//Button functions
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage.close();
				
			}
		});
		
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String text, stringNum;
				int number;
				try {
					ArrayList<Integer> listOfNum = new ArrayList<>();
					for(int i=0; i<arraySize;i++) {
						if(cbs[i].isSelected()) {
							text = cbs[i].getText();
							stringNum = text.replaceAll("[a-z.äöüA-Z]", "");
							number = Integer.parseInt(stringNum.trim());
							listOfNum.add(number);
						}
					}
					Datenbank.connect();
					Datenbank.delete(listOfNum, stage);
					stage.close();
				}
				catch(SQLException | ClassNotFoundException e) {
					System.out.println(e);
					
				}
				
			}
		});
		
		
		
		Scene scene = new Scene(bp,370,330);
		stage.setScene(scene);
		stage.show();
	}

}
