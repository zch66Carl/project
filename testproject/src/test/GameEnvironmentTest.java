package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.GameEnvironment;
import game.Generation;
import game.Player;

class GameEnvironmentTest {

	@Test
	void updateBattlesTest() {
		GameEnvironment env = new GameEnvironment();
		env.preDayLogic();
		assertEquals(env.getBattles().size(), 3);
		assertTrue(env.getWildBattleMonster() != null);
		for(int i=0; i<12; i++) env.preDayLogic();//increases to day 13
		assertEquals(env.getBattles().size(), 5);
		assertTrue(env.getWildBattleMonster() != null);//13%3=1 so wild monster
		env.preDayLogic();
		assertTrue(env.getWildBattleMonster() == null);
	}
	
	@Test
	void preDayLogic() {
		GameEnvironment env = new GameEnvironment();
		env.preDayLogic();
		for(int i=0; i<9; i++) {
			assertTrue(env.getShop().getStock()[i] != null);
		}
	}
	
	@Test
	void postDayLogicTest() {
		GameEnvironment env = new GameEnvironment();
		int decr = 0, inc = 0;
		for(int i=0; i<100; i++) {
			Player one = Generation.generateEnemyTeam(1, 1);//team size 2
			env.setPlayer(one);
			env.postDayLogic();
			if(one.getTeam().size()==3) inc++;
			Player two = Generation.generateEnemyTeam(15, 1);//team size 4
			env.setPlayer(two);
			env.postDayLogic();
			if(two.getTeam().size()==3) decr++;
		}
		
		assertTrue(inc > 0);
		assertTrue(decr > 0);
	}

}
