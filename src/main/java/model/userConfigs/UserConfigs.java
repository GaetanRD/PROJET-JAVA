/** 
 * Chat-IRC Project by Gaetan and Morgan for the Cnam 
 * Tutor Romain BLIN
 * 
 * This class is used to keep the login and the password of the user
 * 
 * 
 * 
 */

package model.userConfigs;

import java.net.Socket;
import java.util.LinkedList;

import controller.threads.ThreadListener;
import controller.threads.ThreadListenerMessage;
import view.Login;
import view.MainWindow;

public class UserConfigs {

	private static String login;
	private static String pass;
	private static String server;
	private static String instruction;
	private static int port;

	private static boolean stopTheThread;
	private static boolean stopTheThreadMessage;

	private static String message;

	private static Login loginWindow;
	private static MainWindow mainWindow;

	private static Socket clientSocket;
	private static Thread t = new Thread(new ThreadListener());
	private static Thread t2 = new Thread(new ThreadListenerMessage());

	private static LinkedList<String> membersList = new LinkedList<>();

	private static String currentChannel;
	private static String newChannel;

	private static boolean logged; // May to disable or enable objects if the user is connected to the app or not
	private static boolean connectedToAChannel; // May to disable or enable objects if the user is connected to a
												// channel or not

	public UserConfigs(String login, String pass) {
		UserConfigs.setLogin(login);
		UserConfigs.setPass(pass);
		UserConfigs.setLogged(false);
		UserConfigs.setConnectedToAChannel(false);
		UserConfigs.setStopTheThread(false);
		UserConfigs.setStopTheThreadMessage(false);

	}

	public static void exitLoginWindow() {
		loginWindow.setVisible(false);
	}

	public static String getServer() {
		return server;
	}

	public static void setServer(String server) {
		UserConfigs.server = server;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		UserConfigs.port = port;
	}

	public static Socket getClientSocket() {
		return clientSocket;
	}

	public static void setClientSocket(Socket clientSocket) {
		UserConfigs.clientSocket = clientSocket;
	}

	public static String getLogin() {
		return login;
	}

	public static void setLogin(String login) {
		UserConfigs.login = login;
	}

	public static String getPass() {
		return pass;
	}

	public static void setPass(String pass) {
		UserConfigs.pass = pass;
	}

	public static boolean isConnectedToAChannel() {
		return connectedToAChannel;
	}

	public static void setConnectedToAChannel(boolean connectedToAChannel) {
		UserConfigs.connectedToAChannel = connectedToAChannel;
	}

	public static boolean isLogged() {
		return logged;
	}

	public static void setLogged(boolean logged) {
		UserConfigs.logged = logged;
	}

	public static Thread getT() {
		return t;
	}

	public static void setT(Thread t) {
		UserConfigs.t = t;
	}

	public static Login getLoginWindow() {
		return loginWindow;
	}

	public static void setLoginWindow(Login loginWindow) {
		UserConfigs.loginWindow = loginWindow;
	}

	public static MainWindow getMainWindow() {
		return mainWindow;
	}

	public static void setMainWindow(MainWindow mainWindow) {
		UserConfigs.mainWindow = mainWindow;
	}

	public static String getInstruction() {
		return instruction;
	}

	public static void setInstruction(String instruction) {
		UserConfigs.instruction = instruction;
	}

	public static String getCurrentChannel() {
		return currentChannel;
	}

	public static void setCurrentChannel(String currentChannel) {
		UserConfigs.currentChannel = currentChannel;
	}

	public static String getNewChannel() {
		return newChannel;
	}

	public static void setNewChannel(String newChannel) {
		UserConfigs.newChannel = newChannel;
	}

	public static String getMessage() {
		return message;
	}

	public static void setMessage(String message) {
		UserConfigs.message = message;
	}

	public static boolean isStopTheThread() {
		return stopTheThread;
	}

	public static void setStopTheThread(boolean b) {
		UserConfigs.stopTheThread = b;
	}

	public static LinkedList<String> getMembersList() {
		return membersList;
	}

	public static void setMembersList(LinkedList<String> membersList) {
		UserConfigs.membersList = membersList;
	}

	public static boolean isStopTheThreadMessage() {
		return stopTheThreadMessage;
	}

	public static void setStopTheThreadMessage(boolean stopTheThreadMessage) {
		UserConfigs.stopTheThreadMessage = stopTheThreadMessage;
	}

	public static Thread getT2() {
		return t2;
	}

	public static void setT2(Thread t2) {
		UserConfigs.t2 = t2;
	}
}
