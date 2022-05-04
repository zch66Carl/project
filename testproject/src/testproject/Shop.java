package testproject;

import java.util.Random;

import testproject.display.Display;
import testproject.items.HealingItem;
import testproject.items.Item;
import testproject.items.StatsItem;
import testproject.monsters.FlyingMonster;
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
	 * Refresh available stock to buy
	 */
	public void refreshStock(int day, int diff) {
		Random rand = new Random();
		stock[0] = new Monster("Monster 1", rand.nextInt(11)+15, rand.nextInt(21)+35);
		stock[1] = new Monster("Monster 2", rand.nextInt(11)+15, rand.nextInt(21)+35);
		stock[2] = new FlyingMonster("Monster 3 (Flying)", rand.nextInt(11)+15, rand.nextInt(21)+35);
		stock[3] = new HealingItem("Small", rand.nextInt(5)+1);
		stock[4] = new HealingItem("Medium",rand.nextInt(5)+1);
		stock[5] = new HealingItem("Large",rand.nextInt(5)+1);
		stock[6] = new StatsItem("Small", rand.nextInt(5)+1);
		stock[7] = new StatsItem("Medium", rand.nextInt(5)+1);
		stock[8] = new StatsItem("Large", rand.nextInt(5)+1);
	}
	
	/**
	 * Adds special items and monsters to the shop's refresh
	 */
	public void specialRefresh() {
		//To be implemented with special monsters and escape rope
	}
	
		
	public void buyItem(Player player, int itemIndex) {
		int currentGold = player.getGold();
		int stockQuantity = stock[itemIndex].getQuantity();
		System.out.println("Current gold " + currentGold);
		if((currentGold >= stock[itemIndex].getPrice())&&(stock[itemIndex].getQuantity()>0)) {
			if(itemIndex>=3&&itemIndex<9) {
				player.addItem((Item)stock[itemIndex]);
				currentGold -= stock[itemIndex].getPrice();
				stockQuantity -= 1;
				stock[itemIndex].setQuantity(stockQuantity);
				player.setGold(currentGold);
				System.out.println("Item purchased");
			}else if(itemIndex>=0 && itemIndex<3) {
				player.addMonster((Monster)stock[itemIndex]);
				currentGold -= stock[itemIndex].getPrice();
				stockQuantity -= 1;
				stock[itemIndex].setQuantity(stockQuantity);
				player.setGold(currentGold);
				System.out.println("Monster Purchased");
			}
		}
	}
	
	public void sellItem(Player player, Item item) {
		for(Item items: player.getInventory()) {
			if(items.equals(item)&&items.getQuantity()>0) {
				items.setQuantity(items.getQuantity()-1);
				player.setGold(player.getGold()+items.getPrice());
			}
		}
	}
	
	public void sellMonster(Player player, Monster monster) {
		int sellPrice = monster.getPrice();
		player.removeMonster(monster);
		player.setGold(player.getGold()+sellPrice);
	}
	
	public void shop(Player player) {
		while(true) {
			Display.displayText("Current stock:", null, null);
			for(int i=0; i<9; i++) {
				if(stock[i]==null) continue;
				Display.displayText(i+1 + ": "+stock[i].getName()+", price "+stock[i].getPrice() + ", quantity "+stock[i].getQuantity(), null, null);
			}
			Display.displayText("Enter 0 to return or an index to buy a item or monster:", null, null);
			int inp = Display.getInput(null);
			if(inp == 0) return;
			buyItem(player, inp-1);
			//TODO: add an option to sell an item/monster
		}
	}
	
	
	public static void main(String[] arg) {
		ArrayList<Monster> team = new ArrayList<Monster>();
		
		team.add(new Monster("Tiger", 26, 75));
		
		
		Player player = new Player("Player 1", 500, team, new ArrayList<Item>());
		player.setInventory();
		Shop shop = new Shop();
		shop.refreshStock(1, 2);
		
		for(int n=0;n<9;n++) {
			System.out.println(shop.stock[n]);
		}
	
		shop.buyItem(player, 3);
		shop.buyItem(player, 2);
		shop.buyItem(player, 8);
		shop.buyItem(player, 8);
		System.out.println(player.getInventory());
		System.out.println(shop.stock[2].getPrice());
		System.out.println(player.getGold());
		System.out.println(player);
		System.out.println(shop.stock[3]);
		System.out.println(shop.stock[2].getQuantity());
		
	}
	
}
