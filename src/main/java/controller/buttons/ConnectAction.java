package controller.buttons;

import java.awt.event.ActionEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.AbstractAction;
import org.apache.log4j.Logger;

import controller.messages.ReceiveMessageProcess;
import view.Login;
import view.MainWindow;

public class ConnectAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ConnectAction.class.getName());
	private Login windowLogin;
	private String response;
	
	public ConnectAction(String text, Login windowLogin) {
		super(text);
		this.windowLogin = windowLogin;
	}

	public void actionPerformed(ActionEvent e) {
		String file = "{\"login\":\"" + windowLogin.getLoginField().getText() + "\",\"\":\"sha1:"
				+ windowLogin.getPasswordField().getText() + "\",\"instruction\":\"connect\"}\n";

		System.out.println(file);

		try {
			// Create a stream on the client keyboard input.
			final Socket clientSocket = new Socket("192.168.32.129", 4567);
			processClient(clientSocket, file);
			new Thread(new ReceiveMessageProcess(clientSocket, windowLogin)).start();

		} catch (IOException e1) {
			LOG.error("Error during socket init.", e1);
		} finally {
			System.out.println(windowLogin.getResponseServer() + " : Vous êtes connecter");
			MainWindow window = new MainWindow();
			window.setVisible(true);
			windowLogin.setVisible(false);
		}
	}

	/**
	 * Send a msg to the server
	 * 
	 * @param clientSocket : the client Socket already connected to the server
	 * @param msg          : the content of the message to send
	 */
//

//
//	

	private static void processClient(Socket clientSocket, String msg) {
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

	public Login getWindowLogin() {
		return windowLogin;
	}

	public void setWindowLogin(Login windowLogin) {
		this.windowLogin = windowLogin;
	}
	

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
