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
import java.util.ArrayList;
import java.util.Collections;
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
		case 130:
			
			receiveMessage(lList);
		case 200:
			if (UserConfigs.getInstruction() == "connect") {
				UserConfigs.setCurrentChannel("default");
				UserConfigs.setNewChannel("default");
				connectWindow();
			} else if (UserConfigs.getInstruction() == "disconnect") {
				disconnectWindow();
			} else if (UserConfigs.getInstruction() == "subscribe_channel") {
				connectToAChannel();
			}
			break;
		case 310:
			errorConnection(lList);
			break;

		case 120:
			createChannelsList(lList);
			break;

		case 110:
			createMembersList(lList);
			break;
		case 0:
		case 1:
			if (UserConfigs.getInstruction() == "send_message") {
				UserConfigs.getMainWindow().getTp().setText(UserConfigs.getMainWindow().getTp().getText()
						+ "[Error] -> Votre message n'a pas pu être envoyé au serveur\n");
			}
//		case 311:
//			errorConnection(code, loginWindow, message);
//			break;
//		
		default:
			// errorConnection(000, loginWindow, message);
			break;
		}
	}

	private void connectToAChannel() {
		UserConfigs.getMainWindow().getTp().setText("Bienvenue dans le canal " + UserConfigs.getCurrentChannel() + "\n");
		UserConfigs.setInstruction("");

	}

	private void receiveMessage(LinkedList<Object> lList) {
		UserConfigs.getMainWindow().getTp().setForeground(Color.black);
		
		
		UserConfigs.getMainWindow().getTp().setText(UserConfigs.getMainWindow().getTp().getText() + "[\""
				+ lList.get(2).toString() + "\"] --- " + lList.get(1).toString() + "\n");
		
		System.out.println("-gezgqzgze---" + UserConfigs.getMainWindow().getTp().getText());

	}

	private static void connectWindow() {
		JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Bienvenue " + UserConfigs.getLogin(),
				"Information", JOptionPane.INFORMATION_MESSAGE);

		UserConfigs.setLogged(true);
		UserConfigs.setConnectedToAChannel(true);
		UserConfigs.setStopTheThread(false);
		UserConfigs.setStopTheThreadMembers(false);
		UserConfigs.setStopTheThreadChannels(false);

		MainWindow window = new MainWindow();
		window.setVisible(true);
		UserConfigs.setMainWindow(window);
		UserConfigs.exitLoginWindow();

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

	private void errorConnection(LinkedList<Object> lList) {
		JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Code error : " + lList, "Information",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
