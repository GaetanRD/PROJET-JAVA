package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.userConfigs.UserConfigs;

public class KeyFieldSize implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (UserConfigs.getLoginWindow().getLoginField().getText().length() > 40) {

			UserConfigs.getLoginWindow().getLoginField().setText(UserConfigs.getLoginWindow().getLoginField().getText()
					.substring(0, UserConfigs.getLoginWindow().getLoginField().getText().length() - 1));
		}
	}

}
