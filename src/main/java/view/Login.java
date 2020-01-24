package view;
import javax.swing.*;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.buttons.ConnectAction;
import controller.buttons.CloseProgram;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
	private JButton bouton;
	private JButton bouton2;
	private JTextField textpseudo;
	private JTextField textmdp;
	private JLabel label;
	public Login() {
		super();
		
		build();
	}
	private void build() {
		setTitle("coucou");
		setSize(400,240);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPanel());
	}
	private JPanel buildContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("CONNEXION");
		
		panel.add(label);
		
		JTextField textpseudo = new JTextField();
		textpseudo.setColumns(10); 
		
		panel.add(textpseudo);
		
		JTextField textmdp = new JTextField();
		textmdp.setColumns(10);
		
		panel.add(textmdp);
		
		JButton bouton = new JButton(new ConnectAction("Connexion"));
		panel.add(bouton);
 
		JButton bouton2 = new JButton(new CloseProgram("quitter"));
		panel.add(bouton2);
		
		
		return panel;
	}
	
	
	public JTextField getField1(){
		return textpseudo;
	}
	
	public JTextField getField2(){
		return textmdp;
	}
	
	

}
