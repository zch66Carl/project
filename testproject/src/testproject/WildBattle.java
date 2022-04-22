package testproject;

import java.util.ArrayList;

/**
 * This class implements a battle against a single wild monster, which will join the player's
 * team when defeated.
 */
public class WildBattle {
	private Monster wildMonster;
	
	public WildBattle(Monster wildMonster) {
		this.wildMonster=wildMonster;
	}
	
	public void reward(Player player) {
		for(Monster activeMonster: player.getTeam()) {
			activeMonster.setCurrentXp(activeMonster.getCurrentXp()+50);
			if(activeMonster.getCurrentXp()>=activeMonster.getXpRequired()) {
				activeMonster.levelUp();
			}
		}
		
	}
	
	/**
	 * 
	 *  Controls the flow of the battle, delegating decision making to the Player and Monster
	 *  entities involved.
	 * @param player The player entity participating in the battle.
	 */
	public void battle(Player player) {
		
		Display.displayText(String.format("A wild %s appears, battle start!", wildMonster.getName()),null, null);
		
		boolean outcome=false;
		
		while(true) {
			if(!player.checkIfActiveMonster()) {
				break;
			}
			
			player.getActiveMonster().preTurnLogic();
			Display.displayText("Player turn.", null, null);
			player.makeMove(wildMonster);
			
			if(!wildMonster.isAwake()) {
				outcome=true;
				break;
			}
			
			wildMonster.preTurnLogic();
			Display.displayText(String.format("Wild Monster turn: %s", wildMonster.toString()), null, null);
			wildMonster.makeRandomMove(player.getActiveMonster());
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

	/**
	 * Quality control tests for the WildBattle class, includes test against specialised monsters,
	 *  as well as players with varying inventories, teams, etc.
	 * After each battle the current state of the player's team is printed to verify that defeated
	 *  monsters join the player's team.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		WildBattle battle = new WildBattle(new Monster("lizard", 15, 40));
		ArrayList<Monster> team = new ArrayList<Monster>();
		team.add(new Monster("goat", 20, 30));
		team.add(new FlyingMonster("crow", 30, 20));
		Player player = new Player("player_name", 0, team, new ArrayList<Item>());
		battle.battle(player);
		
		System.out.println("Player post battle 1:");
		System.out.println(player.toString());
		
		System.out.println("Next battle against flying enemy with item:");
		player.refreshTeam();
		WildBattle battle2 = new WildBattle(new FlyingMonster("eagle", 5, 100));
		player.addItem(new HealingItem("Small", 1));
		
		battle2.battle(player);
		System.out.println(player.toString());
	}
}
