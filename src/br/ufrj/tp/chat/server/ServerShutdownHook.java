package br.ufrj.tp.chat.server;

public class ServerShutdownHook extends Thread {
	private Server server;
	
	public ServerShutdownHook(Server server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		try{
			server.shutdown();
		} catch(Exception e){}
	}
}
