/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used for the different answer of the server for the different windows
 * 
 * 
 */

package controller.codes;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import controller.messages.SendMessageProcess;
import model.userConfigs.UserConfigs;
import view.Login;
import view.MainWindow;

public class CodeSwitch {

	// Switch for the different codes and call the method the app need to use. If
	// the parent wondow is the login window
	public CodeSwitch(LinkedList<Object> lList) {
		switch ((Integer) lList.get(0)) {
		case 110:
			createMembersList(lList);
			break;
		case 120:
			createChannelsList(lList);
			break;
		case 130:
			receiveMessage(lList);
			break;
		case 200:
			if (UserConfigs.getInstruction() == "connect") {
				connectWindow();
			} else if (UserConfigs.getInstruction() == "disconnect") {
				disconnectWindow();
			} else if (UserConfigs.getInstruction() == "subscribe_channel") {
				connectToAChannel();
			}

			break;
		case 310:
			errorConnection((Integer) lList.get(0), "Login déjà utilisé.");
			break;
		case 311:
			errorConnection((Integer) lList.get(0), "Mot de passe incorrecte.");
			break;
		case 312:
			if (UserConfigs.getInstruction() == "send_message") {
				receiveMessageError();
			} else {
				errorConnection((Integer) lList.get(0), "Erreur de connexion : Utilisateur déconnecté.");
			}
			break;
		case 313:
			errorConnection((Integer) lList.get(0), "Login ou mot de passe trop long");
			break;
		case 320:
			if (UserConfigs.getInstruction() == "send_message") {
				receiveMessageError();
			} else {
				errorConnection((Integer) lList.get(0), "Utilisateur n'est pas connecté à un canal");
			}
			break;
		case 420:
			if (UserConfigs.getInstruction() == "send_message") {
				receiveMessageError();
			} else {
				errorConnection((Integer) lList.get(0), "Mauvais canal");
			}
			break;
		case 421:
			System.out.println("Impossible de recevoir la liste des canaux");
			break;
		case 422:
			System.out.println("Impossible de recevoir la liste des membres");
			break;
		case 430:
			if (UserConfigs.getInstruction() == "send_message") {
				receiveMessageError();
			} else {
				errorConnection((Integer) lList.get(0), "Message trop long");
			}
			break;
		case 431:
			if (UserConfigs.getInstruction() == "send_message") {
				receiveMessageError();
			} else {
				errorConnection((Integer) lList.get(0), "Message vide");
			}
			break;
		default:
			if (UserConfigs.getInstruction() == "send_message") {
				receiveMessageError();
			} else {
				errorConnection(000, "Erreur inconnue");
			}
			break;
		}

	}

	private void connectToAChannel() {
		if (!UserConfigs.isConnectedToAChannel()) {
			UserConfigs.setConnectedToAChannel(true);
		} else {
			// UserConfigs.setNewChannel(UserConfigs.getMainWindow().getChannelsList().getSelectedValue());
		}
		System.out.println("ezgfzegegerger" + UserConfigs.getNewChannel());
		UserConfigs.setCurrentChannel(UserConfigs.getNewChannel());
		UserConfigs.getMainWindow().getTp()
				.setText("Bienvenue dans le canal " + UserConfigs.getCurrentChannel() + "\n");
		UserConfigs.setInstruction("");

	}

	private void receiveMessage(LinkedList<Object> lList) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		UserConfigs.getMainWindow().getTp().setForeground(Color.black);

