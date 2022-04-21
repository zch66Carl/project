package testproject;


public class HealingItem extends Item{
	private int healAmount;

	public HealingItem(String size, int quantity) {
		switch(size) {
		case "Small":
			healAmount = 50;
			super.setName("Small Healing Potion");
			super.setPrice(30);
			super.setQuantity(quantity);
			break;
		case "Medium":
			super.setName("Medium Healing Potion");
			healAmount = 150;
			super.setPrice(50);
			super.setQuantity(quantity);
			break;
		case "Large":
			super.setName("Large Healing Potion");
			healAmount = 300;
			super.setPrice(75);
			super.setQuantity(quantity);
			break;
		}
	}
	
	public int getHealAmount() {
		return healAmount;
	}
	
	public void useItem(Monster monster) {
		monster.heal(healAmount);
		super.useItem();
	}
	
	
	public String toString() {
		return String.format("%s, recovers %d, price %d, quantity %d", super.getName(),getHealAmount(),super.getPrice(),super.getQuantity());
	}
}
