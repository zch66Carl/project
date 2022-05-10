package commandline;

import testproject.GameEnvironment;
import testproject.items.HealingItem;
import testproject.items.Item;
import testproject.monsters.FlyingMonster;
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
		Monster one = new Monster("Basic", 10, 30);
		Monster two = new FlyingMonster("Flying", 5, 30);
		IO.textOut("Choose a starting monster:");
		IO.textOut("0 "+one.toString());
		IO.textOut("1 "+two.toString());
		int monst = IO.getInt(0, 1);
		if(monst==0) env.getPlayer().addMonster(one);
		else env.getPlayer().addMonster(two);
		
		env.getPlayer().addItem(new HealingItem("Small", 1));
		
		Main mainRun = new Main(env);
		mainRun.run();
	}
}
