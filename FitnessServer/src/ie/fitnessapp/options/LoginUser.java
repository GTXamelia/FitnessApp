package ie.fitnessapp.options;

import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginUser {

	public static boolean fileStatus(int clientID, Socket clientSocket, ObjectOutputStream out, String userDetails) {
		
		File file = new File("Users/"+ie.fitnessapp.objects.RegisterOB.getPPSN()+"/"+ie.fitnessapp.objects.RegisterOB.getPPSN()+".txt");
		
		if(file.exists()){
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - found user with PPSN \""+userDetails+"\"");
			
			return true;
		}else{
			return false;
		}
		
	}
}
