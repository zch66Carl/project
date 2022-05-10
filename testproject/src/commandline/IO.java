package commandline;

import java.util.Scanner;

public class IO {
	public static void textOut(String text) {
		System.out.println(text);
	}
	
	public static int getInt(int lowerBound, int upperBound) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			int inp = sc.nextInt();
			if(lowerBound<=inp && inp<=upperBound) return inp;
			IO.textOut("Out of range, try again.");
		}
	}
	
	public static String getString() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
}
