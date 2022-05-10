package testproject;

public class ItemBuilder {
	public enum ItemSize{
		SMALL,
		MEDIUM,
		LARGE
	}
	
	public static Item createRevive(int price) {
		Item item = new Item("Revive potion", price);
		item.setIsRevive(true);
		return item;
	}
	
	public static Item createHeal(int price, ItemSize size) {
		String sizeStr = (size == ItemSize.SMALL ? "Small" : (size == ItemSize.MEDIUM ? "Medium" : "Large"));
		Item item = new Item(sizeStr + " Heal Potion", price);
		int healAmount = (size == ItemSize.SMALL ? 20 : (size == ItemSize.MEDIUM ? 50 : 100));
		item.setHealAmount(healAmount);
		return item;
	}
	
	public static Item createBuff(int price, ItemSize size) {
		String sizeStr = (size == ItemSize.SMALL ? "Small" : (size == ItemSize.MEDIUM ? "Medium" : "Large"));
		Item item = new Item(sizeStr + " Buff Potion", price);
		int buffAmount = (size == ItemSize.SMALL ? 5 : (size == ItemSize.MEDIUM ? 10 : 20));
		item.setBuffAmount(buffAmount);
		return item;
	}
}
