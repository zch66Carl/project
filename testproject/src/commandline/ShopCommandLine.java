package commandline;

import java.util.ArrayList;

import testproject.Shop;
import testproject.monsters.Monster;
import testproject.GameEnvironment;
import testproject.Item;
import testproject.Player;
import testproject.Purchaseable;

public class ShopCommandLine {
	private void sellItem(Player pla, Shop shop) {
		ArrayList<Item> inv = pla.getInventory();
		if(inv.size()==0) {
			IO.textOut("Inventory is empty, no items to sell.");
			return;
		}
		
		IO.textOut("Current inventory:");
		for(int i=0; i<inv.size(); i++) IO.textOut((i+1) + ": " + inv.get(i).getName() +", sell price: " + inv.get(i).getSellPrice());
		IO.textOut("Enter 0 to return to shop or an item index to sell that item:");
		int inp = IO.getInt(0, inv.size());
		if(inp == 0) return;
		IO.textOut(shop.sellPurchaseable(pla, inv.get(inp - 1)));
	}
	
	private void sellMonster(Player pla, Shop shop) {
		ArrayList<Monster> team = pla.getTeam();
		if(team.size()==0) {
			IO.textOut("There are no monsters in your team to sell");
			return;
		}
		
		IO.textOut("Current team:");
		for(int i=0; i<team.size(); i++) IO.textOut((i+1) + ": " + team.get(i).getName() +", sell price: " + team.get(i).getSellPrice());
		IO.textOut("Enter 0 to return to shop or a monster index to sell that monster:");
		int inp = IO.getInt(0, team.size());
		if(inp == 0) return;
		IO.textOut(shop.sellPurchaseable(pla, team.get(inp - 1)));
	}
	
	private void sell(Player pla, Shop shop) {
		IO.textOut("Enter 0 to return to shop, 1 to sell an item or 2 to sell a monster.");
		int inp = IO.getInt(0, 2);
		if(inp==0) return;
		if(inp ==1) sellItem(pla, shop);
		else sellMonster(pla, shop);
	}
	
	void run(GameEnvironment env) {
		Shop shop = env.getShop();
		Player pla = env.getPlayer();
		while(true) {
			IO.textOut("Welcome to the shop.");
			
			IO.textOut("Current gold: " + pla.getGold());
			
			ArrayList<Purchaseable> stock = new ArrayList<Purchaseable>();
			ArrayList<Integer> indices = new ArrayList<Integer>();
			for(int i = 0; i<shop.getStock().length; i++) {
				Purchaseable purchase = shop.getStock()[i];
				if(purchase == null) continue;
				stock.add(purchase);
				indices.add(i);
			}
			
			IO.textOut("Current stock:");
			for(int i=0;i<stock.size(); i++) {
				IO.textOut((i+1) + ": " + stock.get(i).getName() + ", price: " + stock.get(i).getPrice());
			}
			
			IO.textOut("Enter 0 to exit shop, an index to buy an item or " + (stock.size() + 1) + " to sell a monster or an item.");
			int inp = IO.getInt(0, stock.size() + 1);
			if(inp==0) return;
			if(inp==stock.size()+1) sell(pla, shop);
			else IO.textOut(shop.buyPurchaseable(pla, indices.get(inp - 1)));
		}
	}
}
