package testproject;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Player {
	private String name;
	private int gold;
	private ArrayList<Monster> team = new ArrayList<Monster>();
	private ArrayList<Purchaseable> inventory = new ArrayList<Purchaseable>();
	private int activeMonsterIndex;
	
	public Player(String name, int gold, ArrayList<Monster> team, ArrayList<Purchaseable> inventory) {
		this.name=name;
		this.gold=gold;
		this.team=team;
		this.inventory=inventory;
		activeMonsterIndex=0;
	}
	
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
	public void setActiveMonsterIndex(int index) {
		activeMonsterIndex = index;
	}
	
	public int getActiveMonsterIndex() {
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
	
	public boolean addItem(Purchaseable item) {
		if(!inventory.contains(item)) {
			inventory.add(item);
			return true;
		}
		return false;
	}
	
	public boolean removeItem(Purchaseable item) {
		if(inventory.contains(item)) {
			inventory.remove(item);
			return true;
		}
		return false;
	}
	
	
	public Monster getActiveMonster() {
		return team.get(activeMonsterIndex);
	}
	public boolean checkIfActiveMonster() {
		if(!team.get(activeMonsterIndex).isAwake()) {
			for(int i=0; i<team.size(); i++) {
				if(team.get(i).isAwake()) {
					activeMonsterIndex=i;
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	public void makeMove(Monster enemy) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Active Monster is: "+getActiveMonster().toString());
		System.out.println("Enter 0 to attack, 1 to use an item, or 2 to swap the monster on the field.");
		int move = sc.nextInt();
		if(move==0) {
			System.out.println(String.format("Dealt %s damage to %s.", getActiveMonster().getDamage(), enemy.getName()));
			enemy.dealDamage(getActiveMonster().getDamage());
		}
		else if(move==1) {
			System.out.println("Items not usable yet :(");
		}
		else if(move==2) {
			System.out.println("Options are:");
			for(int i=0; i<team.size(); i++) {
				if(team.get(i).isAwake()) System.out.println(i+": "+team.get(i).toString());
			}
			int newActive = sc.nextInt();
			if(newActive>0 && newActive<team.size() && team.get(newActive).isAwake()) {
				activeMonsterIndex=newActive;
			}
			else {
				System.out.println("Invalid number");
			}
		}
	}
	public void makeRandomMove(Monster enemy) {
		Random rand = new Random();
		//TODO
	}

	public String toString() {
		String monstersString = new String();
		for(Monster m : team) monstersString+=m.toString()+"\n";
		return String.format("Player: %s\n"
				+ "Monsters:\n"
				+ "%s", name, monstersString);
	}
}
