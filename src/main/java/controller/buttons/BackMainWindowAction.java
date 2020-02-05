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
import view.Log;
import view.MainWindow;

public class BackMainWindowAction extends AbstractAction {

	
	
	private static Log WindowLog;
	
	public BackMainWindowAction(String text) {
		super(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("-------- Bouton de retour au menu cliqué -----------");

		
		WindowLog.setVisible(false);

	}

}
