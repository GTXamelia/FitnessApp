package ie.fitnessapp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import ie.fitnessapp.options.Register;
import ie.fitnessapp.settings.Extensions;

public class EchoServer {
  public static void main(String[] args) throws Exception {
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
	
	boolean check = false;

	ClientServiceThread(Socket s, int i) {
		clientSocket = s;
	    clientID = i;
	}
	
  public void run() {
    System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - connection accepted");
    try{
    	out = new ObjectOutputStream(clientSocket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(clientSocket.getInputStream());
		System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - connection accepted");
		
		do{
			try{
				sendMessage("1. Register"
						+ "\n2. Login"
						+ "\n3. Add Fitness Record"
						+ "\n4. Add Meal Record"
						+ "\n5. View Last 10 Fitness Records"
						+ "\n6. View Last 10 Meal Records"
						+ "\n7. Delete a Record");
				
				message = (String)in.readObject();
				
				if(message.compareToIgnoreCase("1")==0){
					
					// Outputs message to server console, informing on what the user is doing
					System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - is trying to register");
					
					// Asks user to enter their name.
					// Then stores that input in an object
					sendMessage("Please enter your name.");
					Register.setName((String)in.readObject());
					
					// Asks user to enter their address.
					// Then stores that input in an object
					sendMessage("Please enter your address.");
					Register.setAddess((String)in.readObject());
					
					// Asks user to enter their PPSN.
					// Then stores that input in an object
					sendMessage("Please enter your PPSN.");
					Register.setPPSN((String)in.readObject());
					
					// Asks user to enter their age.
					// Then stores that input in an object
					sendMessage("Please enter your age.");
					Register.setAge(Integer.parseInt((String)in.readObject()));
					
					// Asks user to enter their weight.
					// Then stores that input in an object
					sendMessage("Please enter your weight.");
					Register.setWeight(Double.parseDouble((String)in.readObject()));
					
					// Asks user to enter their height.
					// Then stores that input in an object
					sendMessage("Please enter your height.");
					Register.setHeight(Double.parseDouble((String)in.readObject()));
				}
				
				
				
			}
			catch(ClassNotFoundException classnot){
				System.err.println("Data received in unknown format");
			}
			
    	}while(!message.equals("3"));
      
		System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - has disconnected");
    }
    catch (SocketException SocketException){
    	System.out.println("Client "+clientID+": Address - "+clientSocket.getInetAddress().getHostName()+" - disconnected unexpectedly");
    	
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
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
