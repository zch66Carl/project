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
	public static Monster generateMonster(int day, int diff, boolean isPlayerMonster, boolean isWildMonster) {
		return new Monster("a", 0, 0);
	}
	
	public static Item generateItem(int day, int diff, boolean enemyItem) {
		return new HealingItem("small", 1);
	}
	
	public static Player generateEnemyTeam(int day, int diff) {
		return new Player("bob", 0, new ArrayList<Monster>(), new ArrayList<Item>());
	}
}
