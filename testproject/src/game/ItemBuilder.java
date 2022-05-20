package game;

/**
 * A class which constructs each type of item, taking in a price and if applicable a size.
 */
public class ItemBuilder {
	/**
	 * An enum for the size of the item, either SMALL, MEDIUM, or LARGE.
	 */
	public enum ItemSize{
		SMALL,
		MEDIUM,
		LARGE
	}
	
	/**
	 * Creates and returns a full revive item.
	 * @param price The price of this item.
	 * @return The desired item.
	 */
	public static Item createRevive(int price) {
		Item item = new Item("Revive potion", price);
		item.setIsRevive(true);
		return item;
	}
	
	/**
	 * Creates and returns a healing item of the desired price and size.
	 * @param price The price of the item.
	 * @param size The size of the heal.
	 * @return The desired item.
	 */
	public static Item createHeal(int price, ItemSize size) {
		String sizeStr = (size == ItemSize.SMALL ? "Small" : (size == ItemSize.MEDIUM ? "Medium" : "Large"));
		Item item = new Item(sizeStr + " Heal Potion", price);
		int healAmount = (size == ItemSize.SMALL ? 20 : (size == ItemSize.MEDIUM ? 50 : 100));
		item.setHealAmount(healAmount);
		return item;
	}
	
	/**
	 * Creates and returns a damage buff item of the desired price and size.
	 * @param price The price of the item.
	 * @param size The size of the damage buff.
	 * @return The desired item.
	 */
	public static Item createBuff(int price, ItemSize size) {
		String sizeStr = (size == ItemSize.SMALL ? "Small" : (size == ItemSize.MEDIUM ? "Medium" : "Large"));
		Item item = new Item(sizeStr + " Buff Potion", price);
		int buffAmount = (size == ItemSize.SMALL ? 10 : (size == ItemSize.MEDIUM ? 20 : 50));
		item.setBuffAmount(buffAmount);
		return item;
	}
}
