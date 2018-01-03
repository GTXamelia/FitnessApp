package ie.fitnessapp.options;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import ie.fitnessapp.settings.OutputMessages;

public class FitnessRecords {

	public static void RecordsAdd(int clientID, Socket clientSocket, String userDetails, String option1, double convert) throws IOException, ClassNotFoundException {
		
		PrintWriter writer;
		File file = new File("Users/"+userDetails+"/Fitness-Records.txt");
		
		// Gets the user selection and get the associated string
		if (option1.equals("1")){
			option1 = "Walking:";
		}else if (option1.equals("2")){
			option1 = "Running:";
		}else if (option1.equals("3")){
			option1 = "Cycling:";
		}
		
		// Checks if file exists
		// If file exists information is appended to it line by line
		// If there is no file found one is created and information appended to it line by line
		if(file.exists()){
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - added "+option1+" "+convert); // Client status output to console
			writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Fitness-Records.txt"), true));
			
			writer.println(option1+" "+convert); // Data to be written
		}else{
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - created \"Fitness-Records.txt\" and added "+option1+" "+convert); // Client status output to console
			file.getParentFile().mkdirs(); // Create file directory using reference 'file'
			
			writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Fitness-Records.txt"), true));
			
			writer.println(option1+" "+convert); // Data to be written
		}
		writer.close(); // Close PrintWriter
		
		OutputMessages.Addon = option1 +" "+  convert +" added to file \n";
	}

	public static void FitnessDelete(int clientID, Socket clientSocket, String userDetails, String option1) throws IOException, ClassNotFoundException {
		
		BufferedReader in;
		File file = new File("Users/"+userDetails+"/Fitness-Records.txt");
		ArrayList<String> list = new ArrayList<String>();
		PrintWriter writer;
		
		// Clear array list in case there is remaining data from previous deletions
		list.clear();
		
		// Variables
		String line;
		String keep = null;
		int stop = 0;
		
		// If file exists data will be read in from the file and added to an array list
		// the last 10 elements of the array list will be taken in 
		// the element that the user chose will be remove from the array list
		// the remaining elements in the array list will then be output back to the file
		if(file.exists()){
			
			in = new BufferedReader(new FileReader("Users/"+userDetails+"/Fitness-Records.txt"));
			
			// Read in all data from file and add to array list
			while((line = in.readLine()) != null){
				list.add(line);
			}
			
			// Run threw array list from top to bottom
			// Only last 10 are taken
			for(int i=list.size(); i>=0; i--){
				
		        if(stop == (Integer.parseInt(option1))){
		        	//System.out.println("removed: " +stop +" "+ list.get(stop));
		        	
		        	System.out.println(keep = list.get(i));
		        	list.remove(i); // Remove data
		        	i = 0;
		        }
		        
		        stop++;
		        
		        // If stop == 20 then 10 headers and 10 information prices have been read from array list
		        // the for loop is then stopped by making i = 0
		        if(stop == 20){
		        	i = 0;
		        }
			}
			
			writer = new PrintWriter(new FileOutputStream(new File("Users/"+userDetails+"/Fitness-Records.txt")));
			
			// Print all elements of array list to file
			for(String str: list) {
				  writer.println(str);
			}
			writer.close(); // Close writer
			in.close(); // Close buffered reader
			
			FitnessFileChecker(keep, userDetails);
		}else{
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - tried to delete from a file which doesn't exsist"); // Client status output to console
			
			// If file doens't exist the client will be informed
			OutputMessages.Addon = "File doesn't exsist \n";
		}
	}
	
	public static String FitnessListLast10(int clientID, Socket clientSocket, String userDetails, String option1, String option2) throws IOException, ClassNotFoundException {
		
		BufferedReader in;
		File file = new File("Users/"+userDetails+"/Fitness-Records.txt");
		ArrayList<String> list = new ArrayList<String>();
		
		// Variables
		String line = null;
		int stop = 0;
		
		// Clears list if any information is present
		// Stop data being appended twice if run twice
		list.clear();
		
		// If file exists data is read from the file line by line and stored in an array list
		// If no file is found then nothing will happen
		if(file.exists()){
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - is accessing fitness records"); // Client status output to console
			
			in = new BufferedReader(new FileReader("Users/"+userDetails+"/Fitness-Records.txt"));
			
			// Read from file line by line and add contents to list array
			// Stops when it reaches end of file
			while((line = in.readLine()) != null){
				list.add(line);
			}
			
			// Reset line variable
			line = "";
			
			// Goes to end of array list get the last 10 elements in the list
			// If there is less than 10 then it will get all of them
			for(int j = list.size() - 1; j >= 0; j--){
				
				line += (stop+1)+"."+list.get(j) + "\n";
				stop++;
				
				if (stop == 10){
					j = 0;
				}
			}
			
			in.close(); // Closes buffered reader
		}else{
			System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - tried to show list of fitness records (No fitness records)"); // Client status output to console
			
			// If file doens't exist the client will be informed
			line = "File doesn't exsist, please add records first. \n";
		}
		
		OutputMessages.Addon = line;
		
		return line;
	}
	
	public static void FitnessFileChecker(String keep, String userDetails) throws IOException, ClassNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader("Users/"+userDetails+"/Fitness-Records.txt"));     
		if (br.readLine() == null) {
			OutputMessages.Addon = "File is empty so there is nothing to delete \n";
		}else{
			OutputMessages.Addon = "Deleted "+keep+" "+"from the file\n";
		}
		br.close();
	}
}
