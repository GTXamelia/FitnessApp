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
		
		File file = new File("Users/"+userDetails+"/Meal-Records.txt");
		
		
		if(file.exists()){
			PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Meal-Records.txt"), true));
			
			writer.println(option2); // Information about meal
			writer.println(option1); // Meal header
			
			writer.close();
		}else{
			file.getParentFile().mkdirs();
			
			PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Meal-Records.txt"), true));
			
			writer.println(option2); // Information about meal
			writer.println(option1); // Meal header
			
			writer.close();
			
		}
	}
	
	public static void MealListLast10(int clientID, Socket clientSocket, String userDetails, String option1, String option2) throws IOException, ClassNotFoundException {
		
		BufferedReader in = new BufferedReader(new FileReader("Users/"+userDetails+"/Meal-Records.txt"));
		File file = new File("Users/"+userDetails+"/Meal-Records.txt");
		ArrayList<String> list = new ArrayList<String>();
		
		String line;
		int stop = 0;
		
		list.clear();
		
		if(file.exists()){
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - is accessing meal records");
			
			// Read from file line by line and add contents to list array
			while((line = in.readLine()) != null){
				list.add(line);
			}
			
			line = "";
			 for(int i=list.size()-1; i>=0; i--) {
				 
				 line += list.get(i) + "\n";
				 
		         stop++;
		        
		        if(stop == 20){
		        	i = 0;
		        }
			 }
			 
			 System.out.println(line);
		
		}else{
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - tried to show list of meals (No meal records)");
		}
		in.close();
	}
}
