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
		for(int i=0; i<100; i++) {
			Monster plaMonst = Generation.generatePlayerMonster(1, 1);
		}
	}

}
