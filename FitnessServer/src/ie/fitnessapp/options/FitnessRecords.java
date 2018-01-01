package ie.fitnessapp.options;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class FitnessRecords {

	public static void RecordsAdd(int clientID, Socket clientSocket, String userDetails, String option1, String option2) throws IOException, ClassNotFoundException {
		
		double convert;
		
		if (option1.equals("1")){
			option1 = "Walking";
		}else if (option1.equals("2")){
			option1 = "Running";
		}else if (option1.equals("3")){
			option1 = "Cycling";
		}
		
		convert = Double.parseDouble(option2);
		
		File file = new File("Users/"+userDetails+"/Fitness-Records/"+option1+".txt");
		
		
		if(file.exists()){
			PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Fitness-Records/"+option1+".txt"), true));
			
			writer.println(convert);
			
			writer.close();
		}else{
			file.getParentFile().mkdirs();
			
			PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Fitness-Records/"+option1+".txt"), true));
			
			writer.println(convert);
			
			writer.close();
			
		}
	}
}
