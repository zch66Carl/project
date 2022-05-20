package commandline;

import game.GameEnvironment;
import game.Player;

/**
 * The end of the command line program.
 */
public class GameOverCommandLine {
	/**
	 * Displays the final game information (length, gold, score).
	 * @param env The GameEnvironment entity.
	 */
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
