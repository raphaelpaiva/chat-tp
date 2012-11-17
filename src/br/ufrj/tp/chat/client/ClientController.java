package br.ufrj.tp.chat.client;

import java.io.IOException;
import java.net.UnknownHostException;

public interface ClientController {
	public void start(String clientId) throws UnknownHostException, IOException;

}
