package application;

import java.sql.*;
import java.util.ArrayList;


import javafx.stage.Stage;

public class Datenbank {
	private static final String host = "localhost";
	private static final String port = "3306";
	private static final String databank = "to_buy";
	private static final String username = "root";
	private static final String pw = "";
	
	private static Connection con;
	
	
	//DB is connect
	public static boolean isConnect() {
		return(con == null ? false : true);
	}
	
	//Connecting with the DB
	public static void connect() throws ClassNotFoundException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port +"/"+databank, username, pw);
			System.out.println("Ist verbunden");
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
	}
	
	//For disconnect the DB
	public static void disconnect() {
		if(isConnect()) {
			try {
				con.close();
				System.out.println("Verbindung getrennt");
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//For insert a new Product in the DB
	public static void insert(String toBuy, String value) throws SQLException {
		Statement stmt = con.createStatement();
		String query = "INSERT INTO kaufen(produkt, menge) VALUES('"+toBuy+"','"+value+"');";
		try {
			stmt.execute(query);
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		stmt.close();
		disconnect();
		
	}
	
	//For deleting a Product from DB
	public static void delete(ArrayList<Integer> listOfNum, Stage stage) throws SQLException {
		int id;
		Statement stmt = con.createStatement();
		for(int i=0; i<listOfNum.size();i++) {
			id = listOfNum.get(i);
			System.out.println(id);
			stmt.execute("DELETE FROM kaufen WHERE id=" + id + ";");
		}
		stmt.close();
		disconnect();
		Einkaufsliste.BuyList(stage);
		
		
		
	}
	
	//Create a list from all products from DB
	public static ArrayList<String> readIn() throws SQLException {
		String query = "SELECT * FROM kaufen ORDER BY id ASC";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		ArrayList<String> arl = new ArrayList<>(); 
		int columns = result.getMetaData().getColumnCount();
		while(result.next()) {
			for(int i=1; i<=columns;i++) {
				arl.add(result.getString(i));
				
			}
			
		}
		stmt.close();
		disconnect();
		return arl;
	}

}
