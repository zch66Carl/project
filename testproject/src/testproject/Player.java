package testproject;

import java.util.ArrayList;
public class Player {
	private String name;
	private int gold;
	private ArrayList<Monster> team = new ArrayList<Monster>();
	private ArrayList<Purchaseable> inventory = new ArrayList<Purchaseable>();
	private int activeMonsterIndex;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void setInventory(Item item) {
		inventory.add(item);
	}
	
	public ArrayList<Purchaseable> getInventory() {
		return inventory;
	}
	public void setActiveMonster(int index) {
		activeMonsterIndex = index;
	}
	
	public int getActiveMonster() {
		return activeMonsterIndex;
	}
	
	public boolean addMonster(Monster monster) {
		if(!team.contains(monster)) {
			team.add(monster);
			return true;
		}
		return false;
	}
	
	public boolean removeMonster(Monster monster) {
		if(team.contains(monster)) {
			team.remove(monster);
			return true;
		}
		return false;
	}
	
	public boolean addItem(Item item) {
		if(!inventory.contains(item)) {
			inventory.add(item);
			return true;
		}
		return false;
	}
	
	public boolean removeItem(Item item) {
		if(inventory.contains(item)) {
			inventory.remove(item);
			return true;
		}
		return false;
	}
	
	
}
