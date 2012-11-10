package br.ufrj.tp.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {
	
	private static final int DEFAULT_PORT = 6161;
	private ServerSocket serverSocket;
	private List<ClientListener> listeners;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Server server = new Server();

		server.start();
		
		while(true) {
			server.listen();
		}
		
	}
	
	private void start() throws IOException {
		System.out.println("Starting Server...");
		
		serverSocket = new ServerSocket(DEFAULT_PORT);
		listeners = new LinkedList<ClientListener>();
		
		Runtime.getRuntime().addShutdownHook(new ServerShutdownHook(this));
	}

	
	public void listen() throws IOException {
		Socket socket = serverSocket.accept();
		System.out.println("Accepted socket from " + socket.getInetAddress() + ".");
		
		ClientListener listener = new ClientListener(socket, this);
		
		register(listener);
	}
	
	public void broadcast(String message) {
		for (ClientListener listener : listeners) {
			listener.sendMessage(message);
		}
	}
	
	private void register(ClientListener listener) {
		listeners.add(listener);
		listener.start();
	}
	
	protected void deregister(ClientListener listener) {
		listeners.remove(listener);
	}

	public void shutdown() {
		try {
			System.out.println("Shutting down...");
			
			for (ClientListener listener : listeners) {
				listener.shutdown();
			}
			
			listeners.clear();
			
			serverSocket.close();
		} catch (Exception e) {
			System.out.println("Error while shutting down Server. StackTrace should follow.");
			e.printStackTrace();
		}
	}

}
