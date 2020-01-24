package model.dao;

import java.sql.Connection;

public abstract class AbstractDAO {
	protected Connection connect;

	protected AbstractDAO(Connection connect) {
		this.connect = connect;
	}
}
