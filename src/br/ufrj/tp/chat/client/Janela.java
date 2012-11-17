package br.ufrj.tp.chat.client;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Janela extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public static JTextArea txtHistorico;
	public static JTextArea txtAcao;
	public static JButton btnEnvia;
	
	
	public Janela(String cliente){
		
		setTitle("Chat - " + cliente);
		setSize(500, 600);
		setResizable(false);
		JPanel pnlHistorico = new JPanel();
		JPanel pnlAcoes = new JPanel();
		txtHistorico = new JTextArea();
		txtAcao = new JTextArea();
		btnEnvia = new JButton("Enviar");

		txtHistorico.setLineWrap(true);
		txtHistorico.setWrapStyleWord(true);
		txtHistorico.setPreferredSize(new Dimension(490,450));
		txtHistorico.setEditable(false);
		txtHistorico.setBorder(BorderFactory.createLoweredBevelBorder());
		txtHistorico.setText("");
		pnlHistorico.add(txtHistorico);
		
		txtAcao.setBorder(BorderFactory.createLoweredBevelBorder());
		txtAcao.setLineWrap(true);
		txtAcao.setWrapStyleWord(true);
		txtAcao.setPreferredSize(new Dimension(390,110));
		btnEnvia.setPreferredSize(new Dimension(95,110));
		btnEnvia.setIcon(new javax.swing.ImageIcon(getClass().getResource("envia.png")));
		btnEnvia.setVerticalTextPosition(JButton.BOTTOM);
		btnEnvia.setHorizontalTextPosition(JButton.CENTER);
		pnlAcoes.add(txtAcao);
		pnlAcoes.add(btnEnvia);
		
		this.add(pnlHistorico, BorderLayout.NORTH);
		this.add(pnlAcoes, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		escreve("Conexão estabelecida com o servidor!");
	}
	
	public static void escreve(String texto){
		txtHistorico.setText(txtHistorico.getText()  + texto + "\n");
	}
	
}
