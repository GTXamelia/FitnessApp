package ie.fitnessapp.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import ie.fitnessapp.objects.RegisterOB;
import ie.fitnessapp.options.FitnessRecords;
import ie.fitnessapp.options.LoginUser;
import ie.fitnessapp.options.MealRecords;
import ie.fitnessapp.options.RegisterUser;
import ie.fitnessapp.settings.OutputMessages;

public class EchoServer {
  public static void main(String[] args) throws Exception {
    @SuppressWarnings("resource")
	ServerSocket m_ServerSocket = new ServerSocket(2004,10);
    int id = 0;
    while (true) {
      Socket clientSocket = m_ServerSocket.accept();
      ClientServiceThread cliThread = new ClientServiceThread(clientSocket, id++);
      cliThread.start();
    }
  }
}

class ClientServiceThread extends Thread {
	Socket clientSocket;
	String message;
	int clientID = -1;
	boolean running = true;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	// Misc Variables
	String userDetails;
	String option1;
	String option2;
	boolean check;
	int maxLength;

	ClientServiceThread(Socket s, int i) {
		clientSocket = s;
	    clientID = i;
	}
	
  public void run() {
    System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - connection started");
    try{
    	out = new ObjectOutputStream(clientSocket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(clientSocket.getInputStream());
		
		do{
			try{
				sendMessage(OutputMessages.Addon+ "\n" + OutputMessages.MainMenu);
				
				message = (String)in.readObject();
				
				if(message.compareToIgnoreCase("1")==0){
					
					// Outputs message to server console, informing on what the user is doing
					System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - is trying to register");
					
					// Asks user to enter their name.
					// Then stores that input in an object
					sendMessage("Please enter your name.");
					RegisterOB.setName((String)in.readObject());
					
					// Asks user to enter their address.
					// Then stores that input in an object
					sendMessage("Please enter your address.");
					RegisterOB.setAddess((String)in.readObject());
					
					// Asks user to enter their PPSN.
					// Then stores that input in an object
					sendMessage("Please enter your PPSN.");
					RegisterOB.setPPSN((String)in.readObject());
					
					// Asks user to enter their age.
					// Then stores that input in an object
					sendMessage("Please enter your age.");
					RegisterOB.setAge(Integer.parseInt((String)in.readObject()));
					
					// Asks user to enter their weight.
					// Then stores that input in an object
					sendMessage("Please enter your weight.");
					RegisterOB.setWeight(Double.parseDouble((String)in.readObject()));
					
					// Asks user to enter their height.
					// Then stores that input in an object
					sendMessage("Please enter your height.");
					RegisterOB.setHeight(Double.parseDouble((String)in.readObject()));
					
					RegisterUser.Register(clientID,clientSocket);
				}
				
				else if(message.compareToIgnoreCase("2")==0){
					// Outputs message to server console, informing on what the user is doing
					System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - is trying to login");
					
					// Asks user to enter their name.
					// Then stores that input in an object
					sendMessage("What is your PPSN?");
					userDetails = (String)in.readObject();
					
					check = LoginUser.fileStatus(clientID,clientSocket, out, userDetails);
					
					if(check){
						System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - logged in");
						do {
						
							sendMessage(OutputMessages.Addon+ "\n" + OutputMessages.LoginMenu);
							
							message = (String)in.readObject();
							
							if(message.compareToIgnoreCase("1")==0){
								
								sendMessage(OutputMessages.FitnessMenu);
								option1 = (String)in.readObject();
								
								sendMessage("Duration of activity:");
								option2 = (String)in.readObject();
								
								FitnessRecords.RecordsAdd(clientID, clientSocket, userDetails, option1, option2);
							}
							
							else if(message.compareToIgnoreCase("2")==0){
								
								sendMessage(OutputMessages.MealMenu);
								option1 = (String)in.readObject();
								
								sendMessage("Description of Meal:");
								message = (String)in.readObject();
								
								maxLength = message.length();
								
								if(maxLength >= 100){
									maxLength = 100;
								}else{
									System.out.println(maxLength);
									maxLength = message.length();
								}
								
								option2 = (message.substring(0, maxLength));
								
								MealRecords.MealAdd(clientID, clientSocket, userDetails, option1, option2);
							}
							
							else if (message.compareToIgnoreCase("3")==0){
								MealRecords.MealListLast10(clientID, clientSocket, userDetails, option1, option2);// Displays last 10 meal records
							}
							
							else if (message.compareToIgnoreCase("4")==0){
								FitnessRecords.FitnessListLast10(clientID, clientSocket, userDetails, option1, option2);// Displays last 10 fitness records
							}
							
							else if (message.compareToIgnoreCase("5")==0){
								
								sendMessage(OutputMessages.DeleteMenu);
								message = (String)in.readObject();
								
								sendMessage("What element would you like to delete");
								option1 = (String)in.readObject();
								
								if(message.equals("1")){
									MealRecords.MealDelete(clientID, clientSocket, userDetails, option1); // Delete a selected element
								}else if(message.equals("2")){
									System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - Is trying to delete");
									FitnessRecords.FitnessDelete(clientID, clientSocket, userDetails, option1); // Delete a selected element
								}
							}
						}while(!message.equals("6"));
						
					}else if(!check){
						// TODO Output message to user saying login failed
					}
				}
			}
			catch(ClassNotFoundException classnot){
				System.err.println("Data received in unknown format");
			}
			
    	}while(!message.equals("8"));
      
		System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - has disconnected");
    }
    catch (SocketException SocketException){
    	System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - disconnected unexpectedly");
    	
    }
    catch (Exception e) {
      e.printStackTrace();
    }
}

  // Responsible for sending a message to the user
  void sendMessage(String msg){
		try{
			out.writeObject(msg);
			out.flush();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
  
  
}