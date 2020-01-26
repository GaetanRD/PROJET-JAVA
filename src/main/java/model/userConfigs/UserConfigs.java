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

public class UserConfigs {

	private static String login;
	private static String pass;
	
	private static boolean logged; // May to disable or enable objects if the user is connected to the app or not
	private static boolean connectedToAChannel; // May to disable or enable objects if the user is connected to a
												// channel or not

	public UserConfigs(String login, String pass) {
		UserConfigs.setLogin(login);
		UserConfigs.setPass(pass);
		UserConfigs.setLogged(false);
		UserConfigs.setConnectedToAChannel(false);
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

}
