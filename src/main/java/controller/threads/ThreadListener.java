package controller.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
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

		LinkedList<Object> lList = new LinkedList<>();

		try {
			in = UserConfigs.getClientSocket().getInputStream();
			isr = new InputStreamReader(in, "UTF-8");
			br = new BufferedReader(isr);

			while (true) {

				lList.clear();

				// Read the first line of the network stream
				String line = br.readLine();

				if (line != null) {
					final String ip = UserConfigs.getClientSocket().getInetAddress().getHostAddress();
//					LOG.info(ip + " : " + line);
					DecodeJSon dJson = new DecodeJSon(line);

					for (int i = 0; i < dJson.getaObj().size(); i++) {
						lList.add(dJson.getaObj().get(i));
					}
					if((Integer)lList.get(0) == 130)
					LOG.info(ip + " : " + line);
					
					new CodeSwitch(lList);

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error("Error during reading message from the server.", e);
		} finally {
			if (br != null) {
				br = null;
			}
			if (isr != null) {
				isr = null;
			}
			if (in != null) {
				in = null;
			}
						
		}
	}
}
