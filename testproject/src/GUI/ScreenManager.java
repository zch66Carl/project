package GUI;

import game.GameEnvironment;
import game.Player;

/**
 * Contains all methods that manages screen transitions.
 * @author chenz
 *
 */
public class ScreenManager {
	private GameEnvironment env;
	public GameEnvironment getEnv() {
		return env;
	}
	
	public void launchMainScreen() {
		MainScreen mainWindow = new MainScreen(this);
	}
	public void closeMainScreen(MainScreen mainWindow) {
		mainWindow.closeWindow();
	}
	public void launchSetupScreen() {
		env = new GameEnvironment();
		SetupScreen setupWindow = new SetupScreen(this);
	}
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
		env.preDayLogic();
		env.updateBattles();
		launchMainScreen();
	}
	public void launchBattleScreen(int battleChoice) {
		if(battleChoice >= env.getBattles().size()) {
			BattleScreen battleWindow = new BattleScreen(this, true, 0);
		}
		else{
			BattleScreen battleWindow = new BattleScreen(this, false, battleChoice);
		}
	}
	public void closeBattleScreen(BattleScreen battleWindow) {
		battleWindow.closeWindow();
		launchMainScreen();
	}
	public void justCloseBattleScreen(BattleScreen battleWindow) {
		battleWindow.closeWindow();
	}
	public void launchItemsScreen() {
		ItemScreen itemsWindow = new ItemScreen(this);
	}
	public void closeItemsScreen(ItemScreen itemsWindow) {
		itemsWindow.closeWindow();
		launchMainScreen();
	}
	public void launchShopScreen() {
		ShopScreen shopWindow = new ShopScreen(this);		
	}
	public void closeShopScreen(ShopScreen shopWindow) {
		shopWindow.closeWindow();
		launchMainScreen();
	}
	public void launchGameOverScreen() {
		GameOverScreen gameOverWindow = new GameOverScreen(this);
	}
	public void closeGameOverScreen(GameOverScreen gameOverWindow) {
		gameOverWindow.closeWindow();
		launchSetupScreen();
	}
	
	public void launchTeamScreen() {
		TeamScreen teamWindow = new TeamScreen(this);
	}
	public void closeTeamScreen(TeamScreen teamWindow) {
		teamWindow.closeWindow();
		launchMainScreen();
	}
	
	
	public static void main(String[] arg) {
		ScreenManager scrMan = new ScreenManager();
		scrMan.launchSetupScreen();
	}
}
