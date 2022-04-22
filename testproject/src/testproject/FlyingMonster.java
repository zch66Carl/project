package testproject;

import java.util.Random;

/**
 * A special type of Monster, with a fly move, that allows them to not take damage in the next turn.
 *
 */
public class FlyingMonster extends Monster {
	private boolean isFlying;
	
	public FlyingMonster(String name, int damage, int maxHealth) {
		super(name, damage, maxHealth);
		isFlying=false;
	}
	
	/**
	 * Doesn't deal damage if the Monster is currently flying.
	 */
	public void dealDamage(int damageDealt) {
		if(isFlying) {
			Display.displayText("Missed, as enemy is flying.", null, null);
		}
		else {
			super.dealDamage(damageDealt);
		}
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
	public void makeMove(Monster enemy) {
		Display.displayText("Enter 0 to attack or 1 to fly.", null, null);
		int inp=Display.getInput(null);
		if(inp==0) super.makeMove(enemy);
		else fly();
	}
	/**
	 * Same as makeMove but random.
	 */
	public void makeRandomMove(Monster enemy) {
		Random rand = new Random();
		int move=rand.nextInt(2);
		if(move==0) fly();
		else super.makeRandomMove(enemy);
	}
	
	
	public void rest() {
		isFlying=false;
		super.rest();
	}
	
	private void fly() {
		isFlying=true;
		Display.displayText(getName()+" flies into the air.", null, null);
	}
}
