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
	public CodeSwitch(int code, String message, Login loginWindow) {
		switch (code) {
		case 200:
			connectWindow(loginWindow);
			break;

		case 310:
			errorAlreadyConnect(loginWindow);
			break;

		default:
			break;
		}
	}

	private static void connectWindow(Login loginWindow) {
		JOptionPane.showMessageDialog(loginWindow, "Vous êtes connecté. Bienvenue " + UserConfigs.getLogin(),
				"Information", JOptionPane.INFORMATION_MESSAGE);
		
		UserConfigs.setLogged(true);
		UserConfigs.setConnectedToAChannel(false);
		

		MainWindow window = new MainWindow();
		window.setVisible(true);
		loginWindow.setVisible(false);

	}

	private void errorAlreadyConnect(Login loginWindow) {
		JOptionPane.showMessageDialog(loginWindow, "Vous êtes déjà connecté. Quittez et réessayez.", "Information",
				JOptionPane.INFORMATION_MESSAGE);

	}

	// Switch for the different codes and call the method the app need to use. If
	// the parent wondow is the login main window
	public CodeSwitch(int code, String message, MainWindow mainWindow) {
		switch (code) {
		case 200:
			disconnectWindow(mainWindow);
			break;

		default:
			break;
		}
	}

	private void disconnectWindow(MainWindow mainWindow) {
		JOptionPane.showMessageDialog(mainWindow, "Vous êtes déconnecté.", "Information",
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
		mainWindow.setVisible(false);

	}

}
