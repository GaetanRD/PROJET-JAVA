package controller.buttons;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.AbstractAction;
import org.apache.log4j.Logger;

import view.Login;

public class ConnectAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ConnectAction.class.getName());
	private Login windowLogin;
	private String login;
	private String password;

	public ConnectAction(String text, Login windowLogin) {
		super(text);
		this.windowLogin = windowLogin;
		
		
		System.out.println(this.password + "  " +  password);
	}

	public void actionPerformed(ActionEvent e) {
		
		System.out.println(windowLogin.getLoginField().getText() + " " + windowLogin.getPasswordField().getText());
//		Scanner sc = null;
//
//		try {
//			// Create a stream on the client keyboard input.
//			sc = new Scanner(System.in);
//			while (true) {
//				final String test = sc.nextLine();
//				final Socket clientSocket = new Socket("192.168.32.129", 4567);
//
//				processClient(clientSocket, test);
//
//			}
////			{"login":"CocoFox","password":"sha1:XXXXXXXX","instruction":"connect"}
//		} catch (IOException e1) {
//			LOG.error("Error during socket init.", e1);
//		} finally {
//			if (sc != null) {
//				sc.close();
//			}
//		}
	}

	/**
	 * Send a msg to the server
	 * 
	 * @param clientSocket : the client Socket already connected to the server
	 * @param msg          : the content of the message to send
	 */
//
//	System.out.println("Vous êtes connecter");
//
//	MainWindow window = new MainWindow();window.setVisible(true);
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
			try {
				if (clientSocket != null) {
					clientSocket.close();
				}
			} catch (IOException e) {
				LOG.error("Error during stream closing.", e);
			}
		}
	}

	public Login getWindowLogin() {
		return windowLogin;
	}

	public void setWindowLogin(Login windowLogin) {
		this.windowLogin = windowLogin;
	}

}
