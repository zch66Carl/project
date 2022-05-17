package testproject;

import java.util.ArrayList;
import java.util.Random;

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
	private int score;
	private ArrayList<Monster> team = new ArrayList<Monster>();
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int activeMonsterIndex;
	
	
	public Player(String name, int gold, ArrayList<Monster> team, ArrayList<Item> inventory) {
		this.name=name;
		this.gold=gold;
		this.score = 0;
		this.team=team;
		this.inventory = inventory;
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
	
	public int getScore() {
		return score;
	}
	
	public ArrayList<Monster> getTeam() {
		return team;
	}
	
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void setActiveMonsterIndex(int index) {
		activeMonsterIndex = index;
		team.get(index).setWasActiveDuringBattle(true);
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
		inventory.add(item);
	}
	
	public boolean removeItem(Item item) {
		if(inventory.contains(item)) {
			inventory.remove(item);
			return true;
		}
		return false;
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
		if(team.size()==0) return false;
		if(!team.get(activeMonsterIndex).isAwake()) {
			for(int i=0; i<team.size(); i++) {
				if(team.get(i).isAwake()) {
					activeMonsterIndex=i;
					team.get(i).setWasActiveDuringBattle(true);
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	
	public void preBattle() {
		for(Monster monst : team) {
			monst.setWasActiveDuringBattle(false);
		}
	}
	
	public void rewardPostBattle(int day, int diff, boolean wasWildBattle) {
		for(Monster monst : team) {
			monst.reward();
		}
		score += 10 * diff;
		if(!wasWildBattle) {
			gold += 10 + day + 5 * diff;
		}
	}
	
	/**
	 * Same as makeMove, but randomly chooses an option instead of taking player input.
	 * @param enemy The enemy monster.
	 */
	public String makeRandomMove(Monster enemy) {
		Random rand = new Random();
		//TODO implement fully
		return getActiveMonster().makeRandomMove(enemy);
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
		return name + ", gold: " + gold + ", score: " + score + ", team size: " + team.size() + "/4.";
	}
}
