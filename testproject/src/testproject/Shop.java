package testproject;


import testproject.monsters.Monster;

import java.util.ArrayList;
public class Shop {
	/**
	 * Shop for player to buy/sell items and monsters
	 */
	private Purchaseable[] stock = new Purchaseable[9];
	public Purchaseable[] getStock() {
		return stock;
	}
	
	/**
	 * Refresh available stock to buy - 3  monsters and 9 items
	 */
	public void refreshStock(int day, int diff) {
		for(int i=0; i<3; i++) stock[i] = Generation.generateMonster(day, diff, true, false);
		for(int i=3; i<9; i++) stock[i] = Generation.generateItem(day, diff, false);
	}
	
		
	public String buyPurchaseable(Player player, int itemIndex) {
		int currentGold = player.getGold();
		if(currentGold >= stock[itemIndex].getPrice()) {
			player.setGold(currentGold - stock[itemIndex].getPrice()); 
			if(itemIndex >= 3) player.addItem((Item)stock[itemIndex]);
			else player.addMonster((Monster)stock[itemIndex]);
			String ret = "Bought " + stock[itemIndex].getName();
			stock[itemIndex] = null;
			return ret;
		}
		return "Not enough gold.";
	}
	
	public String sellPurchaseable(Player player, Purchaseable purchase) {
		player.setGold(player.getGold() + purchase.getSellPrice());
		if(purchase instanceof Monster) player.removeMonster((Monster) purchase);
		else player.removeItem((Item) purchase);
		return "Sold " + purchase.getName() + " for " + purchase.getSellPrice() + " gold.";
	}
	
	
	public static void main(String[] arg) {
		ArrayList<Monster> team = new ArrayList<Monster>();
		
		team.add(new Monster("Tiger", 3));
		
		
		Player player = new Player("Player 1", 500, team, new ArrayList<Item>());
		Shop shop = new Shop();
		shop.refreshStock(1, 2);
		
		for(int n=0;n<9;n++) {
			System.out.println(shop.stock[n]);
		}
	
		shop.buyPurchaseable(player, 3);
		shop.buyPurchaseable(player, 2);
		shop.buyPurchaseable(player, 8);
		shop.buyPurchaseable(player, 8);
		System.out.println(player.getInventory());
		System.out.println(shop.stock[2].getPrice());
		System.out.println(player.getGold());
		System.out.println(player);
		System.out.println(shop.stock[3]);
		
	}
	
}
