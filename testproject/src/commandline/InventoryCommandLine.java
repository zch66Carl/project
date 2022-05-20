package commandline;

import java.util.ArrayList;

import testproject.GameEnvironment;
import testproject.Item;
import testproject.Player;
import testproject.monsters.Monster;

/**
 * The command line interface for displaying the inventory, and choosing and using an item from it.
 */
public class InventoryCommandLine {
	/**
	 * Displays the monsters in the player's team that an item may be used on and prompts the user to either return to the prievious
	 * menu or use the item on one of those monsters.
	 * @param item The item to use.
	 * @param pla The Player entity to use the item.
	 * @return A boolean, true if the item was used or false if not.
	 */
	private boolean useItem(Item item, Player pla) {
		ArrayList<Monster> usableOn = item.getMonstersUsableOn(pla.getTeam());
		if(usableOn.size() == 0) {
			IO.textOut("Item not usable:");
			String message = new String();
			if(item.isRevive()) message = "No monsters are fainted.";
			else if(item.isHeal()) message = "Monsters allready at full health.";
			IO.textOut(message);
			return false;
		}
		IO.textOut("Select a monster to use the item on or 0 to return:");
		for(int i=0; i<usableOn.size(); i++) {
			IO.textOut((i+1) + ": " + usableOn.get(i).toString());
		}
		int inp = IO.getInt(0, usableOn.size());
		if(inp==0) return false;
		pla.useItem(item, usableOn.get(inp - 1)); //TODO: make useItem return a string to display
		return true;
	}
	
	/**
	 * Displays the player's inventory and promts the user to return to the prievious menu or use one of the items.
	 * @param env The GameEnvironment entity.
	 * @return A boolean, whether or not an item was used.
	 */
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
