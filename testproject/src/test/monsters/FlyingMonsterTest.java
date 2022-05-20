package test.monsters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.monsters.FlyingMonster;

class FlyingMonsterTest {

	@Test
	void regularAttackTest() {
		//test that subclass monsters can still do regular attack.
		FlyingMonster fly = new FlyingMonster("fly", 1);
		FlyingMonster enemy = new FlyingMonster("enemy", 1);
		fly.makeMove(0, enemy);
		assertTrue(enemy.getHealth() < enemy.getMaxHealth());
	}
	
	@Test
	void flyTest() {
		FlyingMonster fly = new FlyingMonster("fly", 1);
		fly.dealDamageToSelf(1);
		assertEquals(fly.getHealth(), fly.getMaxHealth() - 1);
		fly.makeMove(1, null);
		fly.dealDamageToSelf(1);
		assertEquals(fly.getHealth(), fly.getMaxHealth() - 1);
		fly.preTurnLogic();
		fly.dealDamageToSelf(1);
		assertEquals(fly.getHealth(), fly.getMaxHealth() - 2);
		fly.makeMove(1, null);
		fly.rest();
		fly.dealDamageToSelf(1);
		assertEquals(fly.getHealth(), fly.getMaxHealth() - 1);
	}

}
