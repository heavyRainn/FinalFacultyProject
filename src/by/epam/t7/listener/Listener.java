package by.epam.t7.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.epam.t7.dao.pool.ConnectionPool;

/**
 * Listener is used to initialize and destroy connection pool
 */
public class Listener implements ServletContextListener {

	/**
	 * Destroys the connection pool
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ConnectionPool.getInstance().stop();

	}

	/**
	 * Initialize the connection pool
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ConnectionPool.getInstance().initConnectionPool();

	}

}
