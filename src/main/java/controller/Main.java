package controller;

import java.sql.Connection;

import javax.swing.SwingUtilities;

import model.dao.DAOException;
import model.dao.DAOFactory;
import model.dao.DAOUser;
import model.dao.bean.User;
import view.Login;

public class Main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				Login windowLogin = new Login();
				windowLogin.setVisible(true);
			}
			
		});
		
		Connection c = null;

		try {
			c = DAOFactory.getConnection();

			DAOUser userDAO = DAOFactory.getDAOUser(c);

			User u = userDAO.find(1);

			System.out.println(u.getLogin());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
