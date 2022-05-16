package commandline;


import java.util.ArrayList;

import testproject.GameEnvironment;
import testproject.Player;
import testproject.monsters.Monster;

public class BattleCommandLine {
	private boolean makeMove(GameEnvironment env, Monster enemy) {
		Player pla = env.getPlayer();
		while(true) {
			IO.textOut("Active Monster is: "+pla.getActiveMonster().toString());
			IO.textOut("Enter 0 to attack, 1 to use an item, 2 to swap the monster on the field or 3 to flee the battle.");
			int move = IO.getInt(0, 3);
			if(move==0) {
				ArrayList<String> moves = pla.getActiveMonster().getAttackStrings();
				IO.textOut("Attack options are:");
				for(int i=0; i<moves.size(); i++) IO.textOut((i+1) + ": " + moves.get(i));
				IO.textOut("Enter 0 to return to move selection or an index to choose an attack:");
				int inp = IO.getInt(0, moves.size());
				if(inp==0) continue;
				IO.textOut(pla.getActiveMonster().makeMove(inp - 1, enemy));
				return false;
			}
			else if(move==1) {
				InventoryCommandLine inv = new InventoryCommandLine();
				if(inv.run(env)) return false;
			}
			else if(move==2) {
				ArrayList<Monster> options = new ArrayList<Monster>();
				ArrayList<Integer> indices = new ArrayList<Integer>();
				for(int i=0; i<pla.getTeam().size(); i++) {
					Monster monst = pla.getTeam().get(i);
					if(monst.isAwake() && monst != pla.getActiveMonster()) {
						options.add(monst);
						indices.add(i);
					}
				}
				if(options.size() == 0) continue;
				
				IO.textOut("Options are:");
				for(int i=0; i<options.size(); i++) {
					IO.textOut((i+1) + ": "+options.get(i).toString());
				}
				IO.textOut("Enter 0 to return to move selection or an index to choose a new active monster:");
				int newActive = IO.getInt(0, options.size());
				if(newActive == 0) continue;
				pla.setActiveMonsterIndex(indices.get(newActive - 1));
				//TODO: make changing monster remove any status effects from the monster used before such as isFlying.
				return false;
			}
			else if(move == 3) return true;
		}
	}
	
	public boolean run(GameEnvironment env, boolean isWildBattle, int teamBattleInd) {
		Player pla = env.getPlayer();
		Player enemyTeam = new Player("", 0, null, null);
		Monster wildMonster = env.getWildBattleMonster();
		if(!isWildBattle) enemyTeam = env.getBattles().get(teamBattleInd);
		boolean outcome = false;
		
		if(isWildBattle) IO.textOut("Wild "+wildMonster.getName()+" appears!");
		else IO.textOut("Enemy team "+enemyTeam.getName() + " appears, battle start!");
				
		while(true) {
			if(!pla.checkIfActiveMonster()) {
				break;
			}
			
			pla.getActiveMonster().preTurnLogic();
			IO.textOut("Player turn.");
			if(makeMove(env, isWildBattle ? wildMonster : enemyTeam.getActiveMonster())) {
				IO.textOut("Fleeing battle.");
				return false;
			}
			
			if((isWildBattle && !wildMonster.isAwake()) || (!isWildBattle && !enemyTeam.checkIfActiveMonster())) {
				outcome=true;
				break;
			}
			
			Monster enemy = isWildBattle ? wildMonster : enemyTeam.getActiveMonster();
			enemy.preTurnLogic();
			IO.textOut("Enemy turn: " + enemy.toString());
			Monster plaMonst = pla.getActiveMonster();
			IO.textOut(isWildBattle ? wildMonster.makeRandomMove(plaMonst) : enemyTeam.makeRandomMove(plaMonst));
		}
		
		if(outcome) {
			IO.textOut("You won!");
			for(Monster monst : pla.getTeam()) {
				monst.reward();
			}
			if(isWildBattle) {
				wildMonster.rest();
				pla.addMonster(wildMonster);
			}
		}
		else {
			if(isWildBattle) IO.textOut(wildMonster.getName() + " eliminated your entire team! So Powerful");
			else IO.textOut(enemyTeam.getName() + " won!");
		}
		//TODO: refresh enemy?
		return outcome;
	}
}
