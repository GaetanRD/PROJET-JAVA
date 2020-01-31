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
import org.apache.log4j.Logger;

import controller.buttons.ConnectAction;
import controller.threads.ThreadListener;
import model.userConfigs.UserConfigs;
import view.Login;
import view.MainWindow;

public class SendMessageProcess {

	private static final Logger LOG = Logger.getLogger(ConnectAction.class.getName());
	public SendMessageProcess(Login windowLogin) {
		UserConfigs.getClientSocket();

	}

	public SendMessageProcess(MainWindow mainWindow) {

	}

	public void SendMessageProcessForConnection(String login, String pass, String server, int port) {
		String msg = "{\"login\":\"" + login + "\",\"password\":\"sha1:" + pass + "\",\"instruction\":\"connect\"}";
		UserConfigs.setLogin(login);
		UserConfigs.setPass(pass);
		UserConfigs.setServer(server);
		UserConfigs.setPort(port);

		try {
			UserConfigs.setClientSocket(new Socket(server, port));
			Thread t = new Thread(new ThreadListener());
			t.start();
			processClient(msg);
		} catch (IOException e1) {
			LOG.error("Error during socket init.", e1);
		}

	}

	public void SendMessageProcessForChannelsList(String login, String pass, String server, int port) {
		String msg = "{\"login\":\"" + login + "\",\"password\":\"sha1:" + pass
				+ "\",\"instruction\":\"list_channels\"}";
		

		processClient(msg);
		
		
	
	}

	public void SendMessageProcessForDisconnection(String login, String pass, String server, int port) {
		String msg = "{\"login\":\"" + login + "\",\"password\":\"sha1:" + pass
				+ "\",\"instruction\":\"disconnect\"}";

		processClient(msg);
		
		

	}

	private void processClient(String msg) {
		OutputStream out = null;
		OutputStreamWriter osw = null;
		PrintWriter pw = null;

		LOG.info("[CLIENT] - Message : " + msg);
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

		}
	}

}
