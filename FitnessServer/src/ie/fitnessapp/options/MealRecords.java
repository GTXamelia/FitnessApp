package ie.fitnessapp.options;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MealRecords {

	public static void MealAdd(int clientID, Socket clientSocket, String userDetails, String option1, String option2) throws IOException, ClassNotFoundException {
		
		File file = new File("Users/"+userDetails+"/Meal-Records.txt");
		PrintWriter writer;
		
		// Gets the user selection and get the associated string
		if (option1.equals("1")){
			option1 = "Breakfast:";
		}else if (option1.equals("2")){
			option1 = "Lunch:";
		}else if (option1.equals("3")){
			option1 = "Dinner:";
		}else if (option1.equals("4")){
			option1 = "Snack:";
		}else if (option1.equals("5")){
			option1 = "Supper:";
		}
		
		// Checks if file exists
		// If file exists information is appended to it line by line
		// If there is no file found one is created and information appended to it line by line
		if(file.exists()){
			writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Meal-Records.txt"), true));
			
			writer.println(option2); // Information about meal
			writer.println(option1); // Meal header
			
		}else{
			file.getParentFile().mkdirs(); // Create file directory using reference 'file'
			
			writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Meal-Records.txt"), true));
			
			writer.println(option2); // Information about meal
			writer.println(option1); // Meal header
		}
		writer.close(); // Closes PrintWriter
	}
	
	public static void MealListLast10(int clientID, Socket clientSocket, String userDetails, String option1, String option2) throws IOException, ClassNotFoundException {
		
		BufferedReader in = new BufferedReader(new FileReader("Users/"+userDetails+"/Meal-Records.txt"));
		File file = new File("Users/"+userDetails+"/Meal-Records.txt");
		ArrayList<String> list = new ArrayList<String>();
		
		// Variables
		String line;
		int stop = 0;
		
		// Clears list if any information is present
		// Stop data being appended twice if run twice
		list.clear();
		
		// If file exists data is read from the file line by line and stored in an array list
		// If no file is found then nothing will happen
		if(file.exists()){
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - is accessing meal records"); // Client status output to console
			
			// Read from file line by line and add contents to list array
			// Stops when it reaches end of file
			while((line = in.readLine()) != null){
				list.add(line);
			}
			
			// Reset line variable
			line = "";
			
			// Goes to end of array list get the last 10 elements in the list
			// If there is less than 10 then it will get all of them
			for(int i=list.size()-1; i>=0; i--){
				 
				line += list.get(i) + "\n";// Adds all data from list to a string
				 
		        stop++;
		        
		        if(stop == 20){
		        	i = 0;
		        }
			}
		}else{
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - tried to show list of meals (No meal records)"); // Client status output to console
		}
		in.close(); // Closes buffered reader
	}
}
