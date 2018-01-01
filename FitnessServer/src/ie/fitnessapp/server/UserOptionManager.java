package ie.fitnessapp.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class UserOptionManager {

	public static void main(String[] args) {
		
		
	}// End of main
	
	public static void Register(int clientID, Socket clientSocket) {
		System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - connection accepted");
		
		File file = new File("Users/"+ie.fitnessapp.options.Register.getPPSN()+"/"+ie.fitnessapp.options.Register.getPPSN()+".txt");
		
		if(file.exists()){
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - profile already exsists");
		}else{
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - register successful");
			file.getParentFile().mkdirs();
			
			try {
				PrintWriter writer = new PrintWriter("Users/"+ie.fitnessapp.options.Register.getPPSN()+"/"+ie.fitnessapp.options.Register.getPPSN()+".txt", "UTF-8");
				
				writer.println(ie.fitnessapp.options.Register.getName());
				writer.println(ie.fitnessapp.options.Register.getAddess());
				writer.println(ie.fitnessapp.options.Register.getPPSN());
				writer.println(ie.fitnessapp.options.Register.getAge());
				writer.println(ie.fitnessapp.options.Register.getHeight());
				writer.println(ie.fitnessapp.options.Register.getWeight());
				
				writer.close();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
			
			
			
		
			
		
	}
	
	
}
