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
			System.out.println("Conex‹o fechada");
		} finally {
			if(!this.socket.isClosed()) shutdown();
		}
		
	}
	
	private void listen() throws IOException {
		while (!this.socket.isClosed()) {
			String message;
			message = clientToServer.readLine();
			if(message != null){
				broadcast(message);
			}
			
			if(message.contains("/GAME")){
				Server.game = true;
				Server.pergunta = 1;
				broadcast("\nJogo de Perguntas e Respostas Iniciado!\n");
				broadcast("?" + Server.pergunta);
			}
			if(Server.game == true && message.startsWith("!" + Server.pergunta)){
				broadcast("\nResposta Correta!\n");
				Server.pergunta++;
				if (Server.pergunta <= 5){
					broadcast("?" + Server.pergunta);
				}else{
					broadcast("\nJogo Terminado!\n");
					broadcast("?P");
				}
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
