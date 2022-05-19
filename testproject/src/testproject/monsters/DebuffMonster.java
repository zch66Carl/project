package testproject.monsters;

import java.util.Random;

public class DebuffMonster extends Monster {
	private static String[] types = {"Hyena", "Panda"};
	
	public DebuffMonster(String name, int startLevel) {
		super(name, startLevel);
		attacks.add("1: Debuff attack.");
	}
	
	public static String getRandomName() {
		Random rand = new Random();
		return descriptives[rand.nextInt(descriptives.length)] + " " + types[rand.nextInt(types.length)];
	}
	
	public String makeMove(int move, Monster enemy) {
		if(move == 1) return debuff(enemy);
		return super.makeMove(move, enemy);
	}
	public String makeRandomMove(Monster enemy) {
		Random rand = new Random();
		int move=rand.nextInt(2);
		if(move==0) return debuff(enemy);
		else return super.makeRandomMove(enemy);
	}
	
	private String debuff(Monster enemy) {
		enemy.addDamageBuff(-getTotalDamage()/3, 3);
		return "Debuffed " + enemy.getName() + " by " + getTotalDamage()/3;
	}
}
