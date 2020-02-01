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

	public DisconnectAction(MainWindow window, String text) {
		super(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("-------- Bouton de déconnexion cliqué -----------");

		UserConfigs.setInstruction("disconnect");
		
		new SendMessageProcess();
		
		

	}

}
