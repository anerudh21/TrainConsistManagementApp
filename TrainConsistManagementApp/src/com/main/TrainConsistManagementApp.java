package com.main;
import java.util.*;

/**
 * =======================================
 * MAIN CLASS - TrainConsistManagementApp
 * =======================================
 * 
 * Use Case 5: Preserve Insertion Order of Bogies
 * 
 * Description:
 * This class maintains the exact attachment order of bogies
 * while also preventing duplicate entries using LinkedHashSet.
 * 
 * At this stage, the application:
 * - Attaches bogies in order
 * - Preserves insertion sequence
 * - Avoids duplicate bogies
 * - Displays final train formation
 * 
 * This maps ordered uniqueness using LinkedHashSet.
 * 
 * @author Developer
 * @version 5.0
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
		
		// LinkedHashSet preserves order and ensures uniqueness
		Set<String> formation = new LinkedHashSet<>();
		
		//Display initial consist information
		System.out.println("Train initializaed sucessfully");
		System.out.println("Inital Bogie Count: " + formation.size());
		System.out.println("Current Train Consist: " + formation);
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
					System.out.print("Enter the name of bogie to add: ");
					String bogie = scanner.nextLine();
			
					if(formation.add(bogie)) {
						System.out.printf("Added bogie [%s] to train successfully.\n", bogie);
					}else {
						System.out.printf("Duplicate Ignored: [%s] already in train.\n", bogie);
					}
					
					
					
					yield true;
				}
				case "2" -> {
					System.out.print("Enter name of bogie to remove: ");
					String bogie = scanner.nextLine();
					
					if(!formation.contains(bogie)) {
						System.out.printf("The bogie [%s] does not exist.\n", bogie);
						yield true;
					}
					
					formation.remove(bogie);
					System.out.printf("Removed bogie [%s] from train successfully.\n", bogie);
					
					yield true;
				}
				case "3" -> {
					System.out.print("Enter name of bogie to check: ");
					String bogie = scanner.nextLine();
					
					if(formation.contains(bogie)) {
						System.out.printf("Contains \'%s\'? :  true\n", bogie);
					} else {
						System.out.printf("Contains \'%s\'? :  false\n", bogie);
					}
					
					yield true;
				}
				case "4" -> {
					System.out.println(formation);
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