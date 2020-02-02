/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used for the different answer of the server for the different windows
 * 
 * 
 */

package controller.codes;

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
		case 200:
			if (UserConfigs.getInstruction() == "connect") {
				connectWindow();
			} else if (UserConfigs.getInstruction() == "disconnect") {
				disconnectWindow();
			} else if (UserConfigs.getInstruction() == "subscribe_channel") {
				System.out.println("--------- Demande la liste des memebres d'un channel ---------");
				UserConfigs.setInstruction("list_channel_members");
				new SendMessageProcess();
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
//		case 311:
//			errorConnection(code, loginWindow, message);
//			break;
//		
		default:
			// errorConnection(000, loginWindow, message);
			break;
		}
	}

	private static void connectWindow() {
		JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), ". Bienvenue " + UserConfigs.getLogin(),
				"Information", JOptionPane.INFORMATION_MESSAGE);

		UserConfigs.setLogged(true);
		UserConfigs.setConnectedToAChannel(true);
		UserConfigs.setCurrentChannel("default");
		UserConfigs.setNewChannel("default");

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

		UserConfigs.setConnectedToAChannel(false);
		UserConfigs.setLogged(false);

		Login windowLogin = new Login();
		windowLogin.setVisible(true);
		UserConfigs.getMainWindow().setVisible(false);

	}

	private void createChannelsList(LinkedList<Object> lList) {

		DefaultListModel<String> listModel = new DefaultListModel<>();

		for (int i = 1; i < lList.size(); i++) {
			listModel.addElement(lList.get(i).toString());
		}
		UserConfigs.getMainWindow().getChannelsList().setModel(listModel);
		
	}

	private void createMembersList(LinkedList<Object> lList) {
		LinkedList<String> membersList = new LinkedList<>();

		for (int i = 1; i < lList.size(); i++) {
			membersList.add(lList.get(i).toString());
		}

		Collections.sort(membersList);

		for (int i = 0; i < membersList.size(); i++) {
			UserConfigs.getMainWindow().getTextAreaMembers()
					.setText(UserConfigs.getMainWindow().getTextAreaMembers().getText() + membersList.get(i) + "\n");
		}

	}

	private void errorConnection(LinkedList<Object> lList) {
		JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Code error : " + lList, "Information",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
