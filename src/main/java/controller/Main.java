/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This is the Main class
 * 
 */

package controller;

import javax.swing.SwingUtilities;

import model.userConfigs.CheckDBClient;
import model.userConfigs.UserConfigs;
import view.Login;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				Login windowLogin = new Login();
				windowLogin.setVisible(true);
				UserConfigs.setLoginWindow(windowLogin);
			}

		});
		CheckDBClient.main(args);

	}

}
