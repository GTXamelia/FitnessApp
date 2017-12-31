package ie.fitnessapp.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import ie.fitnessapp.options.Register;

public class FileManager {

	public static void main(String[] args) {

		Properties prop = new Properties();
		OutputStream output = null;
		InputStream input = null;
		File f = new File("config.properties");
		Register Register;
		
		// Set up not complete
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			
			ie.fitnessapp.options.Register.setName(prop.getProperty("name"));
			ie.fitnessapp.options.Register.setAddess(prop.getProperty("address"));
			ie.fitnessapp.options.Register.setPPSN(prop.getProperty("ppsn"));
			ie.fitnessapp.options.Register.setAge(Integer.parseInt(prop.getProperty("age")));
			ie.fitnessapp.options.Register.setWeight(Double.parseDouble(prop.getProperty("weight")));
			ie.fitnessapp.options.Register.setHeight(Double.parseDouble(prop.getProperty("height")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		try {
			output = new FileOutputStream("config.properties");

			// set the properties value
			prop.setProperty("serverip", "192.168.0.11");
			prop.setProperty("serverport", "2004");
			
			ServerOptions ServerOptions = new ServerOptions();
			
			ServerOptions.setServerIp("192.168.0.11");
			ServerOptions.setServerPort("2004");

			// save properties to project root folder
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
		
		
		
	}// End of main
}
