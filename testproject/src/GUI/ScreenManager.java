package GUI;

import game.GameEnvironment;
import game.Player;

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
		env.preDayLogic(1);
		env.updateBattles();
		launchMainScreen();
	}
	public void launchBattleScreen(Player battle) {
		BattleScreen battleWindow = new BattleScreen(this, battle);
	}
	public void closeBattleScreen(BattleScreen battleWindow) {
		battleWindow.closeWindow();
		launchMainScreen();
	}
	public void launchItemsScreen() {
		ItemScreen itemsWindow = new ItemScreen(this);
	}
	public void closeItemsScreen(ItemScreen itemsWindow) {
		itemsWindow.closeWindow();
		launchMainScreen();
	}
	public void launchShopScreen() {
		env.getShop().refreshStock(env.getCurDay(), env.getDifficulty());
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
	public void launchMonsterScreen() {
		MonsterScreen monsterWindow = new MonsterScreen(this);
	}
	public void closeMonsterScreen(MonsterScreen monsterWindow) {
		monsterWindow.closeWindow();
		launchTeamScreen();
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
