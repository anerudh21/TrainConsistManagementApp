package com.main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;



/**
 * =======================================
 * MAIN CLASS - TrainConsistManagementApp
 * =======================================
 * 
 * Use Case 9: Group Bogies by Type
 * 
 * Description:
 * This class groups similar bogies together using
 * Java Stream Collectors.groupingBy().
 * 
 * At this stage, the application:
 * - Creates a list of bogies
 * - Streams the list
 * - Groups bogies by name
 * - Stores grouped data in a Map
 * - Displays grouped structures
 * 
 * This maps classification logic using groupingBy
 * 
 * @author Developer
 * @version 9.0
 */
public class TrainConsistManagementApp {
	
	// Inner Bogie class to model passanger bogies
	static class Bogie{
		private String name;
		private int capacity;
		
		public Bogie(String name, int capacity) {
			this.name = name;
			this.capacity = capacity;
		}
		
		public String getName() {
			return this.name;
		}
		
		public int getCapacity() {
			return this.capacity;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public void setCapacity(int capacity) {
			this.capacity = capacity;
		}
		
		@Override 
		public String toString(){
			return String.format("(%s, %s)", name, capacity);
		}
	}
	
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
		
		// Create a list of passenger bogies
		List<Bogie> bogies = new ArrayList<>();

		
		//Display initial consist information
		System.out.println("Train initializaed sucessfully");
		System.out.println("Inital Bogie Count: " + bogies.size());
		System.out.println("Current Train Consist: " + bogies);
		System.out.println("\nSystem ready for operations\n");
		
		boolean inMenu = true;
		
		while(inMenu) {
			System.out.println("\n1. Add Bogies");
			System.out.println("2. Remove Bogies");
			System.out.println("3. Check if Bogie Exists");
			System.out.println("4. Display Consists");
			System.out.println("5. Sort Consists");
			System.out.println("6. Filter By Potential Passenger Lobies");
			System.out.println("7. Group By Bogie Type");
			System.out.println("0. Exit");
			System.out.print("Enter Choice: ");
			String choice = scanner.nextLine();
			
			inMenu = switch(choice) {
				case "1" -> {
					System.out.print("Enter the name of bogie to add: ");
					String bogie = scanner.nextLine();
					
					System.out.print("Enter the capacity of bogie to add: ");
					String capacity = scanner.nextLine();
			
					bogies.add(new Bogie(bogie, Integer.parseInt(capacity)));
					System.out.printf("Added bogie [%s] of [%s] capacity to train successfully.\n", bogie, capacity);
							
					yield true;
				}
				case "2" -> {
					System.out.print("Enter name of bogie to remove: ");
					String bogie = scanner.nextLine();
					
					for(Bogie b : bogies) {
						if(b.getName().equals(bogie)) {
							bogies.remove(b);
							System.out.printf("Removed bogie [%s] from train successfully.\n", bogie);
							yield true;
						}
					}

					System.out.printf("The bogie [%s] does not exist.\n", bogie);
					yield true;

				}
				case "3" -> {
					System.out.print("Enter name of bogie to check: ");
					String bogie = scanner.nextLine();
					
					for(Bogie b : bogies) {
						if(b.getName().equals(bogie)) {
							System.out.printf("Contains \'%s\'? :  true\n", bogie);
							yield true;
						}
					}
					
					System.out.printf("Contains \'%s\'? :  false\n", bogie);
					
					yield true;
				}
				case "4" -> {
					System.out.println("Bogie Capacity Details:-\n");
					
					for(Bogie bogie : bogies) {
						System.out.printf("%s -> %s\n", bogie.getName(), bogie.getCapacity());
					}
					
					yield true;
				}
				case "5" -> {
					Collections.sort(bogies, Comparator.comparingInt(Bogie::getCapacity));
					System.out.println("Bogies sorted successfully!");
					yield true;
				}
				case "6" -> {
					System.out.println("Filtering Bogies (Capacity > 60): ");
					for(Bogie bogie : bogies.stream().filter(b -> b.getCapacity() > 60).collect(Collectors.toList())) {
						System.out.printf("%s -> %s\n", bogie.getName(), bogie.getCapacity());
					}
					yield true;
				}
				case "7" -> {
					// ---- GROUP USING COLLECTORS.GROUPINGBY ----
					Map<String, List<Bogie>> groupedBogies = bogies.stream().collect(Collectors.groupingBy(Bogie::getName));
					
					// Display grouped structure
					System.out.println("\nGrouped Bogies: \n");
					for(Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
						System.out.printf("Boogie Type: %s\n", entry.getKey());
						for(Bogie bogie : entry.getValue()) {
							System.out.printf("Capacity -> %s\n", bogie.getCapacity());
						}
					}
					System.out.println();
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