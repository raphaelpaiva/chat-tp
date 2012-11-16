package br.ufrj.tp.chat.client;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class Chat {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		JFrame janela = new JanelaConecta();
		janela
		.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2)
				- (janela.getWidth() / 2), (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2)
				- (janela.getHeight() / 2));
		janela.setVisible(true);
				
		

		/*
		//Colocar para fechar a conex‹o ao fechar a janela!!!
		client.shutdown();*/
	}

}
