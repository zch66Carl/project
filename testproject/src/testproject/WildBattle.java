package testproject;

import java.util.ArrayList;

public class WildBattle {
	private Monster wildMonster;
	
	public WildBattle(Monster wildMonster) {
		this.wildMonster=wildMonster;
	}
	
	void battle(Player player) {
		Display.displayText(String.format("A wild %s appears, battle start!", wildMonster.getName()),null, null);
		
		boolean outcome=false;
		
		while(true) {
			if(!player.checkIfActiveMonster()) {
				break;
			}
			
			Display.displayText("Player turn.", null, null);
			player.makeMove(wildMonster);
			
			if(!wildMonster.isAwake()) {
				outcome=true;
				break;
			}
			
			Display.displayText(String.format("Wild Monster turn: %s", wildMonster.toString()), null, null);
			wildMonster.makeMove(player.getActiveMonster());
		}
		
		if(outcome) {
			Display.displayText("You won!",null,null);
			Display.displayText(String.format("%s joins your team!", wildMonster.getName()),null,null);
			wildMonster.rest();
			player.addMonster(wildMonster);
			return;
		}
		else {
			Display.displayText(String.format("%s eliminated your entire team! So Powerful", wildMonster.getName()), null, null);
			return;
		}
	}
	
	public static void main(String[] args) {
		WildBattle battle = new WildBattle(new Monster("lizard", 15, 40));
		ArrayList<Monster> team = new ArrayList<Monster>();
		team.add(new Monster("goat", 20, 30));
		team.add(new Monster("crow", 30, 5));
		Player player = new Player("player_name", 0, team, new ArrayList<Item>());
		battle.battle(player);
		System.out.println(player.toString());
	}
}
