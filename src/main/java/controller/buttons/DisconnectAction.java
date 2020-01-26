/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used for the action of the button of disconnection
 * 
 */

package controller.buttons;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import controller.messages.SendMessageProcess;
import model.userConfigs.UserConfigs;
import view.MainWindow;

public class DisconnectAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private MainWindow mainWindow;

	public DisconnectAction(MainWindow window, String text) {
		super(text);
		this.mainWindow = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("-------- Bouton de déconnexion cliqué -----------");
		SendMessageProcess smp = new SendMessageProcess(this.mainWindow);

		smp.SendMessageProcessForDisconnection(UserConfigs.getLogin(), UserConfigs.getPass(), "localhost", 4567);

	}

}
