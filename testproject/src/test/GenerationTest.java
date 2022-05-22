package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Generation;
import game.monsters.*;
import game.Item;
import game.Player;

class GenerationTest {

	@Test
	void generateMonsterTest() {
		int fly = 0, debuff = 0, poison = 0, risky = 0;
		for(int i=0; i<100; i++) {
			Monster plaMonst = Generation.generatePlayerMonster(7, 1);
			Monster enemyEasy = Generation.generateEnemyTeam(7, -1).getTeam().get(0);//generate should change it to (7, 1)
			Monster enemyHard = Generation.generateEnemyTeam(7, 4).getTeam().get(0);//generate should change it to (7, 3)
			Monster wild = Generation.generateWildMonster(7, 2);
			assertTrue(enemyEasy.getDamage() < plaMonst.getDamage());
			assertTrue(enemyHard.getDamage() > plaMonst.getDamage());
			assertTrue(wild.getDamage() > plaMonst.getDamage());
			if(plaMonst instanceof FlyingMonster) fly++;
			if(plaMonst instanceof DebuffMonster) debuff++;
			if(plaMonst instanceof PoisonMonster) poison++;
			if(plaMonst instanceof RiskyMonster) risky++;
		}
		
		int normal = 100 - fly - debuff - poison - risky;
		//MIGHT OCCASIONALLY FAIL
		assertTrue(25 < normal && normal < 55); //40 normal
		assertTrue(8 < fly && fly < 28); //18 of each
		assertTrue(8 < debuff && debuff < 28);//^
		assertTrue(8 < poison && poison < 28);//^
		assertTrue(1 < risky && risky < 11);//6 of risky
	}

	@Test
	void generateItemTest() {
		assertTrue(Generation.generateItem(1, 1, false) instanceof Item);
		
		int revive = 0, heal = 0, buff = 0;
		for(int i=0; i<100; i++) {
			Item item = Generation.generateItem(1, 1, false);
			if(item.isHeal()) heal++;
			else if(item.isRevive()) revive++;
			else buff++;
		}
		
		//MIGHT OCCASIONALLY FAIL
		assertTrue(2 < revive && revive < 17);//about 9 revives (1/11)
		assertTrue(-25 < heal - buff && heal-buff < 25);//roughly equal.
	}
	
	
	@Test
	void generateEnemyTeamTest() {
		Player enemy = Generation.generateEnemyTeam(1, 1);
		assertEquals(enemy.getTeam().size(), 2);
		assertEquals(enemy.getInventory().size(), 1);
		enemy = Generation.generateEnemyTeam(15, 1);
		assertEquals(enemy.getTeam().size(), 4);
		assertEquals(enemy.getInventory().size(), 4);
	}
}
