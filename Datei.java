package application;


import java.sql.SQLException;
import java.util.ArrayList;

public class Datei {
	
	private static ArrayList<String> arraylist;
	
	//Read all out from DB
	public static ArrayList<String> readAllOutDB() throws ClassNotFoundException {
		try {
			Datenbank.connect();
			arraylist = new ArrayList<>(Datenbank.readIn());
			
		}catch( ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return arraylist;
	}
	
	
	public static Produkt[] createProduct(ArrayList<String> arraylist, int size) {
		
		Produkt[] produkte = new Produkt[size];
		int j=0;
		for(int i = 0; i<arraylist.size(); i+=3) {
			int id = Integer.parseInt(arraylist.get(i));
			String name = arraylist.get(i+1);
			int value = Integer.parseInt(arraylist.get(i+2));
			produkte[j] = new Produkt(id,name,value);
			j++;
		}
		return  produkte;
		
	}
	
	

}
