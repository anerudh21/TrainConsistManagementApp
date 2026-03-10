package com.main;
import java.util.*;

/**
 * =======================================
 * MAIN CLASS - TrainConsistManagementApp
 * =======================================
 * 
 * Use Case 1: Initialize Train and Display Consist Summary
 * 
 * Description:
 * This class represents the entry point of the Train Consist
 * Management Application.
 * 
 * At this stage, the application:
 * - Creates an empty train consist
 * - Use a dynamic List to store 
 * - Displays intial bogie count
 * - Prints the current state of the train
 * 
 * This use case introduces collection initialization and 
 * basic program startup flow.
 * 
 * @author Developer
 * @version 1.0
 */
public class TrainConsistManagementApp {
	
	/**
	 * Main entry point to the app
	 * 
	 * @param args	Command-Line args
	 */
	public static void main(String[]args) {
		
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
		System.out.println("\nSystem ready for operations");
	}
}