package test.monsters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.monsters.DebuffMonster;

class DebuffMonsterTest {

	@Test
	void debuffTest() {
		DebuffMonster debuff = new DebuffMonster("debuff", 1);
		DebuffMonster enemy = new DebuffMonster("enemy", 1);
		debuff.makeMove(1, enemy);
		assertTrue(enemy.getTotalDamage() < enemy.getDamage());
		for(int i=0; i<3; i++) enemy.preTurnLogic();
		assertTrue(enemy.getTotalDamage() < enemy.getDamage());
		enemy.preTurnLogic();
		assertEquals(enemy.getTotalDamage(), enemy.getDamage());
	}

}
