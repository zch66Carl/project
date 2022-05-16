package commandline;

import testproject.GameEnvironment;
import testproject.ItemBuilder;
import testproject.monsters.Monster;

public class Start {
	public static void main(String[] args) {
		GameEnvironment env = new GameEnvironment();
		IO.textOut("Welcome to the command line application.");
		IO.textOut("Enter a player name:");
		String name = IO.getString();
		IO.textOut("Enter the number of days the game should run, between 5 and 15");
		int days = IO.getInt(1, 15);
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
		
		env.getPlayer().addItem(ItemBuilder.createHeal(10, ItemBuilder.ItemSize.SMALL));
		
		Main mainRun = new Main(env);
		mainRun.run();
	}
}
