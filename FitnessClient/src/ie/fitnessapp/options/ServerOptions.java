package ie.fitnessapp.options;

public class ServerOptions {
	private static String serverIp;
	private static String serverPort;
	
	// Getters And Setters
	public static String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public static String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String string) {
		this.serverPort = string;
	}
}
