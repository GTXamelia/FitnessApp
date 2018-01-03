package ie.fitnessapp.options;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import ie.fitnessapp.settings.OutputMessages;

public class RegisterUser {

	public static void main(String[] args) {
	}// End of main

	// Allows the user to add a new user to the server
	public static void Register(int clientID, Socket clientSocket) {

		PrintWriter writer;
		File file = new File("Users/" + ie.fitnessapp.objects.RegisterOB.getPPSN() + "/User-Details.txt");

		if (file.exists()) {
			System.out.println("Client " + clientID + ": Address - " + clientSocket.getInetAddress().getHostName() + " - tried registering with PPSN: \"" + ie.fitnessapp.objects.RegisterOB.getPPSN() + "\" (Already Registered)"); // Outputs message to console displaying client status
			OutputMessages.Addon = "PPSN: \"" + ie.fitnessapp.objects.RegisterOB.getPPSN()+ "\" already exsits try again";
		} else {

			file.getParentFile().mkdirs(); // Create file directory using reference 'file'

			try {
				writer = new PrintWriter("Users/" + ie.fitnessapp.objects.RegisterOB.getPPSN() + "/User-Details.txt", "UTF-8");

				// Prints each element of the RegisterOB object to the file
				writer.println(ie.fitnessapp.objects.RegisterOB.getName());
				writer.println(ie.fitnessapp.objects.RegisterOB.getAddess());
				writer.println(ie.fitnessapp.objects.RegisterOB.getPPSN());
				writer.println(ie.fitnessapp.objects.RegisterOB.getAge());
				writer.println(ie.fitnessapp.objects.RegisterOB.getWeight());
				writer.println(ie.fitnessapp.objects.RegisterOB.getHeight());

				writer.close(); // Close PrintWriter

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Client " + clientID + ": Address - " + clientSocket.getInetAddress().getHostName() + " - registered with PPSN: \"" + ie.fitnessapp.objects.RegisterOB.getPPSN() + "\""); // Outputs message to console displaying client status
			OutputMessages.Addon = "Register successful with PPSN: \"" + ie.fitnessapp.objects.RegisterOB.getPPSN()+ "\"";
		}
	}
}
