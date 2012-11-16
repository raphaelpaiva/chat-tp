package br.ufrj.tp.chat.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;

public class Client {

	private static final String SERVER_IP_ADDRESS = "127.0.0.1";
	private static final int SERVER_DEFAULT_PORT = 6161;

	private Socket socket;
	private PrintWriter clientToServer;
	
	private ClientReaderService clientReader;
	
	private String clientId;
	
	public Client(String clientId) {
		this.clientId = clientId;
	}

	public void start() throws UnknownHostException, IOException {
		socket = new Socket(SERVER_IP_ADDRESS, SERVER_DEFAULT_PORT);
		clientToServer = new PrintWriter(socket.getOutputStream());

		clientReader = new ClientReaderService(socket, clientId);
		clientReader.start();
		
		System.out.println("Conectado ao servidor!");
		JFrame janela = new Janela(this.clientId);
		janela
		.setLocation((java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize().width / 2)
				- (janela.getWidth() / 2), (java.awt.Toolkit
				.getDefaultToolkit().getScreenSize().height / 2)
				- (janela.getHeight() / 2));
		janela.setVisible(true);
	}
	
	public void sendMessage(String message) {
		clientToServer.println(clientId + ": " + message);
		clientToServer.flush();
	}
	
	public void shutdown() throws IOException {
		clientReader.shutdown();
		socket.close();
	}

}
