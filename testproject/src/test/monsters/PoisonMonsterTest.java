package test.monsters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.monsters.PoisonMonster;

class PoisonMonsterTest {

	@Test
	void poisonTest() {
		PoisonMonster poison = new PoisonMonster("poison", 1);
		PoisonMonster enemy = new PoisonMonster("enemy", 5);//high level so have lots of health and won't drain to 0 over test.
		int lastHealth = enemy.getHealth();
		poison.makeMove(1, enemy);
		assertTrue(enemy.getHealth() < lastHealth);
		lastHealth = enemy.getHealth();
		enemy.preTurnLogic();
		assertTrue(enemy.getHealth() < lastHealth);
		lastHealth = enemy.getHealth();
		for(int i=0; i<4; i++) enemy.preTurnLogic();
		assertTrue(enemy.getHealth() < lastHealth);
		lastHealth = enemy.getHealth();
		enemy.preTurnLogic();
		assertFalse(enemy.getHealth() < lastHealth);
	}

}
