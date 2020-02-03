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
		if (!UserConfigs.getMainWindow().getTextfield().getText().isEmpty()) {
			for (int i = 0; i < UserConfigs.getMainWindow().getChannelsList().getModel().getSize(); i++) {
				if (UserConfigs.getMainWindow().getChannelsList().getModel().getElementAt(i).toString()
						.contains(UserConfigs.getMainWindow().getTextfield().getText())) {
					JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Ce canal existe déjà.", "Information",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				} 
			}
			
			UserConfigs.setNewChannel(UserConfigs.getMainWindow().getTextfield().getText());
			UserConfigs.setInstruction("subscribe_channel");
			new SendMessageProcess();
			UserConfigs.setCurrentChannel(UserConfigs.getNewChannel());
			UserConfigs.getMainWindow().getTextfield().setText("");
			
			
			
		} else {
			JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Le nom de votre canal est vide.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
