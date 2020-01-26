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
import controller.messages.SendMessageProcess;
import view.Login;

public class ConnectAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private Login loginWindow;

	public ConnectAction(String text, Login windowLogin) {
		super(text);
		this.loginWindow = windowLogin;

	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("-------- Bouton de connexion cliqué -----------");
		SendMessageProcess smp = new SendMessageProcess(this.loginWindow);

		smp.SendMessageProcessForConnection(this.loginWindow.getLoginField().getText(),
				this.loginWindow.getPasswordField().getText(), "localhost", 4567);

	}

}
