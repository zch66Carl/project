package testproject;

import java.util.ArrayList;

import testproject.items.Item;
import testproject.monsters.Monster;

/**
 * The environment managing the structure of the game, including days, choosing actions on each day and
 *  difficulty;
 *
 */
public class GameEnvironment {
	private Player player;
	private int numDays;
	private int curDay;
	private int difficulty;
	private Shop shop;
	private Monster wildMonster;
	private ArrayList<Player> battles;
	
	public GameEnvironment() {
		player = new Player("def_name", 0, new ArrayList<Monster>(), new ArrayList<Item>());
		numDays = 0;
		difficulty = 0;
		shop = new Shop();
		wildMonster = null;
		battles = new ArrayList<Player>();
	}
	
	public void setPlayer(Player newPlayer) {
		player = newPlayer;
	}
	public Player getPlayer() {
		return player;
	}
	public void setNumDays(int newNumDays) {
		numDays = newNumDays;
	}
	public int getNumDays() {
		return numDays;
	}
	public void setDifficulty(int newDiff) {
		difficulty = newDiff;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public Shop getShop() {
		return shop;
	}
	public Monster getWildBattleMonster() {
		return wildMonster;
	}
	public void setWildBattleMonster(Monster newMonst) {
		wildMonster = newMonst;
	}
	public ArrayList<Player> getBattles(){
		return battles;
	}
	
	private void updateBattles() {
		if(curDay%5==1) wildMonster = Generation.generateMonster(curDay, difficulty, false, true);
		else wildMonster=null;
		
		battles.clear();
		for(int i=0; i<3; i++) {
			battles.add(Generation.generateEnemyTeam(curDay, difficulty));
		}
	}

	public void preDayLogic(int day) {
		curDay = day;
		player.refreshTeam();
		shop.refreshStock(curDay, difficulty);
		updateBattles();
	}
	
	public void postDayLogic() {
		//TODO: random events
		//TODO: return Array of String messages about random events to display.
	}
}
