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

public class CreateChannel extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public CreateChannel(String text) {
		super(text);

	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("-------- Bouton de création d'un channel cliqué -----------");

		String newChannel = null;
		int canCreate = 1;
		newChannel = (String) JOptionPane.showInputDialog(UserConfigs.getMainWindow(), "Donnez un nom à votre canal",
				"canal");
		if (newChannel != null) {
			if (newChannel.isEmpty()) {
				JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Le nom de votre canal est vide.",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			} else {

				for (int i = 0; i < UserConfigs.getMainWindow().getChannelsList().getModel().getSize(); i++) {
					if (UserConfigs.getMainWindow().getChannelsList().getModel().getElementAt(i).toString()
							.contains(newChannel)) {
						JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Ce canal existe déjà.",
								"Information", JOptionPane.INFORMATION_MESSAGE);
						canCreate = 0;
					}

				}
				if (canCreate == 1) {
					UserConfigs.setNewChannel(newChannel);
					UserConfigs.setInstruction("subscribe_channel");
					new SendMessageProcess();
					UserConfigs.setCurrentChannel(UserConfigs.getNewChannel());
					UserConfigs.getMainWindow().getTextfield().setText("");
				}

			}

		}
	}

}
