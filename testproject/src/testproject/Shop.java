package testproject;

import testproject.monsters.Monster;


/**
 * The Shop conatins some stock, that is refreshed each day, and a variable tracking the total gold spent by the player.
 * It also contains the behaviour to buy and sell items, as well as refresh the stock.
 */
public class Shop {
	/**
	 * The total gold spent in the shop over the course of the game.
	 */
	private int spentGold = 0;
	/**
	 * Simple getter for goldSpent.
	 * @return The total amount of gold spent in the shop.
	 */
	public int getGoldSpent() {
		return spentGold;
	}
	
	/**
	 * A static array of the stock, which is always 9 purchaseable objects. Entries are set to null once bought.
	 */
	private Purchaseable[] stock = new Purchaseable[9];
	/**
	 * Simple getter for the stock. Note that some entries may be null.
	 * @return A Purchaseable[] of size 9, the stock of the shop, note that some entries may be null.
	 */
	public Purchaseable[] getStock() {
		return stock;
	}
	
	/**
	 * Uses Generation to generate 3 random Monster objects for the shop and 6 Item objects, using the current day and difficulty.
	 * @param day The current day.
	 * @param diff The difficulty (between 1 and 3)
	 */
	public void refreshStock(int day, int diff) {
		for(int i=0; i<3; i++) stock[i] = Generation.generateMonster(day, diff, true, false);
		for(int i=3; i<9; i++) stock[i] = Generation.generateItem(day, diff, false);
	}
	
		
	/**
	 * Takes an Player object and the itemIndex that the player wishes to buy and if the player has enough gold, buys the object and
	 * adds it to the player's inventory. A string is returned describing the whether or not the item could be bought.
	 * @param player The Player wishing to buy the item.
	 * @param itemIndex The index (into the stock array) of the Purchaseable object desired.
	 * @return A string detailing the success or failure of buying the object.
	 */
	public String buyPurchaseable(Player player, int itemIndex) {
		int currentGold = player.getGold();
		if(currentGold >= stock[itemIndex].getPrice()) {
			player.setGold(currentGold - stock[itemIndex].getPrice()); 
			spentGold += stock[itemIndex].getPrice();
			
			if(itemIndex >= 3) player.addItem((Item)stock[itemIndex]);
			else player.addMonster((Monster)stock[itemIndex]);
			
			String ret = "Bought " + stock[itemIndex].getName();
			stock[itemIndex] = null;
			return ret;
		}
		return "Not enough gold.";
	}
	
	/**
	 * Sells an object to the shop giving the player the sell price of the object in gold.
	 * @param player The Player selling the object.
	 * @param purchase The object being sold to the shop.
	 * @return A String describing the sell.
	 */
	public String sellPurchaseable(Player player, Purchaseable purchase) {
		player.setGold(player.getGold() + purchase.getSellPrice());
		if(purchase instanceof Monster) player.removeMonster((Monster) purchase);
		else player.removeItem((Item) purchase);
		return "Sold " + purchase.getName() + " for " + purchase.getSellPrice() + " gold.";
	}
}
