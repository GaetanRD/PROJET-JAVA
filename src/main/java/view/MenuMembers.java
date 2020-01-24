package view;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;

public class MenuMembers extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> listMembers = new JList<String>();
	private JTextArea textArea = new JTextArea();
	private JButton jButton1 = new JButton();
	DefaultListModel<String> dlm = new DefaultListModel<>();

	public MenuMembers() {
		super();
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void jbInit() throws Exception {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(348, 300));
		this.setTitle("Copie d\'une liste vers une zone de texte");
		textArea.setBounds(new Rectangle(189, 24, 129, 163));
		jButton1.setText("jButton1");
		jButton1.setBounds(new Rectangle(34, 219, 272, 35));
		jButton1.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {
				jButton1_actionPerformed(e);
			}
		});
		textArea.setBounds(new Rectangle(189, 24, 129, 163));
		jButton1.setText("jButton1");
		this.getContentPane().add(listMembers, null);
		this.getContentPane().add(textArea, null);
		this.getContentPane().add(jButton1, null);
		listMembers.setModel(dlm); // association du jList1 au modèle:
		listMembers.setBounds(new Rectangle(17, 22, 130, 165));
		// la modification (initialisation) du modèle:
		dlm.addElement("Romain");
		dlm.addElement("Morgan");
		dlm.addElement("Julien");
		dlm.addElement("Olivier");
		dlm.addElement("Marlène");
	}

	// Remplacé (surchargé) pour pouvoir quitter lors de System Close
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}

	void jButton1_actionPerformed(ActionEvent e) {
		textArea.append((String) listMembers.getSelectedValue() + "\n"); // ajout de lélément sélectionné
	}

}
