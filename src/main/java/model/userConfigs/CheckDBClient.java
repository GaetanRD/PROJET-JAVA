package model.userConfigs;
import java.sql.*;




public class CheckDBClient {
		
		
		// JDBC driver name and database URL
		   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   static final String DB_URL = "jdbc:mysql://localhost:3306/";

		   //  Database credentials
		   static final String USER = "Morgan";
		   static final String PASS = "Azerty42!";
		   
		   public static void main(String[] args) {
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      //System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      //System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      //System.out.println("Creating table in given database...");
		      stmt = conn.createStatement();
		      
		      String sql ="CREATE DATABASE IF NOT EXISTS `Log`;"; 

		      stmt.executeUpdate(sql);
		      
		      String sql2 ="CREATE TABLE IF NOT EXISTS `Log`.`Log` ( `idLog` INT NOT NULL AUTO_INCREMENT, `Login` VARCHAR(45) DEFAULT ' ', `Message` VARCHAR(2500) DEFAULT ' ', `DATE` VARCHAR(45) DEFAULT ' ', PRIMARY KEY (`idLog`)) ENGINE = InnoDB;";
		      stmt.executeUpdate(sql2);
		      //System.out.println("Created table in given database...");
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   //System.out.println("Goodbye!");
		}//end main
		
		
}
