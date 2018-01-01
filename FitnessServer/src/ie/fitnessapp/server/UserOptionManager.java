package ie.fitnessapp.server;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class UserOptionManager {

	public static void main(String[] args) {
		
		
	}// End of main
	
	public static void Register(int clientID, Socket clientSocket) {
		System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - connection accepted");
		
		
			try {
				PrintWriter writer = new PrintWriter("/Users/"+ie.fitnessapp.options.Register.getPPSN()+"/"+ie.fitnessapp.options.Register.getPPSN()+".txt", "UTF-8");
				
				writer.println(ie.fitnessapp.options.Register.getPPSN());
				
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
			
		
	}
	
	
}
