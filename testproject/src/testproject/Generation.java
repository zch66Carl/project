package testproject;

import java.util.ArrayList;
import java.util.Random;

import testproject.ItemBuilder.ItemSize;
import testproject.monsters.Monster;

/**
 * Handles generation of Monsters, Players, and Items, given the day and difficulty (1-3).
 *
 */
public class Generation {
	private static int uniqueName = 101;
	
	public static Monster generateMonster(int day, int diff, boolean isPlayerMonster, boolean isWildMonster) {
		int level = day;
		if(!isPlayerMonster) {
			level+=diff-2;//-1 from player if easy, same if normal, +1 if hard.
		}
		if(isWildMonster) {
			level+=5;//wild monster should be quite high level
		}
		String[] descriptive = {"Fiery","Glowing","Prismatic","Bold","Ebony","Emerald","Golden","Ivory","Scarlet",
								"Violet","Icy","Azure"};
		String[] types = {"Goat","Eagle","Fox","Bat","Lion","Owl","Wolf","Rhino"};
		Random rand = new Random();
		String name = descriptive[rand.nextInt(12)]+" "+types[rand.nextInt(7)];
		
		//TODO: decide specific type of monster, store names in monster classes e.g. so OWL is always a flying monster
		
		return new Monster(name, level);
	}
	
	public static Item generateItem(int day, int diff, boolean enemyItem) {
		Random rand = new Random();
		int itemType = rand.nextInt(11);//1/11 chance to generate revive, roughly one every two days, equal chance 5/11 to get heal or buff
		int itemSize = rand.nextInt(41) + day;//medium most likely, small less likely as days coninue, and large more likely
		ItemSize size = (itemSize < 15 ? ItemSize.SMALL : (itemSize < 35 ? ItemSize.MEDIUM : ItemSize.LARGE));
		int price = (size == ItemSize.SMALL ? 5 + day : (size==ItemSize.MEDIUM ? 10 + 2*day : 15 + 3*day));
		if(itemType<5) return ItemBuilder.createHeal(price, size);
		else if(itemType<10) return ItemBuilder.createBuff(price, size);
		else {
			price = 20 + 3*day;
			return ItemBuilder.createRevive(price);
		}
	}
	
	public static Player generateEnemyTeam(int day, int diff) {
		ArrayList<Monster> mon = new ArrayList<Monster>();
		mon.add(generateMonster(day, diff, false, false));
		mon.add(generateMonster(day, diff, false, false));
		return new Player("bob"+uniqueName++, 0, mon, new ArrayList<Item>());
	}
}
