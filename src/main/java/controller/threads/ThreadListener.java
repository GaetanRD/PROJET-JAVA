package controller.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

import controller.DecodeJSon;
import controller.codes.CodeSwitch;
import model.userConfigs.UserConfigs;

public class ThreadListener implements Runnable {

	private static final Logger LOG = Logger.getLogger(ThreadListener.class.getName());

	public ThreadListener() {

	}

	@Override
	public void run() {
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			in = UserConfigs.getClientSocket().getInputStream();
			isr = new InputStreamReader(in, "UTF-8");
			br = new BufferedReader(isr);

			while (true) {
				// Read the first line of the network stream
				

					String line = br.readLine();
					if (line != null) {
					final String ip = UserConfigs.getClientSocket().getInetAddress().getHostAddress();
					LOG.info(ip + " : " + line);
					DecodeJSon dJson = new DecodeJSon(line);
					System.out.println("-------" + dJson.getaObj().get(0));
					new CodeSwitch((Integer) dJson.getaObj().get(0), (String) dJson.getaObj().get(1));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error("Error during reading message from the server.", e);
		}
	}
}
