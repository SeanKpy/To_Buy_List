package application;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class Main extends Application {
	
	
	@Override
	public void start(Stage stage) {
		
		stage.setTitle("To Buy Liste");	
		stage.setResizable(false);
		BorderPane root = new BorderPane();
		GridPane gp = new GridPane();
		
		
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(10, 10, 10, 10));
		gp.setGridLinesVisible(false);
		root.setCenter(gp);
		
		
		Text titel = new Text("To Do Buy Liste");
		titel.setFont(Font.font("Bauhaus 93",FontWeight.BOLD, 40));
		BorderPane.setAlignment(titel, Pos.CENTER);
		root.setTop(titel);
		
		
		Button btn1 = new Button("Zum Kaufen");
		btn1.setMinWidth(110);
		btn1.setFont(Font.font("Arial",FontWeight.NORMAL,15));
		gp.add(btn1, 0,0);
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				To_Buy.ToBuy(stage);
			}
		});
		
		
		Button btn2 = new Button("Einkaufliste");
		btn2.setFont(Font.font("Arial",FontWeight.NORMAL,15));
		btn2.setMinWidth(110);
		gp.add(btn2, 0, 1);
		
		btn2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Einkaufsliste.BuyList(stage);
				
				
			}
		});
		
		
		Scene scene = new Scene(root,300,300);
		stage.setScene(scene);
		stage.show();
		
		
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}
