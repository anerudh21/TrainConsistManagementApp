package com.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;



/**
 * =======================================
 * MAIN CLASS - TrainConsistManagementApp
 * =======================================
 * 
 * Use Case 6: Map Bogie to Capacity (HashMap)
 * 
 * Description:
 * This class associates each bogie with its seating or 
 * load capacity using a key-value mapping structure.
 * 
 * At this stage, the application:
 * - Creates a HashMap for bogie-capacity mapping
 * - Inserts capacity values for each bogie
 * - Iterates through map entries
 * - Displays  bogie and capacity information
 * 
 * This maps lookup-based access using HashMap.
 * 
 * @author Developer
 * @version 6.0
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
		

		// HashMap stores data in key -> value format
		Map<String, Integer> capacityMap = new HashMap<>();

		
		//Display initial consist information
		System.out.println("Train initializaed sucessfully");
		System.out.println("Inital Bogie Count: " + capacityMap.size());
		System.out.println("Current Train Consist: " + capacityMap.keySet());
		System.out.println("\nSystem ready for operations\n");
		
		boolean inMenu = true;
		
		while(inMenu) {
			System.out.println("\n1. Add Bogies");
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
					
					System.out.print("Enter the capacity of bogie to add: ");
					String capacity = scanner.nextLine();
			
					capacityMap.put(bogie, Integer.parseInt(capacity));
					System.out.printf("Added bogie [%s] of [%s] capacity to train successfully.\n", bogie, capacity);
							
					yield true;
				}
				case "2" -> {
					System.out.print("Enter name of bogie to remove: ");
					String bogie = scanner.nextLine();
					
					if(!capacityMap.keySet().contains(bogie)) {
						System.out.printf("The bogie [%s] does not exist.\n", bogie);
						yield true;
					}
					
					capacityMap.remove(bogie);
					System.out.printf("Removed bogie [%s] from train successfully.\n", bogie);
					
					yield true;
				}
				case "3" -> {
					System.out.print("Enter name of bogie to check: ");
					String bogie = scanner.nextLine();
					
					if(capacityMap.keySet().contains(bogie)) {
						System.out.printf("Contains \'%s\'? :  true\n", bogie);
					} else {
						System.out.printf("Contains \'%s\'? :  false\n", bogie);
					}
					
					yield true;
				}
				case "4" -> {
					
					System.out.println("Bogie Capacity Details");
					for(Entry<String, Integer> entry : capacityMap.entrySet()) {
						System.out.printf("%s -> %d\n", entry.getKey(), entry.getValue());
					}
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