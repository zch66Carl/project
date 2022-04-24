package testproject;

import testproject.display.Display;

public class TeamBattle {
	private Player enemy;
	
	public TeamBattle(Player enemy) {
		this.enemy = enemy;
	}
	
	public String getEnemyName() {
		return enemy.getName();
	}
	
	public boolean battle(Player player) {
		
		Display.displayText(String.format("Enemy team %s appears, battle start!", enemy.getName()),null, null);
		
		boolean outcome=false;
		
		while(true) {
			if(!player.checkIfActiveMonster()) {
				break;
			}
			
			player.getActiveMonster().preTurnLogic();
			Display.displayText("Player turn.", null, null);
			player.makeMove(enemy.getActiveMonster());
			
			if(!enemy.checkIfActiveMonster()) {
				outcome=true;
				break;
			}
			
			enemy.getActiveMonster().preTurnLogic();
			Display.displayText(String.format("Enemy turn: %s", enemy.getActiveMonster().toString()), null, null);
			enemy.makeRandomMove(player.getActiveMonster());
		}
		
		if(outcome) {
			Display.displayText("You won!",null,null);
			//TODO: reward.
		}
		else {
			Display.displayText(String.format("%s eliminated your entire team! So Powerful", enemy.getName()), null, null);
		}
		enemy.refreshTeam();
		return outcome;
	}
}
