package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class To_Buy {
	
	public static void ToBuy(Stage stageMain) {
		Stage stage = new Stage();
		stage.setTitle("Zum einkaufen");
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();
		gp.setHgap(5);
		gp.setVgap(10);
		gp.setPadding(new Insets(10, 10, 10, 10));
		HBox hb = new HBox();
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(10));
		bp.setCenter(gp);
		bp.setBottom(hb);
		
		//All Label Text create with add on gridpane
		Label text = new Label("Produkt:");
		text.setFont(Font.font("Arial",FontWeight.NORMAL,15));
		gp.add(text, 0, 0);
		Label text2 = new Label("Anzahl:");
		text2.setFont(Font.font("Arial",FontWeight.NORMAL,15));
		gp.add(text2, 0, 1);
		
		//All Text Fields create with add
		TextField field1 = new TextField();
		field1.minWidth(60.0);
		gp.add(field1, 1, 0);
		TextField field2 = new TextField();
		field2.minWidth(60.);
		gp.add(field2, 1, 1);
		
		//Buttons create and add
		
		Button btn1 = new Button("OK");
		btn1.setMinWidth(60.0);
		hb.getChildren().add(btn1);
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e)  {
				try {
					if((field1.getText()!= "")&&(field2.getText()!="")) {
						Datenbank.connect();
						Datenbank.insert(field1.getText(), field2.getText());
						field1.clear();
						field2.clear();
					}
					else {
						Alert alarm = new Alert(AlertType.WARNING, "Alle Felder müssen ausgefühlt sein.");
						alarm.showAndWait();
					}
				} 
				catch (SQLException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
				
		});
		
	
		
		
		Scene scene = new Scene(bp,250,150);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(stageMain);
		stage.setResizable(true);
		stage.setScene(scene);
		stage.show();
		
	}

}
