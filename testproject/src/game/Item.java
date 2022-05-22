package game;

import java.util.ArrayList;

import game.monsters.Monster;

/**
 *A single Item class, which contains the behviour for heal items, damage buff items, and full revives. Implements purchaseable so
 *it can be bought and sold.
 */
public class Item implements Purchaseable{
	/**
	 * The price of the Item.
	 */
	private int price;
	/**
	 * The name of the Item.
	 */
	private String name;
	
	/**
	 * The heal amount of the item, zero if this is not a heal item.
	 */
	private int healAmount;
	/**
	 * The damage buff amount of the item, zero if this is not a damage buff item.
	 */
	private int buffAmount;
	/**
	 * The duration of the damage buff if this is a damage buff item, always 3.
	 */
	private int buffDuration = 3;
	/**
	 * Whether or not this item is a full revive itm.
	 */
	private boolean isRevive;
	
	/**
	 * Constructs an Item with a name and price, and sets healAmount and buffAmount to zero and isRevive to false, so the Item
	 * is initially not any type of item.
	 * @param name String. The name of this item.
	 * @param price int. The price of this item.
	 */
	public Item(String name, int price) {
		this.name = name;
		this.price = price;
		
		healAmount = 0;
		buffAmount = 0;
		isRevive = false;
	}
	
	/**
	 * Returns the price at which this item is bought from the shop.
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * Returns the price at which this item is sold back to the shop, which is the half the original price.
	 */
	public int getSellPrice() {
		return price / 2;
	}
	
	/**
	 * Returns the name of the item.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Simple setter for healAmount.
	 * @param newHealAmount int. The new heal amount of this item..
	 */
	public void setHealAmount(int newHealAmount) {
		healAmount = newHealAmount;
	}
	/**
	 * Simple setter for buffAmount.
	 * @param newBuffAmount int. The new damage buff value of this item.
	 */
	public void setBuffAmount(int newBuffAmount) {
		buffAmount = newBuffAmount;
	}
	/**
	 * SImple setter for isRevive.
	 * @param newIsRevive boolean. Whether this item is a revive item or not.
	 */
	public void setIsRevive(boolean newIsRevive) {
		isRevive = newIsRevive;
	}
	
	/**
	 * Whether or not the item is a full revive item.
	 * @return boolean. Whether the item is a full revive or not.
	 */
	public boolean isRevive() {
		return isRevive;
	}
	/**
	 * Whether or not the item is a healing item.
	 * @return boolean. Whether the item is a healing item or not.
	 */
	public boolean isHeal() {
		return healAmount > 0;
	}
	
	/**
	 * Filters a list of monsters, returning a list of those which this item may be used on (e.g. heal item not useable on monster at full health).
	 * @param team ArrayList&lt;Monster>. The initial list of monsters.
	 * @return ArrayList&lt;Monster>. Those of the original list which this item may be used on.
	 */
	public ArrayList<Monster> getMonstersUsableOn(ArrayList<Monster> team){
		if(buffAmount > 0) return team;
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		for(Monster monst : team) {
			if(monst.isAwake()) {
				if(monst.getHealth() < monst.getMaxHealth() && isHeal()) monsters.add(monst);
			}
			else if(isRevive) monsters.add(monst);
		}
		return monsters;
	}
	
	/**
	 * Uses this item on the given monster, either healing the monster, calling Monster.rest(), or adding a damage buff to the monster.
	 * @param monster Monster. The monster on which to use this item.
	 */
	public void useItem(Monster monster) {
		if(isHeal()) monster.heal(healAmount);
		if(isRevive) monster.rest();
		if(buffAmount>0) {
			monster.addDamageBuff(buffAmount, buffDuration);
		}
	}
	
	/**
	 * Returns a string representation of the item, containing the item name, and healAmount or buffAmount if applicable.
	 */
	public String toString() {
		if(isRevive) return name + ".";
		if(isHeal()) return name + ", heal amount: " + healAmount + ".";
		return name + ", damage buff: " + buffAmount + ".";
	}

}
