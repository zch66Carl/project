package testproject;

import java.util.Random;
import java.util.ArrayList;
public class Shop {
	private Purchaseable[] stock = new Purchaseable[9];
	
	public Purchaseable[] getStock() {
		return stock;
	}
	
	
	public void refreshStock() {
		Random rand = new Random();
		stock[0] = new Monster("Monster 1", rand.nextInt(11)+15, rand.nextInt(21)+35);
		stock[1] = new Monster("Monster 2", rand.nextInt(11)+15, rand.nextInt(21)+35);
		stock[2] = new Monster("Monster 3", rand.nextInt(11)+15, rand.nextInt(21)+35);
		stock[3] = new HealingItem("Small", rand.nextInt(5)+1);
		stock[4] = new HealingItem("Medium",rand.nextInt(5)+1);
		stock[5] = new HealingItem("Large",rand.nextInt(5)+1);
		stock[6] = new StatsItem("Small", rand.nextInt(5)+1);
		stock[7] = new StatsItem("Medium", rand.nextInt(5)+1);
		stock[8] = new StatsItem("Large", rand.nextInt(5)+1);
	}
	
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
	
	public static void main(String[] arg) {
		ArrayList<Monster> team = new ArrayList<Monster>();
		
		team.add(new Monster("Tiger", 26, 75));
		
		
		Player player = new Player("Player 1", 500, team, new ArrayList<Item>());
		player.setInventory();
		Shop shop = new Shop();
		shop.refreshStock();
		
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
