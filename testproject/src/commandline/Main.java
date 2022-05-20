package commandline;

import java.util.ArrayList;

import game.GameEnvironment;

/**
 * The main loop of the game, where the days are run.
 */
public class Main {
	/**
	 * Runs each day, displaying information to the player, performing pre day logic, post day logic and within each day 
	 * taking input to decide which action to take and delegating each action to other classes e.g. ShopCommandLine.
	 * Once all days have been run, the GameOverCommandLine is run.
	 * @param env The GameEnvironment entity.
	 */
	public void run(GameEnvironment env) {
		while(env.getCurDay() < env.getNumDays()) {
			env.preDayLogic();
			int day = env.getCurDay();
			
			IO.textOut("Day "+day+", " + (env.getNumDays() - day + 1) + " days remaining.");
			IO.textOut(env.getPlayer().toString());
			boolean gameOver = false;
			while(true) {
				//options: choose and fight a battle, shop, view and use items, view, rename and reorder monster
				//			and end day
				IO.textOut("Choose an option:");
				IO.textOut("0: fight a battle.");
				IO.textOut("1: shop.");
				IO.textOut("2: view inventory.");
				IO.textOut("3: view team.");
				IO.textOut("4: end day.");
				IO.textOut("5: end game.");
				int option = IO.getInt(0, 5);
				if(option==0) {
					BattleCommandLine battle = new BattleCommandLine();
					battle.chooseBattle(env);
				}
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
				else if(option==4) break;
				else {
					IO.textOut("Are you sure you want to end the game now? Enter 0 to cancel or 1 to end the game.");
					int inp = IO.getInt(0, 1);
					if(inp==0) continue;
					else {
						gameOver = true;
						break;
					}
				}
			}
			
			if(gameOver) break;
			
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
}
