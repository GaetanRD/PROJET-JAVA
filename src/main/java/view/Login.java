/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class has used for the login window
 * 
 * 
 */

package view;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import controller.buttons.CloseProgram;
import controller.buttons.ConnectAction;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField loginField = new JTextField();
	private JTextField passwordField = new JTextField();
	private JTextField AdrServerT = new JTextField();
	private JTextField portS = new JTextField();

	public Login() {
		super();

		build();
	}

	private void build() {
		setTitle("Chat-IRC Connexion");
		setSize(300, 150);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPanel());
	}

	private JPanel buildContentPanel() {
		JPanel Firstpanel = new JPanel(new BorderLayout());
		JPanel panel_label = new JPanel(new GridLayout(4, 1));
		JPanel panel_textField = new JPanel(new GridLayout(4, 1));
		JPanel panel_button = new JPanel(new GridLayout(1, 2));
		Firstpanel.add(panel_label, BorderLayout.WEST);
		Firstpanel.add(panel_textField, BorderLayout.CENTER);
		Firstpanel.add(panel_button, BorderLayout.SOUTH);
		
		
		JLabel AdrServer = new JLabel("Adresse du serveur : ", SwingConstants.RIGHT);
		panel_label.add(AdrServer);
		
		
		AdrServerT.setColumns(10);
		panel_textField.add(AdrServerT);
		
		JLabel port = new JLabel("port : ", SwingConstants.RIGHT);
		panel_label.add(port);
		
		
		portS.setColumns(5);
		panel_textField.add(portS);
		
		JLabel pseudo = new JLabel("Pseudo : ", SwingConstants.RIGHT);
		panel_label.add(pseudo);

		loginField.setColumns(10);
		panel_textField.add(loginField);
		
		JLabel MDP = new JLabel("Mot de passe : ", SwingConstants.RIGHT);
		panel_label.add(MDP);

		passwordField.setColumns(10);
		panel_textField.add(passwordField);

		JButton connectButton = new JButton(new ConnectAction("Connexion"));
		panel_button.add(connectButton);

		JButton quitButton = new JButton(new CloseProgram("Quitter"));
		panel_button.add(quitButton);

		return Firstpanel;
	}

	public JTextField getAdrServer() {
		return AdrServerT;
	}
	
	public JTextField getPortServer() {
		
		return portS;
	}
	
	public JTextField getLoginField() {
		return loginField;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public void setLoginField(JTextField loginField) {
		this.loginField = loginField;
	}

	public void setPasswordField(JTextField passwordField) {
		this.passwordField = passwordField;
	}
	
	public void setAdrServer(JTextField AdrServerT) {
		this.AdrServerT = AdrServerT;
	}
	
	public void setPortServer(JTextField portS) {
		this.portS = portS;
	}
	

}
