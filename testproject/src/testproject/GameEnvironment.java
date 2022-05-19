package testproject;

import java.util.ArrayList;
import java.util.Random;

import GUI.*;
import testproject.monsters.Monster;

/**
 * The environment managing the structure of the game, including days, choosing actions on each day and
 *  difficulty;
 *
 */
public class GameEnvironment {
	private Player player;
	private int numDays;
	private int curDay;
	private int difficulty;
	private Shop shop;
	private Monster wildMonster;
	private ArrayList<Player> battles;
	
	public GameEnvironment() {
		player = new Player("def_name", 0, new ArrayList<Monster>(), new ArrayList<Item>());
		numDays = 0;
		difficulty = 0;
		shop = new Shop();
		wildMonster = null;
		battles = new ArrayList<Player>();
	}
	
	public void setPlayer(Player newPlayer) {
		player = newPlayer;
	}
	public Player getPlayer() {
		return player;
	}
	public void setNumDays(int days) {
		numDays = days;
	}
	public int getNumDays() {
		return numDays;
	}
	public int getCurDay() {
		return curDay;
	}
	public void setDifficulty(int newDiff) {
		difficulty = newDiff;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public Shop getShop() {
		return shop;
	}
	public Monster getWildBattleMonster() {
		return wildMonster;
	}
	public void setWildBattleMonster(Monster newMonst) {
		wildMonster = newMonst;
	}
	public ArrayList<Player> getBattles(){
		return battles;
	}
	public void launchMainScreen() {
		MainScreen mainWindow = new MainScreen(this);
	}
	public void closeMainScreen(MainScreen mainWindow) {
		mainWindow.closeWindow();
	}
	public void launchSetupScreen() {
		SetupScreen setupWindow = new SetupScreen(this);
	}
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
		updateBattles();
		launchMainScreen();
	}
	public void launchBattleScreen(Player battle) {
		BattleScreen battleWindow = new BattleScreen(this,battle);
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
		shop.refreshStock(curDay, difficulty);
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
	private void updateBattles() {
		if(curDay%5==1) wildMonster = Generation.generateMonster(curDay, difficulty, false, true);
		else wildMonster=null;
		
		battles.clear();
		int numBattles = 3 + curDay / 6; //3 battles on first 5 days, 4 on days 6-11, 5 on rest (max day 15)
		for(int i=0; i<numBattles; i++) {
			battles.add(Generation.generateEnemyTeam(curDay, difficulty));
		}
	}

	public void preDayLogic(int day) {
		curDay = day;
		player.refreshTeam();
		shop.refreshStock(curDay, difficulty);
		updateBattles();
	}
	
	public ArrayList<String> postDayLogic() {
		ArrayList<String> ret = new ArrayList<String>();
		for(Monster monst : player.getTeam()) {
			String tmp = monst.levelUpCheck();
			if(tmp!=null) ret.add(tmp);
		}
		
		Random rand = new Random();
		if(player.getTeam().size()<=2 && rand.nextInt(10)==0) {//one tenth chance of gaining monster
			Monster joining = Generation.generateMonster(curDay, difficulty, true, false);
			player.addMonster(joining);
			ret.add(joining.getName() + " joined the team!");
		}
		else if(player.getTeam().size()>2) {
			for(Monster monst : player.getTeam()) {
				int chance = 15;// 1/15 chance of monster leaving
				if(!monst.isAwake()) chance = 10;// 1/10 chance if monster fainted during today and wasn't revived
				if(rand.nextInt(chance) == 0) {
					ret.add(monst.getName() + " got tired of fighting and retired.");
					player.removeMonster(monst);
					break;
				}
			}
		}
		
		player.refreshTeam();
		
		return ret;
	}
	
	public static void main(String[] arg) {
		GameEnvironment env = new GameEnvironment();
		env.launchSetupScreen();
	}
}
