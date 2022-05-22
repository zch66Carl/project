package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import game.Item;
import game.monsters.Monster;

class ItemTest {

	@Test
	void getMonstersUsableOnTest() {
		ArrayList<Monster> team = new ArrayList<Monster>();
		team.add(new Monster("tmp1", 1));
		team.add(new Monster("tmp2", 2));
		team.add(new Monster("tmp3", 2));
		team.add(new Monster("tmp4", 2));
		team.add(new Monster("tmp5", 2));
		team.add(new Monster("tmp6", 15));
		team.get(1).dealDamageToSelf(100);
		team.get(2).dealDamageToSelf(3);
		team.get(5).dealDamageToSelf(50);
		
		Item item = new Item("name", 6);
		assertEquals(item.getName(), "name");
		assertEquals(item.getPrice()/2, item.getSellPrice());
		item.setHealAmount(8);
		assertEquals(item.getMonstersUsableOn(team).size(), 2);
		item.setHealAmount(0);
		item.setBuffAmount(5);
		assertEquals(item.getMonstersUsableOn(team).size(), 6);
		item.setBuffAmount(0);
		item.setIsRevive(true);
		assertEquals(item.getMonstersUsableOn(team).size(), 1);
	}
	
	@Test 
	void useItemTest(){
		Monster monst = new Monster("tmp", 1);
		Item item = new Item("name", 6);
		item.setBuffAmount(5);
		item.useItem(monst);
		assertEquals(monst.getTotalDamage(), monst.getDamage() + 5);
		for(int i=0; i<3; i++) monst.preTurnLogic();
		assertEquals(monst.getTotalDamage(), monst.getDamage()+5);
		monst.preTurnLogic();
		assertEquals(monst.getTotalDamage(), monst.getDamage());
		item.setBuffAmount(0);
		item.setHealAmount(2);
		monst.dealDamageToSelf(5);
		item.useItem(monst);
		assertEquals(monst.getHealth(), monst.getMaxHealth() - 3);
		item.setHealAmount(0);
		item.setIsRevive(true);
		monst.dealDamageToSelf(100);
		item.useItem(monst);
		assertTrue(monst.isAwake());
		assertEquals(monst.getHealth(), monst.getMaxHealth());
	}

	@Test
	void toStringTest() {
		Item item = new Item("name", 5);
		item.setHealAmount(5);
		assertEquals("name, heal amount: 5.", item.toString());
		item = new Item("name", 5);
		item.setBuffAmount(5);
		assertEquals("name, damage buff: 5.", item.toString());
		item = new Item("revive", 5);
		item.setIsRevive(true);
		assertEquals("revive.", item.toString());
	}
}
