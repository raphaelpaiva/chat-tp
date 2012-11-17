package br.ufrj.tp.chat.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class JanelaConecta extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	public JTextField usuario;
	
	public static JButton btnOK;
	public static JButton btnSair;
	
	private ClientController delegate;
	
	public JanelaConecta(ClientController delegate){
		
		super("Bem-vindo ao Chat");
		
		this.delegate = delegate;
		
		JPanel painel1 = new JPanel();
		JLabel imagem = new JLabel();
		imagem.setIcon(new javax.swing.ImageIcon(getClass().getResource(
			"login.png")));
		painel1.setLayout(new BoxLayout(painel1, BoxLayout.Y_AXIS));
		painel1.add(imagem);
		//painel1.add(new JSeparator());
		
		usuario = new JTextField();
		usuario.setText("");
		usuario.requestFocus();
		
		btnOK = new JButton("Conectar");
		btnOK.addActionListener(this);
		btnSair = new JButton("Sair");
		btnSair.addActionListener(this);
		
		JPanel gridLogin = new JPanel();
		gridLogin.setLayout(new GridLayout(2,2, 10, 10));
		gridLogin.add(new JLabel("Identificação:          "));
		gridLogin.add(usuario);
		
		gridLogin.setMaximumSize(new Dimension(300,70));
		gridLogin.setPreferredSize(new Dimension(300,50));
		
		JPanel painel2 = new JPanel();
		painel2.setLayout(new BoxLayout(painel2, BoxLayout.Y_AXIS));
		painel2.add(Box.createVerticalGlue());
		painel2.add(gridLogin);
		painel2.add(Box.createVerticalGlue());
		
		JPanel painel3 = new JPanel();		
		JPanel botoes = new JPanel();
		painel3.setLayout(new BoxLayout(painel3, BoxLayout.Y_AXIS));
		painel3.add(new JSeparator());
		botoes.add(btnOK);
		botoes.add(btnSair);
		painel3.add(botoes);
		
		this.add(painel1, BorderLayout.NORTH);
		this.add(painel2, BorderLayout.CENTER);
		this.add(painel3, BorderLayout.SOUTH);
		this.setSize(400,350);
		this.setLocation((java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize().width / 2)
				- (this.getWidth() / 2), (java.awt.Toolkit
				.getDefaultToolkit().getScreenSize().height / 2)
				- (this.getHeight() / 2));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == JanelaConecta.btnSair){
			System.exit(0);
		}
		
		if(e.getSource() == JanelaConecta.btnOK){
			String clientId = this.usuario.getText();
			
			try {
				delegate.start(clientId);
			} catch (UnknownHostException e1) {
				System.out.println("Host desconhecido!");
			} catch (IOException e1) {
				System.out.println("Erro ao ler dados do servidor! " + e);
			}
			
			this.dispose();
		}
		
	}

}
