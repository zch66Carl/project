package game.monsters;

import java.util.Random;

/**
 * A special type of Monster, with a second attack which instant kills either the enemy or itself randomly.
 */
public class RiskyMonster extends Monster {
	/**
	 * Used for name generation.
	 */
	private static String[] types = {"Mosquito", "Ant", "Grub", "Worm", "Cicada", "Bee", "Spider"};
	
	/**
	 * Calls the Monster constructor, and then adds the attack string of the one shot attack.
	 * @param name String. The monsters name.
	 * @param startLevel int. The monster's starting level.
	 */
	public RiskyMonster(String name, int startLevel) {
		super(name, startLevel);
		attacks.add("One shot, (but for whom?).");
	}
	
	/**
	 * Generates a random name for this type of monster, using the "One hit" descriptor always, and the RiskyMonster
	 * types strings.
	 * @return A random name for a RiskyMonster
	 */
	public static String getRandomName() {
		Random rand = new Random();
		return "One hit " + types[rand.nextInt(types.length)];
	}
	
	/**
	 * Performs one shot attack if move is 1, else calls base Monster makeMove().
	 */
	public String makeMove(int move, Monster enemy) {
		if(move == 1) return oneShot(enemy);
		return super.makeMove(move, enemy);
	}
	/**
	 * 50% chance to make a one shot attack, else makes a basic attack.
	 */
	public String makeRandomMove(Monster enemy) {
		Random rand = new Random();
		int move=rand.nextInt(2);
		if(move==0) return oneShot(enemy);
		else return super.makeRandomMove(enemy);
	}
	
	
	/**
	 * Has a 50% chance of one shotting the enemy and a 50% chance of oneshotting itself.
	 * @param enemy Monster. The enemy monster to attack.
	 * @return String. A description of the attack, and who it hit.
	 */
	private String oneShot(Monster enemy) {
		Random rand = new Random();
		if(rand.nextInt(2)==0) {
			return "Oops\n" + dealDamageToSelf(10000);
		}
		return enemy.dealDamageToSelf(10000);
	}
}
