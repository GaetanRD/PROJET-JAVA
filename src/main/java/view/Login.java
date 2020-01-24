package view;

import javax.swing.*;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.buttons.ConnectAction;
import controller.buttons.CloseProgram;

import javax.swing.JButton;

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField loginField;
	private JTextField passwordField;
	public Login() {
		super();

		build();
	}

	private void build() {
		setTitle("coucou");
		setSize(400, 240);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPanel());
	}

	private JPanel buildContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JLabel label = new JLabel("CONNEXION");

		panel.add(label);

		JTextField loginField = new JTextField();
		loginField.setColumns(10);
		panel.add(loginField);
		this.loginField = loginField;

		JTextField passwordField = new JTextField();
		passwordField.setColumns(10);
		panel.add(passwordField);
		this.passwordField = passwordField;

		JButton connectButton = new JButton(new ConnectAction("Connexion", this));
		panel.add(connectButton);

		JButton quitButton = new JButton(new CloseProgram("Quitter"));
		panel.add(quitButton);

		return panel;
	}

	public JTextField getLoginField() {
		return loginField;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

}
