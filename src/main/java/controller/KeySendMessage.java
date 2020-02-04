package controller;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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

		ArrayList<String> a = new ArrayList<>();

		if (UserConfigs.getLoginWindow().getLoginField().getText().length() > 44) {

			UserConfigs.getLoginWindow().getLoginField().setText(UserConfigs.getLoginWindow().getLoginField().getText()
					.substring(0, UserConfigs.getLoginWindow().getLoginField().getText().length() - 1));
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
