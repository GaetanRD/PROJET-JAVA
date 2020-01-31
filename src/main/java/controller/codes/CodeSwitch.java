/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used for the different answer of the server for the different windows
 * 
 * 
 */

package controller.codes;

import javax.swing.JOptionPane;

import model.userConfigs.UserConfigs;
import view.Login;
import view.MainWindow;

public class CodeSwitch {

	// Switch for the different codes and call the method the app need to use. If
	// the parent wondow is the login window
	public CodeSwitch(int code, String message) {
		System.out.println(code);
		
		switch (code) {
		case 200:
			if(!UserConfigs.isLogged()) {
				connectWindow(message);
			} else {
				disconnectWindow();
			} 
			break;
		case 310:
			errorConnection(code, message);
						break;
//		case 311:
//			errorConnection(code, loginWindow, message);
//			break;
//		
		default:
		//	errorConnection(000, loginWindow, message);
			break;
		}
	}

	private static void connectWindow(String message) {
		JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), message + ". Bienvenue " + UserConfigs.getLogin(),
				"Information", JOptionPane.INFORMATION_MESSAGE);

		UserConfigs.setLogged(true);
		UserConfigs.setConnectedToAChannel(false);

		MainWindow window = new MainWindow();
		window.setVisible(true);
		UserConfigs.setMainWindow(window);
		UserConfigs.exitLoginWindow();

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
	
	private void errorConnection(int code, String message) {
		JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), code + message, "Information",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
