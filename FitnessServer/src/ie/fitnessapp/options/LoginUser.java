package ie.fitnessapp.options;

import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ie.fitnessapp.settings.OutputMessages;

// LoginUser is responsible for logging valid users in and denying ones that don't exist
public class LoginUser {

	public static boolean fileStatus(int clientID, Socket clientSocket, ObjectOutputStream out, String userDetails) {
		
		File file = new File("Users/"+userDetails+"/User-Details.txt");// File location
		
		// If file exists a true statement is returned
		// If file is not found a false statement is returned
		if(file.exists()){
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - logging in with PPSN: \""+userDetails+"\""); // Client status output to console
			
			OutputMessages.Addon = "Logging in with PPSN: \""+userDetails+"\"";
			
			return true;
		}else{
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - login failed with PPSN: \""+userDetails+"\" (Doesn't exist)"); // Client status output to console
			
			OutputMessages.Addon = "Failed in with PPSN: \""+userDetails+"\"";
			
			return false;
		}
	}
}
