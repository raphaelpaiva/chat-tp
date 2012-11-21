package br.ufrj.tp.chat.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class Chat implements ClientController, ActionListener, KeyListener {

	private Client client;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Chat chat = new Chat();
		chat.abrirJanelaConecta();
	}

	private void abrirJanelaConecta() {
		JFrame janela = new JanelaConecta(this);
		janela
		.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2)
				- (janela.getWidth() / 2), (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2)
				- (janela.getHeight() / 2));
		janela.setVisible(true);
	}

	@Override
	public void start(String clientId) throws UnknownHostException, IOException {
		client = new Client(clientId);
		client.start();
		
		abrirJanelaChat(clientId);
	}

	private void abrirJanelaChat(String clientId) {
		final JFrame janela = new Janela(clientId);
		janela
		.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2)
				- (janela.getWidth() / 2), (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2)
				- (janela.getHeight() / 2));
		Janela.btnEnvia.addActionListener(this);
		Janela.txtAcao.addKeyListener(this);
		
		janela.setVisible(true);
		
		janela.addWindowListener( 
			    new WindowAdapter() { 
			        public void windowClosing(WindowEvent e) { 
			            try {
							client.sendByeByeMessage();
			            	client.shutdown();
							janela.dispose();
						} catch (IOException e1) {}
			        } 
			    } 
			);
		Janela.escreve("Conex‹o estabelecida com servidor em " + client.getAddress());
		Janela.escreve("Conectado como " + clientId);
		client.sendIntroMessage();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Janela.btnEnvia){
			envia(Janela.txtAcao.getText());
			Janela.txtAcao.setText("");
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == 10 && arg0.getSource() == Janela.txtAcao){
			envia(Janela.txtAcao.getText());
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == 10 && arg0.getSource() == Janela.txtAcao){
		Janela.txtAcao.setText(new String(""));
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	public void envia(String mensagem){
		client.sendMessage(mensagem);
		if (mensagem.contains(GamePerguntas.r1)) client.sendMessageGame("!1");
		if (mensagem.contains(GamePerguntas.r2)) client.sendMessageGame("!2");
		if (mensagem.contains(GamePerguntas.r3)) client.sendMessageGame("!3");
		if (mensagem.contains(GamePerguntas.r4)) client.sendMessageGame("!4");
		if (mensagem.contains(GamePerguntas.r5)) client.sendMessageGame("!5");
		
	}
	
}
