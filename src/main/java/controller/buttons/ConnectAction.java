package controller.buttons;

import view.Login;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import view.MainWindow;
import view.Login;

public class ConnectAction extends AbstractAction {
	public ConnectAction(String texte){
		super(texte);
	}
 
	public void actionPerformed(ActionEvent e) { 
		
		System.out.println("Vous êtes connecter");
		MainWindow window = new MainWindow();
		window.setVisible(true); 	
		
	} 
}
