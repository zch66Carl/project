package testproject.monsters;

import java.util.ArrayList;
import java.util.Random;

import testproject.Purchaseable;

/**
 * The Monster class, containing all required information for use in shops, leveling up and battle.
 * Also is responsible for move making when chossing specific attacks, and handles all currently
 * effective status effects. Special moves and such are implemented via subclasses.
 *
 */
public class Monster implements Purchaseable{
	private String name;
	private int price;
	
	private int damage;
	private int maxHealth;
	private int health;
	private boolean isAwake;
	
	private boolean wasActiveDuringBattle;
	
	private int maxLevel = 35;//max level is maxDays(25)+2(hard diff)+5(wild monster) + 3 for safety.
	private int rewardXP = 6;
	private int passiveXP = 3;//was part of battle but wasn't active
	private int[] xpRequired = {5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 15, 15, 15, 15, 15, 15, 15, 15, 15, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
	private int[] levelDamage    = {5,  8,  11, 15, 20, 25, 30,  36,  42,  48,  54,  60,  66,  72,  78,  84,  90,  98,  106, 112, 120, 130, 140, 150, 160, 170, 180, 190, 200, 215, 230,  245,  260,  280,  300};
	private int[] levelMaxHealth = {20, 30, 45, 60, 75, 90, 110, 130, 150, 170, 190, 220, 250, 290, 320, 350, 380, 410, 430, 470, 510, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 1050, 1100, 1200, 1250};
	private int level;
	private int xp;
	
	ArrayList<String> attacks;

	public Monster(String name, int startLevel){
		this.name=name;
		
		level=startLevel;
		xp=0;
		Random rand = new Random();
		damage = levelDamage[level] + rand.nextInt(6)-2;
		maxHealth = levelMaxHealth[level] + rand.nextInt(6) - 2;
		health=maxHealth;
		
		price = 3*level + rand.nextInt(5);
		
		isAwake=true;
		
		attacks = new ArrayList<String>();
		attacks.add(0, "Basic Attack");
	}
	
	public void setWasActiveDuringBattle(boolean newBool) {
		wasActiveDuringBattle = newBool;
	}
	public boolean getWasAciveDuringBattle() {
		return wasActiveDuringBattle;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void reward() {
		if(wasActiveDuringBattle) xp += rewardXP;
		else xp += passiveXP;
	}
	
	public String levelUpCheck() {
			if(xp < xpRequired[level]) return null;
			xp = 0;
			if(level==maxLevel-1) return null;
			level++;
			Random rand = new Random();
			damage = levelDamage[level] + rand.nextInt(6)-2;
			maxHealth = levelMaxHealth[level] + rand.nextInt(6)-2;
			health = maxHealth;
			
			price += rand.nextInt(4) + 1;
			
			return name + " levelled up! " + "level: " + level + ", damage: " + damage + ", health: " + maxHealth;
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
		return String.format("%s, health: %s/%s, damage: %s, level: %s, %s", name, health, maxHealth, damage, level, isAwake ? "awake." : "fainted.");
	}
}
