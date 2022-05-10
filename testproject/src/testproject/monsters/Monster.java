package testproject.monsters;

import java.util.ArrayList;

import testproject.Purchaseable;

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
	
	ArrayList<String> attacks;

	public Monster(String name, int damage, int maxHealth){
		this.name=name;
		this.damage=damage;
		this.maxHealth=maxHealth;
		health=maxHealth;
		level=1;
		currentXp=0;
		xpRequired=50;
		price=50;
		isAwake=true;
		
		attacks = new ArrayList<String>();
		attacks.add(0, "Basic Attack");
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
	public int getSellPrice() {
		return price / 2;
	}
	
	public ArrayList<String> getAttackStrings(){
		return attacks;
	}
	
	/**
	 * Deals damage to the Monster, making them faint if health goes below zero.
	 * @param damageDealt The damage to deal to this Monster.
	 */
	public String dealDamage(int damageDealt) {
		String ret = new String();
		ret = "Dealt " + damageDealt + " damage to " + name;
		health-=damageDealt;
		if(health<=0) {
			health=0;
			isAwake=false;
			ret += "\n" + name + " fainted!";
		}
		return ret;
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
		if(health<=0) isAwake = false;
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
	public String makeMove(int move, Monster enemy) {
		if(move == 0) return enemy.dealDamage(damage);
		return "";
	}
	/**
	 * Same as makeMove, but makes a random move instead of taking user input.
	 * @param enemy
	 */
	public String makeRandomMove(Monster enemy) {
		return enemy.dealDamage(damage);
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
		return String.format("%s, health: %s, damage: %s, %s", name, health, damage, isAwake ? "awake" : "fainted");
	}
}
