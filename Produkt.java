package application;

public class Produkt {
	private int id;
	private String name;
	private int amount;
	
	
	public Produkt(int i, String n, int am) {
		this.id = i;
		this.name = n;
		this.amount = am;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}

}
