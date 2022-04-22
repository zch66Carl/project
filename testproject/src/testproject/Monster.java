package testproject;

/**
 * The Monster class, containing all required information for use in shops, leveling up and battle.
 * Also is responsible for move making when chossing specific attacks, and handles all currently
 * effective status effects. Special moves and such are implemented via subclasses.
 *
 */
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
	
	/**
	 * Deals damage to the Monster, making them faint if health goes below zero.
	 * @param damageDealt The damage to deal to this Monster.
	 */
	public void dealDamage(int damageDealt) {
		health-=damageDealt;
		if(health<=0) {
			health=0;
			isAwake=false;
			Display.displayText(String.format("%s fainted!", name),null,null);
		}
	}
	
	/**
	 * Heals by a certain amount.
	 * @param healAmount The amount to heal by.
	 */
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
	
	/**
	 * Relevant for some subclasses.
	 */
	public void preTurnLogic() {
		
	}
	
	/**
	 * For the base Monster there is only one attack possible, dealing damage to the enemy.
	 * @param enemy
	 */
	public void makeMove(Monster enemy) {
		Display.displayText(String.format("Dealt %s damage to %s.", damage, enemy.getName()), null, null);
		enemy.dealDamage(damage);
	}
	/**
	 * Same as makeMove, but makes a random move instead of taking user input.
	 * @param enemy
	 */
	public void makeRandomMove(Monster enemy) {
		Display.displayText(String.format("%s deals %s damage to %s", name, damage, enemy.getName()), null, null);
		enemy.dealDamage(damage);
	}
	
	/**
	 * Sets health to full and removes any status effects.
	 */
	public void rest() {
		health=maxHealth;
		isAwake=true;
	}
	
	/**
	 * String representation of the Monster.
	 */
	public String toString() {
		return String.format("%s, health: %s, damage: %s", name, health, damage);
	}
}
