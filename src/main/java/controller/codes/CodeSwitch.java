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
				connectWindow();
			} else if (UserConfigs.getInstruction() == "disconnect") {
				disconnectWindow();
			}
			break;
		case 310:
			errorConnection(lList);
			break;

		case 120:
			createChannelsList(lList);
			System.out.println("--------- Demande d'inscription à un channel ---------");
			UserConfigs.setInstruction("subscribe_channel");
			new SendMessageProcess();
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

	private void receiveMessage(LinkedList<Object> lList) {
		UserConfigs.getMainWindow().getTp().setForeground(Color.black);
		UserConfigs.getMainWindow().getTp().setText(UserConfigs.getMainWindow().getTp().getText() + "[\""
				+ lList.get(2).toString() + "\"] --- " + lList.get(1).toString() + "\n");

	}

	private static void connectWindow() {
		JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), ". Bienvenue " + UserConfigs.getLogin(),
				"Information", JOptionPane.INFORMATION_MESSAGE);

		UserConfigs.setLogged(true);
		UserConfigs.setConnectedToAChannel(true);
		UserConfigs.setCurrentChannel("default");
		UserConfigs.setNewChannel("default");
		
		UserConfigs.setStopTheThreadMessage(false);

		MainWindow window = new MainWindow();
		window.setVisible(true);
		UserConfigs.setMainWindow(window);
		UserConfigs.exitLoginWindow();

		System.out.println("--------- Demande de la liste de channels ---------");
		UserConfigs.setInstruction("list_channels");
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
		UserConfigs.setStopTheThreadMessage(true);
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

		for (int i = 1; i < lList.size(); i++) {
			listModel.addElement(lList.get(i).toString());
		}
		UserConfigs.getMainWindow().getChannelsList().setModel(listModel);

	}

	private void createMembersList(LinkedList<Object> lList) {

		UserConfigs.getMembersList().removeAll(UserConfigs.getMembersList());

		System.out.println("Vider " + UserConfigs.getMembersList());

		for (int i = 1; i < lList.size(); i++) {

			UserConfigs.getMembersList().add(lList.get(i).toString());

		}

		Collections.sort(UserConfigs.getMembersList());

		UserConfigs.getMainWindow().getTextAreaMembers().setText(UserConfigs.getMembersList().get(0) + "\n");

		if (UserConfigs.getMembersList().size() > 2) {
			for (int i = 1; i < UserConfigs.getMembersList().size() - 1; i++) {
				UserConfigs.getMainWindow().getTextAreaMembers()
						.setText(UserConfigs.getMainWindow().getTextAreaMembers().getText()
								+ UserConfigs.getMembersList().get(i) + "\n");
			}
		}
		
		if (UserConfigs.getMembersList().size() > 1) {
			UserConfigs.getMainWindow().getTextAreaMembers().setText(
					UserConfigs.getMainWindow().getTextAreaMembers().getText() + UserConfigs.getMembersList().getLast());

		}
		

	}

	private void errorConnection(LinkedList<Object> lList) {
		JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Code error : " + lList, "Information",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
