package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DAOFactory {

	private static final Logger LOG = Logger.getLogger(DAOFactory.class.getName());

	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PASSWORD;
	private static final String DB_DRIVER;

	static {
		InputStream in = null;
		Properties prop = new Properties();
		in = DAOFactory.class.getResourceAsStream("/config.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			LOG.fatal("Error during the load of properties file", e);
			System.exit(-1);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				LOG.fatal("Error during stream closing", e);
				System.exit(-1);
			}
		}

		DB_URL = prop.getProperty("db.url");
		DB_USER = prop.getProperty("db.user");
		DB_PASSWORD = prop.getProperty("db.password");
		DB_DRIVER = prop.getProperty("db.driver");
		
		if (DB_URL == null || DB_USER == null || DB_PASSWORD == null || DB_DRIVER == null) {
			LOG.fatal("One property is null");
			System.exit(-1);
		}
		
		
	}

	public static Connection getConnection() throws DAOException {

		try {
			Class.forName(DB_DRIVER);
			return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new DAOException("Error during loading mysql Driver.", e);
		} catch (SQLException e) {
			throw new DAOException("Error during connection init in MySQL", e);
		}

	}

	public static DAOUser getDAOUser(Connection c) {
		return new DefaultDAOUser(c);
	}
}
