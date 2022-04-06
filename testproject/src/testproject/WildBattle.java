package testproject;

import java.util.ArrayList;

public class WildBattle {
	private Monster wildMonster;
	
	public WildBattle(Monster wildMonster) {
		this.wildMonster=wildMonster;
	}
	
	void battle(Player player) {
		System.out.println(String.format("A wild %s appears, battle start!", wildMonster.getName()));
		
		boolean outcome=false;
		
		while(true) {
			if(!player.checkIfActiveMonster()) {
				break;
			}
			
			System.out.println("Player turn.");
			player.makeMove(wildMonster);
			
			if(!wildMonster.isAwake()) {
				outcome=true;
				break;
			}
			
			System.out.println(String.format("Wild Monster turn: %s", wildMonster.toString()));
			wildMonster.makeMove(player.getActiveMonster());
		}
		
		if(outcome) {
			System.out.println("You won!");
			System.out.println(String.format("%s joins your team!", wildMonster.getName()));
			wildMonster.rest();
			player.addMonster(wildMonster);
			return;
		}
		else {
			System.out.println(String.format("%s eliminated your entire team! So Powerful", wildMonster.getName()));
			return;
		}
	}
	
	public static void main(String[] args) {
		WildBattle battle = new WildBattle(new Monster("lizard", 15, 40));
		ArrayList<Monster> team = new ArrayList<Monster>();
		team.add(new Monster("goat", 20, 30));
		team.add(new Monster("crow", 30, 5));
		Player player = new Player("player_name", 0, team, new ArrayList<Purchaseable>());
		battle.battle(player);
		System.out.println(player.toString());
	}
}
