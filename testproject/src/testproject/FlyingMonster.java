package testproject;

import java.util.Random;
import java.util.Scanner;

public class FlyingMonster extends Monster {
	private boolean isFlying;
	
	public FlyingMonster(String name, int damage, int maxHealth) {
		super(name, damage, maxHealth);
		isFlying=false;
	}
	
	public void dealDamage(int damageDealt) {
		if(isFlying) {
			Display.displayText("Missed, as enemy is flying.", null, null);
		}
		else {
			super.dealDamage(damageDealt);
		}
	}
	
	void preTurnLogic() {
		isFlying=false;
	}
	void makeMove(Monster enemy) {
		Display.displayText("Enter 0 to attack or 1 to fly.", null, null);
		int inp=Display.getInput(null);
		if(inp==0) super.makeMove(enemy);
		else fly();
	}
	void makeRandomMove(Monster enemy) {
		Random rand = new Random();
		int move=rand.nextInt(2);
		if(move==0) fly();
		else super.makeRandomMove(enemy);
	}
	void rest() {
		isFlying=false;
		super.rest();
	}
	
	void fly() {
		isFlying=true;
		Display.displayText(getName()+" flies into the air.", null, null);
	}
}
