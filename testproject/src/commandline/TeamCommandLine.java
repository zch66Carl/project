package commandline;

import java.util.ArrayList;

import game.GameEnvironment;
import game.monsters.Monster;

/**
 * Command line interface to display the Player's team, reorder the team, and rename individual monsters.
 */
public class TeamCommandLine {
	/**
	 * Displays each monster in the Player's team, and prompts the user to return to the prievious menu, choose a new monster to be
	 * first in the order, or rename one of the monsters.
	 * @param env The GameEnvironment entity.
	 */
	void run(GameEnvironment env) {
		while(true) {
			ArrayList<Monster> team = env.getPlayer().getTeam();
			IO.textOut("Team is:");
			for(int i = 0; i<team.size(); i++) {
				IO.textOut(i + ": " + team.get(i).toString());
			}
			IO.textOut("Choose an option:");
			IO.textOut("0: return to prievious menu.");
			IO.textOut("1: rename a monster.");
			IO.textOut("2: reorder monsters.");
			int inp = IO.getInt(0, 2);
			if(inp == 0) return;
			IO.textOut("Enter monster index.");
			int monInd = IO.getInt(0, team.size() - 1);
			if(inp == 1) {
				IO.textOut("Enter new name:");
				String newName = IO.getString();
				team.get(monInd).setName(newName);
			}
			else if(inp == 2) {
				Monster mon = team.get(monInd);
				team.remove(mon);
				team.add(0, mon);
			}
		}
	}
}
