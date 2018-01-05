package ie.fitnessapp.options;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import ie.fitnessapp.settings.OutputMessages;

public class MealRecords {

	// Adds meal record to the meal-record file
	public static void MealAdd(int clientID, Socket clientSocket, String userDetails, String option1, String option2) throws IOException, ClassNotFoundException {

		File file = new File("Users/" + userDetails + "/Meal-Records.txt");
		PrintWriter writer;

		// Gets the user selection and get the associated string
		if (option1.equals("1")) {
			option1 = "Breakfast:";
		} else if (option1.equals("2")) {
			option1 = "Lunch:";
		} else if (option1.equals("3")) {
			option1 = "Dinner:";
		} else if (option1.equals("4")) {
			option1 = "Snack:";
		} else if (option1.equals("5")) {
			option1 = "Supper:";
		}

		// Checks if file exists
		// If file exists information is appended to it line by line
		// If there is no file found one is created and information appended to
		// it line by line
		if (file.exists()) {
			writer = new PrintWriter( new FileOutputStream(new File("Users/" + userDetails + "/Meal-Records.txt"), true));

			writer.println(option1 + " " + option2); // Meal data printed to file

		} else {
			file.getParentFile().mkdirs(); // Create file directory using reference 'file'

			writer = new PrintWriter( new FileOutputStream(new File("Users/" + userDetails + "/Meal-Records.txt"), true));

			writer.println(option1 + " " + option2); // Meal data printed to file
		}
		writer.close(); // Closes PrintWriter
	}

	// Allows the user to delete one of the 10 from the list
	public static void MealDelete(int clientID, Socket clientSocket, String userDetails, String option1) throws IOException, ClassNotFoundException {

		BufferedReader in;
		File file = new File("Users/" + userDetails + "/Meal-Records.txt");
		ArrayList<String> list = new ArrayList<String>();
		PrintWriter writer;

		// Clear array list in case there is remaining data from previous
		// deletions
		list.clear();

		// Variables
		String line;
		int stop = 0;
		String keep = null;

		// If file exists data will be read in from the file and added to an
		// array list
		// the last 10 elements of the array list will be taken in
		// the element that the user chose will be remove from the array list
		// the remaining elements in the array list will then be output back to
		// the file
		if (file.exists()) {

			in = new BufferedReader(new FileReader("Users/" + userDetails + "/Meal-Records.txt"));

			// Read in all data from file and add to array list
			while ((line = in.readLine()) != null) {
				list.add(line);
			}

			// Run threw array list from top to bottom
			// Only last 10 are taken
			for (int i = list.size(); i >= 0; i--) {

				if (stop == (Integer.parseInt(option1))) {

					keep = list.get(i);
					list.remove(i); // Remove data
					i = 0;
				}

				stop++;
			}

			writer = new PrintWriter(new FileOutputStream(new File("Users/" + userDetails + "/Meal-Records.txt")));

			// Print all elements of array list to file
			for (String str : list) {
				writer.println(str);
			}
			writer.close(); // Close writer
			in.close(); // Close buffered reader

			// Checks if file is empty
			MealFileChecker(keep, userDetails);
		} else {
			System.out.println("Client " + clientID + ": Address - " + clientSocket.getInetAddress().getHostName() + " - tried to delete from a file which doesn't exsist"); // Client status output to console

			// If file doens't exist the client will be informed
			OutputMessages.Addon = "File doesn't exsist \n";
		}
	}

	// Displays the last 10 meal records added to the file
	public static String MealListLast10(int clientID, Socket clientSocket, String userDetails, String option1, String option2) throws IOException, ClassNotFoundException {

		BufferedReader in;
		File file = new File("Users/" + userDetails + "/Meal-Records.txt");
		ArrayList<String> list = new ArrayList<String>();

		// Variables
		String line = null;
		int stop = 0;

		// Clears list if any information is present
		// Stop data being appended twice if run twice
		list.clear();

		// If file exists data is read from the file line by line and stored in
		// an array list
		// If no file is found then nothing will happen
		if (file.exists()) {
			System.out.println("Client " + clientID + ": Address - " + clientSocket.getInetAddress().getHostName() + " - is accessing meal records"); // Client status output to console

			in = new BufferedReader(new FileReader("Users/" + userDetails + "/Meal-Records.txt"));

			// Read from file line by line and add contents to list array
			// Stops when it reaches end of file
			while ((line = in.readLine()) != null) {
				list.add(line);
			}

			// Reset line variable
			line = "";

			// Goes to end of array list get the last 10 elements in the list
			// If there is less than 10 then it will get all of them
			for (int j = list.size() - 1; j >= 0; j--) {

				line += (stop + 1) + "." + list.get(j) + "\n";
				stop++;

				if (stop == 10) {
					j = 0;
				}
			}

			in.close(); // Closes buffered reader
		} else {
			System.out.println("Client " + clientID + ": Address - " + clientSocket.getInetAddress().getHostName() + " - tried to show list of meals (No meal records)"); // Client status output to console

			// If file doesn't exist a message will be sent back informing the
			// client
			line = "File doesn't exsist, please add records first. \n";
		}

		return line;
	}
	
	// Creates a message for the user about the status of deletion
	public static void MealFileChecker(String keep, String userDetails) throws IOException, ClassNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader("Users/" + userDetails + "/Fitness-Records.txt"));
		OutputMessages.Addon = "";

		if (br.readLine() == null) {
			OutputMessages.Addon = "File is empty now. \n";
		} else if (keep == null) {
			OutputMessages.Addon = "That record doesn't exsist\n";
		} else {
			OutputMessages.Addon = "Deleted " + keep + " " + "from the file\n";
		}
		br.close();
	}
}
