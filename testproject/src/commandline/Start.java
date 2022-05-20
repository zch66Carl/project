package commandline;

import testproject.GameEnvironment;
import testproject.Generation;
import testproject.Player;
import testproject.monsters.Monster;

/**
 * The entry point for the command line application, it sets up the game environment and passes off the running of the game to Main.
 */
public class Start {
	/**
	 * The entry point to the command line application, takes input from the command line, setting up the GameEnvironment entity and
	 * then passses off the GameEnvironment to Main.
	 * @param args command line arguments (unused)
	 */
	public static void main(String[] args) {
		GameEnvironment env = new GameEnvironment();
		IO.textOut("Welcome to the command line application.");
		
		IO.textOut("Enter a player name (must be 3-15 characters and alphabetical only):");
		String name = IO.getString();
		while(!Player.isValidName(name)) {
			IO.textOut("Invalid name.");
			name = IO.getString();
		}
		
		IO.textOut("Enter the number of days the game should run, between 5 and 15");
		int days = IO.getInt(5, 15);
		
		IO.textOut("Enter the difficult, between 1 and 3");
		int diff = IO.getInt(1, 3);
		
		env.setNumDays(days);
		env.setDifficulty(diff);
		env.getPlayer().setName(name);
		
		//select starting monster.
		Monster one = Generation.generateMonster(1, diff, true, false);
		Monster two = Generation.generateMonster(1, diff, true, false);
		Monster three = Generation.generateMonster(1, diff, true, false);
		IO.textOut("Choose a starting monster:");
		IO.textOut("0 "+one.toString());
		IO.textOut("1 "+two.toString());
		IO.textOut("2 "+three.toString());
		int monst = IO.getInt(0, 2);
		if(monst==0) env.getPlayer().addMonster(one);
		else if(monst==1) env.getPlayer().addMonster(two);
		else env.getPlayer().addMonster(three);
		
		env.getPlayer().setGold(15*(3-diff));//start with 0 gold on hard, 30 on easy and 15 on medium.
		
		Main mainRun = new Main();
		mainRun.run(env);
	}
}
