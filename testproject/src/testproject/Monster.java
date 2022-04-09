package testproject;

public class Monster implements Purchaseable{
	private String name;
	private int damage;
	private int maxHealth;
	private int health;
	private int price;
	private boolean isAwake;

	public Monster(String name, int damage, int maxHealth){
		this.name=name;
		this.damage=damage;
		this.maxHealth=maxHealth;
		health=maxHealth;
		isAwake=true;
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

	void makeMove(Monster enemy) {
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
