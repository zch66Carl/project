package game.monsters;

import java.util.Random;

/**
 * A special type of Monster with a poison attack.
 */
public class PoisonMonster extends Monster {
	/**
	 * Used for name generation.
	 */
	private static String[] types = {"Frog", "Snake"};
	
	/**
	 * Calls base Monster constructor and adds attack string for poison attack.
	 * @param name String. The mosnter's name.
	 * @param startLevel int. The monster's start level.
	 */
	public PoisonMonster(String name, int startLevel) {
		super(name, startLevel);
		attacks.add("Poison attack.");
	}
	
	/**
	 * Generates a random name for PoisonMonster
	 * @return A random name for PoisonMonster
	 */
	public static String getRandomName() {
		Random rand = new Random();
		return descriptives[rand.nextInt(descriptives.length)] + " " + types[rand.nextInt(types.length)];
	}
	
	/**
	 * If move is 1 makes poison attack, else makes base class move.
	 */
	public String makeMove(int move, Monster enemy) {
		if(move == 1) return poison(enemy);
		return super.makeMove(move, enemy);
	}
	/**
	 * Randomly makes poison attack or base class move with 50% probability of each.
	 */
	public String makeRandomMove(Monster enemy) {
		Random rand = new Random();
		int move=rand.nextInt(2);
		if(move==0) return poison(enemy);
		else return super.makeRandomMove(enemy);
	}
	
	/**
	 * The poison attack, dealing half this monster's attack damage to the enemy, and then adding a persistent damage attack of 
	 * one fifth of this monster's total damage, which lasts for five turns.
	 * @param enemy Monster. The enemy monster.
	 * @return String. A description of the attack.
	 */
	private String poison(Monster enemy) {
		String ret = "";
		ret += enemy.dealDamageToSelf(getTotalDamage()/2) + "\n";
		int poison = getTotalDamage()/5;
		if(poison==0) poison=1;
		ret += enemy.dealPersistentDamageToSelf(poison, 5);
		return ret;
	}
}
