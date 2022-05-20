package commandline;

import java.util.Scanner;

/**
 * A input output class simple functionality for the input and ouput required for the command line program through static methods.
 */
public class IO {
	/**
	 * Displays the given text.
	 * @param text The text to display.
	 */
	public static void textOut(String text) {
		System.out.println(text);
	}
	
	/**
	 * Takes a range and prompts the user for input repeatedly until an integer in that range is given. The value is then returned.
	 * @param lowerBound The lowest valid integer value for the input.
	 * @param upperBound The highest valid integer value for the input.
	 * @return An integer in the range [lowerBound, upperBound] from command line input.
	 */
	public static int getInt(int lowerBound, int upperBound) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			int inp = sc.nextInt();
			if(lowerBound<=inp && inp<=upperBound) return inp;
			IO.textOut("Out of range, try again (range is " + lowerBound + " to " + upperBound +").");
		}
	}
	
	/**
	 * Takes a whole line of input from the command line.
	 * @return A String, the line of inpu.
	 */
	public static String getString() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
}
