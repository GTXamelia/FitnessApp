package ie.fitnessapp.options;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class RegisterUser {

	public static void main(String[] args) {
	}// End of main
	
	public static void Register(int clientID, Socket clientSocket, ObjectOutputStream out) {
		System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - connection accepted");
		
		File file = new File("Users/"+ie.fitnessapp.objects.RegisterOB.getPPSN()+"/User-Details.txt");
		
		if(file.exists()){
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - profile already exsists");
			
			try {
				out.writeObject("Profile already exsists for PPSN:\""+ie.fitnessapp.objects.RegisterOB.getPPSN()+"\", please try again.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			
			file.getParentFile().mkdirs();
			
			try {
				PrintWriter writer = new PrintWriter("Users/"+ie.fitnessapp.objects.RegisterOB.getPPSN()+"/User-Details.txt", "UTF-8");
				
				writer.println(ie.fitnessapp.objects.RegisterOB.getName());
				writer.println(ie.fitnessapp.objects.RegisterOB.getAddess());
				writer.println(ie.fitnessapp.objects.RegisterOB.getPPSN());
				writer.println(ie.fitnessapp.objects.RegisterOB.getAge());
				writer.println(ie.fitnessapp.objects.RegisterOB.getWeight());
				writer.println(ie.fitnessapp.objects.RegisterOB.getHeight());
				
				writer.close();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - register successful");
			
			try {
				out.writeObject("PPSN ID \""+ie.fitnessapp.objects.RegisterOB.getPPSN()+"\" added to the system.");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
