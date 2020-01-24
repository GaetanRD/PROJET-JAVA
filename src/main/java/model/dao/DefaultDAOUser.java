package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import model.dao.bean.User;

public class DefaultDAOUser extends AbstractDAO implements DAOUser {

	protected DefaultDAOUser(Connection connect) {
		super(connect);
	}

	@Override
	public User find(Object id) throws DAOException {
		final String sql = "SELECT * FROM `users` WHERE `id`= ? ";
		if (!(id instanceof Integer)) {
			throw new DAOException("The ID isn't an Integer.");
		}

		PreparedStatement st = null;
		ResultSet r = null;

		try {
			st = connect.prepareStatement(sql);
			st.setInt(1, (int) id);
			r = st.executeQuery();

			if (r.next()) {
				User u = new User();
				u.setId(r.getInt("id"));
				u.setLogin(r.getString("login"));
				u.setPass(r.getString("pass"));

				return u;
			}

			throw new DAOException("User not found.");

		} catch (SQLException e) {
			throw new DAOException("Error during loading user from the database", e);
		} finally {
			DAOUtils.close(r, st);
		}

	}

	@Override
	public User create(User obj) throws DAOException {
		final String sql = "INSERT INTO `users` VALUES (NULL, ?, ?, ?, ?)";

		PreparedStatement st = null;
		ResultSet rs = null;
		int r;

		try {
			st = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(3, obj.getLogin());
			st.setString(4, obj.getPass());
			r = st.executeUpdate();
			
			rs = st.getGeneratedKeys();
			
			
			
			if(r > 0 && rs.next()) {
				obj.setId(rs.getInt(1));
				return obj;
			}
			
			throw new DAOException("Error during user insert in the database");
			
		} catch (SQLException e) {
			throw new DAOException("Error during creating user in the database", e);
		} finally {
			DAOUtils.close(st);
		}
		

	}

	@Override
	public User update(User obj) throws DAOException {
		final String sql = "UPDATE `users` "
				+ "SET `first_name` = ? ,"
				+ "`last_name` = ? ,"
				+ "`login` = ? ,"
				+ "`pass` = ? "
				+ "WHERE id = ?";

		PreparedStatement st = null;
		int r;

		try {
			st = connect.prepareStatement(sql);
			st.setString(3, obj.getLogin());
			st.setString(4, obj.getPass());
			r = st.executeUpdate();

			
			if(r > 0) {
				return obj;
			}
			
			throw new DAOException("Error during user insert in the database");
			
		} catch (SQLException e) {
			throw new DAOException("Error during creating user in the database", e);
		} finally {
			DAOUtils.close(st);
		}
	
	}

	@Override
	public void delete(User obj) throws DAOException {
		final String sql = "DELETE FROM `users` WHERE `id`= ? ";

		PreparedStatement st = null;
		int r;

		try {
			st = connect.prepareStatement(sql);
			st.setInt(1, obj.getId());
			r = st.executeUpdate();

			if (r == 0) {
				throw new DAOException("User not found.");
			}

		} catch (SQLException e) {
			throw new DAOException("Error during deleting user from the database", e);
		} finally {
			DAOUtils.close(st);
		}

	}

	@Override
	public List<User> list() throws DAOException {
		final String sql = "SELECT * FROM `users`";
		final List<User> usersList = new LinkedList<>();
		PreparedStatement st = null;
		ResultSet r = null;

		try {
			st = connect.prepareStatement(sql);
			r = st.executeQuery();

			while (r.next()) {
				final User u = new User();
				u.setId(r.getInt("id"));
				u.setLogin(r.getString("login"));
				u.setPass(r.getString("pass"));
				usersList.add(u);
			}
			
			return usersList;
			
		} catch (SQLException e) {
			throw new DAOException("Error during loading user from the database", e);
		} finally {
			DAOUtils.close(r, st);
		}

	}

	@Override
	public User getUserFromLoginAndPassword(String login, String password) throws DAOException {
		final String sql = "SELECT * FROM `users` WHERE `login` = ? AND `pass` = ? ";
		if (password.isEmpty() || login.isEmpty()){
			throw new DAOException("Login or password are missing.");
		}

		PreparedStatement st = null;
		ResultSet r = null;

		try {
			st = connect.prepareStatement(sql);
			st.setString(1, login);
			st.setString(2, password);
			r = st.executeQuery();

			if (r.next()) {
				User u = new User();
				u.setId(r.getInt("id"));
				u.setLogin(r.getString("login"));
				u.setPass(r.getString("pass"));

				return u;
			}

			throw new DAOException("User not found, login or password not match.");

		} catch (SQLException e) {
			throw new DAOException("Error during loading user from the database", e);
		} finally {
			DAOUtils.close(r, st);
		}
	}

}
