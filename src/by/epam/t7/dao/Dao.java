package by.epam.t7.dao;

import java.sql.Connection;

import by.epam.t7.dao.pool.ConnectionPool;
import by.epam.t7.exception.ConnectionPoolException;

/**
 * A data access object (DAO) is an object that provides an abstract interface
 * to some type of database or other persistence mechanism.This interface is the
 * basic interface to others interfaces.
 */
public interface Dao {

	/**
	 * Returns connection to the connection pool
	 * 
	 * @param connection
	 */
	default void returnConnection(Connection connection) {
		ConnectionPool.getInstance().returnConnection(connection);

	}

	/**
	 * Takes connection from the connection pool
	 * 
	 * @return
	 * @throws ConnectionPoolException
	 */
	default Connection takeConnection() throws ConnectionPoolException {
		return ConnectionPool.getInstance().takeConnection();

	}
}
