package game;

/**
 * The purchaseable interface shared by Monster and Item. This determines how purchaseable objects should behave.
 */
public interface Purchaseable {
	/**
	 * The price at which this object should be bought from the shop.
	 * @return int. The buy price value of the object.
	 */
	public int getPrice();
	/**
	 * The price at which this object should be sold back to the shop.
	 * @return int. The sell price value of the object.
	 */
	public int getSellPrice();
	/**
	 * The name of this object.
	 * @return String. The name of this object.
	 */
	public String getName();
}
