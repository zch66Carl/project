package testproject;

import java.util.ArrayList;

import testproject.items.HealingItem;
import testproject.items.Item;
import testproject.monsters.Monster;

/**
 * Handles generation of Monsters, Players, and Items, given the day and difficulty (1-3).
 *
 */
public class Generation {
	private static int uniqueName = 101;
	
	public static Monster generateMonster(int day, int diff, boolean isPlayerMonster, boolean isWildMonster) {
		String name = ""+uniqueName++;//TODO: actual names
		
		return new Monster(name, 0, 0);
	}
	
	public static Item generateItem(int day, int diff, boolean enemyItem) {
		return new HealingItem("small", 1);
	}
	
	public static Player generateEnemyTeam(int day, int diff) {
		ArrayList<Monster> mon = new ArrayList<Monster>();
		mon.add(generateMonster(day, diff, false, false));
		mon.add(generateMonster(day, diff, false, false));
		return new Player("bob"+uniqueName++, 0, mon, new ArrayList<Item>());
	}
}
