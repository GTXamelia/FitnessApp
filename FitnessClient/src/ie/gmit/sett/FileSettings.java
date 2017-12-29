package ie.gmit.sett;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class FileSettings {
  public static void main(String[] args) {

	Properties prop = new Properties();
	OutputStream output = null;
	File f = new File("config.properties");
	
	if(f.exists()){
		// Doesn't create file
		System.out.println("File existed");
	}else{
		try {
			
			output = new FileOutputStream("config.properties");

			// set the properties value
			prop.setProperty("serverip", "192.168.0.11");
			prop.setProperty("serverport", "2004");

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
	}
	
  }// End of main
}// End of class