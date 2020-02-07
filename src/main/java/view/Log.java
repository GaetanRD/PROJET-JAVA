/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class has used for the main window
 * 
 * 
 */

package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.JComboBox;

import model.userConfigs.Connexion;

public class Log extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel();

	private JTextPane tpLog = new JTextPane();
	private SimpleAttributeSet setErrorMessage;
	
	private JLabel labelChannels = new JLabel("Pseudo");
	
	private JList<String> PseudoList;
	private JComboBox<Object> liste1;
	
	JScrollPane sPaneTextAreaPseudo = new JScrollPane(PseudoList);
	JScrollPane sPaneTextMainLabel = new JScrollPane(tpLog);

	public Log() {
		super();

		build();
	}

	private void build() {
		setTitle("T'Chat - IRC Client (v0.1 alpha)");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(buildContentPane());

	}

	private JPanel buildContentPane() {
		panel.setLayout(null);

		MainLabel();
		ListOfPseudoArea();
		

		return panel;
	}

	private void MainLabel() {
		
		sPaneTextMainLabel.setLocation(10, 10);
		sPaneTextMainLabel.setSize(600, this.getHeight() - 50);
		tpLog.setEditable(false);
		sPaneTextMainLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		sPaneTextMainLabel.setOpaque(true);
		
		
		
		sPaneTextMainLabel.getViewport().add(tpLog);
		getOperation(null);
		panel.add(sPaneTextMainLabel, null);

	}
	
	private void ListOfPseudoArea() {
		//JScrollPane sPaneTextAreaChannels = new JScrollPane(PseudoList);

		labelChannels.setLocation(sPaneTextMainLabel.getX() + sPaneTextMainLabel.getWidth() + 5, 10);
		labelChannels.setSize(this.getWidth() - sPaneTextMainLabel.getWidth() - sPaneTextMainLabel.getX(), 10);

		panel.add(labelChannels, null);

		// create the list
		//PseudoList = new JList<String>();
		 
			 try {
					Connection con = Connexion.connecterDB();
					PreparedStatement pstatement = null;
					
					pstatement = con.prepareStatement("SELECT DISTINCT `Login` FROM log.log;");
					ResultSet rs = pstatement.executeQuery();
					liste1 = new JComboBox<Object>();
					liste1.addItem("");
					while(rs.next())
		            {   
						String login = rs.getString(1);
						liste1.addItem(login);
		            }
					
				}catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
							JOptionPane.ERROR_MESSAGE);
				}
		 
			 ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	 String s = (String) liste1.getSelectedItem();
		            	 System.out.println(s);
		            	 getOperation(s);
		            }
			 };
		liste1.addActionListener(cbActionListener);
		//Object[] elements = new Object[]{};
		//liste1 = new JComboBox<Object>(elements);
		
		liste1.setLocation(labelChannels.getX(), labelChannels.getY() + labelChannels.getHeight() + 5);
		liste1.setSize(labelChannels.getWidth() - 15,  20);
		liste1.setBackground(Color.white);
		panel.add(liste1, null);

	}


	public SimpleAttributeSet getSetErrorMessage() {
		return setErrorMessage;
	}

	public void setSetErrorMessage(SimpleAttributeSet setErrorMessage) {
		this.setErrorMessage = setErrorMessage;
	}

	public JTextPane getTp() {
		return tpLog;
	}

	public void setTp(JTextPane tpLog) {
		this.tpLog = tpLog;
	}
	
	public JScrollPane getSPaneTextAreaPseudo() {
		return sPaneTextAreaPseudo;
	}

	public void setSPaneTextAreaPseudo(JScrollPane sPaneTextAreaPseudo) {
		this.sPaneTextAreaPseudo = sPaneTextAreaPseudo;
	}
	
	
	public void getOperation(String s)// va récuperer dans la bdd local tous les message en rapport avec le login selectionner
	{		
		try
		{
			if (s != null) {
				Connection con = Connexion.connecterDB();
				PreparedStatement pstatement = null;
				System.out.println(s);
				pstatement = con.prepareStatement("SELECT * FROM log.log WHERE `Login`= '"+ s +"' ;");
				ResultSet rs = pstatement.executeQuery();
				
				String text = "";
				
				while(rs.next())
	            {           	   
					text = text + rs.getString(2) + " - " + rs.getString(4) + " - " + rs.getString(3) + "\n";
					tpLog.setText(text);
	            }
				
				con.close();
			}
			
			
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
					JOptionPane.ERROR_MESSAGE);
		}			
	}   
	

	
}
