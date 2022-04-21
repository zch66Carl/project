package testproject;

public class Monster implements Purchaseable{
	private String name;
	private int damage;
	private int maxHealth;
	private int health;
	private int price;
	private boolean isAwake;
	private int level;
	private int currentXp;
	private int xpRequired;
	private int quantity;

	public Monster(String name, int damage, int maxHealth){
		this.name=name;
		this.damage=damage;
		this.maxHealth=maxHealth;
		health=maxHealth;
		level=1;
		currentXp=0;
		xpRequired=50;
		quantity=1;
		price=50;
		isAwake=true;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setCurrentXp(int xp) {
		currentXp = xp;
	}
	
	public int getCurrentXp() {
		return currentXp;
	}
	
	public void setXpRequired() {
		xpRequired += 30*level;
	}
	
	public int getXpRequired() {
		return xpRequired;
	}
	
	public void levelUp() {
			this.level+=1;
			this.damage+=5;
			this.maxHealth+=15;
			rest();
			this.setCurrentXp(0);
			setXpRequired();
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String newName) {
		name=newName;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int newDamage) {
		damage=newDamage;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int newMaxHealth) {
		maxHealth=newMaxHealth;
	}
	public int getHealth() {
		return health;
	}
	public int getPrice() {
		return price;
	}
	public void dealDamage(int damageDealt) {
		health-=damageDealt;
		if(health<=0) {
			health=0;
			isAwake=false;
			Display.displayText(String.format("%s fainted!", name),null,null);
		}
	}
	public void heal(int healAmount) {
		health+=healAmount;
		if(health>maxHealth) health=maxHealth;
	}
	public boolean isAwake() {
		return isAwake;
	}
	public void setIsAwake(boolean isAwake) {
		this.isAwake=isAwake;
	}
	
	void preTurnLogic() {
		
	}
	void makeMove(Monster enemy) {
		Display.displayText(String.format("Dealt %s damage to %s.", damage, enemy.getName()), null, null);
		enemy.dealDamage(damage);
	}
	void makeRandomMove(Monster enemy) {
		Display.displayText(String.format("%s deals %s damage to %s", name, damage, enemy.getName()), null, null);
		enemy.dealDamage(damage);
	}
	void rest() {
		health=maxHealth;
		isAwake=true;
	}
	
	public String toString() {
		return String.format("%s, health: %s, damage: %s", name, health, damage);
	}
}
