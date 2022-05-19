package commandline;

import java.util.ArrayList;

import testproject.GameEnvironment;
import testproject.Item;
import testproject.Player;
import testproject.monsters.Monster;

public class InventoryCommandLine {
	private boolean useItem(Item item, Player pla) {
		ArrayList<Monster> usableOn = item.getMonstersUsableOn(pla.getTeam());
		if(usableOn.size() == 0) {
			IO.textOut("Item not usable:");
			String message = new String();
			if(item.getIsRevive()) message = "No monsters are fainted.";
			else if(item.getHealAmount() > 0) message = "Monsters allready at full health.";
			IO.textOut(message);
			return false;
		}
		IO.textOut("Select a monster to use the item on:");
		for(int i=0; i<usableOn.size(); i++) {
			IO.textOut(i + ": " + usableOn.get(i).toString());
		}
		int inp = IO.getInt(0, usableOn.size() - 1);
		pla.useItem(item, usableOn.get(inp)); //TODO: make useItem return a string to display
		return true;
	}
	
	public boolean run(GameEnvironment env) {
		Player pla = env.getPlayer();
		ArrayList<Item> inv = pla.getInventory();
		IO.textOut("Your inventory: ");
		for(int i=0; i<inv.size(); i++) {
			IO.textOut((i+1)+": "+inv.get(i).toString());
		}
		IO.textOut("Enter 0 to return or an item index to use that item.");
		int inp = IO.getInt(0, inv.size());
		if(inp==0) return false;
		return useItem(inv.get(inp - 1), pla);
	}
}
