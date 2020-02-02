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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import controller.KeySendMessage;
import controller.buttons.DisconnectAction;
import controller.buttons.SelectChannel;
import controller.buttons.SendMessage;
import model.userConfigs.UserConfigs;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel();

	private JTextPane tp = new JTextPane();
	private SimpleAttributeSet setWelcome;
	private SimpleAttributeSet setUserMessage;
	private SimpleAttributeSet setErrorMessage;

	private JLabel labelMembers = new JLabel("Membres sur ce canal");
	private JLabel labelChannels = new JLabel("Autres canaux");

	private JList<String> channelsList;
	private JTextArea textAreaMembers = new JTextArea();

	JScrollPane sPaneTextAreaMembers = new JScrollPane(textAreaMembers);
	JScrollPane sPaneTextAreaChannels = new JScrollPane(channelsList);

	private JTextField textfield = new JTextField();

	private JButton buttonDisplayLogs = new JButton("Accèder aux logs");
	private JButton disconnectButton = new JButton(new DisconnectAction("Se déconnecter"));
	private JButton sendButton = new JButton("Envoyer");
	private JButton createChannel = new JButton("Créer un channel");
	private JButton joinChannel = new JButton("Rejoindre");

	private int buttonWidth = 300;
	private int buttonHeight = 30;

	public MainWindow() {
		super();

		build();
	}

	private void build() {
		setTitle("T'Chat - IRC Client (v0.1 alpha)");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPane());

	}

	private JPanel buildContentPane() {
		panel.setLayout(null);

		LogButton();
		NewChannelButton();
		MainLabel();
		DisconnectButton();
		ListOfMemberArea();
		ListOfChannelArea();
		TextField();
		ChangeChannelButton();
		SendButton();

		return panel;
	}

	private void LogButton() {
		buttonDisplayLogs.setLocation(20, 10);
		buttonDisplayLogs.setSize(540 / 3, 20);

		panel.add(buttonDisplayLogs, null);
	}

	private void NewChannelButton() {
		createChannel.setLocation(buttonDisplayLogs.getX() + buttonDisplayLogs.getWidth() + 20, 10);
		createChannel.setSize(buttonDisplayLogs.getWidth(), buttonDisplayLogs.getHeight());

		panel.add(createChannel, null);
	}

	private void DisconnectButton() {
		disconnectButton.setLocation(createChannel.getX() + createChannel.getWidth() + 20, 10);
		disconnectButton.setSize(buttonDisplayLogs.getWidth(), buttonDisplayLogs.getHeight());

		panel.add(disconnectButton, null);
	}

	private void MainLabel() {
		tp.setLocation(10, buttonHeight + 10);
		tp.setSize(600, this.getHeight() - 120);
		tp.setEditable(false);
		tp.setBorder(BorderFactory.createLineBorder(Color.black));
		tp.setText("Bienvenue sur le canal " + UserConfigs.getCurrentChannel());
		
		setWelcome = new SimpleAttributeSet();
		StyleConstants.setForeground(setWelcome, Color.blue);
		tp.setCharacterAttributes(setWelcome, true);
		tp.setText("Bienvenue sur le canal " + UserConfigs.getCurrentChannel() + "\n");
		
		setUserMessage = new SimpleAttributeSet();
		StyleConstants.setForeground(setUserMessage, Color.black);
		
		panel.add(tp, null);

	}

	public SimpleAttributeSet getSetWelcome() {
		return setWelcome;
	}

	public void setSetWelcome(SimpleAttributeSet setWelcome) {
		this.setWelcome = setWelcome;
	}

	public SimpleAttributeSet getSetUserMessage() {
		return setUserMessage;
	}

	public void setSetUserMessage(SimpleAttributeSet setUserMessage) {
		this.setUserMessage = setUserMessage;
	}

	public SimpleAttributeSet getSetErrorMessage() {
		return setErrorMessage;
	}

	public void setSetErrorMessage(SimpleAttributeSet setErrorMessage) {
		this.setErrorMessage = setErrorMessage;
	}

	private void ListOfMemberArea() {
		
		JScrollPane sPaneTextAreaMembers = new JScrollPane(textAreaMembers);
		

		labelMembers.setLocation(tp.getX() + tp.getWidth() + 5, tp.getY() - 10);
		labelMembers.setSize(this.getWidth() - tp.getWidth() - tp.getX(), 10);

		panel.add(labelMembers, null);

		sPaneTextAreaMembers.setLocation(labelMembers.getX(), labelMembers.getY() + labelMembers.getHeight() + 5);
		sPaneTextAreaMembers.setSize(labelMembers.getWidth() - 15, this.getHeight() - 300);

		System.out.println(sPaneTextAreaMembers.getY());

		textAreaMembers.setBackground(Color.lightGray);
		textAreaMembers.setEditable(false);
		panel.add(sPaneTextAreaMembers, null);
	}

	private void ListOfChannelArea() {
		JScrollPane sPaneTextAreaChannels = new JScrollPane(channelsList);

		labelChannels.setLocation(labelMembers.getX(), 350);
		labelChannels.setSize(labelMembers.getWidth(), labelMembers.getHeight());

		panel.add(labelChannels, null);

		// create the list
		channelsList = new JList<String>();

		sPaneTextAreaChannels.setLocation(labelChannels.getX(), labelChannels.getY() + labelChannels.getHeight() + 5);
		sPaneTextAreaChannels.setSize(labelChannels.getWidth() - 15, 145);
		sPaneTextAreaChannels.getViewport().add(channelsList);
		panel.add(sPaneTextAreaChannels, null);

	}

	private void TextField() {

		if (UserConfigs.isLogged() && UserConfigs.isConnectedToAChannel()) {
			textfield.setEnabled(true);
		} else {
			textfield.setEnabled(false);
		}

		textfield.setLocation(10, tp.getY() + tp.getHeight() + 20);
		textfield.setSize(tp.getWidth(), 20);

		textfield.addKeyListener(new KeySendMessage());
		System.out.println(UserConfigs.getMessage());

		panel.add(textfield, null);
	}

	public JTextPane getTp() {
		return tp;
	}

	public void setTp(JTextPane tp) {
		this.tp = tp;
	}

	private void ChangeChannelButton() {
		joinChannel.setLocation(labelChannels.getX() + 5, 510);
		joinChannel.setSize(buttonWidth / 2, 20);

		joinChannel.addActionListener(new SelectChannel(channelsList.getSelectedValue()));

		panel.add(joinChannel, null);
	}

	private void SendButton() {

		if (UserConfigs.isLogged() && UserConfigs.isConnectedToAChannel()) {
			sendButton.setEnabled(true);
		} else {
			sendButton.setEnabled(false);
		}

		sendButton.setLocation(labelChannels.getX(), textfield.getY());
		sendButton.setSize(buttonWidth / 2, 20);
		sendButton.addActionListener(new SendMessage(textfield.getText()));

		panel.add(sendButton, null);
	}

	public JList<String> getChannelsList() {
		return channelsList;
	}

	public void setChannelsList(JList<String> channelsList) {
		this.channelsList = channelsList;
	}

	public JScrollPane getSPaneTextAreaChannels() {
		return sPaneTextAreaChannels;
	}

	public void setSPaneTextAreaChannels(JScrollPane sPaneTextAreaChannels) {
		this.sPaneTextAreaChannels = sPaneTextAreaChannels;
	}

	public JTextArea getTextAreaMembers() {
		return textAreaMembers;
	}

	public void setTextAreaMembers(JTextArea textAreaMembers) {
		this.textAreaMembers = textAreaMembers;
	}

	public JScrollPane getSPaneTextAreaMembers() {
		return sPaneTextAreaMembers;
	}

	public void setSPaneTextAreaMembers(JScrollPane sPaneTextAreaMembers) {
		this.sPaneTextAreaMembers = sPaneTextAreaMembers;
	}

	
	public JTextField getTextfield() {
		return textfield;
	}

	public void setTextfield(JTextField textfield) {
		this.textfield = textfield;
	}

}
