package ie.fitnessapp.client;

import ie.fitnessapp.settings.SetUp;

// Runner is responsible for starting the program
public class Runner {

	public static void main(String[] args) {
		
		// Try SetUp and if any error occurs an error message is output
		try{
			SetUp.main(args); // Runs setup which is responsible for making the program run
		}
		catch(Exception Exception){
			System.out.println("Server Error");
		}
	}
}
