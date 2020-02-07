/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used for the process when the app sends a message to the server
 * 
 * 
 */

package controller.messages;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import java.net.SocketException;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import controller.buttons.ConnectAction;
import controller.threads.ThreadListener;
import controller.threads.ThreadListenerChannelsList;
import controller.threads.ThreadListenerMembersList;
import model.userConfigs.Connexion;
import model.userConfigs.UserConfigs;

public class SendMessageProcess {

	private static final Logger LOG = Logger.getLogger(ConnectAction.class.getName());

	public SendMessageProcess() {
		
		String msg = null;

		if (UserConfigs.getInstruction() == "subscribe_channel") {
			msg = "{\"login\":\"" + UserConfigs.getLogin() + "\",\"password\":\"sha1:" + UserConfigs.getPass()
					+ "\",\"channel\":\"" + UserConfigs.getCurrentChannel()
					+ "\",\"instruction\":\"subscribe_channel\",\"target_channel\":\"" + UserConfigs.getNewChannel()
					+ "\"}";

		} else if (UserConfigs.getInstruction() == "send_message") {

			msg = "{\"login\":\"" + UserConfigs.getLogin() + "\",\"password\":\"sha1:" + UserConfigs.getPass()
					+ "\",\"channel\":\"" + UserConfigs.getCurrentChannel()
					+ "\",\"instruction\":\"send_message\",\"message\":" + UserConfigs.getMessage() + "}";
			Logmsg(msg);
		} else {

			msg = "{\"login\":\"" + UserConfigs.getLogin() + "\",\"password\":\"sha1:" + UserConfigs.getPass()
					+ "\",\"instruction\":" + UserConfigs.getInstruction() + "}";
		}

		if (!UserConfigs.isLogged()) {
			try {
				UserConfigs.setClientSocket(new Socket(UserConfigs.getServer(), UserConfigs.getPort()));
				
				UserConfigs.setT(new Thread(new ThreadListener()));
				UserConfigs.getT().start();
				UserConfigs.setT3(new Thread(new ThreadListenerChannelsList()));
				UserConfigs.getT3().start();
				UserConfigs.setT2(new Thread(new ThreadListenerMembersList()));
				UserConfigs.getT2().start();
				processClient(msg);
			} catch (IOException e1) {
				LOG.error("Error during socket init.", e1);
				JOptionPane.showMessageDialog(UserConfigs.getLoginWindow(), "Erreur : impossible de joindre le serveur",
						"Information", JOptionPane.INFORMATION_MESSAGE);

			}
		} else {
			try {
				UserConfigs.getClientSocket().setSoTimeout(10000);
				 System.out.println("Socket time out in "+UserConfigs.getClientSocket()+"ms");  
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      
	         
			processClient(msg);

		}

	}
	
	public void Logmsg(String msg) {
		
		String Login = UserConfigs.getLogin();
		String Msg = UserConfigs.getMessage();
		    
	    String pattern = "yyyy-MM-dd kk:mm";
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	    String Date = simpleDateFormat.format(new Date());
		
		try
		{
			Connection con = Connexion.connecterDB();
			Statement st;
			
			st = con.createStatement();
			st.executeUpdate("INSERT INTO log.log (`Login`, `Message`, `DATE`) VALUE ('"+ Login +"', '"+ Msg +"', '"+ Date +"');");
			con.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Error during Data Manipulation.", e);
		}	
	}

	public void processClient(String msg) {
		OutputStream out = null;
		OutputStreamWriter osw = null;
		PrintWriter pw = null;
		
		
		LOG.info("[CLIENT] - Message d'instruction : " + UserConfigs.getInstruction() + " -> " + msg);
		try {
			// Open the output stream of the client socket.
			out = UserConfigs.getClientSocket().getOutputStream();
			osw = new OutputStreamWriter(out);
			pw = new PrintWriter(osw);

			// Print and fluch the msg in the pipeline
			pw.println(msg);
			pw.flush();

		} catch (IOException e) {
			LOG.error("Error during socket outputstream.", e);
		} finally {
			if (UserConfigs.getClientSocket().isClosed()) {
				if (pw != null) {
					pw = null;
				}
				if (osw != null) {
					osw = null;
				}
				if (out != null) {
					out = null;
				}

			}
		}
	}

}
