package testproject;

import java.util.ArrayList;
public class Shop {
	private ArrayList<Purchaseable> stock = new ArrayList<Purchaseable>();
	private int availableSlots;
	
	public ArrayList<Purchaseable> getStock() {
		return stock;
	}
	
	public void setAvailableSlots(int numberOfSlots) {
		availableSlots = numberOfSlots;
	}
	
	public boolean isFull() {
		if (availableSlots == 0) {
			return true;
		}
		return false;
	}
	
	public void addItem(Purchaseable item) {
		if(!stock.contains(item)&&!isFull()) {
			stock.add(item);
			availableSlots-=1;
		}
	}
	
	public void buyItem(Player player, int itemIndex) {
		int currentGold = player.getGold();
		if(currentGold >= stock.get(itemIndex).getPrice()) {
			player.getInventory().add(stock.get(itemIndex));
			currentGold -= stock.get(itemIndex).getPrice();
		}
	}
	
}
