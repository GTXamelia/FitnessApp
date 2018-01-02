package ie.fitnessapp.server;

// Runner is responsible for running the server
public class Runner {

	public static void main(String[] args) {
		
		// Try EchoServer and if any error occurs it will display a message
		try {
			EchoServer.main(args); // Runs EchoServer which is responsible to communicate with the client(s)
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}