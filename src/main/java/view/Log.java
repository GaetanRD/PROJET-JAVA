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
import controller.buttons.BackMainWindowAction;
import controller.buttons.DisconnectAction;
import model.userConfigs.UserConfigs;

public class Log extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel();

	private JTextPane tp = new JTextPane();
	private SimpleAttributeSet setErrorMessage;
	


	private JButton buttonDisplayMain = new JButton(new BackMainWindowAction("Retour au menu"));
	private JButton disconnectButton = new JButton(new DisconnectAction("Se déconnecter"));

	private int buttonWidth = 300;
	private int buttonHeight = 30;

	public Log() {
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

		MainButton();
		MainLabel();
		DisconnectButton();

		return panel;
	}

	private void MainButton() {
		buttonDisplayMain.setLocation(20, 10);
		buttonDisplayMain.setSize(540 / 3, 20);

		panel.add(buttonDisplayMain, null);
	}

	private void DisconnectButton() {
		disconnectButton.setLocation(buttonDisplayMain.getX() + buttonDisplayMain.getWidth() + 20, 10);
		disconnectButton.setSize(buttonDisplayMain.getWidth(), buttonDisplayMain.getHeight());

		panel.add(disconnectButton, null);
	}

	private void MainLabel() {
		tp.setLocation(10, buttonHeight + 10);
		tp.setSize(770, this.getHeight() - 90);
		tp.setEditable(false);
		tp.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		panel.add(tp, null);

	}


	public SimpleAttributeSet getSetErrorMessage() {
		return setErrorMessage;
	}

	public void setSetErrorMessage(SimpleAttributeSet setErrorMessage) {
		this.setErrorMessage = setErrorMessage;
	}

	public JTextPane getTp() {
		return tp;
	}

	public void setTp(JTextPane tp) {
		this.tp = tp;
	}

	
}
