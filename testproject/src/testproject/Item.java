package testproject;

import java.util.ArrayList;

import testproject.monsters.Monster;

//TODO: consolidate all types of items into this class and deleste subclasses as only need heal, statbuff and revive
//		-> three variables, healAmmount, buffAmount and isRevive

public class Item implements Purchaseable{
	private int price;
	private String name;
	
	private int healAmount;
	private int buffAmount;
	private boolean isRevive;
	
	
	public Item(String name, int price) {
		this.name = name;
		this.price = price;
		
		healAmount = 0;
		buffAmount = 0;
		isRevive = false;
	}
	
	public int getPrice() {
		return price;
	}
	public int getSellPrice() {
		return price / 2;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setHealAmount(int newHealAmount) {
		healAmount = newHealAmount;
	}
	public int getHealAmount() {
		return healAmount;
	}
	public void setBuffAmount(int newBuffAmount) {
		buffAmount = newBuffAmount;
	}
	public int getBuffAmount() {
		return buffAmount;
	}
	public void setIsRevive(boolean newIsRevive) {
		isRevive = newIsRevive;
	}
	public boolean getIsRevive() {
		return isRevive;
	}
	
	
	public ArrayList<Monster> getMonstersUsableOn(ArrayList<Monster> team){
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		for(Monster monst : team) {
			if(monst.isAwake()) {
				if(monst.getHealth() < monst.getMaxHealth() && healAmount != 0) monsters.add(monst);
				if(buffAmount != 0) monsters.add(monst);
			}
			else if(isRevive) monsters.add(monst);
		}
		return monsters;
	}
	
	public void useItem(Monster monster) {
		monster.heal(healAmount);
		if(isRevive) monster.rest();
		//TODO: buffing stats
	}
	
	public String toString() {
		if(isRevive) return name;
		if(healAmount > 0) return name + ", heal amount: " + healAmount;
		return name + ", damage buff: " + buffAmount;
	}
}
