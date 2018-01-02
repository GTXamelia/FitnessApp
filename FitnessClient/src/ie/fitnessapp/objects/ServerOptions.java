package ie.fitnessapp.objects;

// Used to store server port and IP
public class ServerOptions {
	private static String serverIp;
	private static String serverPort;
	
	// Getters and setters for server IP
	public static String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		ServerOptions.serverIp = serverIp;
	}
	
	// Getters and setters for server port
	public static String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String string) {
		ServerOptions.serverPort = string;
	}
}
