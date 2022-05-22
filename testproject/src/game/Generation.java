package game;

import java.util.ArrayList;
import java.util.Random;

import game.ItemBuilder.ItemSize;
import game.monsters.*;

/**
 * Handles random generation of Monsters, Players, and Items, given the day and difficulty (1-3).
 */
public class Generation {
	/**
	 * Generates a random monster by using Monster.genRandomName(), and deriving the monster level from the day and difficulty.
	 * The level will start at the current day, and if the desired Monster is wild, 5 is added to the level, if the monster is not
	 * a player monster (is an enemy monster) the level is changed based on the difficulty (-1 if on easy (diff==1), +1 if on hard
	 * (diff==3). A wild monster will never be a base class monster, it will always be a special type.
	 * @param day int. The current day (should be >0)
	 * @param diff int. The difficulty (1-3)
	 * @param isPlayerMonster boolean. If the generated monster is for the player (at start or in shop).
	 * @param isWildMonster boolean. If the generated monster is for a wild battle monster.
	 * @return Monster. A randomly generated monster.
	 */
	private static Monster generateMonster(int day, int diff, boolean isPlayerMonster, boolean isWildMonster) {
		if(day<1) day=1;
		if(diff<1) diff=1;
		if(diff>3) diff=3;
		
		int level = day;
		if(!isPlayerMonster) {
			level+=diff-2;//-1 from player if easy, same if normal, +1 if hard.
		}
		if(isWildMonster) {
			level+=5;//wild monster should be quite high level
		}
		
		Random rand = new Random();
		if(!isWildMonster && rand.nextInt(5)<2) {// 2/5 chance of being normal monster
			return new Monster(Monster.getRandomName(), level);
		}
		
		int type = rand.nextInt(10);// 1/10 chance of risky monster, 3/10 for others 
								  //overall 3/50 for risky, 9/50 for the 3 others each.
		if(type<3) return new FlyingMonster(FlyingMonster.getRandomName(), level);
		if(type<6) return new PoisonMonster(PoisonMonster.getRandomName(), level);
		if(type<9) return new DebuffMonster(DebuffMonster.getRandomName(), level);
		return new RiskyMonster(RiskyMonster.getRandomName(), level);
	}
	
	/**
	 * Public interface for generating player monster (start or shop), using the private general monster generation function.
	 * @param day inte. The current day.
	 * @param diff int. The current difficuly.
	 * @return Monster. The desired monster.
	 */
	public static Monster generatePlayerMonster(int day, int diff) {
		return generateMonster(day, diff, true, false);
	}
	/**
	 * Public interface for generating wild monster (wild battle), using the private general monster generation function.
	 * @param day int. The current day.
	 * @param diff int. The current difficuly.
	 * @return Monster. The desired monster.
	 */
	public static Monster generateWildMonster(int day, int diff) {
		return generateMonster(day, diff, false, true);
	}
	
	
	/**
	 * Generates a random item from the current day, using ItemBuilder to create the items. There is a small chance of the item
	 * being a full revive, and equal chance of the item being a heal or a damage buff. The chance of a larger size item increases
	 * in later days, as well as being more common if the item is for an enemy.
	 * @param day int. The current day.
	 * @param diff int. The difficulty (unused in this function).
	 * @param enemyItem boolean. If this item is for an enemy player, or for the shop.
	 * @return Item. A randomly generated item.
	 */
	public static Item generateItem(int day, int diff, boolean enemyItem) {
		if(day<1) day=1;
		if(diff<1) diff=1;
		if(diff>3) diff=3;
		
		Random rand = new Random();
		int itemType = rand.nextInt(11);//1/11 chance to generate revive, roughly one every two days, equal chance 5/11 to get heal or buff
		int itemSize = rand.nextInt(41) + day;//medium most likely, small less likely as days coninue, and large more likely
		if(enemyItem) itemSize += 10;//Makes better items more common for enemies.
		ItemSize size = (itemSize < 15 ? ItemSize.SMALL : (itemSize < 35 ? ItemSize.MEDIUM : ItemSize.LARGE));
		int price = (size == ItemSize.SMALL ? 5 + day : (size==ItemSize.MEDIUM ? 10 + 2*day : 15 + 3*day));
		if(itemType<5) return ItemBuilder.createHeal(price, size);
		else if(itemType<10) return ItemBuilder.createBuff(price, size);
		else {
			price = 20 + 3*day;
			return ItemBuilder.createRevive(price);
		}
	}
	
	/**
	 * Randomly generates an enemy Player, using the above generate functions for Monster and Item. Enemy players will have team sizes
	 * of 2 in the early days and up to 4 by the end of the game, and 1 item in the early days and up to 4 by the end.
	 * @param day int. The current day. 
	 * @param diff int. The difficulty.
	 * @return Player. A randomly generated enemy Player entity.
	 */
	public static Player generateEnemyTeam(int day, int diff) {
		if(day<1) day=1;
		if(diff<1) diff=1;
		if(diff>3) diff=3;
		
		ArrayList<Monster> mon = new ArrayList<Monster>();
		int numMonsters = 2 + day / 6;//team size of 2 untill day 5, 3 from 6-11, 4 from 12 to 15
		for(int i=0; i<numMonsters; i++) {
			//generate an enemy monster (false, false).
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
