package testproject;

public class Item implements Purchaseable{
	private int price;
	private String name;
	private int quantity;
	
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void useItem(Item item) {
		quantity -= 1;
	}
	
	public boolean equals(Item item) {
		return this.getName()==item.getName();
	}
	
	public String toString() {
		return String.format("%s, price %d", name,price);
	}
}
