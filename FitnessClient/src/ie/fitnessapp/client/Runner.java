package ie.fitnessapp.client;

import ie.fitnessapp.settings.SetUp;

public class Runner {

	public static void main(String[] args) {
		
		try{
			SetUp.main(args);
		}
		catch(Exception Exception){
			System.out.println("Server Error");
		}
		
		
	}

}
