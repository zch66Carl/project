package GUI;

import testproject.GameEnvironment;
import testproject.Player;

public class ScreenManager {
	public void launchMainScreen(GameEnvironment env) {
		MainScreen mainWindow = new MainScreen(this, env);
	}
	public void closeMainScreen(MainScreen mainWindow, GameEnvironment env) {
		mainWindow.closeWindow();
	}
	public void launchSetupScreen() {
		GameEnvironment env = new GameEnvironment();
		SetupScreen setupWindow = new SetupScreen(this, env);
	}
	public void closeSetupScreen(SetupScreen setupWindow, GameEnvironment env) {
		setupWindow.closeWindow();
		env.preDayLogic(1);
		env.updateBattles();
		launchMainScreen(env);
	}
	public void launchBattleScreen(GameEnvironment env, Player battle) {
		BattleScreen battleWindow = new BattleScreen(this, env, battle);
	}
	public void closeBattleScreen(BattleScreen battleWindow, GameEnvironment env) {
		battleWindow.closeWindow();
		launchMainScreen(env);
	}
	public void launchItemsScreen(GameEnvironment env) {
		ItemScreen itemsWindow = new ItemScreen(this, env);
	}
	public void closeItemsScreen(ItemScreen itemsWindow, GameEnvironment env) {
		itemsWindow.closeWindow();
		launchMainScreen(env);
	}
	public void launchShopScreen(GameEnvironment env) {
		env.getShop().refreshStock(env.getCurDay(), env.getDifficulty());
		ShopScreen shopWindow = new ShopScreen(this, env);		
	}
	public void closeShopScreen(ShopScreen shopWindow, GameEnvironment env) {
		shopWindow.closeWindow();
		launchMainScreen(env);
	}
	public void launchGameOverScreen(GameEnvironment env) {
		GameOverScreen gameOverWindow = new GameOverScreen(this, env);
	}
	public void closeGameOverScreen(GameOverScreen gameOverWindow) {
		gameOverWindow.closeWindow();
		launchSetupScreen();
	}
	public void launchMonsterScreen(GameEnvironment env) {
		MonsterScreen monsterWindow = new MonsterScreen(this, env);
	}
	public void closeMonsterScreen(MonsterScreen monsterWindow, GameEnvironment env) {
		monsterWindow.closeWindow();
		launchTeamScreen(env);
	}
	public void launchTeamScreen(GameEnvironment env) {
		TeamScreen teamWindow = new TeamScreen(this, env);
	}
	public void closeTeamScreen(TeamScreen teamWindow, GameEnvironment env) {
		teamWindow.closeWindow();
		launchMainScreen(env);
	}
	
	
	public static void main(String[] arg) {
		ScreenManager scrMan = new ScreenManager();
		scrMan.launchSetupScreen();
	}
}
