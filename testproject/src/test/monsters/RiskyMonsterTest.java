package test.monsters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.monsters.RiskyMonster;

class RiskyMonsterTest {

	@Test
	void oneShotTest() {
		RiskyMonster risky = new RiskyMonster("risky", 100);// so max level - lots of health.
		RiskyMonster enemy = new RiskyMonster("enemy", 100);
		risky.makeMove(1, enemy);
		assertTrue(risky.isAwake() != enemy.isAwake());
		int numRisky = 0;
		int numEnemy = 0;
		for(int i=0; i<100; i++) {
			risky.rest();
			enemy.rest();
			risky.makeMove(1, enemy);
			if(!risky.isAwake()) numRisky++;
			if(!enemy.isAwake()) numEnemy++;
		}
		assertEquals(numRisky + numEnemy, 100);
		assertTrue(35 < numRisky && numRisky < 65);//roughly 50%
	}

}
