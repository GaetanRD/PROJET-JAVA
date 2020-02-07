package model.userConfigs;
import java.sql.*;




public class Connexion {
	
	public static void main(String[] args) {
		
	}
	
	public static Connection connecterDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/";
			String user = "root";
			String pass = "";
			String db =  "log";
			Connection con = DriverManager.getConnection(url + db , user, pass);
			System.out.println("=========================== co local établie ===========================");
			return con;
			
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
