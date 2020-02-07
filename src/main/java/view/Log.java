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
	
	private JComboBox<Object> PseudoList;
	
	JScrollPane sPaneTextMainLabel = new JScrollPane(tpLog);
	
	private int buttonHeight = 30;

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
		
		sPaneTextMainLabel.setLocation(10, buttonHeight + 10);
		sPaneTextMainLabel.setSize(770, this.getHeight() - 80);
		tpLog.setEditable(false);
		sPaneTextMainLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		sPaneTextMainLabel.setOpaque(true);
		
		
		
		sPaneTextMainLabel.getViewport().add(tpLog);
		getOperation(null);
		panel.add(sPaneTextMainLabel, null);

	}
	
	private void ListOfPseudoArea() {
		labelChannels.setLocation(20, 10);
		labelChannels.setSize(540 / 3, 20);

		panel.add(labelChannels, null);

		 
			 try {
					Connection con = Connexion.connecterDB();
					PreparedStatement pstatement = null;
					
					pstatement = con.prepareStatement("SELECT DISTINCT `Login` FROM log.log;");
					ResultSet rs = pstatement.executeQuery();
					PseudoList = new JComboBox<Object>();
					PseudoList.addItem("");
					while(rs.next())
		            {   
						String login = rs.getString(1);
						PseudoList.addItem(login);
		            }
					
				}catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
							JOptionPane.ERROR_MESSAGE);
				}
		 
			 ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	 String P = (String) PseudoList.getSelectedItem();
		            	 System.out.println(P);
		            	 getOperation(P);
		            }
			 };
			 PseudoList.addActionListener(cbActionListener);
		
			 PseudoList.setLocation(labelChannels.getX() + labelChannels.getWidth() - 130, 10);
			 PseudoList.setSize(labelChannels.getWidth(), labelChannels.getHeight());
			 PseudoList.setBackground(Color.white);
		panel.add(PseudoList, null);

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
	
	
	public void getOperation(String P)
	{		
		try
		{
			if (P != null) {
				Connection con = Connexion.connecterDB();
				PreparedStatement pstatement = null;
				
				pstatement = con.prepareStatement("SELECT * FROM log.log WHERE `Login`= '"+ P +"' ;");
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
