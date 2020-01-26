/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used for the process when the server send a message to the app
 * 
 * 
 */

package controller.messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.log4j.Logger;

import controller.DecodeJSon;
import controller.codes.CodeSwitch;
import view.Login;
import view.MainWindow;

public class ReceiveMessageProcess implements Runnable {
	private static final Logger LOG = Logger.getLogger(ReceiveMessageProcess.class.getName());
	private Socket server;
	private Login windowLogin;
	private MainWindow window;
	static String message;

	public ReceiveMessageProcess(Socket server, Login windowLogin) {
		this.server = server;
		this.windowLogin = windowLogin;
	}
	
	public ReceiveMessageProcess(Socket server, MainWindow window) {
		this.server = server;
		this.window = window;
	}

	

	public void run() {
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			in = server.getInputStream();
			isr = new InputStreamReader(in, "UTF-8");
			br = new BufferedReader(isr);

			// Read the first line of the network stream
			String line = br.readLine();
			final String ip = server.getInetAddress().getHostAddress();
			LOG.info(ip + " : " + line);
			DecodeJSon dJson = new DecodeJSon(line);
			System.out.println("-------" + dJson.getaObj().get(0));
			
			if (this.window != null) {
				new CodeSwitch((Integer)dJson.getaObj().get(0), (String)dJson.getaObj().get(1), window);
			} else if (this.windowLogin != null) {
				new CodeSwitch((Integer)dJson.getaObj().get(0), (String)dJson.getaObj().get(1), windowLogin);
			}			
		

		} catch (IOException e) {
			LOG.error("Error during reading message from the server.", e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				LOG.error("Error during stream closing.", e);
			}
		}

	}

}
