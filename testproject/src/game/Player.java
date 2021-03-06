package game;

import java.util.ArrayList;
import java.util.Random;

import game.monsters.Monster;

/**
 * The Player entity is used both for the player as well as enemy teams you fight against.
 * It contains a name, the players current gold and score, the player's team and inventory and the currently active monster.
 * It also contains behaviour for using items, making random moves for enemies, changing the currently active monster,
 * pre and post battle behaviour as well as rewarding the player post battle and refreshing the player's team.
 */
public class Player {
	/**
	 * The player's name.
	 */
	private String name;
	/**
	 * The player's current amount of gold.
	 */
	private int gold;
	/**
	 * The player's score.
	 */
	private int score;
	/**
	 * An ArrayList of the player's monsters, in the order which they will fight.
	 */
	private ArrayList<Monster> team = new ArrayList<Monster>();
	/**
	 * An ArrayList of the player's items, which they may use on their monsters.
	 */
	private ArrayList<Item> inventory = new ArrayList<Item>();
	/**
	 * The index of the active monster in the team array.
	 */
	private int activeMonsterIndex;
	
	/**
	 * The constructor takes a name, initial gold amount, initial team and inventory. It also initializes the score and 
	 * active monster index to zero.
	 * @param name String. The player's name.
	 * @param gold int. The player's initial gold.
	 * @param team ArrayList&lt;Monster> The player's initial team.
	 * @param inventory ArrayList&lt;Item> The player's initial inventory.
	 */
	public Player(String name, int gold, ArrayList<Monster> team, ArrayList<Item> inventory) {
		this.name=name;
		this.gold=gold;
		this.score = 0;
		this.team=team;
		this.inventory = inventory;
		activeMonsterIndex=0;
	}
	/**
	 * Constructor with empty team and inventory.
	 * @param name String. The player's name
	 * @param gold int. The player's starting gold.
	 */
	public Player(String name, int gold) {
		this(name, gold, new ArrayList<Monster>(), new ArrayList<Item>());
	}
	
	/**
	 * A static method determining if the given string is a valid name (3-15 characters, no special characters).
	 * @param potentialName String. The name to test.
	 * @return boolean. True if the name is valid, false if not.
	 */
	public static boolean isValidName(String potentialName) {
		if(potentialName.length()>=3 && potentialName.length()<=15) {
			boolean isAlphabetic = true;
			for(char c : potentialName.toCharArray()) {
				if(!Character.isAlphabetic(c)) isAlphabetic=false;
			}
			if(isAlphabetic) return true;
		}
		return false;
	}
	
	/**
	 * Simple setter for the player's name.
	 * @param newName String. The new name of this player.
	 */
	public void setName(String newName) {
		name = newName;
	}
	/**
	 * A simple getter for the player's name.
	 * @return String. The player's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Simple setter for the player's gold.
	 * @param gold int. The new amount of gold.
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}
	/**
	 * A simple getter for the player's gold.
	 * @return int. The player's amount of gold.
	 */
	public int getGold() {
		return gold;
	}
	
	/**
	 * Simple getter for the player's score.
	 * @return int. The player's score.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Simple getter for the player's team.
	 * @return ArrayList&lt;Monster>. A list of the monsters in the player's team.
	 */
	public ArrayList<Monster> getTeam() {
		return team;
	}
	
	/**
	 * Simple getter for the player's inventory.
	 * @return ArrayList&lt;Item>. A list of the items in the player's inventory.
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	/**
	 * Uses an item on the given monster, removing the item from the invenventory once done.
	 * @param item Item. The item to use.
	 * @param monster Monster. The Monster the item should be used on.
	 */
	public void useItem(Item item, Monster monster) {
		inventory.remove(item);
		item.useItem(monster);
	}
	
	/**
	 * Sets the active monster index and marks that monster as active during battle, as such this should be used even within the Player
	 * class instead of setting activeMonsterIndex directly.
	 * @param index int. The new index of the active monster.
	 */
	public void setActiveMonsterIndex(int index) {
		if(index >= team.size()) index = team.size()-1;
		if(index < 0) index = 0;
		
		activeMonsterIndex = index;
		team.get(index).setWasActiveDuringBattle(true);
	}
	
