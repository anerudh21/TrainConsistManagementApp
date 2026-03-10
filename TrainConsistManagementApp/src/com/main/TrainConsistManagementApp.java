package com.main;
import java.util.*;

/**
 * =======================================
 * MAIN CLASS - TrainConsistManagementApp
 * =======================================
 * 
 * Use Case 3: Track Unique Bogie IDs
 * 
 * Description:
 * This class ensures that duplicate bogie IDs are not
 * added into the train formation using HashSet
 * 
 * At this stage, the application:
 * - Stores bogie IDs
 * - Prevents duplicate automatically
 * - Displays unique identifiers
 * 
 * This maps uniqueness validation using Set.
 * 
 * @author Developer
 * @version 3.0
 */
public class TrainConsistManagementApp {
	
	/**
	 * Main entry point to the app
	 * 
	 * @param args	Command-Line args
	 */
	public static void main(String[]args) {
		// Initialize scanner
		Scanner scanner = new Scanner(System.in);
		
		// Display welcome banner
		System.out.println("==========================================");
		System.out.println("   === Train Consist Management App ===   ");
		System.out.println("==========================================");
		
		// Create a Set to store unique bogie IDs
		// HashSet stores only unique values
		Set<String> bogies = new HashSet<>();
		
		//Display initial consist information
		System.out.println("Train initializaed sucessfully");
		System.out.println("Inital Bogie Count: " + bogies.size());
		System.out.println("Current Train Consist: " + bogies);
		System.out.println("\nSystem ready for operations\n");
		
		boolean inMenu = true;
		
		while(inMenu) {
			System.out.println("1. Add Bogies");
			System.out.println("2. Remove Bogies");
			System.out.println("3. Check if Bogie Exists");
			System.out.println("4. Display Consists");
			System.out.println("0. Exit");
			System.out.print("Enter Choice: ");
			String choice = scanner.nextLine();
			
			inMenu = switch(choice) {
				case "1" -> {
					System.out.print("Enter the ID of bogie to add: ");
					String bogie = scanner.nextLine();
					
					if(bogies.add(bogie)) {
						System.out.printf("Added bogie [%s] to train successfully.\n", bogie);
					}else {
						System.out.printf("Duplicated bogie [%s] ignored.\n", bogie);
					}
					
					yield true;
				}
				case "2" -> {
					System.out.print("Enter ID of bogie to remove: ");
					String bogie = scanner.nextLine();
					
					if(!bogies.contains(bogie)) {
						System.out.printf("The bogie [%s] does not exist.\n", bogie);
						yield true;
					}
					
					bogies.remove(bogie);
					System.out.printf("Removed bogie [%s] from train successfully.\n", bogie);
					
					yield true;
				}
				case "3" -> {
					System.out.print("Enter ID of bogie to check: ");
					String bogie = scanner.nextLine();
					
					if(bogies.contains(bogie)) {
						System.out.printf("Contains \'%s\'? :  true\n", bogie);
					} else {
						System.out.printf("Contains \'%s\'? :  false\n", bogie);
					}
					
					yield true;
				}
				case "4" -> {
					System.out.println(bogies);
					yield true;
				}
				case "0" -> {
					System.out.println("Thank You!!");
					yield false;
				}
				default -> {
					System.out.println("Invalid Choice!!");
					yield true;
				}
			};
		}
		
		scanner.close();
	}
}