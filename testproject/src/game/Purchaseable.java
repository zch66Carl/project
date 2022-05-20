package game;

/**
 * The purchaseable interface shared by Monster and Item. This determines how purchaseable objects should behave.
 */
public interface Purchaseable {
	/**
	 * The price at which this object should be bought from the shop.
	 * @return An integer price value.
	 */
	public int getPrice();
	/**
	 * The price at which this object should be sold back to the shop.
	 * @return An integer sell price value.
	 */
	public int getSellPrice();
	/**
	 * The name of this object.
	 * @return The name of this object.
	 */
	public String getName();
}
