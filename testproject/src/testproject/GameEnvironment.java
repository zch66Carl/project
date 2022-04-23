package testproject;

import java.util.ArrayList;

/**
 * The environment managing the structure of the game, including days, choosing actions on each day and
 *  difficulty;
 *
 */
public class GameEnvironment {
	Player player;
	int numDays;
	int difficulty;
	Shop shop;
	WildBattle wildBattle;
	ArrayList<TeamBattle> battles;
	
	/**
	 * This is the setup for the game, taking user input to determine the players name, difficulty, starting
	 * 	monster, number of days, etc.
	 */
	public GameEnvironment() {
		Display.displayText("Welcome to the game.", null, null);
		Display.displayText("Enter a player name", null, null);
		String name = Display.getStringInput(null);
		Display.displayText("Enter the number of days the game should run, between 5 and 20", null, null);
		int days = Display.getInput(null);
		Display.displayText("Enter the difficult, between 1 and 3", name, null);
		int diff = Display.getInput(null);
		player = new Player(name, 0, new ArrayList<Monster>(), new ArrayList<Item>());
		shop = new Shop();
		numDays = days;
		difficulty = diff;
		//select starting monster.
		Monster one = new Monster("Basic", 10, 30);
		Monster two = new FlyingMonster("Flying", 5, 30);
		Display.displayText("Choose a starting monster:", name, null);
		Display.displayText("0 "+one.toString(), null, null);
		Display.displayText("1 "+two.toString(), name, null);
		int mon = Display.getInput(null);
		if(mon==0) player.addMonster(one);
		else player.addMonster(two);
	}
	
	public void chooseBattle() {
		
	}
	
	public void viewInventory() {
		
	}
	
	public void viewTeam() {
		
	}
	
	
	
	public void runGame() {
		for(int day = 0; day<numDays; day++) {
			//Restock shop, battle, rest team, etc.
			while(true) {
				//options: choose and fight a battle, shop, view and use items, view, rename and reorder monster
				//			and end day
				break;
			}
		}
	}
	
	/**
	 * The entry point to the program.
	 * Constructs the game environment, and then runs the game.
	 */
	public static void main(String[] args) {
		GameEnvironment ge = new GameEnvironment();
		ge.runGame();
	}
}
