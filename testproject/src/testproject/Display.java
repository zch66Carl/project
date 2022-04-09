package testproject;

import java.util.Scanner;

public class Display {
	private static boolean isCommandLine = true;
	
	public static void setCommandLine(boolean newIsCommandLine) {
		isCommandLine=newIsCommandLine;
	}
	
	public static void displayText(String commandLineText, String guiText, DisplayType displayType) {
		if(isCommandLine) {
			System.out.println(commandLineText);
		}
	}
	
	public static int getInput(DisplayType displayType) {
		if(isCommandLine) {
			Scanner sc = new Scanner(System.in);
			return sc.nextInt();
		}
		return 0;
	}
}
