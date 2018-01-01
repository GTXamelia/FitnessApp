package ie.fitnessapp.options;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class FitnessRecords {

	public static void RecordsAdd(int clientID, Socket clientSocket, ObjectOutputStream out, String userDetails, String message) throws IOException, ClassNotFoundException {
		
		ObjectInputStream in = null;
		
		File file = new File("Users/"+userDetails+"/Fitness-Records/"+message+".txt");
		
		if (message.equals("1")){
			message = "Walking";
		}else if (message.equals("2")){
			message = "Running";
		}else if (message.equals("3")){
			message = "Cycling";
		}
		
		if(file.exists()){
			PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Fitness-Records/"+message+".txt"), true));
			writer.println("Test");
			
			writer.close();
		}else{
			file.getParentFile().mkdirs();
			
			PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Fitness-Records/"+message+".txt"), true));
			
			writer.println("Test");
			
			writer.close();
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
