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
	 * @param price int. The price of this item.
	 * @return Item. The desired revive item.
	 */
	public static Item createRevive(int price) {
		Item item = new Item("Revive Potion", price);
		item.setIsRevive(true);
		return item;
	}
	
	/**
	 * Creates and returns a healing item of the desired price and size.
	 * @param price int. The price of the item.
	 * @param size ItemBuilder.ItemSize. The size of the heal.
	 * @return Item. The desired healing item.
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
	 * @param price int. The price of the item.
	 * @param size ItemBuilder.ItemSize. The size of the damage buff.
	 * @return Item. The desired damage buff item.
	 */
	public static Item createBuff(int price, ItemSize size) {
		String sizeStr = (size == ItemSize.SMALL ? "Small" : (size == ItemSize.MEDIUM ? "Medium" : "Large"));
		Item item = new Item(sizeStr + " Buff Potion", price);
		int buffAmount = (size == ItemSize.SMALL ? 10 : (size == ItemSize.MEDIUM ? 20 : 50));
		item.setBuffAmount(buffAmount);
		return item;
	}
}
