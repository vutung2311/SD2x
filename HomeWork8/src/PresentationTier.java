/*
 * SD2x Homework #8
 * This class represents the Presentation Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below. 
 * Also implement the start method as described in the assignment description.
 */

import java.util.Scanner;

public class PresentationTier {

	private Scanner standardInput;
	
	private LogicTier logicTier; // link to the Logic Tier
	
	public PresentationTier(LogicTier logicTier) {
		this.logicTier = logicTier;
	}
	
	public void start() {

		standardInput = new Scanner(System.in);

		System.out.println("Which feature do you like to use?");
		System.out.println("1. findBookTitlesByAuthor");
		System.out.println("2. showNumberOfBooksInYear");
		System.out.print("Enter your choice: ");
		int choice = standardInput.nextInt();
		switch (choice) {
			case 1:
				showBookTitlesByAuthor();
				break;
			case 2:
				showNumberOfBooksInYear();
				break;
			default:
				System.out.println("Invalid option. Exiting...");
		}
	}

	public void showBookTitlesByAuthor() {
		System.out.print("Enter author name that you want to search books by: \n");
		String authorName = standardInput.next();
		System.out.println("Result is:");
		System.out.println(String.join("\n", logicTier.findBookTitlesByAuthor(authorName)));
	}

	public void showNumberOfBooksInYear() {
		System.out.print("Enter the year that you can to count books which published in: \n");
		int year = standardInput.nextInt();
		System.out.println("Result is:");
		System.out.println(logicTier.findNumberOfBooksInYear(year));

	}

}
