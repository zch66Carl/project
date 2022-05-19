package testproject.monsters;

import java.util.Random;

public class PoisonMonster extends Monster {
	private static String[] types = {"Frog", "Snake"};
	
	
	public PoisonMonster(String name, int startLevel) {
		super(name, startLevel);
		attacks.add("1: Poison attack.");
	}
	
	public static String getRandomName() {
		Random rand = new Random();
		return descriptives[rand.nextInt(descriptives.length)] + " " + types[rand.nextInt(types.length)];
	}
	
	public String makeMove(int move, Monster enemy) {
		if(move == 1) return poison(enemy);
		return super.makeMove(move, enemy);
	}
	public String makeRandomMove(Monster enemy) {
		Random rand = new Random();
		int move=rand.nextInt(2);
		if(move==0) return poison(enemy);
		else return super.makeRandomMove(enemy);
	}
	
	private String poison(Monster enemy) {
		String ret = "";
		ret += enemy.dealDamageToSelf(getTotalDamage()/2) + "\n";
		ret += enemy.dealPersistentDamageToSelf(getTotalDamage()/5, 5);
		return ret;
	}
}
