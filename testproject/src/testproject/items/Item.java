package testproject.items;

import testproject.Player;
import testproject.Purchaseable;
import testproject.monsters.Monster;

//TODO: consolidate all types of items into this class and deleste subclasses as only need heal, statbuff and revive
//		-> three variables, healAmmount, buffAmount and isRevive

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
	public void useItem(Player player, Monster monster) {
		quantity -= 1;
	}
	
	public boolean equals(Item item) {
		return this.getName()==item.getName();
	}
	
	public String toString() {
		return String.format("%s, price %d", name,price);
	}
}
