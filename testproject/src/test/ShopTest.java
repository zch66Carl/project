package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import game.Shop;
import game.Player;
import game.Item;
import game.monsters.Monster;

class ShopTest {

	@Test
	void refreshStockTest() {
		Shop shop = new Shop();
		shop.refreshStock(1, 1);
		for(int i=0; i<3; i++) {
			assertTrue(shop.getStock()[i] instanceof Monster);
		}
		for(int i=3; i<9; i++) {
			assertTrue(shop.getStock()[i] instanceof Item);
		}
	}
	
	@Test
	void buyPurchaseableTest() {
		Shop shop = new Shop();
		shop.refreshStock(1, 1);
		Player pla = new Player("name", 30, new ArrayList<Monster>(), new ArrayList<Item>());
		int i;
		int cumulativePrice = 0;
		for(i=0; i<9; i++) {
			if(cumulativePrice + shop.getStock()[i].getPrice() > 30) break;
			cumulativePrice += shop.getStock()[i].getPrice();
			shop.buyPurchaseable(pla, i);
			assertEquals(shop.getStock()[i], null);
			assertEquals(pla.getInventory().size() + pla.getTeam().size(), i+1);
		}
		shop.buyPurchaseable(pla, i);
		assertTrue(shop.getStock()[i] != null);
		assertFalse(pla.getInventory().size() + pla.getTeam().size() == i+1);
		assertEquals(pla.getGold(), 30 - cumulativePrice);
		assertEquals(cumulativePrice, shop.getGoldSpent());
	}
	
	@Test
	void sellPurchaseableTest() {
		Shop shop = new Shop();
		Player pla = new Player("name", 0, new ArrayList<Monster>(), new ArrayList<Item>());
		Monster one = new Monster("name", 1);
		Item two = new Item("name", 5);
		pla.addMonster(one);
		pla.addItem(two);
		shop.sellPurchaseable(pla, one);
		assertEquals(pla.getTeam().size(), 0);
		assertEquals(pla.getGold(), one.getSellPrice());
		shop.sellPurchaseable(pla, two);
		assertEquals(pla.getInventory().size(), 0);
		assertEquals(pla.getGold(), one.getSellPrice() + 5/2);
	}
}
