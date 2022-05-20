package test.monsters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.monsters.Monster;

class MonsterTest {

	@Test
	void constructorTest() {
		String name = "abfjts";
		Monster testMonster = new Monster(name, 3);
		assertEquals(name, testMonster.getName());
		assertTrue(11 < testMonster.getDamage() && testMonster.getDamage() < 20);
		assertTrue(testMonster.isAwake());
		testMonster = new Monster(name, -1);
		testMonster = new Monster(name, 100);
	}
	
	@Test
	void rewardAndLevelUpCheckTest() {
		Monster testMonst = new Monster("tmp", 1);
		testMonst.reward();
		assertEquals(null, testMonst.levelUpCheck());
		testMonst.setWasActiveDuringBattle(true);
		testMonst.reward();
		assertFalse(testMonst.levelUpCheck() == null);
	}
	
	@Test
	void priceTest() {
		Monster testMonst = new Monster("tmp", 1);
		assertEquals(testMonst.getPrice()/2, testMonst.getSellPrice());
	}
	
	@Test
	void damageAndBuffTest() {
		Monster testMonst = new Monster("tmp", 1);
		assertEquals(testMonst.getDamage(), testMonst.getTotalDamage());
		testMonst.addDamageBuff(3, 4);
		assertTrue(testMonst.getDamage() < testMonst.getTotalDamage());
		testMonst.addDamageBuff(-100, 2);
		assertEquals(testMonst.getTotalDamage(), 0);
	}
	
	@Test
	void testDealDamageAndHeal() {
		Monster testMonst = new Monster("tmp", 1);
		testMonst.dealDamageToSelf(5);
		assertEquals(testMonst.getMaxHealth()-5, testMonst.getHealth());
		testMonst.heal(3);
		assertEquals(testMonst.getMaxHealth()-2, testMonst.getHealth());
		testMonst.heal(100);
		assertEquals(testMonst.getMaxHealth(), testMonst.getHealth());
		testMonst.dealDamageToSelf(100);
		assertFalse(testMonst.isAwake());
		assertEquals(testMonst.getHealth(), 0);
		testMonst.heal(100);
		assertEquals(testMonst.getHealth(), 0);
		testMonst.rest();
		assertEquals(testMonst.getHealth(), testMonst.getMaxHealth());
	}

	@Test
	void preTurnLogicTest() {
		Monster testMonst = new Monster("tmp", 1);
		testMonst.addDamageBuff(10, 2);
		testMonst.dealPersistentDamageToSelf(2, 2);
		testMonst.dealPersistentDamageToSelf(3, 1);
		testMonst.preTurnLogic();
		assertEquals(testMonst.getTotalDamage(), testMonst.getDamage()+10);
		assertEquals(testMonst.getHealth(), testMonst.getMaxHealth() - 5);
		assertEquals(testMonst.preTurnLogic().size(), 1);
		assertEquals(testMonst.getTotalDamage(), testMonst.getDamage()+10);
		assertEquals(testMonst.getHealth(), testMonst.getMaxHealth() - 7);
		assertEquals(testMonst.preTurnLogic().size(), 0);
		assertEquals(testMonst.getTotalDamage(), testMonst.getDamage());
		assertEquals(testMonst.getHealth(), testMonst.getMaxHealth() - 7);
	}
	
	@Test
	void makeMoveTest() {
		Monster testMonst = new Monster("tmp", 1);
		Monster enemy = new Monster("ene", 1);
		testMonst.makeMove(0, enemy);
		assertTrue(enemy.getHealth() < enemy.getMaxHealth());
		assertEquals(testMonst.makeMove(1, enemy), "");
	}
	
	@Test
	void resetStatusEffectsTest() {
		Monster testMonst = new Monster("tmp", 1);
		testMonst.addDamageBuff(10, 2);
		testMonst.dealPersistentDamageToSelf(2, 2);
		testMonst.dealPersistentDamageToSelf(3, 1);
		testMonst.resetStatusEffects();
		assertEquals(testMonst.preTurnLogic().size(), 0);
		testMonst.addDamageBuff(10, 2);
		testMonst.dealPersistentDamageToSelf(2, 2);
		testMonst.dealPersistentDamageToSelf(3, 1);
		testMonst.rest();
		assertEquals(testMonst.preTurnLogic().size(), 0);
	}
}
