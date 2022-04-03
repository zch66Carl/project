package testproject;

public class Monster {
	private String name;
	private int damage;
	private int maxHealth;
	private int health;
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
	public void dealDamage(int damageDealt) {
		health-=damageDealt;
		if(health<=0) {
			health=0;
			isAwake=false;
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
}
