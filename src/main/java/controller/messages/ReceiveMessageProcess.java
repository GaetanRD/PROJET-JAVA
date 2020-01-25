package controller.messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.log4j.Logger;

import view.Login;

public class ReceiveMessageProcess implements Runnable {
	private static final Logger LOG = Logger.getLogger(ReceiveMessageProcess.class.getName());
	private Socket server;
	private Login windowLogin;

	public ReceiveMessageProcess(Socket server, Login windowLogin) {
		this.server = server;
		this.windowLogin = windowLogin;
	}

	@Override
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
			while (line != null) {
				final String ip = server.getInetAddress().getHostAddress();
				LOG.info(ip + " : " + line);
				windowLogin.setResponseServer("Reponse du serveur : " + line);
				// Read the next line readed on the network stream.
				line = br.readLine();
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
