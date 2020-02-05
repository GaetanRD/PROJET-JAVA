package controller;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import controller.messages.SendMessageProcess;
import model.userConfigs.UserConfigs;

public class KeySendMessage implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == Event.ENTER && !UserConfigs.getMainWindow().getTextfield().getText().isEmpty()) {
			UserConfigs.setInstruction("send_message");
			new SendMessageProcess();
			UserConfigs.getMainWindow().getTextfield().setText("");
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		UserConfigs.setMessage(UserConfigs.getMainWindow().getTextfield().getText());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
