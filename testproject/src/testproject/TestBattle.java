package testproject;

import java.util.Scanner;
import java.util.Random;

public class TestBattle {
	private Monster player;
	private Monster enemy;
	
	public TestBattle(Monster player, Monster enemy) {
		this.player=player;
		this.enemy=enemy;
	}
	
	public void battle() {
		Random rand = new Random();
		Scanner sc = new Scanner(System.in);
		System.out.println("Battle Begin!");
		System.out.println(String.format("The fated battle between you, %s, and your enemy, %s.", 
				player.getName(), enemy.getName()));
		boolean playerWon;
		while(true) {
			if(!player.isAwake()) {
				System.out.println(String.format("Oh no, %s fainted.", player.getName()));
				playerWon=false;
				break;
			}
			System.out.println(String.format("Player turn:\n"
					+ "%s has %s health, %s has %s health.\n"
					+ "enter 0 to attack or 1 to heal:",
					player.getName(), player.getHealth(), enemy.getName(), enemy.getHealth()));
			int move = sc.nextInt();
			if(move==0) {
				enemy.dealDamage(player.getDamage());
				System.out.println(String.format("%s dealt %s damage to %s",
						player.getName(), player.getDamage(), enemy.getName()));
			}
			else if(move==1) {
				player.heal(10);
				System.out.println(String.format("%s healed, health is now %s", 
						player.getName(), player.getHealth()));
			}
			
			if(!enemy.isAwake()) {
				System.out.println(String.format("%s fainted!", enemy.getName()));
				playerWon=true;
				break;
			}
			System.out.println("Enemy turn:");
			if(rand.nextFloat()<0.5) {
				System.out.println("Enemy attacked!");
				player.dealDamage(enemy.getDamage());
			}
			else{
				System.out.println("Enemy healed.");
				enemy.heal(5);
			}
		}
		System.out.println("Battle over.");
		if(playerWon) {
			System.out.println("The player won the battle");
		}
		else {
			System.out.println("The player lost the battle");
		}
	}
	
	public static void main(String[] args) {
		Monster player = new Monster("player monster", 30, 100);
		Monster enemy = new Monster("enemy monster", 40, 95);
		TestBattle battle = new TestBattle(player, enemy);
		battle.battle();
	}
}
