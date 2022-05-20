package commandline;


import java.util.ArrayList;

import testproject.GameEnvironment;
import testproject.Player;
import testproject.monsters.Monster;

/**
 * Command line interface for choosing battles, running battles, and making player moves.
 */
public class BattleCommandLine {
	/**
	 * Command line interface to make a move, either attacking (attack strings are displayed and one is chosen), 
	 * using an item (delegated to InventoryCommandLine), switching the currently active monster in your team,
	 * or fleeing the battle. The user will be continually prompted for input until a definitive action is taken, as
	 * the user may choose to cancel selections.
	 * @param env The GameEnvironment entity.
	 * @param enemy The enemy Monster, either the enemy team's active monster or a wild monster.
	 * @return A boolean, true if the player chose to flee the battle.
	 */
	private boolean makeMove(GameEnvironment env, Monster enemy) {
		Player pla = env.getPlayer();
		while(true) {
			IO.textOut("Active Monster is: "+pla.getActiveMonster().toString());
			IO.textOut("Enemy monster is: " + enemy.toString());
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
				ArrayList<Monster> options = pla.getSwitchableMonsters();
				
				if(options.size() == 0) continue;
				
				IO.textOut("Options are:");
				for(int i=0; i<options.size(); i++) {
					IO.textOut((i+1) + ": "+options.get(i).toString());
				}
				IO.textOut("Enter 0 to return to move selection or an index to choose a new active monster:");
				int newActive = IO.getInt(0, options.size());
				if(newActive == 0) continue;
				pla.setActiveMonster(options.get(newActive - 1));
				return false;
			}
			else if(move == 3) return true;
		}
	}
	
	/**
	 * Comman line interface to run a battle, runs pre battle logic, each turn with makeMove() and Player.makeRandomMove() and handles
	 * the rewards if the player wins the battle. If a wild battle is won the player gains the wild monster.
	 * @param env The GameEnvironment entity.
	 * @param isWildBattle Whether or not it is a wild battle being run.
	 * @param teamBattleInd The index of the enemy Player in the game environment's battle list.
	 * @return A boolean, whether or not the battle was won.
	 */
	public boolean run(GameEnvironment env, boolean isWildBattle, int teamBattleInd) {
		Player pla = env.getPlayer();
		Player enemyTeam = new Player("", 0, null, null);
		Monster wildMonster = env.getWildBattleMonster();
		if(!isWildBattle) {
			enemyTeam = env.getBattles().get(teamBattleInd);
			enemyTeam.preBattle();
		}
		
		boolean outcome = false;
		pla.preBattle();
		
		if(isWildBattle) IO.textOut("Wild "+wildMonster.getName()+" appears!");
		else IO.textOut("Enemy team led by "+enemyTeam.getName() + " appears, battle start!");
				
		while(true) {
			ArrayList<String> preTurnMessages = pla.preTurnLogic();
			for(String str : preTurnMessages) {
				IO.textOut(str);
			}
			
			if(!pla.checkIfActiveMonster()) {
				break;
			}
			IO.textOut("Player turn.");
			if(makeMove(env, isWildBattle ? wildMonster : enemyTeam.getActiveMonster())) {
				IO.textOut("Fleeing battle.");
				return false;
			}
			
			if(isWildBattle) preTurnMessages = wildMonster.preTurnLogic();
			else preTurnMessages = enemyTeam.preTurnLogic();
			for(String str : preTurnMessages) {
				IO.textOut(str);
			}
			
			if((isWildBattle && !wildMonster.isAwake()) || (!isWildBattle && !enemyTeam.checkIfActiveMonster())) {
				outcome=true;
				break;
			}
			Monster enemy = isWildBattle ? wildMonster : enemyTeam.getActiveMonster();
			IO.textOut("Enemy turn: " + enemy.toString());
			Monster plaMonst = pla.getActiveMonster();
			IO.textOut(isWildBattle ? wildMonster.makeRandomMove(plaMonst) : enemyTeam.makeRandomMove(plaMonst));
		}
		
		pla.postBattle();
		
		
		if(outcome) {
			IO.textOut("You won!");
			if(isWildBattle) {
				wildMonster.rest();
				IO.textOut(wildMonster.getName() + " joins your team!");
				pla.addMonster(wildMonster);
				TeamSizeLimit.check(pla);
			}
			pla.rewardPostBattle(env.getCurDay(), env.getDifficulty(), isWildBattle);
		}
		else {
			if(isWildBattle) IO.textOut(wildMonster.getName() + " eliminated your entire team! So Powerful");
			else IO.textOut(enemyTeam.getName() + " won!");
		}
		if(isWildBattle) wildMonster.rest();
		else enemyTeam.refreshTeam();
		return outcome;
	}

	
	/**
	 * Command line interface for choosing a battle, the possible battles are displayed, and the player can choose to fight one,
	 * or exit to the prievious menu, if a battle is fought and one it is removed from the current battles for this day.
	 * @param env The GameEnvironment entity.
	 */
	public void chooseBattle(GameEnvironment env) {
		if(!env.getPlayer().checkIfActiveMonster()) {
			IO.textOut("Can not fight any battle since your team are all fainted.");
		}
		
		IO.textOut("Choose which battle to fight.");
		int i=0;
		for(Player enemy : env.getBattles()) IO.textOut((i++) + ": " + enemy.getName());
		if(env.getWildBattleMonster() != null) IO.textOut((i++) + ": Wild Battle!");
		IO.textOut(i + ": Cancel selection.");
		int choice = IO.getInt(0, i);
		if(choice == i) return;
		boolean wild = (choice >= env.getBattles().size());
		if(run(env, wild, choice)) {
			if(wild) env.setWildBattleMonster(null);
			else env.getBattles().remove(choice);
		}
		IO.textOut("Team status:");
		for(Monster monst : env.getPlayer().getTeam()) {
			IO.textOut(monst.toString());
		}
	}
}
