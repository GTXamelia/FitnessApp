package ie.fitnessapp.options;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class FitnessRecords {

	public static void Records(int clientID, Socket clientSocket, ObjectOutputStream out, String userDetails) throws IOException {
		
		PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Fitness-Records.txt"), true /* append = true */));
			
		writer.println("Test \n");
		
		writer.close();
		
		
		
	}

}
