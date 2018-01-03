package ie.fitnessapp.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import ie.fitnessapp.objects.ServerOptions;

// Requester allows the user to communicate with the server
public class Requester {
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message = "";
	Scanner stdin;

	Requester() {
	}

	// Runs a new thread to communicate with the server
	public static void main(String args[]) {
		Requester client = new Requester();
		client.run();
	}

	// Threaded client
	void run() {

		// Scanner to get user input
		stdin = new Scanner(System.in);

		try {
			// 1. creating a socket to connect to the server
			requestSocket = new Socket(ServerOptions.getServerIp(), Integer.parseInt(ServerOptions.getServerPort()));
			System.out.println("Connected to " + ServerOptions.getServerIp() + " in port " + ServerOptions.getServerPort());

			// 2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());

			// 3: Communicating with the server
			// This section will keep running and sending data to the server
			// Can end it by entering 'exit' which will end the connection and
			// program
			do {
				try {
					message = (String) in.readObject();
					System.out.println(message);
					message = stdin.nextLine();
					sendMessage(message);

				} catch (ClassNotFoundException classNot) {
					System.err.println("ERROR: Data received in unknown format");
				}

			} while (!message.equals("exit"));

		} catch (UnknownHostException unknownHost) {
			System.err.println("ERROR: You are trying to connect to an unknown host");
		} catch (ConnectException | NullPointerException NullPointerException) {
			System.out.println("ERROR: Connection Error" + "\n 1.Check that server IP: " + ServerOptions.getServerIp()
					+ " and Port: " + ServerOptions.getServerPort() + " are correct."
					+ "\n 2.Check server settings in the \"config.properties\".");
		} catch (IOException ioException) {
			System.out.println("ERROR: Input/Output Error");

		}

		finally {
			// 4: Closing connection
			try {
				in.close();
				out.close();
				requestSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	// sendMessage sends a string that is sent to it to the server
	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}