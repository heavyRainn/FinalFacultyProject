package by.epam.t7.dao.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

import org.apache.log4j.Logger;

import by.epam.t7.exception.ConnectionPoolException;

/**
 * This class is a cache of database connections maintained so that the
 * connections can be reused when future requests to the database are required.
 */
public class ConnectionPool {

	private final static Logger logger = Logger.getRootLogger();

	/**
	 * This class is a singleton, it's the only instance of connection pool,
	 * available via getInstance() method.
	 */
	private volatile static ConnectionPool instance = null;

	private final String driverName;
	private final String url;
	private final String user;
	private final String password;
	private final int poolSize;

	/**
	 * Thread save queue of unused connections.
	 */
	private BlockingQueue<Connection> connectionFreeQueue;
	/**
	 * Thread save queue of used connections.
	 */
	private BlockingQueue<Connection> connectionBusyQueue;

	private ConnectionPool() {
		DbResourceManager dbResourceManager = DbResourceManager.getInstance();
		this.driverName = dbResourceManager.getValue(DbParametr.DRIVER);
		this.url = dbResourceManager.getValue(DbParametr.URL);
		this.user = dbResourceManager.getValue(DbParametr.USER);
		this.password = dbResourceManager.getValue(DbParametr.PASSWORD);
		this.poolSize = Integer.parseInt(dbResourceManager.getValue(DbParametr.POOL_SIZE));
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null) {
					instance = new ConnectionPool();
				}
			}
		}
		return instance;
	}

	/**
	 * Returns connection back to unused(available) connections of connection
	 * pool.
	 */
	public void returnConnection(Connection connection) {
		connectionFreeQueue.add(connection);
		connectionBusyQueue.remove(connection);
	}

	/**
	 * Initializes available connections to the database.
	 */
	public void initConnectionPool() {
		try {
			Class.forName(driverName);
			connectionBusyQueue = new ArrayBlockingQueue<>(poolSize);
			connectionFreeQueue = new ArrayBlockingQueue<>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				Connection connection = (Connection) DriverManager.getConnection(url, user, password);
				PooledConnection pooledConnection = new PooledConnection(connection);
				connectionFreeQueue.add(pooledConnection);
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e.getMessage());
		}

	}

	/**
	 * Destroys the connection pool
	 */
	public void stop() {
		try {
			clearConnectionQueue();
		} catch (ConnectionPoolException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Destroys queues of connections.
	 * 
	 * @throws ConnectionPoolException
	 */
	public void clearConnectionQueue() throws ConnectionPoolException {

		try {
			closeConnectionsQueue(connectionBusyQueue);
			closeConnectionsQueue(connectionFreeQueue);
		} catch (SQLException e) {
			throw new ConnectionPoolException(e);
		}
	}

	/**
	 * Destroys queue of connections.
	 * 
	 * @param queue
	 * @throws SQLException
	 */
	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			((PooledConnection) connection).reallyClose();
		}

	}

	/**
	 * Gives database connection, if one is available.
	 * 
	 * @return
	 * @throws ConnectionPoolException
	 */
	public synchronized Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;

		try {
			connection = connectionFreeQueue.take();
			connectionBusyQueue.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException(e);
		}

		return connection;
	}

	public class PooledConnection implements Connection {

		private Connection connection;

		public PooledConnection(Connection connection) throws SQLException {
			this.connection = connection;
			this.connection.setAutoCommit(true);
		}

		public void reallyClose() throws SQLException {
			connection.close();
		}

		public void clearWarnings() throws SQLException {
			connection.clearWarnings();
		}

		/**
		 * Does not close connection, but returns it to available connections of
		 * the connection pool.
		 */
		public void close() throws SQLException {
			if (connection.isClosed()) {
				throw new SQLException("Attempting to close closed connection.");
			}
			if (connection.isReadOnly()) {
				connection.setReadOnly(false);
			}
			if (!connectionFreeQueue.offer(this)) {
				throw new SQLException("Error allocation connection in the pool");
			}
			if (!connectionBusyQueue.remove(this)) {
				throw new SQLException("Error of deletion connection from the given pool");
			}
		}

		@Override
		public boolean isWrapperFor(Class<?> arg0) throws SQLException {
			return connection.isWrapperFor(arg0);
		}

		@Override
		public <T> T unwrap(Class<T> arg0) throws SQLException {
			return connection.unwrap(arg0);
		}

		@Override
		public void abort(Executor arg0) throws SQLException {
			connection.abort(arg0);

		}

		@Override
		public void commit() throws SQLException {
			connection.commit();
		}

		@Override
		public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
			return connection.createArrayOf(arg0, arg1);
		}

		@Override
		public Blob createBlob() throws SQLException {
			return connection.createBlob();
		}

		@Override
		public Clob createClob() throws SQLException {
			return connection.createClob();
		}

		@Override
		public NClob createNClob() throws SQLException {
			return connection.createNClob();
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			return connection.createSQLXML();
		}

		@Override
		public Statement createStatement() throws SQLException {
			return connection.createStatement();
		}

		@Override
		public Statement createStatement(int arg0, int arg1) throws SQLException {
			return connection.createStatement(arg0, arg1);
		}

		@Override
		public Statement createStatement(int arg0, int arg1, int arg2) throws SQLException {
			return connection.createStatement(arg0, arg1, arg2);
		}

		@Override
		public Struct createStruct(String arg0, Object[] arg1) throws SQLException {
			return connection.createStruct(arg0, arg1);
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			return connection.getAutoCommit();
		}

		@Override
		public String getCatalog() throws SQLException {
			return connection.getCatalog();
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			return connection.getClientInfo();
		}

		@Override
		public String getClientInfo(String arg0) throws SQLException {
			return connection.getClientInfo(arg0);
		}

		@Override
		public int getHoldability() throws SQLException {
			return connection.getHoldability();
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			return connection.getMetaData();
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			return connection.getNetworkTimeout();
		}

		@Override
		public String getSchema() throws SQLException {
			return connection.getSchema();
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			return connection.getTransactionIsolation();
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			return connection.getTypeMap();
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			return connection.getWarnings();
		}

		@Override
		public boolean isClosed() throws SQLException {
			return connection.isClosed();
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			return connection.isReadOnly();
		}

		@Override
		public boolean isValid(int arg0) throws SQLException {
			return connection.isValid(arg0);
		}

		@Override
		public String nativeSQL(String arg0) throws SQLException {
			return connection.nativeSQL(arg0);
		}

		@Override
		public CallableStatement prepareCall(String arg0) throws SQLException {
			return connection.prepareCall(arg0);
		}

		@Override
		public CallableStatement prepareCall(String arg0, int arg1, int arg2) throws SQLException {
			return connection.prepareCall(arg0, arg1, arg2);
		}

		@Override
		public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException {
			return connection.prepareCall(arg0, arg1, arg2, arg3);
		}

		@Override
		public PreparedStatement prepareStatement(String arg0) throws SQLException {
			return connection.prepareStatement(arg0);
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, int arg1) throws SQLException {
			return connection.prepareStatement(arg0, arg1);
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, int[] arg1) throws SQLException {
			return connection.prepareStatement(arg0, arg1);
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, String[] arg1) throws SQLException {
			return connection.prepareStatement(arg0, arg1);
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, int arg1, int arg2) throws SQLException {
			return connection.prepareStatement(arg0, arg1, arg2);
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3) throws SQLException {
			return connection.prepareStatement(arg0, arg1, arg2, arg3);
		}

		@Override
		public void releaseSavepoint(Savepoint arg0) throws SQLException {
			connection.releaseSavepoint(arg0);

		}

		@Override
		public void rollback() throws SQLException {
			connection.rollback();

		}

		@Override
		public void rollback(Savepoint arg0) throws SQLException {
			connection.releaseSavepoint(arg0);

		}

		@Override
		public void setAutoCommit(boolean arg0) throws SQLException {
			connection.setAutoCommit(arg0);

		}

		@Override
		public void setCatalog(String arg0) throws SQLException {
			connection.setCatalog(arg0);

		}

		@Override
		public void setClientInfo(Properties arg0) throws SQLClientInfoException {
			connection.setClientInfo(arg0);

		}

		@Override
		public void setClientInfo(String arg0, String arg1) throws SQLClientInfoException {
			connection.setClientInfo(arg0, arg1);

		}

		@Override
		public void setHoldability(int arg0) throws SQLException {
			connection.setHoldability(arg0);

		}

		@Override
		public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
			connection.setNetworkTimeout(arg0, arg1);

		}

		@Override
		public void setReadOnly(boolean arg0) throws SQLException {
			connection.setReadOnly(arg0);

		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			return connection.setSavepoint();
		}

		@Override
		public Savepoint setSavepoint(String arg0) throws SQLException {
			return connection.setSavepoint(arg0);
		}

		@Override
		public void setSchema(String arg0) throws SQLException {
			connection.setSchema(arg0);

		}

		@Override
		public void setTransactionIsolation(int arg0) throws SQLException {
			connection.setTransactionIsolation(arg0);
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
			connection.setTypeMap(arg0);

		}

	}

}
