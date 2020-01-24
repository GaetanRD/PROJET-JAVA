package controller.buttons;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class Quit extends AbstractAction {
	public Quit(String texte){
		super(texte);
	}
 
	public void actionPerformed(ActionEvent e) { 
		System.out.println("Vous êtes déconnecté. Retour au menu Login");
		System.exit(0);
	} 
}