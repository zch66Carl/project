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
		if(enemyItem) itemSize += 5;//Makes better items more common for enemies.
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
		int numMonsters = 2 + day / 6;//team size of 2 untill day 5, 3 from 6-11,4 from 12 to 15
		for(int i=0; i<numMonsters; i++) {
			mon.add(Generation.generateMonster(day, diff, false, false));
		}
		
		ArrayList<Item> items = new ArrayList<Item>();
		int numItems = 1 + day / 4; //1 from 1-3, 2 from 4-7, 3 from 8-11, 4 from 12-15
		for(int i=0; i<numItems; i++) {
			items.add(Generation.generateItem(day, diff, true));
		}
		
		Random rand = new Random();
		String[] descriptors = {"Dangerous", "Goofy", "Deadly", "Cheeky", "Dastardly", "Venemous", "Sneaky", "Furious"};
		String[] names = {"Dave", "Emily", "Bob", "Robert", "Janus", "John", "Philip", "Steve", "Lisa", "Caroline"};
		String name = descriptors[rand.nextInt(descriptors.length)] + " " +names[rand.nextInt(names.length)];
		
		return new Player(name, 0, mon, items);
	}
}
