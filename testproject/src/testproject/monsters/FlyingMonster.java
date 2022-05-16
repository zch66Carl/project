package testproject.monsters;

import java.util.Random;


/**
 * A special type of Monster, with a fly move, that allows them to not take damage in the next turn.
 *
 */
public class FlyingMonster extends Monster {
	private boolean isFlying;
	
	public FlyingMonster(String name, int startLevel) {
		super(name, startLevel);
		isFlying=false;
		super.getAttackStrings().add(1, "Fly");
	}
	
	/**
	 * Doesn't deal damage if the Monster is currently flying.
	 */
	public String dealDamage(int damageDealt) {
		if(isFlying) {
			return "Attack against " + super.getName() + " missed, as flying.";
		}
		return super.dealDamage(damageDealt);
	}
	
	/**
	 * Sets isFlying to false, i.e. the Monster can only fly for one turn.
	 */
	public void preTurnLogic() {
		isFlying=false;
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
		if(move==0) fly();
		else super.makeRandomMove(enemy);
		return "";
	}
	
	
	public void rest() {
		isFlying=false;
		super.rest();
	}
	
	private String fly() {
		isFlying=true;
		return getName()+" flies into the air.";
	}
}
