package testproject.monsters;

import java.util.ArrayList;
import java.util.Random;


/**
 * A special type of Monster, with a fly move, that allows them to not take damage in the next turn.
 *
 */
public class FlyingMonster extends Monster {
	private boolean isFlying;
	private static String[] types = {"Eagle", "Owl", "Bat", "Falcon", "Hawk", "Pigeon"};
	
	public FlyingMonster(String name, int startLevel) {
		super(name, startLevel);
		isFlying=false;
		attacks.add(1, "Fly");
	}
	
	public static String getRandomName() {
		Random rand = new Random();
		return descriptives[rand.nextInt(descriptives.length)] + " " + types[rand.nextInt(types.length)];
	}
	
	
	/**
	 * Doesn't deal damage if the Monster is currently flying.
	 */
	public String dealDamageToSelf(int damageDealt) {
		if(isFlying) {
			return "Attack against " + super.getName() + " missed, as flying.";
		}
		return super.dealDamageToSelf(damageDealt);
	}
	
	/**
	 * Sets isFlying to false, i.e. the Monster can only fly for one turn.
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
	 * Choose either base attack or fly based on user input.
	 */
	public String makeMove(int move, Monster enemy) {
		if(move == 1) return fly();
		return super.makeMove(move, enemy);
	}
	/**
	 * Same as makeMove but random.
	 */
	public String makeRandomMove(Monster enemy) {
		Random rand = new Random();
		int move=rand.nextInt(2);
		if(move==0) return fly();
		else return super.makeRandomMove(enemy);
	}
	
	public void resetStatusEffects() {
		isFlying = false;
		super.resetStatusEffects();
	}
	
	public void rest() {
		resetStatusEffects();
		isFlying=false;
		super.rest();
	}
	
	private String fly() {
		isFlying=true;
		return getName()+" flies into the air.";
	}
}
