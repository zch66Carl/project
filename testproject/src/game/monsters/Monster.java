package game.monsters;

import java.util.ArrayList;
import java.util.Random;

import game.Purchaseable;

/**
 * Monster is the base class for any monster types, it is responsible for the monsters current status, including health, damage,
 * level, xp, whether the monster is fainted or awake, and temporary status effects. The class also contains the behaviour for
 * the monsters attacks against other monsters and making random attacks, as well as other needed behaviours such as levelling up
 * and resting. Monster also implements Purchaseable, as Monsters can be bought in the shop.
 */
public class Monster implements Purchaseable{
	/**
	 * The name of the monster.
	 */
	private String name;
	/**
	 * The price at which the monster is bought, which is about three times the monsters current level, with some random variation.
	 */
	private int price;
	
	/**
	 * The current base damage for the monster's attacks.
	 */
	private int damage;
	/**
	 * The current max health of the monster.
	 */
	private int maxHealth;
	/**
	 * The current health of the monster.
	 */
	private int health;
	/**
	 * Whether or not the monster is awake.
	 */
	private boolean isAwake;
	
	/**
	 * A boolean recording whether or not the monster was active during a battle, (whether the monster was up front),
	 * this is used to determine how much xp the monster should be rewarded with after battle.
	 */
	private boolean wasActiveDuringBattle;
	
