package br.ufrj.tp.chat.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private static final String SERVER_IP_ADDRESS = "127.0.0.1";
	private static final int SERVER_DEFAULT_PORT = 6500;

	private Socket socket;
	private PrintWriter clientToServer;
	
	private ClientReaderService clientReader;
	
	private String clientId;
	
	public static int pontos;
	
	public Client(String clientId) {
		this.clientId = clientId;
		pontos = 0;
	}

	public void start() throws UnknownHostException, IOException {
		socket = new Socket(SERVER_IP_ADDRESS, SERVER_DEFAULT_PORT);
		clientToServer = new PrintWriter(socket.getOutputStream());

		clientReader = new ClientReaderService(socket, clientId);
		clientReader.start();
	}
	
	public void sendMessage(String message) {
		clientToServer.println(clientId + ": " + message);
		clientToServer.flush();
	}
	
	public void sendMessageGame(String message) {
		clientToServer.println(message);
		clientToServer.flush();
		if (message.startsWith("!")) pontos++;
	}
	
	public void shutdown() throws IOException {
		clientReader.shutdown();
		socket.close();
	}

}
