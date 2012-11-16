package br.ufrj.tp.chat.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import java.awt.*;

//@SuppressWarnings("unused")
public class Janela extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Janela(String cliente){
		setTitle("Chat - " + cliente); // metodo de JFrame
		setSize(500, 600);
		setResizable(false);
		JPanel pnlHistorico = new JPanel();
		JPanel pnlAcoes = new JPanel();
		JTextField txtHistorico = new JTextField();
		JTextField txtAcao = new JTextField();
		JButton btnEnvia = new JButton("Enviar");
		
		txtHistorico.setPreferredSize(new Dimension(490,450));
		txtHistorico.setEditable(false);
		pnlHistorico.add(txtHistorico);
		
		txtAcao.setPreferredSize(new Dimension(390,110));
		btnEnvia.setPreferredSize(new Dimension(95,110));
		pnlAcoes.add(txtAcao);
		pnlAcoes.add(btnEnvia);
		
		this.add(pnlHistorico, BorderLayout.NORTH);
		this.add(pnlAcoes, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
