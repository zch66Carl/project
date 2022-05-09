package testproject;

import java.util.ArrayList;
import java.util.Random;

import testproject.display.Display;
import testproject.items.HealingItem;
import testproject.items.Item;
import testproject.items.StatsItem;
import testproject.monsters.Monster;

/**
 * 
 * The Player class contains the player's name, gold, team and inventory. It is also responsible
 *  for making the player or enemy's move before delegating to a specific monster if attack is
 *  chosen.
 *
 */
public class Player {
	private String name;
	private int gold;
	private ArrayList<Monster> team = new ArrayList<Monster>();
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int activeMonsterIndex;
	
	
	public Player(String name, int gold, ArrayList<Monster> team, ArrayList<Item> inventory) {
		this.name=name;
		this.gold=gold;
		this.team=team;
		setInventory();
		for(Item item : inventory) {
			addItem(item);
		}
		activeMonsterIndex=0;
	}
	
	public void setName(String newName) {
		name = newName;
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
	
	public ArrayList<Monster> getTeam() {
		return team;
	}
	public void setInventory() {
		inventory.add(new HealingItem("Small",0));
		inventory.add(new HealingItem("Medium",0));
		inventory.add(new HealingItem("Large",0));
		inventory.add(new StatsItem("Small",0));
		inventory.add(new StatsItem("Medium",0));
		inventory.add(new StatsItem("Large",0));
	}
	
	
	public ArrayList<Item> getInventory() {
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
	
	public void addItem(Item item) {
		for(Item items: inventory) {
			int currentQuantity = items.getQuantity();
			if(items.equals(item)) {
				currentQuantity += 1;
				items.setQuantity(currentQuantity);
			} 
		}
	}
	
	public boolean removeItem(Purchaseable item) {
		if(inventory.contains(item)) {
			inventory.remove(item);
			return true;
		}
		return false;
	}

	public void viewInventory() {
		Display.displayText("Your inventory: ", null, null);
		for(int i=0; i<inventory.size(); i++) {
			Display.displayText((i+1)+": "+inventory.get(i).toString(), null, null);
		}
		Display.displayText("Enter 0 to return or an item index to use that item.", null, null);
		int inp = Display.getInput(null);
		if(inp==0) return;
		inventory.get(inp).useItem(this, getActiveMonster());
	}
	
	public Monster getActiveMonster() {
		return team.get(activeMonsterIndex);
	}
	
	/**
	 * A function to check whether the team can still fight and set the active monster
	 *  variable if the prievious active monster fainted.
	 * @return Returns true if there is at least one monster in the team still able to fight.
	 */
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
	
	/**
	 * Takes player input to choose which move to make out of attack, use item, switch monster
	 *  and flee.
	 * @param enemy The enemy monster the current move is against.
	 */
	public void makeMove(Monster enemy) {
		Display.displayText("Active Monster is: "+getActiveMonster().toString(), null, null);
		Display.displayText("Enter 0 to attack, 1 to use an item, or 2 to swap the monster on the field.", null, null);
		int move = Display.getInput(null);
		if(move==0) {
			getActiveMonster().makeMove(enemy);
		}
		else if(move==1) {
			viewInventory();
		}
		else if(move==2) {
			Display.displayText("Options are:", null, null);
			for(int i=0; i<team.size(); i++) {
				if(team.get(i).isAwake()) Display.displayText(i+": "+team.get(i).toString(), null, null);
			}
			int newActive = Display.getInput(null);
			if(newActive>0 && newActive<team.size() && team.get(newActive).isAwake()) {
				activeMonsterIndex=newActive;
			}
			else {
				Display.displayText("Invalid number", null, null);
			}
			//TODO: make changing monster remove any status effects from the monster used before such as isFlying.
		}
		//TODO: fleeing battle
	}
	
	/**
	 * Same as makeMove, but randomly chooses an option instead of taking player input.
	 * @param enemy The enemy monster.
	 */
	public void makeRandomMove(Monster enemy) {
		Random rand = new Random();
		//TODO
		Display.displayText("Player.makeRandomMove() Not implemented yet :(", null, null);
		getActiveMonster().makeRandomMove(enemy);
	}
	
	/**
	 * Full reset of the team's status (set health to full and remove status effects, etc.).
	 */
	public void refreshTeam() {
		for(Monster m:team) {
			m.rest();
		}
	}

	/**
	 * String representation of the player name and all the Monsters in the players team.
	 */
	public String toString() {
		String monstersString = new String();
		for(Monster m : team) monstersString+=m.toString()+"\n";
		return String.format("Player: %s\n"
				+ "Monsters:\n"
				+ "%s", name, monstersString);
	}
}
