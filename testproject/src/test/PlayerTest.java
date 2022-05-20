package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Item;
import game.Player;
import game.monsters.Monster;

class PlayerTest {

	@Test
	void constructorTest() {
		Player pla = new Player("name", 0);
		assertEquals(pla.getName(), "name");
		assertEquals(pla.getGold(), 0);
		pla.setGold(5);
		assertEquals(pla.getGold(), 5);
		pla.addMonster(new Monster("name", 1));
		pla.addItem(new Item("name", 5));
		assertEquals(pla.getTeam().size(), 1);
		assertEquals(pla.getInventory().size(), 1);
	}
	
	@Test
	void isValidNameTest() {
		assertEquals(false, Player.isValidName(""));
		assertEquals(false, Player.isValidName("ab"));
		assertEquals(false, Player.isValidName("adcjksdnvkjsdnkjvnskjnv"));
		assertEquals(false, Player.isValidName("acjndsckj5"));
		assertEquals(false, Player.isValidName("dcjd dsk"));
		assertEquals(false, Player.isValidName("!@#$s"));
		assertEquals(true, Player.isValidName("aBc"));
		assertEquals(true, Player.isValidName("aBcDeFgHiJlMnOp"));
	}
	
	@Test
	void useItemTest() {
		Player pla = new Player("name", 0);
		pla.addMonster(new Monster("name", 1));
		Item item = new Item("name", 4);
		item.setBuffAmount(5);
		pla.addItem(item);
		pla.useItem(pla.getInventory().get(0), pla.getActiveMonster());
		assertEquals(pla.getInventory().size(), 0);
		assertTrue(pla.getActiveMonster().getTotalDamage() > pla.getActiveMonster().getDamage());
	}
	
	@Test
	void getSwitchableMonstersTest() {
		Player pla = new Player("name", 0);
		Monster one = new Monster("name1", 1);
		Monster two = new Monster("name2", 1);
		Monster three = new Monster("name3", 1);
		Monster four = new Monster("name4", 1);
		Monster five = new Monster("name5", 1);
		three.dealDamageToSelf(100);
		four.dealDamageToSelf(100);
		pla.addMonster(one);
		pla.addMonster(two);
		pla.addMonster(three);
		pla.addMonster(four);
		pla.addMonster(five);
		assertEquals(pla.getSwitchableMonsters().size(), 2);
		assertEquals(pla.getSwitchableMonsters().get(0), two);
		assertEquals(pla.getSwitchableMonsters().get(1), five);
	}
	
	@Test
	void activeMonsterTest() {
		Player pla = new Player("name",0);
		Monster one = new Monster("name1", 1);
		Monster two = new Monster("name2", 1);
		Monster three = new Monster("name3", 1);
		Monster four = new Monster("name4", 1);
		Monster five = new Monster("name5", 1);
		pla.addMonster(one);
		pla.addMonster(two);
		pla.addMonster(three);
		pla.addMonster(four);
		pla.addMonster(five);
		assertEquals(pla.getActiveMonster(), one);
		one.dealDamageToSelf(100);
		assertEquals(pla.checkIfActiveMonster(), true);
		assertEquals(pla.getActiveMonster(), two);
		two.dealDamageToSelf(100);
		three.dealDamageToSelf(100);
		four.dealDamageToSelf(100);
		five.dealDamageToSelf(100);
		assertEquals(pla.checkIfActiveMonster(), false);
	}
	
	@Test
	void rewardPostBattleTest() {
		Player pla = new Player("name",0);
		Monster one = new Monster("name1", 1);
		Monster two = new Monster("name2", 1);
		Monster three = new Monster("name3", 1);
		Monster four = new Monster("name4", 1);
		Monster five = new Monster("name5", 1);
		pla.addMonster(one);
		pla.addMonster(two);
		pla.addMonster(three);
		pla.addMonster(four);
		pla.addMonster(five);
		
		pla.preBattle();
		pla.setActiveMonster(four);
		pla.getActiveMonster().addDamageBuff(-1, 4);
		assertTrue(pla.getActiveMonster().getTotalDamage() < pla.getActiveMonster().getDamage());
		pla.postBattle();
		assertEquals(pla.getActiveMonster().getDamage(), pla.getActiveMonster().getTotalDamage());
		
		pla.rewardPostBattle(1, 2, false);
		assertTrue(one.levelUpCheck() != null);
		assertEquals(two.levelUpCheck(), null);
		assertEquals(three.levelUpCheck(), null);
		assertTrue(four.levelUpCheck() != null);
		assertEquals(five.levelUpCheck(), null);
		assertEquals(pla.getGold(), 21);
		assertEquals(pla.getScore(), 20);
	}
}
