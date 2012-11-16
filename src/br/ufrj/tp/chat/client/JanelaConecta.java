package br.ufrj.tp.chat.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanelaConecta extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField txtId;
	JButton btnConecta;
	public JanelaConecta(){
		setTitle("UFRJ - Teleprocessamento e Redes"); // metodo de JFrame
		setSize(500, 200);
		setResizable(false);
		JPanel painel = new JPanel(new GridLayout(1,2,2,2));
		JLabel lblId = new JLabel("Identificador do Cliente");
		btnConecta = new JButton("Conectar");
		painel.add(lblId);
		txtId = new JTextField();
		painel.add(txtId);
		this.add(painel,  BorderLayout.NORTH);
		this.add(btnConecta, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
