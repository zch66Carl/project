package commandline;

import testproject.Player;

public class TeamSizeLimit {
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
