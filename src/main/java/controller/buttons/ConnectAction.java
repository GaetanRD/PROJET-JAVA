/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used for the action of the connection button
 * 
 */

package controller.buttons;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import controller.messages.SendMessageProcess;
import model.userConfigs.UserConfigs;

public class ConnectAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public ConnectAction(String text) {
		super(text);

	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("-------- Bouton de connexion cliqué -----------");
		System.out.println(UserConfigs.getLoginWindow().getLoginField().getText());
		System.out.println(UserConfigs.getLoginWindow().getPasswordField().getText());
		System.out.println(UserConfigs.getLoginWindow().getAdrServer().getText());
		System.out.println(UserConfigs.getLoginWindow().getPortServer().getText());
				
		if (UserConfigs.getLoginWindow().getLoginField().getText().isEmpty()
				|| UserConfigs.getLoginWindow().getPasswordField().getText().isEmpty()) {
			JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Erreur : des champs sont vides", "Information",
					JOptionPane.INFORMATION_MESSAGE);

		} else {
			UserConfigs.setLogin(UserConfigs.getLoginWindow().getLoginField().getText());
			UserConfigs.setPass(UserConfigs.getLoginWindow().getPasswordField().getText());
			UserConfigs.setServer(UserConfigs.getLoginWindow().getAdrServer().getText());
			UserConfigs.setPort(Integer.parseInt(UserConfigs.getLoginWindow().getPortServer().getText()));
			UserConfigs.setInstruction("connect");
			new SendMessageProcess();

		}

	}

}
