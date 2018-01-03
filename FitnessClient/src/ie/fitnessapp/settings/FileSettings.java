package ie.fitnessapp.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import ie.fitnessapp.objects.ServerOptions;

public class FileSettings {

	public static void main(String[] args) {

		Properties prop = new Properties();
		OutputStream output = null;
		InputStream input = null;
		File f = new File("config.properties");
		ServerOptions ServerOptions = new ServerOptions();

		// If a config file exists then information will be loaded
		// Otherwise if a file doesn't exist it will be created and information
		// will be written to
		if (f.exists()) {
			// Load a properties file
			try {
				input = new FileInputStream("config.properties");
				prop.load(input);

				// Sets server IP and port to be used in the requester
				ServerOptions.setServerIp(prop.getProperty("serverip"));
				ServerOptions.setServerPort(prop.getProperty("serverport"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				output = new FileOutputStream("config.properties");

				// Set the properties value
				prop.setProperty("serverip", "35.197.223.25"); // Google cloud IP
				prop.setProperty("serverport", "2004"); // Port used

				// Set object data
				ServerOptions.setServerIp("35.197.223.25"); // Google cloud IP
				ServerOptions.setServerPort("2004"); // Port used 

				// Save properties to project root folder
				prop.store(output, null);
			} catch (IOException io) {
				io.printStackTrace();
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}// End of main
}// End of class