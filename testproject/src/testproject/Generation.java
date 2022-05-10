package testproject;

import java.util.ArrayList;
import java.util.Random;
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
		ItemBuilder.ItemSize[] itemSize = {ItemBuilder.ItemSize.SMALL, ItemBuilder.ItemSize.MEDIUM, ItemBuilder.ItemSize.LARGE};
		String[] itemType = {"Healing Item","Stats Item"};
		Random rand = new Random();
		if(itemType[rand.nextInt(1)]=="Healing Item") {
			return ItemBuilder.createHeal(rand.nextInt(20)+10, itemSize[rand.nextInt(2)]);
		}
		return ItemBuilder.createBuff(rand.nextInt(20)+10, itemSize[rand.nextInt(2)]);
	}
	
	public static Player generateEnemyTeam(int day, int diff) {
		ArrayList<Monster> mon = new ArrayList<Monster>();
		mon.add(generateMonster(day, diff, false, false));
		mon.add(generateMonster(day, diff, false, false));
		return new Player("bob"+uniqueName++, 0, mon, new ArrayList<Item>());
	}
}
