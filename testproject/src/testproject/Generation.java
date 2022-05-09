package testproject;

import java.util.ArrayList;
import java.util.Random;
import testproject.items.HealingItem;
import testproject.items.Item;
import testproject.items.StatsItem;
import testproject.monsters.Monster;

/**
 * Handles generation of Monsters, Players, and Items, given the day and difficulty (1-3).
 *
 */
public class Generation {
	private static int uniqueName = 101;
	
	public static Monster generateMonster(int day, int diff, boolean isPlayerMonster, boolean isWildMonster) {
		String[] descriptive = {"Fiery","Glowing","Prismatic","Bold","Ebony","Emerald","Golden","Ivory","Scarlet",
								"Violet","Icy","Azure"};
		String[] types = {"Goat","Eagle","Fox","Bat","Lion","Owl","Wolf","Rhino"};
		Random rand = new Random();
		String name = descriptive[rand.nextInt(12)]+" "+types[rand.nextInt(7)];
		
		return new Monster(name, 10, 30);
	}
	
	public static Item generateItem(int day, int diff, boolean enemyItem) {
		String[] itemSize = {"Small","Medium","Large"};
		String[] itemType = {"Healing Item","Stats Item"};
		Random rand = new Random();
		if(itemType[rand.nextInt(1)]=="Healing Item") {
			return new HealingItem(itemSize[rand.nextInt(2)],rand.nextInt(4));
		}
		return new StatsItem(itemSize[rand.nextInt(2)], rand.nextInt(4));
	}
	
	public static Player generateEnemyTeam(int day, int diff) {
		ArrayList<Monster> mon = new ArrayList<Monster>();
		mon.add(generateMonster(day, diff, false, false));
		mon.add(generateMonster(day, diff, false, false));
		return new Player("bob"+uniqueName++, 0, mon, new ArrayList<Item>());
	}
}
