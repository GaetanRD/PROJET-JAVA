package model.dao;

import model.dao.bean.User;

public interface DAOUser extends DAO<User> {
	public User getUserFromLoginAndPassword(String login, String password) throws DAOException;

}
