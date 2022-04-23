package testproject.items;

public class StatsItem extends Item{
	private int damageIncrease;

	public StatsItem(String size, int quantity) {
		switch(size) {
		case "Small":
			super.setName("Small Attack Potion");
			damageIncrease = 30;
			super.setPrice(50);
			super.setQuantity(quantity);
			break;
		case "Medium":
			super.setName("Medium Attack Potion");
			damageIncrease = 50;
			super.setPrice(85);
			super.setQuantity(quantity);
			break;
		case "Large":
			super.setName("Large Attack Potion");
			damageIncrease = 80;
			super.setPrice(120);
			super.setQuantity(quantity);
			break;
		}
	}
	
	public int getStatsAmount() {
		return damageIncrease;
	}
	
	public String toString() {
		return String.format("%s, damage increase %d, price %d, quantity %d", super.getName(),getStatsAmount(),super.getPrice(),super.getQuantity());
	}
}
