/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used for the different answer of the server for the different windows
 * 
 * 
 */

package controller.codes;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import controller.messages.SendMessageProcess;
import model.userConfigs.UserConfigs;
import view.Login;
import view.MainWindow;

public class CodeSwitch {

	// Switch for the different codes and call the method the app need to use. If
	// the parent wondow is the login window
	public CodeSwitch(LinkedList<Object> lList) {
		System.out.println("string" + lList);

		switch ((Integer)lList.get(0)) {
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
		JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(),". Bienvenue " + UserConfigs.getLogin(),
				"Information", JOptionPane.INFORMATION_MESSAGE);

		UserConfigs.setLogged(true);
		UserConfigs.setConnectedToAChannel(false);

		MainWindow window = new MainWindow();
		window.setVisible(true);
		UserConfigs.setMainWindow(window);
		UserConfigs.exitLoginWindow();
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
		
		for (int i= 1 ; i < lList.size() ; i++) {
			UserConfigs.getMainWindow().getTextAreaChannels().setText(lList.get(i).toString());
			
		}
		
		System.out.println(UserConfigs.getMainWindow().getTextAreaChannels().getLineCount());
		
		
		
		
		
	}

	private void errorConnection(LinkedList<Object> lList) {
		JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Code error : " + lList, "Information",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
