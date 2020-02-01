/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class has used for the main window
 * 
 * 
 */

package view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.buttons.DisconnectAction;
import model.userConfigs.UserConfigs;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel();

	private JLabel mainLabel = new JLabel();
	private JLabel labelMembers = new JLabel("Membres sur ce canal");
	private JLabel labelChannels = new JLabel("Autres canaux");

	private JTextArea textAreaMembers = new JTextArea();

	private JTextArea textAreaChannels = new JTextArea();
	JScrollPane sPaneTextAreaChannels = new JScrollPane(textAreaChannels);

	private JTextField textfield = new JTextField();

	private JButton buttonSetPass = new JButton("Modifier votre mot de passe");
	private JButton buttonDisplayLogs = new JButton("Accèder aux logs");
	private JButton disconnectButton = new JButton(new DisconnectAction(this, "Se déconnecter"));
	private JButton sendButton = new JButton("Envoyer");

	private int buttonWidth = 300;
	private int buttonHeight = 30;

	public MainWindow() {
		super();

		build();
	}

	private void build() {
		setTitle("Chat - IRC Project");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPane());

	}

	private JPanel buildContentPane() {
		panel.setLayout(null);

		MainLabel();
		DisconnectButton();
		ListOfMemberArea();
		ListOfChannelArea();
		SendButton();
		TextField();

		return panel;
	}

	private void DisconnectButton() {
		disconnectButton.setLocation(
				((this.getWidth() - mainLabel.getWidth() - 20) / 2) + mainLabel.getWidth() - buttonWidth / 4, 10);
		disconnectButton.setSize(buttonWidth / 2, buttonHeight / 2);
		panel.add(disconnectButton, null);
	}

	private void ListOfMemberArea() {
		labelMembers.setLocation(mainLabel.getWidth() + 10,
				disconnectButton.getY() + disconnectButton.getHeight() + 10);
		labelMembers.setSize(this.getWidth() - mainLabel.getWidth() - 40, 10);
		labelMembers.setOpaque(true);
		labelMembers.setBackground(Color.WHITE);
		panel.add(labelMembers, null);

		textAreaMembers.setLocation(labelMembers.getX(), labelMembers.getY() + labelMembers.getHeight() + 5);
		textAreaMembers.setSize(labelMembers.getWidth(), this.getHeight() - 300);
		textAreaMembers.setEnabled(false);
		panel.add(textAreaMembers, null);
	}

	private void ListOfChannelArea() {
		JScrollPane sPaneTextAreaChannels = new JScrollPane(textAreaChannels);

		labelChannels.setLocation(mainLabel.getWidth() + 10, textAreaMembers.getY() + textAreaMembers.getHeight() + 5);
		labelChannels.setSize(this.getWidth() - mainLabel.getWidth() - 40, 10);
		labelChannels.setOpaque(true);
		labelChannels.setBackground(Color.WHITE);
		panel.add(labelChannels, null);

		sPaneTextAreaChannels.setLocation(labelChannels.getX(), labelChannels.getY() + labelChannels.getHeight() + 5);
		sPaneTextAreaChannels.setSize(labelChannels.getWidth(), this.getHeight() - 45 - labelMembers.getHeight()
				- textAreaMembers.getHeight() - labelChannels.getHeight() - buttonHeight * 3);
		
		textAreaChannels.setEditable(false);
		textAreaChannels.setOpaque(false);
		panel.add(sPaneTextAreaChannels, null);
	}

	private void SendButton() {

		if (UserConfigs.isLogged() && UserConfigs.isConnectedToAChannel()) {
			sendButton.setEnabled(true);
		} else {
			sendButton.setEnabled(false);
		}

		sendButton.setLocation(
				((this.getWidth() - mainLabel.getWidth() - 20) / 2) + mainLabel.getWidth() - buttonWidth / 4, 525);
		sendButton.setSize(buttonWidth / 2, 20);
		panel.add(sendButton, null);
	}

	private void MainLabel() {
		mainLabel.setLocation(10, 10);
		mainLabel.setSize(550, this.getHeight() - 100);
		mainLabel.setEnabled(false);
		panel.add(mainLabel, null);

		buttonDisplayLogs.setSize(buttonWidth, buttonHeight);
		buttonDisplayLogs.setLocation(mainLabel.getWidth() / 2 - buttonWidth / 2,
				buttonSetPass.getY() + buttonSetPass.getHeight() + 20);

		if (UserConfigs.isLogged() && !UserConfigs.isConnectedToAChannel()) {
			panel.add(buttonDisplayLogs, null);
		}

	}

	private void TextField() {

		if (UserConfigs.isLogged() && UserConfigs.isConnectedToAChannel()) {
			textfield.setEnabled(true);
		} else {
			textfield.setEnabled(false);
		}

		textfield.setEnabled(false);
		textfield.setLocation(10, sendButton.getY());
		textfield.setSize(sendButton.getX() - 20, 20);
		panel.add(textfield, null);
	}

	public JTextArea getTextAreaChannels() {
		return textAreaChannels;
	}

	public void setTextAreaChannels(JTextArea textAreaChannels) {
		this.textAreaChannels = textAreaChannels;
	}

	public JScrollPane getSPaneTextAreaChannels() {
		return sPaneTextAreaChannels;
	}

	public void setSPaneTextAreaChannels(JScrollPane sPaneTextAreaChannels) {
		this.sPaneTextAreaChannels = sPaneTextAreaChannels;
	}
}
