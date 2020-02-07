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

import controller.KeyFieldSize;
import controller.buttons.CloseProgram;
import controller.buttons.ConnectAction;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel loginLabel = new JLabel();
	private JLabel passwordLabel = new JLabel();
	private JLabel addrServerLabel = new JLabel();
	private JLabel portLabel = new JLabel();

	private JTextField loginField = new JTextField();
	private JTextField passwordField = new JTextField();
	private JTextField addrServerField = new JTextField();
	private JTextField portField = new JTextField();

	private JButton connectButton = new JButton(new ConnectAction("Connexion"));
	private JButton quitButton = new JButton(new CloseProgram("Quitter"));

	private JPanel panel = new JPanel();

	public Login() {
		super();

		build();
	}

	private void build() {
		setTitle("T'Chat - IRC - Connexion Client (v0.1 alpha)");
		setSize(300, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(buildContentPanel());
	}

	private JPanel buildContentPanel() {
		panel.setLayout(null);

		loginLabel.setText("Login");
		loginLabel.setLocation(130, 10);
		loginLabel.setSize(80, 20);

		loginField.setLocation(50, loginLabel.getHeight() + loginLabel.getY());
		loginField.setSize(200, 20);
		
		loginField.addKeyListener(new KeyFieldSize());

		passwordLabel.setText("Mot de passe");
		passwordLabel.setLocation(110, loginField.getHeight() + loginField.getY() + 15);
		passwordLabel.setSize(80, 20);

		passwordField.setLocation(50, passwordLabel.getHeight() + passwordLabel.getY());
		passwordField.setSize(200, 20);
		
		passwordField.addKeyListener(new KeyFieldSize());

		addrServerLabel.setText("Serveur");
		addrServerLabel.setLocation(125, passwordField.getHeight() + passwordField.getY() + 15);
		addrServerLabel.setSize(80, 20);

		// For test
		addrServerField.setText("localhost");
		portField.setText("4567");

		addrServerField.setLocation(50, addrServerLabel.getHeight() + addrServerLabel.getY());
		addrServerField.setSize(200, 20);

		portLabel.setText("Port");
		portLabel.setLocation(135, addrServerField.getHeight() + addrServerField.getY() + 15);
		portLabel.setSize(80, 20);

		portField.setLocation(50, portLabel.getHeight() + portLabel.getY());
		portField.setSize(200, 20);

		connectButton.setLocation(50, portField.getHeight() + portField.getY() + 20);
		connectButton.setSize(200, 30);
		
		quitButton.setLocation(50, connectButton.getHeight() + connectButton.getY() + 10);
		quitButton.setSize(200, 30);
		
		panel.add(loginLabel, null);
		panel.add(loginField, null);
		panel.add(passwordLabel, null);
		panel.add(passwordField, null);
		panel.add(addrServerLabel, null);
		panel.add(addrServerField, null);
		panel.add(portLabel, null);
		panel.add(portField, null);
		panel.add(connectButton, null);
		panel.add(quitButton, null);

		return panel;
	}

	public JLabel getLoginLabel() {
		return loginLabel;
	}

	public void setLoginLabel(JLabel loginLabel) {
		this.loginLabel = loginLabel;
	}

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	public JLabel getAddrServerLabel() {
		return addrServerLabel;
	}

	public void setAddrServerLabel(JLabel addrServerLabel) {
		this.addrServerLabel = addrServerLabel;
	}

	public JLabel getPortLabel() {
		return portLabel;
	}

	public void setPortLabel(JLabel portLabel) {
		this.portLabel = portLabel;
	}

	public JTextField getLoginField() {
		return loginField;
	}

	public void setLoginField(JTextField loginField) {
		this.loginField = loginField;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JTextField passwordField) {
		this.passwordField = passwordField;
	}

	public JTextField getAddrServerField() {
		return addrServerField;
	}

	public void setAddrServerField(JTextField adrServerField) {
		this.addrServerField = adrServerField;
	}

	public JTextField getPortField() {
		return portField;
	}

	public void setPortField(JTextField portField) {
		this.portField = portField;
	}

	public JButton getConnectButton() {
		return connectButton;
	}

	public void setConnectButton(JButton connectButton) {
		this.connectButton = connectButton;
	}

	public JButton getQuitButton() {
		return quitButton;
	}

	public void setQuitButton(JButton quitButton) {
		this.quitButton = quitButton;
	}

}
