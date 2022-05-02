package testproject;

import java.util.ArrayList;

import testproject.display.Display;
import testproject.items.Item;
import testproject.monsters.FlyingMonster;
import testproject.monsters.Monster;

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
		
		battles = new ArrayList<TeamBattle>();
	}
	
	public void chooseBattle() {
		Display.displayText("Choose which battle to fight.", null, null);
		int i=0;
		for(TeamBattle tb : battles) Display.displayText((i++) + ": " + tb.getEnemyName(), null, null);
		if(wildBattle != null) Display.displayText((i++) + ": Wild Battle!", null, null);
		Display.displayText(i + ": Cancel selection.", null, null);
		int choice = Display.getInput(null);
		if(choice==i) return;
		if(choice>=battles.size()) {
			if(wildBattle.battle(player)) wildBattle=null;
			return;
		}
		if(battles.get(choice).battle(player)) {
			battles.remove(choice);
		}
		return;
	}
	
	public void viewInventory() {
		//TODO: delegate to player
	}
	
	public void updateBattles(int day) {
		if(day%5==0) wildBattle = new WildBattle(Generation.generateMonster(day, difficulty, false, true));
		else wildBattle=null;
		
		battles.clear();
		for(int i=0; i<3; i++) {
			battles.add(new TeamBattle(Generation.generateEnemyTeam(day, difficulty)));
		}
	}
	
	/**
	 * Runs each day, TODO: each option aside from choose battle should be relegated to the
	 *  relevant class, e.g. shopping should be a method in the shop class, so don't need
	 *  to have public methods for such. e.g. Choose an item and use on a monster can happen both
	 *  during battle and in game environment between battles so should be in player class once.
	 */
	public void runGame() {
		for(int day = 1; day<=numDays; day++) {
			player.refreshTeam();
			shop.refreshStock(day, difficulty);
			updateBattles(day);
			Display.displayText("Day "+day+":", null, null);
			while(true) {
				//options: choose and fight a battle, shop, view and use items, view, rename and reorder monster
				//			and end day
				Display.displayText("Choose an option:", null, null);
				Display.displayText("0: fight a battle.", null, null);
				Display.displayText("1: shop.", null, null);
				Display.displayText("2: view inventory.", null, null);
				Display.displayText("3: view team.", null, null);
				Display.displayText("4: end day.", null, null);
				int option = Display.getInput(null);
				if(option==0) chooseBattle();
				else if(option==1) shop.shop(player);
				else if(option==2) player.viewInventory();
				else if(option==3) player.viewTeam();
				else break;
			}
			Display.displayText("Day "+day+" over.", null, null);
		}
		Display.displayText("Game over!", null, null);
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
