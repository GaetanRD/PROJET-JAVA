/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used for the process when the app sends a message to the server
 * 
 * 
 */

package controller.threads;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import controller.buttons.ConnectAction;
import model.userConfigs.UserConfigs;

public class ThreadListenerChannelsList extends Thread {

	private static final Logger LOG = Logger.getLogger(ConnectAction.class.getName());

	public ThreadListenerChannelsList() {

	}

	@Override
	public synchronized void run() {
		String msg = null;

		if (!UserConfigs.isLogged()) {
			processClient(msg);
		}
	}

	public void processClient(String msg) {
		OutputStream out = null;
		OutputStreamWriter osw = null;
		PrintWriter pw = null;

		do {

			msg = "{\"login\":\"" + UserConfigs.getLogin() + "\",\"password\":\"sha1:" + UserConfigs.getPass()
					+ "\",\"instruction\":\"list_channels\"}";
			if (!msg.isEmpty()) {
				LOG.info("[CLIENT] - Message d'instruction : list_channels -> " + msg);
				try {
					UserConfigs.getT3();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
		}while(UserConfigs.isLogged());
}

}