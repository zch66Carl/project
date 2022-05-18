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
	
	public void useItem(Item item, Monster monster) {
		inventory.remove(item);
		item.useItem(monster);
	}
	
	public void setActiveMonsterIndex(int index) {
		activeMonsterIndex = index;
		team.get(index).setWasActiveDuringBattle(true);
	}
	
	public int getActiveMonsterIndex() {
		return activeMonsterIndex;
	}
	
	public ArrayList<Monster> getSwitchableMonsters(){
		ArrayList<Monster> options = new ArrayList<Monster>();
		for(Monster monst : team) {
			if(monst.isAwake() && monst != getActiveMonster()) {
				options.add(monst);
			}
		}
		return options;
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
	
	public void setActiveMonster(Monster monst) {
		setActiveMonsterIndex(team.indexOf(monst));
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
	
	
	public ArrayList<String> preTurnLogic(){
		ArrayList<String> ret = new ArrayList<String>();
		for(Monster monst : team) {
			for(String str : monst.preTurnLogic()) {
				ret.add(str);
			}
		}
		return ret;
	}
	
	public void preBattle() {
		for(Monster monst : team) {
			monst.setWasActiveDuringBattle(false);
		}
	}
	
	public void postBattle() {
		for(Monster monst : team) {
			monst.resetStatusEffects();
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
		
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		for(Item item : inventory) {
			for(Monster monst : item.getMonstersUsableOn(team)) {
				items.add(item);
				monsters.add(monst);
			}
		}
		if(rand.nextInt(5)==0 && items.size()>0) {// 1/5 chance to use item
			int choice = rand.nextInt(items.size());
			useItem(items.get(choice), monsters.get(choice));
			return "Used item " + items.get(choice).getName();
		}
		
		ArrayList<Monster> switches = getSwitchableMonsters();
		if(rand.nextInt(15)==0 && switches.size()>0) {//1/15 chance
			int choice = rand.nextInt(switches.size());
			setActiveMonster(switches.get(choice));
			return "Switched Monster to " + getActiveMonster().getName();
		}
		
		return getActiveMonster().makeRandomMove(enemy);//7/10 chance of just attacking in some way
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
