package ie.fitnessapp.options;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MealRecords {

	public static void MealAdd(int clientID, Socket clientSocket, String userDetails, String option1, String option2) throws IOException, ClassNotFoundException {
		
		if (option1.equals("1")){
			option1 = "Breakfast";
		}else if (option1.equals("2")){
			option1 = "Lunch";
		}else if (option1.equals("3")){
			option1 = "Dinner";
		}else if (option1.equals("4")){
			option1 = "Snack";
		}else if (option1.equals("5")){
			option1 = "Supper";
		}
		
		File file = new File("Users/"+userDetails+"/Meal-Records/"+option1+".txt");
		
		
		if(file.exists()){
			PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Meal-Records.txt"), true));
			
			writer.println(option1);
			writer.println(option2);
			
			writer.close();
		}else{
			file.getParentFile().mkdirs();
			
			PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Meal-Records.txt"), true));
			
			writer.println(option1);
			writer.println(option2);
			
			writer.close();
			
		}
	}
}
