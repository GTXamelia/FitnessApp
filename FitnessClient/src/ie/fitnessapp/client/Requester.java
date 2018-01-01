package ie.fitnessapp.client;



import java.io.*;
import java.net.*;
import java.util.Scanner;

import ie.fitnessapp.objects.ServerOptions;

public class Requester{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message="";
 	Scanner stdin;
	Requester(){}
	
	void run(){
		
		stdin = new Scanner(System.in);
		
		try{
			
			//1. creating a socket to connect to the server
			requestSocket = new Socket(ServerOptions.getServerIp(), Integer.parseInt(ServerOptions.getServerPort()));
			System.out.println("Connected to "+ServerOptions.getServerIp()+" in port "+ServerOptions.getServerPort());
			
			System.out.println(ServerOptions.getServerIp());
			System.out.println(ServerOptions.getServerPort());
			
			//2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			System.out.println("Hello");
			
			//3: Communicating with the server
			do{
				try{
					message = (String)in.readObject();
					System.out.println(message);
					message = stdin.nextLine();
					sendMessage(message);
						
				}
				catch(ClassNotFoundException classNot)
				{
					System.err.println("data received in unknown format");
				}
			}while(!message.equals("3"));
			
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(ConnectException | NullPointerException NullPointerException){
			//ConnectException.printStackTrace();
			System.out.println("Connection Error: "
					+ "\n 1.Check that server IP: "+ServerOptions.getServerIp()+" and Port: "+ServerOptions.getServerPort()+ " are correct."
					+ "\n 2.Check server settings in the \"config.properties\".");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	
	public static void main(String args[])
	{
		Requester client = new Requester();
		client.run();
	}
}