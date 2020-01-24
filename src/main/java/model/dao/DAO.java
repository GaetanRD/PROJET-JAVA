package model.dao;

import java.util.List;

public interface DAO<T> {

	public T find(Object id) throws DAOException;

	public T create(T obj) throws DAOException;

	public T update(T obj) throws DAOException;

	public void delete(T obj) throws DAOException;

	public List<T> list() throws DAOException;
}
