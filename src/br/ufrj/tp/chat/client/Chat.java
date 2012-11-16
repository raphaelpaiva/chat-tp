package br.ufrj.tp.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class Chat {

	public static void main(String[] args) throws UnknownHostException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Insira seu identificador: ");
		
		String clientId = in.readLine();
		
		Client client = new Client(clientId);

		client.start();


		while (true) {
			String message = in.readLine();
			client.sendMessage(message);

			if (message.equals("/CLOSE")) {
				break;
			}
		}

		in.close();

		client.shutdown();
	}

}
