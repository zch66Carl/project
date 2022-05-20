package testproject.monsters;

import java.util.Random;

/**
 * A special type of Monster, with a second attack which applies a debuff to the enemies damage.
 */
public class DebuffMonster extends Monster {
	/**
	 * Used for name generation.
	 */
	private static String[] types = {"Hyena", "Panda"};
	
	/**
	 * Calls the Monster constructor, and then adds the attack string of the debuff attack.
	 * @param name The monsters name.
	 * @param startLevel The monster's starting level.
	 */
	public DebuffMonster(String name, int startLevel) {
		super(name, startLevel);
		attacks.add("Debuff attack.");
	}
	
	/**
	 * Generates a random name for this type of monster, using the base Monster descriptives and the DebuffMonster types strings.
	 * @return A random name for a DebufMonster
	 */
	public static String getRandomName() {
		Random rand = new Random();
		return descriptives[rand.nextInt(descriptives.length)] + " " + types[rand.nextInt(types.length)];
	}
	
	/**
	 * Performs debuff attack if move is 1, else calls base Monster makeMove().
	 */
	public String makeMove(int move, Monster enemy) {
		if(move == 1) return debuff(enemy);
		return super.makeMove(move, enemy);
	}
	/**
	 * 50% chance to make a debuff attack, else makes a basic attack.
	 */
	public String makeRandomMove(Monster enemy) {
		Random rand = new Random();
		int move=rand.nextInt(2);
		if(move==0) return debuff(enemy);
		else return super.makeRandomMove(enemy);
	}
	
	
	/**
	 * Applies a debuff to the enemy for 3 turns of a third of this monster's attack damage.
	 * @param enemy The enemy Monster to apply the debuff to.
	 * @return A string describing the debuff.
	 */
	private String debuff(Monster enemy) {
		enemy.addDamageBuff(-getTotalDamage()/3, 3);
		return "Debuffed " + enemy.getName() + " by " + getTotalDamage()/3;
	}
}
