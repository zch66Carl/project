package GUI;

import game.GameEnvironment;

/**
 * Contains all methods that manages screen transitions, and is entry point for the GUI application.
 */
public class ScreenManager {
	/**
	 * The game environment.
	 */
	private GameEnvironment env;
	/**
	 * Simple getter for the game environment.
	 * @return GameEnvironment. The current game environment.
	 */
	public GameEnvironment getEnv() {
		return env;
	}
	
	/**
	 * Launches the main screen.
	 */
	public void launchMainScreen() {
		new MainScreen(this);
	}
	/**
	 * Closes the given main screen.
	 * @param mainWindow MainScreen. The main screen to close.
	 */
	public void closeMainScreen(MainScreen mainWindow) {
		mainWindow.closeWindow();
	}
	/**
	 * Creates and assigns a new game environment and launches the setup screen.
	 */
	public void launchSetupScreen() {
		env = new GameEnvironment();
		new SetupScreen(this);
	}
	/**
	 * Closes the given setup screen and launches the main screen, beginning the main loop of the game.
	 * @param setupWindow SetupScreen. The setup screen to close.
	 */
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
		env.preDayLogic();
		env.updateBattles();
		launchMainScreen();
	}
	public void launchBattleScreen(int battleChoice) {
		if(battleChoice >= env.getBattles().size()) {
			new BattleScreen(this, true, 0);
		}
		else{
			new BattleScreen(this, false, battleChoice);
		}
	}
	/**
	 * Closes the battle screen, and launches the main screen again.
	 * @param battleWindow BattleScreen. The battle screen to close.
	 */
	public void closeBattleScreen(BattleScreen battleWindow) {
		battleWindow.closeWindow();
		launchMainScreen();
	}
	/**
	 * Only closes the battle screen without opening the main screen, used when gain a wild monster and transition to team screen instead.
	 * @param battleWindow BattleScreen. The battle screen to close.
	 */
	public void justCloseBattleScreen(BattleScreen battleWindow) {
		battleWindow.closeWindow();
	}
	/**
	 * Launches the item screen.
	 */
	public void launchItemsScreen() {
		new ItemScreen(this);
	}
	/**
	 * Closes the given item screen and launches the main screen.
	 * @param itemsWindow ItemScreen. The item screen to close.
	 */
	public void closeItemsScreen(ItemScreen itemsWindow) {
		itemsWindow.closeWindow();
		launchMainScreen();
	}
	/**
	 * Launches the shop screen.
	 */
	public void launchShopScreen() {
		new ShopScreen(this);		
	}
	/**
	 * Closes the given shop screen and launches the main screen.
	 * @param shopWindow ShopScreen. The shop screen to close.
	 */
	public void closeShopScreen(ShopScreen shopWindow) {
		shopWindow.closeWindow();
		launchMainScreen();
	}
	/**
	 * Launches the game over screen.
	 */
	public void launchGameOverScreen() {
		new GameOverScreen(this);
	}
	/**
	 * Closes the given game over screen and launches the setup screen, starting a new game..
	 * @param gameOverWindow GameOverScreen. The game over screen to close.
	 */
	public void closeGameOverScreen(GameOverScreen gameOverWindow) {
		gameOverWindow.closeWindow();
		launchSetupScreen();
	}
	
	/**
	 * Launches the team screen.
	 */
	public void launchTeamScreen() {
		new TeamScreen(this);
	}
	/**
	 * Closes the given team screen and launches the main screen.
	 * @param teamWindow TeamScreen. The team screen to close.
	 */
	public void closeTeamScreen(TeamScreen teamWindow) {
		teamWindow.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Entry point to the program, creates a screen manager and launches the setup screen, beginning the game.
	 * @param arg String[]. Command line arguments (unused).
	 */
	public static void main(String[] arg) {
		ScreenManager scrMan = new ScreenManager();
		scrMan.launchSetupScreen();
	}
}
