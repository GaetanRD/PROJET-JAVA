package controller.buttons;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import view.MainWindow;

public class MenuSetLogin extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainWindow window;

	public MenuSetLogin(MainWindow window, String text) {
		super(text);

		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showInputDialog(window, "Veuillez choisir votre nouveau pseudo (En construction)",
				"Information", JOptionPane.INFORMATION_MESSAGE);
		System.out.println("Veuillez choisir votre nouveau pseudo");

	}

}
