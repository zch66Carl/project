package commandline;

import testproject.GameEnvironment;
import testproject.Player;

public class GameOverCommandLine {
	void run(GameEnvironment env) {
		IO.textOut("GAME OVER!");
		IO.textOut("Game lasted " + env.getNumDays() + " days.");
		Player pla = env.getPlayer();
		IO.textOut("Player: " + pla.getName());
		IO.textOut("Remaining gold: " + pla.getGold() + ". Spent gold: " + env.getShop().getGoldSpent());
		IO.textOut("Score: " + pla.getScore());
		IO.textOut("Thanks for playing.");
	}
}
