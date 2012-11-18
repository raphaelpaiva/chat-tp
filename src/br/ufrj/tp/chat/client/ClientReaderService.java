package br.ufrj.tp.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReaderService extends Thread {
	
	private BufferedReader serverToClient;
	private boolean active = true;
	private String clientId;
	public ClientReaderService(Socket socket, String clientId) throws IOException {
		serverToClient = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
		this.clientId = clientId;
	}
	
	@Override
	public void run() {
		try {
			while (active) {
				String message;
				message = serverToClient.readLine();
				System.out.println(message);
				if (message.contains("PVT")){
					if (message.contains("@" + this.clientId) || message.startsWith(clientId)){
						Janela.escreve(message);
					}
				}else{
					if(message.startsWith("?")){
						Janela.escreve("Pergunta: " + GamePerguntas.perguntas(message));
					}else{
						if(!message.startsWith("!"))
							Janela.escreve(message);
					}
				}
				
			}
		} catch (IOException e) {
			Janela.escreve("Erro ao ler mensagens do servidor.");
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		active = false;
	}
	
}
