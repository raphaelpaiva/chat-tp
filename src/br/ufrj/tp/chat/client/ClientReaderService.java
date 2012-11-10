package br.ufrj.tp.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReaderService extends Thread {
	
	private BufferedReader serverToClient;
	private String clientId;
	private boolean active = true;
	
	public ClientReaderService(Socket socket, String clientId) throws IOException {
		serverToClient = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
		this.clientId = clientId;
	}
	
	@Override
	public void run() {
		System.out.println("Your id is " + clientId);
		try {
			while (active) {
				String message;
				message = serverToClient.readLine();
				
				if ( !message.startsWith(clientId) ) {
					System.out.println(message);
				}
				
			}
		} catch (IOException e) {
			System.out.println("Error while reading message from Server. StackTrace should follow.");
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		active = false;
	}
	
}
