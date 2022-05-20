package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.ItemBuilder;
import game.ItemBuilder.ItemSize;
import game.Item;

class ItemBuilderTest {

	@Test
	void createHealTest() {
		Item item = ItemBuilder.createHeal(5, ItemSize.SMALL);
		assertEquals(item.getName(), "Small Heal Potion");
		assertEquals(item.getPrice(), 5);
		assertTrue(item.isHeal());
	}
	
	@Test
	void createReviveTest() {
		Item item = ItemBuilder.createRevive(5);
		assertEquals(item.getName(), "Revive Potion");
		assertEquals(item.getPrice(), 5);
		assertTrue(item.isRevive());
	}

	@Test
	void createBuffTest() {
		Item item = ItemBuilder.createBuff(5, ItemSize.LARGE);
		assertEquals(item.getName(), "Large Buff Potion");
		assertEquals(item.getPrice(), 5);
		assertFalse(item.isHeal());
		assertFalse(item.isRevive());
	}
}
