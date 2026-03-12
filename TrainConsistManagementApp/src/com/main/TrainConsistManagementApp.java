package com.main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;



/**
 * =======================================
 * MAIN CLASS - TrainConsistManagementApp
 * =======================================
 * 
 * Use Case 18: Linear Search for Bogie ID
 * 
 * Description:
 * This class demonstrates searching for a specific bogie ID
 * using a simple Linear Seach Algorithm
 * 
 * At this stage, the application:
 * - Creates an array of bogie IDs
 * - Accepts a search key
 * - Traverses array sequentially
 * - Stops when match is found
 * - Displays search result
 * 
 * This maps basic searching logic using sequential traversal.
 * 
 * @author Developer
 * @version 18.0
 */
public class TrainConsistManagementApp {

	// Inner Bogie class to model passanger bogies


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
			System.out.println("--- Train App Menu ---");
			System.out.println("1. Manage Train Consists");
			System.out.println("2. Manage Cargo Trains");
			System.out.println("3. Validate IDs");
			System.out.println("0. Exit");
			System.out.print("Enter Choice: ");
			String choice = scanner.nextLine();

			inMenu = switch(choice) {
			case "1" -> {
				try {
					handleConsistFlow(bogies, scanner);
				}catch(InvalidCapacityException e) {
					System.out.println("Error: " + e.getMessage());
				}
				yield true;
			}
			case "2" -> {
				try {
					handleCargoTrainFlow(scanner);
				}catch(CargoSafetyException e) {
					System.out.println("Error: " + e.getMessage());
				}finally {
					System.out.println("Cargo validation completed for all bogies");
				}
				yield true;
			}
			case "3" -> {
				handleValidationFlow(scanner);
				yield true;
			}
			case "0" -> {
				yield false;
			}
			default -> {
				yield true;
			}
			};
		}

		scanner.close();
	}

	private static void handleConsistFlow(List<Bogie> bogies, Scanner scanner) throws InvalidCapacityException {
		boolean inMenu = true;

		while(inMenu) {
			System.out.println("\n--- Train Consist Menu ---");
			System.out.println("1. Add Bogies");
			System.out.println("2. Remove Bogies");
			System.out.println("3. Check if Bogie Exists");
			System.out.println("4. Display Consists");
			System.out.println("5. Sort Consists");
			System.out.println("6. Filter By Potential Passenger Lobies");
			System.out.println("7. Group By Bogie Type");
			System.out.println("8. Get Total Capacity");
			System.out.println("9. Sorting of Capacities Using Bubble Sort");
			System.out.println("10. Sorting of Bogie Types Alphabetically");
			System.out.println("11. Search for Bogie ID (predefiend list)");
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
			case "8" -> {
				System.out.printf("Total Seating Capacity of Train: %d\n", bogies.stream().map(b -> b.getCapacity()).reduce(0, Integer::sum));
				yield true;
			}
			case "9" -> {
				int capacities[] = new int[bogies.size()];
				
				int idx = 0;
				for(Bogie bogie : bogies) {
					capacities[idx] = bogie.getCapacity();
					idx++;
				}
				
				System.out.println("Original Capacities: " + Arrays.toString(capacities));
				
				// ---- BUBBLE SORT LOGIC ----
				for(int i = 0; i < capacities.length - 1; i++) {
					for(int j  = 0; j < capacities.length - i - 1; j++) {
						if(capacities[i] > capacities[i + 1]) {
							int temp = capacities[i];
							capacities[i] = capacities[i + 1];
							capacities[i + 1] = temp;
						}
					}
				}
				
				System.out.println("Sorted Capacities (Ascending): " + Arrays.toString(capacities));
				yield true;
			}
			case "10" -> {
				String types[] = new String[bogies.size()];
				
				int idx = 0;
				for(Bogie bogie : bogies) {
					types[idx] = bogie.getName();
					idx++;
				}
				
				System.out.println("Original Bogie Names: \n" + Arrays.toString(types) + "\n");
				Arrays.sort(types);
				System.out.println("Sorted Bogie Names (Alphabetical): \n" + Arrays.toString(types) + "\n");
				
				yield true;
			}
			case "11" -> {
				// Create array of bogie IDs
				String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
				
				// Bogie ID to search
				System.out.print("Enter Bogie ID to Search: ");
				String searchId = scanner.nextLine();
				
				// Display all bogies
				System.out.println("Available Bogie IDs: ");
				for(String id : bogieIds) {
					System.out.println(id);
				}
				
				// ---- LINEAR SEARCH LOGIC ----
				// Traverse each element sequentially
				boolean found = false;
				for(String id : bogieIds) {
					if(searchId.equals(id)) {
						found = true;
						break;
					}
				}
				
				if(found) {
					System.out.printf("Bogie %s found in train consist\n", searchId);
				}else {
					System.out.printf("Bogie %s NOT found in train consist\n", searchId);
				}
				yield true;
			}
			case "0" -> {
				System.out.println("Exiting to main menu...\n");
				yield false;
			}
			default -> {
				System.out.println("Invalid Choice!!");
				yield true;
			}
			};
		}
	}

	private static void handleValidationFlow(Scanner scanner) {
		final String TRAIN_ID_REGEX = "TRN-\\d{4}";
		final String CARGO_ID_REGEX = "PET-[A-Z]{2}";

		Pattern trainIdPattern = Pattern.compile(TRAIN_ID_REGEX);
		Pattern cargoIdPattern = Pattern.compile(CARGO_ID_REGEX);

		boolean inMenu = true;

		while(inMenu) {
			System.out.println("--- Validation Menu ---");
			System.out.println("1. Validate Train ID");
			System.out.println("2. Validate Cargo ID");
			System.out.println("0. Exit");
			System.out.print("Enter Your Choice: ");
			String choice = scanner.nextLine();

			inMenu = switch(choice) {
			case "1" -> {
				
				// Accept input
				System.out.print("Enter Train ID (Format: TRN-1234): ");
				String trainId = scanner.nextLine();
				
				Matcher matcher = trainIdPattern.matcher(trainId);
				
				System.out.println("Train ID Valid : " + matcher.matches());
				
				yield true;
			}
			case "2" -> {
				
				// Accept input
				System.out.print("Enter Cargo ID (Format: PET-AB): ");
				String cargoId = scanner.nextLine();
				
				Matcher matcher = cargoIdPattern.matcher(cargoId);
				
				System.out.println("Cargo ID Valid : " + matcher.matches());
				yield true;
			}
			case "0" -> {
				System.out.println("Exiting to main menu...\n");
				yield false;
			}
			default -> {
				System.out.println("Invalid Choice!!");
				yield true;
			}
			};
		}



	}
	
	private static void handleCargoTrainFlow(Scanner scanner) throws CargoSafetyException{
		List<GoodsBogie> goodsBogies = new ArrayList<>();
		
		boolean inMenu = true;
		
		while(inMenu) {
			System.out.println("\n--- Cargo Train Consist Menu ---");
			System.out.println("1. Add Bogies");
			System.out.println("2. Remove Bogies");
			System.out.println("3. Check if Bogie Exists");
			System.out.println("4. Check Cargo Compliance");
			System.out.println("5. Display Consists");
			System.out.println("0. Exit");
			System.out.print("Enter Choice: ");
			String choice = scanner.nextLine();

			inMenu = switch(choice) {
			case "1" -> {
				System.out.print("Enter the type of goods bogie to add: ");
				String type = scanner.nextLine();

				System.out.print("Enter the cargo of to add: ");
				String cargo = scanner.nextLine();

				goodsBogies.add(new GoodsBogie(type, cargo));
				System.out.printf("Added good bogie [%s] with cargo [%s] to train successfully.\n", type, cargo);

				yield true;
			}
			case "2" -> {
				System.out.print("Enter type of bogie to remove: ");
				String type = scanner.nextLine();

				for(GoodsBogie b : goodsBogies) {
					if(b.getType().equals(type)) {
						goodsBogies.remove(b);
						System.out.printf("Removed bogie type [%s] from train successfully.\n", type);
						yield true;
					}
				}

				System.out.printf("The bogie type [%s] does not exist.\n", type);
				yield true;

			}
			case "3" -> {
				System.out.print("Enter type of bogie to check: ");
				String type = scanner.nextLine();

				for(GoodsBogie b : goodsBogies) {
					if(b.getType().equals(type)) {
						System.out.printf("Contains \'%s\'? :  true\n", type);
						yield true;
					}
				}

				System.out.printf("Contains \'%s\'? :  false\n", type);

				yield true;
			}
			case "4" -> {
				
				long startStream = System.nanoTime();
				boolean isSafe = goodsBogies.stream().allMatch(b -> b.getType().equalsIgnoreCase("Cylindrical") && b.getCargo().equalsIgnoreCase("Coal"));
				long endStream = System.nanoTime();
				
				long startLoop = System.nanoTime();
				for(GoodsBogie bogie : goodsBogies) {
					if(bogie.getType().equalsIgnoreCase("Cylindrical") && bogie.getCargo().equalsIgnoreCase("Coal")) {
						isSafe = false;
						break;
					}
				}
				long endLoop = System.nanoTime();
				
				System.out.println("Loop: " + (endLoop - startLoop));
				System.out.println("Stream: " + (endStream - startStream));
				
				System.out.println("Safety Complicance Status: " + isSafe);
				System.out.println("Train formation is " + (isSafe ? "SAFE" : "NOT SAFE"));
				
				if(!isSafe) {
					throw new CargoSafetyException("Unsafe for cargo assignment");
				}
				
				yield true;
			}
			case "5" -> {
				System.out.println("Bogie Capacity Details:-\n");

				for(GoodsBogie bogie : goodsBogies) {
					System.out.printf("%s -> %s\n", bogie.getType(), bogie.getCargo());
				}

				yield true;
			}
			case "0" -> {
				System.out.println("Exititng to main menu....");
				yield false;
			}
			default -> {
				System.out.println("Invalid Choice!!");
				yield true;
			}
			};
		}
		
	}
}

/**
 * Class to represent a bogie
 */
class Bogie{
	private String name;
	private int capacity;

	public Bogie(String name, int capacity) throws InvalidCapacityException{
		if(capacity <= 0) {
			throw new InvalidCapacityException("Capacity must be greater than zero");
		}
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
 * Class to represent a Goods Bogie
 */
class GoodsBogie{
	String type;
	String cargo;
	
	public GoodsBogie(String type, String cargo) {
		this.type = type;
		this.cargo = cargo;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getCargo() {
		return this.cargo;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s)", type, cargo);
	}
}

/**
 * Class to represent the exception thrown on invalid bogie capacity
 */
// ---- CUSTOM EXCEPTION ----
@SuppressWarnings("serial")
class InvalidCapacityException extends Exception {
	public InvalidCapacityException(String message) {
		super(message);
	}
}


/**
* Class to represent a custom runtime exception for invalid cargo
*/
// ---- CUSTOM RUNTIME EXCEPTION ----
@SuppressWarnings("serial")
class CargoSafetyException extends RuntimeException {
	public CargoSafetyException(String message) {
		super(message);
	}
}