	/**
	 * Filters the monsters in the team to return the ones which are awake and aren't the currently active monster.
	 * @return ArrayList&lt;Monster>. The monsters in the team which may be switched to.
	 */
	public ArrayList<Monster> getSwitchableMonsters(){
		ArrayList<Monster> options = new ArrayList<Monster>();
		for(Monster monst : team) {
			if(monst.isAwake() && monst != getActiveMonster()) {
				options.add(monst);
			}
		}
		return options;
	}
	
	/**
	 * Adds a monster to the team.
	 * @param monster Monster. The monster to add.
	 */
	public void addMonster(Monster monster) {
		team.add(monster);
	}
	/**
	 * remove a monster from the team.
	 * @param monster Monster. The monster to remove.
	 */
	public void removeMonster(Monster monster) {
		team.remove(monster);
	}
	
	/**
	 * Adds an Item to the inventory.
	 * @param item Item. The item to add to the inventory.
	 */
	public void addItem(Item item) {
		inventory.add(item);
	}
	/**
	 * Removes an Item from the inventory.
	 * @param item Item. The item to remove.
	 */
	public void removeItem(Item item) {
		inventory.remove(item);
	}
	
	/**
	 * Gets the currently active monster.
	 * @return Monster. The currently active monster.
	 */
	public Monster getActiveMonster() {
		return team.get(activeMonsterIndex);
	}
	
	/**
	 * Sets the active monster index to the index of the given monster.
	 * @param monst Monster. The monster to set the active monster to.
	 */
	public void setActiveMonster(Monster monst) {
		setActiveMonsterIndex(team.indexOf(monst));
	}
	
	/**
	 * A function to check whether the team can still fight and set the active monster variable to the first awake monster
	 * if the current active monster is fainted.
	 * @return boolean. True if there is at least one monster in the team still able to fight.
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
	
	/**
	 * Runs preTurnLogic() for each monster in the team, and returns all the messages from the calls. (for status effects and stuff).
	 * @return ArrayList&lt;String>. A list of messages describing any actions taken pre turn, for displaying to the player.
	 */
	public ArrayList<String> preTurnLogic(){
		ArrayList<String> ret = new ArrayList<String>();
		for(Monster monst : team) {
			for(String str : monst.preTurnLogic()) {
				ret.add(str);
			}
		}
		return ret;
	}
	
	/**
	 * Initializes each monster to not active during battle before the battle starts, so those which are active 
	 * are rewarded apporopriately post battle.
	 */
	public void preBattle() {
		for(Monster monst : team) {
			monst.setWasActiveDuringBattle(false);
		}
		setActiveMonsterIndex(0);
	}
	
	/**
	 * Gets rid of any left over status effects on any of the monsters in the team post battle.
	 */
	public void postBattle() {
		for(Monster monst : team) {
			monst.resetStatusEffects();
		}
	}
	
	/**
	 * Rewards each monster in the team with xp, and rewards the player with an increased score, and if the battle was not a wild
	 * battle, also rewards the player with gold.
	 * @param day int. The current day.
	 * @param diff int. The difficulty.
	 * @param wasWildBattle boolean. Whether or not the battle was a wild battle.
	 */
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
	 * Randomly decides which move to make, If an item may be used, there is a 1/5 chance
	 * an item will be used. There is a 1/15 chance the currently active monster is swapped out. If neither of these actions take place
	 * then the currently active monster will make a random attack against the enemy using Monster.makeRandomMove(enemy).
	 * @param enemy Monster. The enemy monster, to make the move against if an attack is chosen. 
	 * @return String. A description of the move made.
	 */
	public String makeRandomMove(Monster enemy) {
		Random rand = new Random();
		
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		for(Item item : inventory) {
			for(Monster monst : item.getMonstersUsableOn(team)) {
				if(monst != getActiveMonster() && !item.isRevive()) continue;
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
		
		return getActiveMonster().makeRandomMove(enemy);//should happen about 4/5 of the time when an item is usable else 95% of the time.
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
	 * Returns a string representation of the player, listing the name, gold, score and team size.
	 */
	public String toString() {
		return name + ", gold: " + gold + ", score: " + score + ", team size: " + team.size() + "/4.";
	}
}
