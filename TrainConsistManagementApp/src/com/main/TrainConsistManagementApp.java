package com.main;
import java.util.*;



/**
 * =======================================
 * MAIN CLASS - TrainConsistManagementApp
 * =======================================
 * 
 * Use Case 7: Sort Bogies by Capacity (Comparator)
 * 
 * Description:
 * This class sorts passenger bogies based on seating
 * capacity using a custom comparator.
 * 
 * At this stage, the application:
 * - Creates bogie objects
 * - Stores them in a list
 * - Displays unsorted data
 * - Sorts using Comparator logic
 * - Displays the sorted result
 * 
 * This maps custom ordering using Comparator.
 * 
 * @author Developer
 * @version 7.0
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