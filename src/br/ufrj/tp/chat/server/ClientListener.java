package br.ufrj.tp.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientListener extends Thread {

	private Socket socket;
	private BufferedReader clientToServer;
	private PrintWriter serverToClient;
	private Server server;
	
	
	
	public ClientListener(Socket socket, Server server) throws IOException {
		this.socket = socket;
		this.server = server;
		
		clientToServer = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
		serverToClient = new PrintWriter( socket.getOutputStream() );
	}
	
	@Override
	public void run() {
		try {
			
			listen();
			
		} catch (Exception e) {
			System.out.println("Error while listening in ClientHandler. StackTrace should follow.");
			e.printStackTrace();
		} finally {
			shutdown();
		}
		
	}
	
	private void listen() throws IOException {
		while (true) {
			String message;
			message = clientToServer.readLine();
			
			if(message.equals("/CLOSE")) {
				break;
			}
			
			broadcast(message);
			
			if(message.contains("/GAME")){
				Server.game = true;
				broadcast("\nJogo de Perguntas e Respostas Iniciado!\n");
				broadcast("?" + Server.pergunta);
			}
			if(Server.game == true && message.startsWith("!" + Server.pergunta)){
				broadcast("\nResposta Correta!\n");
				Server.pergunta++;
				broadcast("?" + Server.pergunta);
			}
			
		}
	}
	
	private void broadcast(String message) {
		server.broadcast(message);
	}
	
	protected void shutdown() {
		try {
			socket.close();
			deregister();
		} catch (IOException e) {
			System.out.println("Error while shutting down ClientListener. StackTrace should follow.");
			e.printStackTrace();
		}
	}

	private void deregister() {
		server.deregister(this);
	}
	
	public void sendMessage(String message) {
		serverToClient.println(message);
		serverToClient.flush();
	}
	
}
