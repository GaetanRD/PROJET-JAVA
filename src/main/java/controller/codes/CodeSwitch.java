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
			connectWindow(loginWindow, message);
			break;

		case 310:
			errorConnection(code, loginWindow, message);
			break;
		case 311:
			errorConnection(code, loginWindow, message);
			break;
		
		default:
			errorConnection(000, loginWindow, message);
			break;
		}
	}

	private static void connectWindow(Login loginWindow, String message) {
		JOptionPane.showMessageDialog(loginWindow, message + ". Bienvenue " + UserConfigs.getLogin(),
				"Information", JOptionPane.INFORMATION_MESSAGE);
		
		UserConfigs.setLogged(true);
		UserConfigs.setConnectedToAChannel(false);
		

		MainWindow window = new MainWindow();
		window.setVisible(true);
		loginWindow.setVisible(false);

	}

	private void errorConnection(int code, Login loginWindow, String message) {
		JOptionPane.showMessageDialog(loginWindow, code + " - " + message + ". Quittez et réessayez.", "Information",
				JOptionPane.INFORMATION_MESSAGE);

	}
	
	private void error(Login loginWindow) {
		JOptionPane.showMessageDialog(loginWindow, "xxx - Une erreur s'est produite", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	// Switch for the different codes and call the method the app need to use. If
	// the parent window is the login main window
	public CodeSwitch(int code, String message, MainWindow mainWindow) {
		switch (code) {
		case 200:
			disconnectWindow(mainWindow);
			break;
		case 312:
			errorWasNotConnected(mainWindow);
			break;
		case 000:
			errorUnknow(mainWindow);
			break;

		default:
			error(mainWindow);
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
	
	private void errorWasNotConnected(MainWindow mainWindow) {
		JOptionPane.showMessageDialog(mainWindow, "312 - Erreur de conn", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void errorUnknow(MainWindow mainWindow) {
		JOptionPane.showMessageDialog(mainWindow, "000 - Une erreur inconnue est survenue", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void error(MainWindow mainWindow) {
		JOptionPane.showMessageDialog(mainWindow, "xxx - Une erreur s'est produite", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

}
