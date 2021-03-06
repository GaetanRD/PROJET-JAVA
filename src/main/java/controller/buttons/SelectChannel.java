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

public class SelectChannel extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public SelectChannel(String text) {
		super(text);

	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("-------- Bouton de connexion à un channel cliqué -----------");
		if (UserConfigs.getMainWindow().getChannelsList().getSelectedValue() != null && UserConfigs.getCurrentChannel() != null && !UserConfigs.getCurrentChannel().isEmpty()) {
			
			if (!UserConfigs.getCurrentChannel()
					.equals(UserConfigs.getMainWindow().getChannelsList().getSelectedValue())) {
				UserConfigs.setNewChannel(UserConfigs.getMainWindow().getChannelsList().getSelectedValue());
				UserConfigs.setInstruction("subscribe_channel");
				new SendMessageProcess();

			} else {
				JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Vous êtes déjà connecté ce channel.",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Aucun canal sélectionné.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
