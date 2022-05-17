package commandline;

import testproject.GameEnvironment;
import testproject.monsters.Monster;

public class Start {
	private static String getName() {
		while(true) {
			IO.textOut("Enter a player name (must be 3-15 characters and alphabetical only):");
			String name = IO.getString();
			if(name.length()>=3 && name.length()<=15) {
				boolean isAlphabetic = true;
				for(char c : name.toCharArray()) {
					if(!Character.isAlphabetic(c)) isAlphabetic=false;
				}
				if(isAlphabetic) return name;
			}
			IO.textOut("Invalid name.");
		}
	}
	
	public static void main(String[] args) {
		GameEnvironment env = new GameEnvironment();
		IO.textOut("Welcome to the command line application.");
		String name = getName();
		IO.textOut("Enter the number of days the game should run, between 5 and 15");
		int days = IO.getInt(5, 15);
		IO.textOut("Enter the difficult, between 1 and 3");
		int diff = IO.getInt(1, 3);
		env.setNumDays(days);
		env.setDifficulty(diff);
		
		env.getPlayer().setName(name);
		
		//select starting monster.
		Monster one = new Monster("one", 1);
		Monster two = new Monster("two", 1);
		IO.textOut("Choose a starting monster:");
		IO.textOut("0 "+one.toString());
		IO.textOut("1 "+two.toString());
		int monst = IO.getInt(0, 1);
		if(monst==0) env.getPlayer().addMonster(one);
		else env.getPlayer().addMonster(two);
		
		env.getPlayer().setGold(15*(3-diff));//start with 0 gold on hard, 30 on easy and 15 on medium.
		
		Main mainRun = new Main(env);
		mainRun.run();
	}
}
