package by.epam.t7.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.t7.dao.StudentDao;
import by.epam.t7.entity.Student;
import by.epam.t7.entity.User;
import by.epam.t7.exception.ConnectionPoolException;
import by.epam.t7.exception.DaoException;

public class StudentDaoImpl implements StudentDao {

	private static final Logger logger = Logger.getLogger(StudentDaoImpl.class);

	private volatile static StudentDaoImpl instance = null;

	private final static String SQL_SIGNUP_CLIENT = "INSERT INTO facultyfinal.user(u_name,u_surname,u_mail) VALUES(?,?,?)";
	private final static String SQL_SIGNUP_CLIENT_CREDENTIALS = "INSERT INTO facultyfinal.credentials(login,password) VALUES(?,?)";
	private final static String SQL_SIGNUP_CLIENT_ROLE_STUDENT = "INSERT INTO facultyfinal.role(role_id) VALUES (3)";
	private final static String SQL_IS_CLIENT_EXISTS = "SELECT u_id,u_name,u_surname,u_mail FROM facultyfinal.user "
			+ "INNER JOIN facultyfinal.credentials ON user.u_id = credentials.user_id "
			+ "WHERE u_id IN(select user_id from facultyfinal.credentials where binary login = (?));";
	private final static String SQL_FIND_ALL_CLIENTS = "SELECT u_id,u_name,u_surname,u_mail FROM facultyfinal.user;";
	private final static String SQL_FIND_CLIENT_BY_LOGIN_AND_PASSWORD = "SELECT u_id,u_name,u_surname,u_mail FROM "
			+ "facultyfinal.user INNER JOIN facultyfinal.credentials ON user.u_id = credentials.user_id "
			+ "WHERE u_id IN(select user_id from facultyfinal.credentials where binary login = (?) AND binary password = (?))";
	private final static String SQL_FIND_CLIENT_ROLE_BY_ID = "SELECT role FROM facultyfinal.concrete_role INNER "
			+ "JOIN facultyfinal.role ON facultyfinal.concrete_role.role_id = facultyfinal.role.role_id "
			+ " WHERE facultyfinal.role.user_id = ?;";

	public static StudentDaoImpl getInstance() {
		if (instance == null) {
			synchronized (StudentDaoImpl.class) {
				if (instance == null) {
					instance = new StudentDaoImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public List<Student> getAll() throws DaoException {
		logger.info("StudentDaoImpl.getAll()");
		PreparedStatement ps = null;
		List<Student> list;
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = takeConnection();
			list = new ArrayList<>();
			ps = connection.prepareStatement(SQL_FIND_ALL_CLIENTS);
			rs = ps.executeQuery();
			while (rs.next()) {
				Student user = new Student();
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
	public Student findClientByLoginAndPassword(String login, String password) throws DaoException {
		logger.info("StudentDaoImpl.findClientByLoginAndPassword(" + login + " , " + password + " )");
		Student student = null;
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

				student = new Student();
				student.setId((rs.getInt(1)));
				student.setName(rs.getString(2));
				student.setSurname(rs.getString(3));
				student.setMail(rs.getString(4));
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
		return student;
	}

	@Override
	public boolean isExist(String login) throws DaoException {
		logger.info("StudentDaoImpl.isExist(" + login + ")");

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
	public boolean signUp(User stu) throws DaoException {
		logger.info("StudentDaoImpl.signUp(" + stu + ")");

		Student student = (Student) stu;
		boolean flag = false;
		Connection connection = null;
		PreparedStatement prStatement = null;

		if (isExist(student.getLogin()) || student == null) {
			return flag;
		}

		try {

			connection = takeConnection();

			prStatement = connection.prepareStatement(SQL_SIGNUP_CLIENT);
			prStatement.setString(1, student.getName());
			prStatement.setString(2, student.getSurname());
			prStatement.setString(3, student.getMail());

			if (prStatement.executeUpdate() > 0) {
				flag = true;
			}

			prStatement.close();
			
			prStatement = connection.prepareStatement(SQL_SIGNUP_CLIENT_CREDENTIALS);
			prStatement.setString(1, student.getLogin());
			prStatement.setString(2, student.getPassword());

			if (prStatement.executeUpdate() > 0) {
				flag = true;
			}

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
	
	@Override
	public String checkRoleById(String id) throws DaoException {
		logger.info("StudentDaoImpl.checkRoleById(" + id + ")");

		String role = null;

		Connection connection = null;
		PreparedStatement prStatement = null;
		ResultSet rs = null;

		try {

			connection = takeConnection();

			prStatement = connection.prepareStatement(SQL_FIND_CLIENT_ROLE_BY_ID);
			prStatement.setString(1, id);

			rs = prStatement.executeQuery();
			if (rs.next()) {
				role = rs.getString(1);
			}

		} catch (ConnectionPoolException | SQLException e) {
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
		return role;
	}
}
