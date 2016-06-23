package by.epam.t7.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.t7.dao.TeacherDao;
import by.epam.t7.entity.Teacher;
import by.epam.t7.entity.User;
import by.epam.t7.exception.ConnectionPoolException;
import by.epam.t7.exception.DaoException;

public class TeacherDaoImpl implements TeacherDao {

	private static final Logger logger = Logger.getLogger(TeacherDaoImpl.class);

	private volatile static TeacherDaoImpl instance = null;

	private final static String SQL_SIGNUP_CLIENT = "INSERT INTO facultyfinal.user(u_name,u_surname,u_mail) VALUES(?,?,?)";
	private final static String SQL_SIGNUP_CLIENT_CREDENTIALS = "INSERT INTO facultyfinal.credentials(login,password) VALUES(?,?)";
	private final static String SQL_SIGNUP_CLIENT_ROLE_STUDENT = "INSERT INTO facultyfinal.role(role_id) VALUES (2)";
	private final static String SQL_IS_CLIENT_EXISTS = "SELECT u_id,u_name,u_surname,u_mail FROM facultyfinal.user"
			+ " INNER JOIN facultyfinal.credentials ON user.u_id = credentials.user_id WHERE u_id "
			+ "IN(select user_id from facultyfinal.credentials where login = (?));";
	private final static String SQL_FIND_ALL_CLIENTS = "SELECT u_id,u_name,u_surname,u_mail FROM facultyfinal.user;";
	private final static String SQL_FIND_CLIENT_BY_LOGIN_AND_PASSWORD = "SELECT u_id,u_name,u_surname,u_mail FROM facultyfinal.user"
			+ " INNER JOIN facultyfinal.credentials ON user.u_id = credentials.user_id "
			+ "WHERE u_id IN(select user_id from facultyfinal.credentials where login = (?) AND password = (?))";

	public static TeacherDaoImpl getInstance() {
		if (instance == null) {
			synchronized (TeacherDaoImpl.class) {
				if (instance == null) {
					instance = new TeacherDaoImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public List<Teacher> getAll() throws DaoException {
		logger.info("TeacherDaoImpl.getAll()");

		PreparedStatement ps = null;
		List<Teacher> list;
		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = takeConnection();
			list = new ArrayList<>();

			ps = connection.prepareStatement(SQL_FIND_ALL_CLIENTS);

			rs = ps.executeQuery();
			while (rs.next()) {
				Teacher user = new Teacher();
				user.setId((rs.getInt(1)));
				user.setLogin(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setMail(rs.getString(4));
				user.setName(rs.getString(5));
				user.setSurname(rs.getString(6));

				list.add(user);
				user = null;
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		} finally {
			try {
				rs.close();
				ps.close();
				returnConnection(connection);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return list;
	}

	@Override
	public Teacher findClientByLoginAndPassword(String login, String password) throws DaoException {
		logger.info("TeacherDaoImpl.findClientByLoginAndPassword(" + login + " , " + password + ")");

		Teacher teacher = null;
		Connection connection = null;
		PreparedStatement prStatement = null;

		ResultSet rs = null;

		try {

			connection = takeConnection();

			prStatement = connection.prepareStatement(SQL_FIND_CLIENT_BY_LOGIN_AND_PASSWORD);
			prStatement.setString(1, login);
			prStatement.setString(2, password);

			rs = prStatement.executeQuery();
			if (rs.next()) {

				teacher = new Teacher();
				teacher.setId((rs.getInt(1)));
				teacher.setName(rs.getString(2));
				teacher.setSurname(rs.getString(3));
				teacher.setMail(rs.getString(4));
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		} finally {
			try {
				rs.close();
				prStatement.close();
				returnConnection(connection);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return teacher;
	}

	@Override
	public boolean isExist(String login) throws DaoException {
		logger.info("TeacherDaoImpl.isExist(" + login + ")");

		boolean result = false;
		Connection connection = null;
		PreparedStatement prStatement = null;
		ResultSet resultSet = null;

		try {
			connection = takeConnection();

			prStatement = connection.prepareStatement(SQL_IS_CLIENT_EXISTS);
			prStatement.setString(1, login);

			resultSet = prStatement.executeQuery();
			result = resultSet.first();

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		} finally {
			try {
				resultSet.close();
				prStatement.close();
				returnConnection(connection);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return result;
	}

	@Override
	public boolean signUp(User teach) throws DaoException {
		logger.info("TeacherDaoImpl.signUp(" + teach + ")");

		Teacher teacher = (Teacher) teach;
		boolean flag = false;
		Connection connection = null;
		PreparedStatement prStatement = null;

		if (isExist(teacher.getLogin()) || teacher == null) {
			return flag;
		}

		try {
			connection = takeConnection();

			prStatement = connection.prepareStatement(SQL_SIGNUP_CLIENT);
			prStatement.setString(1, teacher.getName());
			prStatement.setString(2, teacher.getSurname());
			prStatement.setString(3, teacher.getMail());

			if (prStatement.executeUpdate() > 0) {
				flag = true;
			}

			prStatement.close();

			prStatement = connection.prepareStatement(SQL_SIGNUP_CLIENT_CREDENTIALS);
			prStatement.setString(1, teacher.getLogin());
			prStatement.setString(2, teacher.getPassword());

			if (prStatement.executeUpdate() > 0) {
				flag = true;
			}

			prStatement.close();

			prStatement = connection.prepareStatement(SQL_SIGNUP_CLIENT_ROLE_STUDENT);

			if (prStatement.executeUpdate() > 0) {
				flag = true;
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		} finally {
			try {
				prStatement.close();
				returnConnection(connection);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return flag;
	}
}
