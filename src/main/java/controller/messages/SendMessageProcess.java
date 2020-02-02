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

public class SendMessageProcess {

	private static final Logger LOG = Logger.getLogger(ConnectAction.class.getName());

	public SendMessageProcess() {
		
		String msg = null;
		
		
		
		if (UserConfigs.getInstruction() == "subscribe_channel") {
			msg = "{\"login\":\"" + UserConfigs.getLogin() + "\",\"password\":\"sha1:" + UserConfigs.getPass()
					+ "\",\"channel\":\"" + UserConfigs.getCurrentChannel()
					+ "\",\"instruction\":\"subscribe_channel\",\"target_channel\":\"" + UserConfigs.getNewChannel() + "\"}";
		} else if (UserConfigs.getInstruction() == "list_channel_members") {
			msg = "{\"login\":\"" + UserConfigs.getLogin() + "\",\"password\":\"sha1:" + UserConfigs.getPass() 
			+ "\",\"channel\":\"" + UserConfigs.getCurrentChannel() + "\",\"instruction\":\"list_channel_members\"}";
		} else {
			msg = "{\"login\":\"" + UserConfigs.getLogin() + "\",\"password\":\"sha1:" + UserConfigs.getPass()
					+ "\",\"instruction\":" + UserConfigs.getInstruction() + "}";
		}

		if (!UserConfigs.isLogged()) {
			try {
				UserConfigs.setClientSocket(new Socket(UserConfigs.getServer(), UserConfigs.getPort()));
				Thread t = new Thread(new ThreadListener());
				t.start();
				processClient(msg);
			} catch (IOException e1) {
				LOG.error("Error during socket init.", e1);
			}
		} else {
			processClient(msg);
		}
	}

	private void processClient(String msg) {
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
		}
	}

}