	/**
	 * The maximum level a monster can reach, after which they will no longer level up.
	 */
	private int maxLevel = 25;//max level is maxDays(15)+2(hard diff)+5(wild monster) + 3 for safety.
	/**
	 * The xp rewarded to the monster post battle if the monster was active during the battle.
	 */
	private int rewardXP = 6;
	/**
	 * The xp rewarded to the monster post battle if the monster was inactive during the battle.
	 */
	private int passiveXP = 3;
	/**
	 * The xp required at each level to level up.
	 */
	private int[] xpRequired = {5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 15, 15, 15, 15, 15, 15, 15, 15, 15, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
	/**
	 * The base damage for the monster's attacks at each level, (some random variation is applied).
	 */
	private int[] levelDamage    = {5,  8,  11, 15, 20, 25, 30,  36,  42,  48,  54,  60,  66,  72,  78,  84,  90,  98,  106, 112, 120, 130, 140, 150, 160, 170, 180, 190, 200, 215, 230,  245,  260,  280,  300};
	/**
	 * The max health for the monster at each level (some random variation is applied).
	 */
	private int[] levelMaxHealth = {20, 30, 45, 60, 75, 90, 110, 130, 150, 170, 190, 220, 250, 290, 320, 350, 380, 410, 430, 470, 510, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 1050, 1100, 1200, 1250};
	/**
	 * The monster's current level.
	 */
	private int level;
	/**
	 * The monster's current xp, should reset to zero after each level up.
	 */
	private int xp;
	
	/**
	 * A ArrayList of the current damageBuffs applied to the monster, which stack on top of the base damage.
	 */
	private ArrayList<Integer> damageBuffs;
	/**
	 * The duration of each damage buff.
	 */
	private ArrayList<Integer> damageBuffDurations;
	
	/**
	 * An ArrayList of persistent damage values (damage that is applied continually each turn).
	 */
	private ArrayList<Integer> persistentDamage;
	/**
	 * The number of turns untill each persistent damage stops being applied.
	 */
	private ArrayList<Integer> persistentDamageDuration;
	
	/**
	 * A list of attack names, to be passed out of the class and displayed to the player so they can select a suitable attack.
	 */
	protected ArrayList<String> attacks;
	
	/**
	 * Descriptors that go befor a monsters type in their name, protected as also used for subclass monster names.
	 */
	protected static String[] descriptives = {"Fiery","Glowing","Prismatic","Bold","Ebony","Emerald","Golden","Ivory","Scarlet",
			"Violet","Icy","Azure"};
	/**
	 * Part of the monsters random name.
	 */
	private static String[] types = {"Goat","Fox","Lion","Hippo", "Wolf","Rhino", "Giraffe", "Fish"};

	/**
	 * Constructs the Monster, setting the name, level, and then the initial damage, maxHealth and price based on that level.
	 * Also initializes ArrayList's like persitenDamage and initializes the xp to 0.
	 * @param name String. The monster's name.
	 * @param startLevel int. The level this monster starts at (should be between 0 and 24 but is set as such if out of range).
	 */
	public Monster(String name, int startLevel){
		this.name=name;
		
		level=startLevel;
		if(level < 0) level = 0;//set to 0 or maxLevel - 1 to avoid out of range index errors.
		if(level >= maxLevel) level = maxLevel-1;
		
		xp=0;
		Random rand = new Random();
		damage = levelDamage[level] + rand.nextInt(6)-2;
		maxHealth = levelMaxHealth[level] + rand.nextInt(6) - 2;
		health=maxHealth;
		
		price = 3*level + rand.nextInt(8);
		
		isAwake=true;
		
		attacks = new ArrayList<String>();
		attacks.add(0, "Basic Attack");
		
		damageBuffs = new ArrayList<Integer>();
		damageBuffDurations = new ArrayList<Integer>();
		
		persistentDamage = new ArrayList<Integer>();
		persistentDamageDuration = new ArrayList<Integer>();
	}
	
	/**
	 * A static method generating a random name using the descriptives and types arrays.
	 * @return String. A random name for a base class monster.
	 */
	public static String getRandomName() {
		Random rand = new Random();
		return descriptives[rand.nextInt(descriptives.length)] + " " + types[rand.nextInt(types.length)];
	}
	
	/**
	 * Simple setter for wasActiveDuringBattle
	 * @param newBool boolean. The new value of wasActiveDuringBattle.
	 */
	public void setWasActiveDuringBattle(boolean newBool) {
		wasActiveDuringBattle = newBool;
	}
	
	/**
	 * Rewards the monster with xp based on the value of wasActiveDuringBattle, using rewardXP and passiveXP, should be called only
	 * after battles.
	 */
	public void reward() {
		if(wasActiveDuringBattle) xp += rewardXP;
		else xp += passiveXP;
	}
	
	/**
	 * Checks if there is sufficient xp to reach the next level, and if so sets xp back to zero, increases the level (if not at maxLevel),
	 * and increases damage, maxHealth, and price accordingly.
	 * @return String. Null if no level up occurs, else a description of the level up and new damage and maxHealth values.
	 */
	public String levelUpCheck() {
			if(xp < xpRequired[level]) return null;
			xp = 0;
			if(level>=maxLevel-1) return null;
			level++;
			Random rand = new Random();
			damage = levelDamage[level] + rand.nextInt(6)-2;
			maxHealth = levelMaxHealth[level] + rand.nextInt(6)-2;
			health = maxHealth;
			
			price += rand.nextInt(4) + 1;
			
			return name + " levelled up! " + "level: " + level + ", damage: " + damage + ", health: " + maxHealth;
	}
	
	/**
	 * Simple getter for the monster's name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Simple setter for the monster's name.
	 * @param newName String. The new name of the monster.
	 */
	public void setName(String newName) {
		name=newName;
	}
	
	/**
	 * Get's the monster's base damage, this should only be used for display purpases as for attacks the getTotalDamage() function should be used.
	 * @return int. The base damage of the monster.
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Simple getter for maxHeatlh.
	 * @return int. The maxHealth of the monster.
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * Simple getter for the monster's current health.
	 * @return int. The monster's current health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * The monster's buy price.
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * The monster's sell price, which is half of it's regular price.
	 */
	public int getSellPrice() {
		return price / 2;
	}
	
	/**
	 * Returns the names of the monster's attacks in an ArrayList, for display and player input purpases.
	 * @return ArrayList<String>. A list of attack names.
	 */
	public ArrayList<String> getAttackStrings(){
		return attacks;
	}
	
	/**
	 * Sums up all the currently active damage buff's or debuffs.
	 * @return int. The total damage buff to this monster.
	 */
	private int getDamageBuff() {
		int dam = 0;
		for(int buff : damageBuffs) dam += buff;
		return dam;
	}
	/**
	 * Adds a damage buff to this monster, taking a damage amount and duration.
	 * @param dam int. The increase or decrease in damage for this buff.
	 * @param dur int. The duration in turns of this buff.
	 */
	public void addDamageBuff(int dam, int dur) {
		damageBuffs.add(dam);
		damageBuffDurations.add(dur);
	}
	
	/**
	 * Gets the total damage of this monster, accounting for the base damage and any buffs. This is the function that should be used
	 * for all attacks.
	 * @return int. The total damage of this monster.
	 */
	public int getTotalDamage() {
		int dam = damage + getDamageBuff();
		if(dam < 0) dam=0;
		return dam;
	}
	
	/**
	 * Deals damage to this monster, rendering them fainted if health goes to 0 or below, returns a message summarising the effects.
	 * @param damageDealt int. The damage to deal to this monster.
	 * @return String. A description of the effects of the damage (damage increase and if fainted it describes the faint).
	 */
	public String dealDamageToSelf(int damageDealt) {
		String ret = new String();
		ret = "Dealt " + damageDealt + " damage to " + name;
		health-=damageDealt;
		if(health<=0) {
			health=0;
			isAwake=false;
			resetStatusEffects();
			ret += "\n" + name + " fainted!";
		}
		return ret;
	}
	
	/**
	 * Adds a persistent damage attack to this monster, to be dealt at the start of each turn for a certain duration.
	 * @param damageDealtPerTurn int. The damage to deal per turn.
	 * @param duration int. The duration for which this attack lasts.
	 * @return String. A description of the attack.
	 */
	public String dealPersistentDamageToSelf(int damageDealtPerTurn, int duration) {
		persistentDamage.add(damageDealtPerTurn);
		persistentDamageDuration.add(duration);
		return "Poisoned " + name + " with " + damageDealtPerTurn + " damage per turn.";
	}
	
	/**
	 * Heals by a certain amount.
	 * @param healAmount int. The amount to heal by.
	 */
	public void heal(int healAmount) {
		if(!isAwake) return;
		health+=healAmount;
		if(health>maxHealth) health=maxHealth;
	}
	/**
	 * Returns if the monster is awake or not.
	 * @return boolean. Whether or not the monster is awake.
	 */
	public boolean isAwake() {
		if(health<=0) isAwake = false;
		return isAwake;
	}
	
	
	/**
	 * Carries out necersary logic to perform before a turn, such as dealing persistent damage, and removing damage buffs and persitent
	 * damage attacks that have run out of duration. More actions may be performed by subclass monsters.
	 * @return ArrayList<String>. A list messages summarising the actions performed.
	 */
	public ArrayList<String> preTurnLogic() {
		ArrayList<String> ret = new ArrayList<String>();
		
		ArrayList<Integer> newDamageBuffs = new ArrayList<Integer>();
		ArrayList<Integer> newBuffDurations = new ArrayList<Integer>();
		for(int i=0; i<damageBuffs.size(); i++) {
			if(damageBuffDurations.get(i)>0) {
				newDamageBuffs.add(damageBuffs.get(i));
				newBuffDurations.add(damageBuffDurations.get(i) - 1);
			}
		}
		damageBuffs = newDamageBuffs;
		damageBuffDurations = newBuffDurations;
		
		ArrayList<Integer> newPersist = new ArrayList<Integer>();
		ArrayList<Integer> newPersistDur = new ArrayList<Integer>();
		for(int i=0; i<persistentDamage.size(); i++) {
			if(persistentDamageDuration.get(i) > 0) {
				newPersist.add(persistentDamage.get(i));
				newPersistDur.add(persistentDamageDuration.get(i) - 1);
			}
		}
		persistentDamage = newPersist;
		persistentDamageDuration = newPersistDur;
				
		for(int dam : persistentDamage) {
			ret.add(dealDamageToSelf(dam) + " (Poison)");
		}
		
		return ret;
	}
	
	/**
	 * Makes a move based on an int move, for the base Monster class there is only one move, 0, which is a basic attack.
	 * @param move int. The move to perform (should be an index of attack strings)
	 * @param enemy Monster. The enemy monster to perform the move against.
	 * @return String. A message summarising the move for display purpases.
	 */
	public String makeMove(int move, Monster enemy) {
		if(move == 0) return enemy.dealDamageToSelf(getTotalDamage());
		return "";
	}
	
	/**
	 * Same as makeMove, but makes a random move instead of taking a move integer to determine the move. Used for the non player monsters.
	 * @param enemy Monster. The enemy monster
	 * @return String. A string describing the random move.
	 */
	public String makeRandomMove(Monster enemy) {
		return enemy.dealDamageToSelf(getTotalDamage());
	}
	
	
	/**
	 * Resets all status effects by clearing the damage buffs and persitent damage attacks. Some subclass monsters may overide this method.
	 */
	public void resetStatusEffects() {
		damageBuffs.clear();
		damageBuffDurations.clear();
		persistentDamage.clear();
		persistentDamageDuration.clear();
	}
	
	
	/**
	 * Sets health to max, sets the monster to awake and removes any status effects.
	 */
	public void rest() {
		health=maxHealth;
		isAwake=true;
		resetStatusEffects();
	}
	
	
	/**
	 * String representation of the monster, including name, health/maxHealth, totalDamage/baseDamage, level and whether the monster is awake.
	 */
	public String toString() {
		return String.format("%s, health: %s/%s, damage: %s/%s, level: %s, %s", name, health, maxHealth, getTotalDamage(), damage, level, isAwake ? "awake." : "fainted.");
	}
	
	/**
	 * Like to string but with only the most basic info.
	 * @return String. A basic representation of Monster.
	 */
	public String basicStr() {
		return String.format("%s, health: %s, damage: %s, level: %s.", name, maxHealth, damage, level);
	}
}
