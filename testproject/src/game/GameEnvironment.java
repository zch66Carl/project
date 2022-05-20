package game;

import java.util.ArrayList;
import java.util.Random;

import game.monsters.Monster;

/**
 * The GameEnvironment contains everything else in the game and keeps track of game status, specifically it contains the Player entity,
 * Shop entity, the battles the player can participate in and the number of days, current day and difficulty.
 * It also contains behaviour to update the battles and the pre and post day logic.
 */
public class GameEnvironment {
	/**
	 * The Player entity.
	 */
	private Player player;
	/**
	 * The number of days the game should run for.
	 */
	private int numDays;
	/**
	 * The current day, should start at 1.
	 */
	private int curDay;
	/**
	 * The difficulty, should be between 1 and 3.
	 */
	private int difficulty;
	/**
	 * The Shop entity.
	 */
	private Shop shop;
	/**
	 * The wildMonster if there is a wild battle on the current day, else is null.
	 */
	private Monster wildMonster;
	/**
	 * An ArrayList of battles that the player can fight on the current day.
	 */
	private ArrayList<Player> battles;
	
	/**
	 * Constructs a default GameEnvironment, with a default empty player, zero days, initializes the current day to 1, the difficulty
	 * to normal, initializes the shop and battle containers.
	 */
	public GameEnvironment() {
		player = new Player("def_name", 0, new ArrayList<Monster>(), new ArrayList<Item>());
		numDays = 0;
		curDay = 0;
		difficulty = 2;
		shop = new Shop();
		wildMonster = null;
		battles = new ArrayList<Player>();
	}
	
	/**
	 * Setter for the player.
	 * @param newPlayer The new player.
	 */
	public void setPlayer(Player newPlayer) {
		player = newPlayer;
	}
	/**
	 * Simple getter for player.
	 * @return The Player entity.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the number of days.
	 * @param numDays The new number of days.
	 */
	public void setNumDays(int numDays) {
		this.numDays = numDays;

	}
	/**
	 * Simple getter for the number of days.
	 * @return The number of days the game should run.
	 */
	public int getNumDays() {
		return numDays;
	}
	
	/**
	 * Simple getter for the current day.
	 * @return The current day.
	 */
	public int getCurDay() {
		return curDay;
	}
	
	/**
	 * Simple setter for the difficulty.
	 * @param newDiff The new difficulty.
	 */
	public void setDifficulty(int newDiff) {
		difficulty = newDiff;
		if(difficulty < 1) difficulty = 1;
		if(difficulty > 3) difficulty = 3;
	}
	/**
	 * Simple getter for difficulty.
	 * @return The difficulty (1 to 3)
	 */
	public int getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Simple getter for the shop.
	 * @return The Shop entity.
	 */
	public Shop getShop() {
		return shop;
	}
	
	/**
	 * Simple getter for the wildMonster.
	 * @return The wild monster Monster, null if there is no wild battle on the current day.
	 */
	public Monster getWildBattleMonster() {
		return wildMonster;
	}
	/**
	 * Simple setter for the wild battle monster.
	 * @param newMonst The new Monster (or null).
	 */
	public void setWildBattleMonster(Monster newMonst) {
		wildMonster = newMonst;
	}
	/**
	 * Simple getter for the battles on the current day.
	 * @return An ArrayList of Player, the enemy players on the current day.
	 */
	public ArrayList<Player> getBattles(){
		return battles;
	}
	
	/**
	 * Updates the days battles, based on the current day and difficulty, wild battles occur every 3 days, and the number of battles
	 * is 3 in the early days, and up to 5 by the end of the game.
	 */
	public void updateBattles() {
		if(curDay%3==1) wildMonster = Generation.generateWildMonster(curDay, difficulty);
		else wildMonster=null;
		
		battles.clear();
		int numBattles = 3 + curDay / 6; //3 battles on first 5 days, 4 on days 6-11, 5 on rest (max day 15)
		for(int i=0; i<numBattles; i++) {
			battles.add(Generation.generateEnemyTeam(curDay, difficulty));
		}
	}

	/**
	 * Takes a new day value and updates the day, refreshes the player team, refreshes the stock of the shop, and updates the battles.
	 * @param day The new day value.
	 */
	public void preDayLogic() {
		curDay++;
		player.refreshTeam();
		shop.refreshStock(curDay, difficulty);
		updateBattles();
	}
	
	/**
	 * Carries out all events after each day ends, checking for level ups for each monster in the player's team, and checking for
	 * the random events of monsters leaving or joining the player's team. Returns messages describing the events that took place.
	 * @return An ArrayList of String messages detailing the random events that took place.
	 */
	public ArrayList<String> postDayLogic() {
		ArrayList<String> ret = new ArrayList<String>();
		for(Monster monst : player.getTeam()) {
			String tmp = monst.levelUpCheck();
			if(tmp!=null) ret.add(tmp);
		}
		
		Random rand = new Random();
		if(player.getTeam().size()<=2 && rand.nextInt(10)==0) {//one tenth chance of gaining monster
			Monster joining = Generation.generatePlayerMonster(curDay, difficulty);
			player.addMonster(joining);
			ret.add(joining.getName() + " joined the team!");
		}
		else if(player.getTeam().size()>2) {
			for(Monster monst : player.getTeam()) {
				int chance = 15;// 1/15 chance of monster leaving
				if(!monst.isAwake()) chance = 10;// 1/10 chance if monster fainted during today and wasn't revived
				if(rand.nextInt(chance) == 0) {
					ret.add(monst.getName() + " got tired of fighting and retired.");
					player.removeMonster(monst);
					break;
				}
			}
		}
		
		player.refreshTeam();
		
		return ret;
	}
}
