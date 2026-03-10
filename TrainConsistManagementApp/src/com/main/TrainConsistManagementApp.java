package com.main;
import java.util.*;

/**
 * =======================================
 * MAIN CLASS - TrainConsistManagementApp
 * =======================================
 * 
 * Use Case 2: Add Passenger Bogies to Train
 * 
 * Description:
 * This class demonstrates how passenger bogies can be
 * managed dynamically using ArrayList operations
 * 
 * At this stage, the application:
 * - Adds new bogies to the train
 * - Removes existing bogies
 * - Checks for bogie availability
 * - Displays the final consist
 * 
 * This maps CRUD operations using ArrayList.
 * 
 * @author Developer
 * @version 2.0
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
		
		// Create a dynamic list to store train bogies
		List<String> trainConsist = new ArrayList<>();
		
		//Display initial consist information
		System.out.println("Train initializaed sucessfully");
		System.out.println("Inital Bogie Count: " + trainConsist.size());
		System.out.println("Current Train Consist: " + trainConsist);
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
					
					trainConsist.add(bogie);
					System.out.printf("Added bogie [%s] to train successfully.\n", bogie);
					yield true;
				}
				case "2" -> {
					System.out.print("Enter name of bogie to remove: ");
					String bogie = scanner.nextLine();
					
					if(!trainConsist.contains(bogie)) {
						System.out.printf("The bogie [%s] does not exist.\n", bogie);
						yield true;
					}
					
					trainConsist.remove(bogie);
					System.out.printf("Removed bogie [%s] from train successfully.\n", bogie);
					
					yield true;
				}
				case "3" -> {
					System.out.print("Enter name of bogie to check: ");
					String bogie = scanner.nextLine();
					
					if(trainConsist.contains(bogie)) {
						System.out.printf("Contains \'%s\'? :  true\n", bogie);
					} else {
						System.out.printf("Contains \'%s\'? :  false\n", bogie);
					}
					
					yield true;
				}
				case "4" -> {
					System.out.println(trainConsist);
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