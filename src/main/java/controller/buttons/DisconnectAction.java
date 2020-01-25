package controller.buttons;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import view.MainWindow;
import view.Login;

public class DisconnectAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainWindow window;

	public DisconnectAction(MainWindow window, String text) {
		super(text);

		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(window, "Vous êtes déconnecté. Retour au menu de connection (En construction)",
				"Information", JOptionPane.INFORMATION_MESSAGE);
		
		Login windowLogin = new Login();
		windowLogin.setVisible(true);
		window.setVisible(false);
	}

}
