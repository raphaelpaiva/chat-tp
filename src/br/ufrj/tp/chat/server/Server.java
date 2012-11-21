package br.ufrj.tp.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {
	
	private static final int DEFAULT_PORT = 6500;
	private ServerSocket serverSocket;
	private List<ClientListener> listeners;
	
	public static boolean game;
	public static int pergunta;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Server server = new Server();
		server.start();
		
		while(true) {
			try{
				server.listen();
			}catch(Exception e){}
		}
		
	}
	
	private void start() throws IOException {
		System.out.println("Iniciando servidor.");
		
		serverSocket = new ServerSocket(DEFAULT_PORT);
		listeners = new LinkedList<ClientListener>();
		game = false;
		pergunta = 1;
		
		Runtime.getRuntime().addShutdownHook(new ServerShutdownHook(this));
	}
	
	public void listen() throws IOException {
		Socket socket = serverSocket.accept();
		System.out.println("Conexao estabelecida com socket " + socket.getInetAddress() + ".");
		
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
			System.out.println("Fechando todas as conexoes com o servidor.");
			
			for (ClientListener listener : listeners) {
				listener.shutdown();
			}
			
			listeners.clear();
			if (!serverSocket.isClosed())
				serverSocket.close();
		} catch (Exception e) {
			System.out.println("Error while shutting down Server. StackTrace should follow.");
			e.printStackTrace();
		}
	}

}
