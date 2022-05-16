package commandline;

import java.util.ArrayList;

import testproject.GameEnvironment;
import testproject.Player;

public class Main {
	private int day;
	private GameEnvironment env;
	
	public Main(GameEnvironment enviro) {
		env = enviro;
	}
	
	public void run() {
		for(day = 1; day<=env.getNumDays(); day++) {
			env.preDayLogic(day);
			IO.textOut("Day "+day+":");
			while(true) {
				//options: choose and fight a battle, shop, view and use items, view, rename and reorder monster
				//			and end day
				IO.textOut("Choose an option:");
				IO.textOut("0: fight a battle.");
				IO.textOut("1: shop.");
				IO.textOut("2: view inventory.");
				IO.textOut("3: view team.");
				IO.textOut("4: end day.");
				int option = IO.getInt(0, 4);
				if(option==0) chooseBattle();
				else if(option==1) {
					ShopCommandLine shop = new ShopCommandLine();
					shop.run(env);
				}
				else if(option==2) {
					InventoryCommandLine inv = new InventoryCommandLine();
					inv.run(env);
				}
				else if(option==3) {
					TeamCommandLine tea = new TeamCommandLine();
					tea.run(env);
				}
				else break;
			}
			
			IO.textOut("Day "+day+" over.");
			ArrayList<String> overnightMessages = env.postDayLogic();
			if(overnightMessages.size() > 0) {
				IO.textOut("Overnight:");
				for(String str : overnightMessages) {
					IO.textOut(str);
				}
			}
			else {
				IO.textOut("It was a quiet night.");
			}
		}
		GameOverCommandLine gameOver = new GameOverCommandLine();
		gameOver.run(env);
	}
	
	private void chooseBattle() {
		IO.textOut("Choose which battle to fight.");
		int i=0;
		for(Player enemy : env.getBattles()) IO.textOut((i++) + ": " + enemy.getName());
		if(env.getWildBattleMonster() != null) IO.textOut((i++) + ": Wild Battle!");
		IO.textOut(i + ": Cancel selection.");
		int choice = IO.getInt(0, i);
		if(choice == i) return;
		BattleCommandLine battle = new BattleCommandLine();
		boolean wild = (choice >= env.getBattles().size());
		if(battle.run(env, wild, choice)) {
			if(wild) env.setWildBattleMonster(null);
			else env.getBattles().remove(choice);
		}
	}
}
