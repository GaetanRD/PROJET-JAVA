package controller.buttons;

import view.co;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import view.MainWindow;
import view.co;

public class Conne extends AbstractAction {
	public Conne(String texte){
		super(texte);
	}
 
	public void actionPerformed(ActionEvent e) { 
		
		System.out.println("Vous êtes connecter");
		MainWindow window = new MainWindow();
		window.setVisible(true); 	
		
	} 
}
