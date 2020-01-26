/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used to close the program
 * 
 */

package controller.buttons;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class CloseProgram extends AbstractAction {
	private static final long serialVersionUID = 1L;

	public CloseProgram(String texte) {
		super(texte);
	}

	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}