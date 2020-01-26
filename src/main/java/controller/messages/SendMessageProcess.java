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
import model.userConfigs.UserConfigs;
import view.Login;
import view.MainWindow;

public class SendMessageProcess {

	private static final Logger LOG = Logger.getLogger(ConnectAction.class.getName());
	private Login windowLogin;
	private MainWindow mainWindow;
	private Socket clientSocket;

	public SendMessageProcess(Login windowLogin) {
		this.windowLogin = windowLogin;

	}
	
	public SendMessageProcess(MainWindow mainWindow) {
		this.mainWindow = mainWindow;

	}

	public void SendMessageProcessForConnection(String login, String pass, String server, int port) {
		String msg = "{\"login\":\"" + login + "\",\"password\":\"sha1:" + pass + "\",\"instruction\":\"connect\"}\n";
		UserConfigs.setLogin(login);
		UserConfigs.setPass(pass);
		

		try {
			clientSocket = new Socket(server, port);
		} catch (IOException e1) {
			LOG.error("Error during socket init.", e1);
		} finally {
			processClient(clientSocket, msg);
			new Thread(new ReceiveMessageProcess(clientSocket, this.windowLogin)).start();

		}

	}

	public boolean SendMessageProcessForDisconnection(String login, String pass, String server, int port) {
		String msg = "{\"login\":\"" + login + "\",\"password\":\"sha1:" + pass
				+ "\",\"instruction\":\"disconnect\"}\n";

		try {
			clientSocket = new Socket(server, port);
		} catch (IOException e1) {
			LOG.error("Error during socket init.", e1);
			return false;
		} finally {
			processClient(clientSocket, msg);
			new Thread(new ReceiveMessageProcess(clientSocket, this.mainWindow)).start();

		}

		return true;

	}

	private void processClient(Socket clientSocket, String msg) {
		OutputStream out = null;
		OutputStreamWriter osw = null;
		PrintWriter pw = null;

		LOG.info("[CLIENT] - Message : " + msg);
		try {
			// Open the output stream of the client socket.
			out = clientSocket.getOutputStream();
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
