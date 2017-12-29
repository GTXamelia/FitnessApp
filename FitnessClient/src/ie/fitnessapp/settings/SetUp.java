package ie.fitnessapp.settings;

// This .java file is responsible for settings up the program.
// It will run each .java file in sequence to set up necessary components 
public class SetUp {

	public static void main(String[] args) {
		
		// Runs Settings.java
		// Responsible for creating the properties file
		FileSettings.main(args);
		
		// Runs EstablishConnection.java
		// Responsible for server connection (If there is a server to connect to)
		EstablishConnection.main(args);
		
	}

}
