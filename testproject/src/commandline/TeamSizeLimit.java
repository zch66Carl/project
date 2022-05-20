package commandline;

import testproject.Player;

/**
 * A command line interface for checking the size of the player's team and promting them to remove a monster if the team is to large,
 * this should be utilized whenever a monster is added to the team in the command line program.
 */
public class TeamSizeLimit {
	/**
	 * Takes the player and checks the team size (max is 4), promting the user to select one to remove one if there are to many.
	 * @param pla The Player entity.
	 */
	public static void check(Player pla) {
		if(pla.getTeam().size()>4) {
			IO.textOut("Too many monsters in team, choose one to remove:");
			for(int i=0; i<pla.getTeam().size(); i++) {
				IO.textOut(i +": " + pla.getTeam().get(i));
			}
			IO.textOut("Enter monster index to remove:");
			int inp = IO.getInt(0, pla.getTeam().size()-1);
			pla.removeMonster(pla.getTeam().get(inp));
			IO.textOut("Monster removed from team.");
		}
	}
}
