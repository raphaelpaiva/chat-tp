package br.ufrj.tp.chat.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;

public class Client implements ActionListener, KeyListener{

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
		
		JFrame janela = new Janela(this.clientId);
		janela
		.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2)
				- (janela.getWidth() / 2), (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2)
				- (janela.getHeight() / 2));
		Janela.btnEnvia.addActionListener(this);
		Janela.txtAcao.addKeyListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Janela.btnEnvia){
			this.sendMessage(Janela.txtAcao.getText());
			Janela.txtAcao.setText(new String(""));
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == 10 && arg0.getSource() == Janela.txtAcao){
			this.sendMessage(Janela.txtAcao.getText());
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == 10 && arg0.getSource() == Janela.txtAcao){
		Janela.txtAcao.setText(new String(""));
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
