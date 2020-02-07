package controller.buttons;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import controller.messages.SendMessageProcess;
import model.userConfigs.UserConfigs;

public class SendMessage extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	
	public SendMessage(String text) {
		super(text);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!UserConfigs.getMainWindow().getTextfield().getText().isEmpty()) {
			if (UserConfigs.getMainWindow().getTextfield().getText().equals("/quit")) {
				UserConfigs.setInstruction("disconnect");
				new SendMessageProcess();
			} else {
				UserConfigs.setInstruction("send_message");
			new SendMessageProcess();
			}
			
			UserConfigs.getMainWindow().getTextfield().setText("");
		}
		
	}

}
