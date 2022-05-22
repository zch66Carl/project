package game.monsters;

import java.util.ArrayList;
import java.util.Random;


/**
 * A special type of Monster, with a fly move, that allows them to not take damage in the next turn.
 */
public class FlyingMonster extends Monster {
	/**
	 * Determines whether the monster is flying or not.
	 */
	private boolean isFlying;
	/**
	 * Used for naming.
	 */
	private static String[] types = {"Eagle", "Owl", "Bat", "Falcon", "Hawk", "Pigeon"};
	
	/**
	 * Calls the constructor of the base Monster class, and then initializes isFlying and adds the fly attack string.
	 * @param name String. The monster's name.
	 * @param startLevel int. The monster's starting level.
	 */
	public FlyingMonster(String name, int startLevel) {
		super(name, startLevel);
		isFlying=false;
		attacks.add(1, "Fly");
	}
	
	/**
	 * Generates a random name for FlyingMonster type monsters.
	 * @return String. A random name for a FlyingMonster.
	 */
	public static String getRandomName() {
		Random rand = new Random();
		return descriptives[rand.nextInt(descriptives.length)] + " " + types[rand.nextInt(types.length)];
	}
	
	
	/**
	 * Overides the base class method, ignoring the damage and returning a String describing such if flying, else calling the base class method.
	 */
	public String dealDamageToSelf(int damageDealt) {
		if(isFlying) {
			return "Attack against " + super.getName() + " missed, as flying.";
		}
		return super.dealDamageToSelf(damageDealt);
	}
	
	/**
	 * Carries out base class pre turn logic, and then set's isFlying to false, so flying only last's through one enemy attack.
	 */
	public ArrayList<String> preTurnLogic() {
		ArrayList<String> ret = new ArrayList<String>();
		for(String str : super.preTurnLogic()) {
			ret.add(str);
		}
		isFlying=false;
		return ret;
	}
	/**
	 * Makes fly move if move is 1, else calls base class method
	 */
	public String makeMove(int move, Monster enemy) {
		if(move == 1) return fly();
		return super.makeMove(move, enemy);
	}
	/**
	 * Randomly makes fly move or base class attack, with 50% chance
	 */
	public String makeRandomMove(Monster enemy) {
		Random rand = new Random();
		int move=rand.nextInt(2);
		if(move==0) return fly();
		else return super.makeRandomMove(enemy);
	}
	
	/**
	 * Set's isFlying to false, then calls base class method.
	 */
	public void resetStatusEffects() {
		isFlying = false;
		super.resetStatusEffects();
	}
	
	/**
	 * Resets status effects and calls base class method.
	 */
	public void rest() {
		resetStatusEffects();
		isFlying=false;
		super.rest();
	}
	
	/**
	 * Set's isFlying to true and returns a string description of the move.
	 * @return String. A description of the monster flying.
	 */
	private String fly() {
		isFlying=true;
		return getName()+" flies into the air.";
	}
}