		UserConfigs.getMainWindow().getTp().setText(UserConfigs.getMainWindow().getTp().getText() + "> " + format.format(date) + " - "
				+ lList.get(2).toString() + " - " + lList.get(1).toString() + "\n");

	}

	private void receiveMessageError() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		UserConfigs.getMainWindow().getTp().setForeground(Color.black);

		UserConfigs.getMainWindow().getTp().setText(UserConfigs.getMainWindow().getTp().getText() + "> " + format.format(date)
				+ "- ERROR - Votre message n'a pas pu être envoyé au serveur\n");

	}

	private static void connectWindow() {
		UserConfigs.setLogged(true);
		UserConfigs.setNewChannel("default");
		UserConfigs.setCurrentChannel(UserConfigs.getNewChannel());

		UserConfigs.setIndexChannel(0);

		JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Bienvenue " + UserConfigs.getLogin(),
				"Information", JOptionPane.INFORMATION_MESSAGE);
		MainWindow window = new MainWindow();
		window.setVisible(true);

		UserConfigs.setMainWindow(window);
		UserConfigs.exitLoginWindow();

		UserConfigs.getMainWindow().getTextfield().setEnabled(true);
		UserConfigs.getMainWindow().getSendButton().setEnabled(true);

		UserConfigs.setInstruction("subscribe_channel");
		new SendMessageProcess();

	}

	// Switch for the different codes and call the method the app need to use. If
	// the parent window is the login main window

	private void disconnectWindow() {
		JOptionPane.showMessageDialog(UserConfigs.getMainWindow(), "Vous êtes déconnecté.", "Information",
				JOptionPane.INFORMATION_MESSAGE);
		if (!UserConfigs.getLogin().isEmpty()) {
			UserConfigs.setLogin(null);
		}

		if (!UserConfigs.getPass().isEmpty()) {
			UserConfigs.setPass(null);
		}

		UserConfigs.getLoginWindow().getPasswordField().setText(null);
		UserConfigs.getLoginWindow().getLoginField().setText(null);
		UserConfigs.getMainWindow().getTextAreaMembers().setText(null);

		UserConfigs.setStopTheThread(true);
		UserConfigs.setStopTheThreadMembers(true);
		UserConfigs.setStopTheThreadChannels(true);
		UserConfigs.setLogged(false);
		UserConfigs.setConnectedToAChannel(false);

		try {
			UserConfigs.getClientSocket().close();
		} catch (IOException e) {
			e.printStackTrace();

		}

		Login windowLogin = new Login();
		windowLogin.setVisible(true);
		UserConfigs.getMainWindow().setVisible(false);
		UserConfigs.setLoginWindow(windowLogin);

	}

	private void createChannelsList(LinkedList<Object> lList) {

		DefaultListModel<String> listModel = new DefaultListModel<>();

		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> tempList = new ArrayList<>();

		for (int i = 1; i < lList.size(); i++) {
			list.add(lList.get(i).toString());
		}

		if (UserConfigs.getMainWindow().getChannelsList().getModel().getSize() > 0) {

			for (int i = 0; i < UserConfigs.getMainWindow().getChannelsList().getModel().getSize(); i++) {
				tempList.add(UserConfigs.getMainWindow().getChannelsList().getModel().getElementAt(i));
			}
		}
		UserConfigs.getMainWindow().getChannelsList().removeAll();

		Collections.sort(list);
		Collections.sort(tempList);
		if (!list.equals(tempList)) {

			listModel.removeAllElements();
			for (int i = 0; i < list.size(); i++) {
				listModel.addElement(list.get(i));
			}

			list.clear();
			tempList.clear();

			UserConfigs.getMainWindow().getChannelsList().setModel(listModel);

			UserConfigs.setInstruction("");

		}

	}

	private void createMembersList(LinkedList<Object> lList) {

		UserConfigs.getMembersList().removeAll(UserConfigs.getMembersList());

		for (int i = 1; i < lList.size(); i++) {

			UserConfigs.getMembersList().add(lList.get(i).toString());
		}

		Collections.sort(UserConfigs.getMembersList());

		if (UserConfigs.getMembersList().size() > 0) {
			UserConfigs.getMainWindow().getTextAreaMembers().setText(UserConfigs.getMembersList().get(0) + "\n");
		}

		if (UserConfigs.getMembersList().size() > 2) {
			for (int i = 1; i < UserConfigs.getMembersList().size() - 1; i++) {
				UserConfigs.getMainWindow().getTextAreaMembers()
						.setText(UserConfigs.getMainWindow().getTextAreaMembers().getText()
								+ UserConfigs.getMembersList().get(i) + "\n");
			}
		}

		if (UserConfigs.getMembersList().size() > 1) {
			UserConfigs.getMainWindow().getTextAreaMembers()
					.setText(UserConfigs.getMainWindow().getTextAreaMembers().getText()
							+ UserConfigs.getMembersList().getLast());

		}

	}

	private void errorConnection(int code, String message) {
		JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Erreur " + code + " : " + message, "Information",
				JOptionPane.INFORMATION_MESSAGE);

		UserConfigs.setInstruction("");

	}

}
