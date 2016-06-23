package by.epam.t7.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.t7.dao.FacultyDao;
import by.epam.t7.entity.Faculty;
import by.epam.t7.entity.Student;
import by.epam.t7.exception.ConnectionPoolException;
import by.epam.t7.exception.DaoException;

public class FacultyDaoImpl implements FacultyDao {

	private static final Logger logger = Logger.getLogger(FacultyDaoImpl.class);

	private volatile static FacultyDaoImpl instance = null;

	private static final String DURRING = "during";
	private static final String FINISHED = "finished";
	private static final String CONFIDENTIAL = "confidential";

	private static final String ID = "faculty_id";
	private static final String NAME = "name";
	private static final String S_DATE = "s_date";
	private static final String E_DATE = "e_date";
	private static final String TEACHER_ID = "teacher_id";
	private static final String FACULTY_ID = "faculty_id";

	private final static String SQL_GET_ALL_FACULTIES = "SELECT faculty_id,name,s_date,e_date,teacher_id"
			+ " FROM facultyfinal.faculty LIMIT ?,?;";
	private final static String SQL_REGISTER_AT_FACULTY = "INSERT INTO `facultyfinal`.`faculty_members`"
			+ " (`faculty_id`, `user_id`,ind) VALUES (?, ?, null);";
	private final static String SQL_GET_ALL_USERS_FACULTIES = "SELECT distinct facultyfinal.faculty.faculty_id,name,s_date,e_date,teacher_id "
			+ "FROM facultyfinal.faculty INNER JOIN facultyfinal.faculty_members"
			+ " ON facultyfinal.faculty.faculty_id = facultyfinal.faculty_members.faculty_id"
			+ " WHERE facultyfinal.faculty_members.user_id = ?;";
	private final static String SQL_SIGNUP_FACULTY = "INSERT INTO facultyfinal.faculty(faculty_id,"
			+ "name,s_date,e_date,teacher_id) VALUES(null,?,?,?,?);";
	private final static String SQL_GET_ID_FACULTY = "SELECT faculty_id FROM facultyfinal.faculty WHERE name = ?;";
	private final static String SQL_INSERT_IN_USERS_TABLE = "INSERT INTO facultyfinal.faculty_members"
			+ "(faculty_id,user_id,ind) VALUES(?,?,null);";
	private final static String SQL_DELETE_FACULTY = "DELETE FROM facultyfinal.faculty WHERE facultyfinal.faculty.faculty_id = (?)";
	private final static String SQL_SHOW_USERS_OF_FACULTY = "SELECT u_id,u_name,u_surname,u_mail FROM facultyfinal.user "
			+ "INNER JOIN facultyfinal.faculty_members ON facultyfinal.user.u_id = facultyfinal.faculty_members.user_id "
			+ "where facultyfinal.faculty_members.faculty_id = (?) AND facultyfinal.faculty_members.user_id IN "
			+ "(SELECT u_id FROM facultyfinal.user INNER JOIN facultyfinal.role ON facultyfinal.user.u_id = facultyfinal.role.user_id "
			+ "WHERE (facultyfinal.role.role_id = 3));";
	private final static String SQL_UPDATE_FACULTY = "UPDATE facultyfinal.faculty SET name=(?),s_date=(?),e_date=(?)"
			+ " WHERE facultyfinal.faculty.faculty_id =(?);";
	private final static String SQL_DELETE_USER_FROM_FACULTY = "DELETE FROM facultyfinal.faculty_members"
			+ " WHERE faculty_id = (?) AND user_id = (?)";
	private final static String SQL_FACULTIY_INFO = "SELECT faculty_id,name,s_date,e_date,teacher_id,link "
			+ "FROM facultyfinal.faculty where faculty_id = (?)";

	public static FacultyDaoImpl getInstance() {
		if (instance == null) {
			synchronized (FacultyDaoImpl.class) {
				if (instance == null) {
					instance = new FacultyDaoImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public List<Faculty> getAll(int index, int length) throws DaoException {
		logger.info("FacultyDaoImpl.getAll(" + index + " , " + length + ")");

		List<Faculty> faculties = null;
		PreparedStatement ps = null;
		Connection connection = null;
		Date date = new Date();
		Date eDate;

		try {
			connection = takeConnection();
			faculties = new ArrayList<>();

			ps = connection.prepareStatement(SQL_GET_ALL_FACULTIES);
			ps.setInt(1, index);
			ps.setInt(2, length);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Faculty faculty = new Faculty();
				faculty.setId((rs.getInt(ID)));
				faculty.setName(rs.getString(NAME));
				faculty.setTeacher(rs.getInt(TEACHER_ID));
				faculty.setStartDate(rs.getDate(S_DATE));

				eDate = rs.getDate(E_DATE);

				faculty.setEndDate(new java.sql.Date(eDate.getTime()));
				faculty.setStatus(date.before(eDate) ? DURRING : FINISHED);

				faculties.add(faculty);
				faculty = null;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				returnConnection(connection);
				ps.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return faculties;
	}

	@Override
	public boolean signUp(int id, int id2) throws DaoException {
		logger.info("FacultyDaoImpl.signUp(" + id + " , " + id2 + ")");

		boolean result = false;
		PreparedStatement ps = null;
		Connection connection = null;

		try {

			connection = takeConnection();

			ps = connection.prepareStatement(SQL_REGISTER_AT_FACULTY);
			ps.setInt(1, id);
			ps.setInt(2, id2);

			if (ps.executeUpdate() > 0) {
				result = true;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				ps.close();
				returnConnection(connection);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return result;
	}

	@Override
	public List<Faculty> getAllUsersFaculties(int userId) throws DaoException {
		logger.info("FacultyDaoImpl.getAllUsersFaculties(" + userId + ")");

		List<Faculty> faculties = null;
		PreparedStatement ps = null;
		Connection connection = null;
		Date date = new Date();
		Date eDate;

		try {
			connection = takeConnection();
			faculties = new ArrayList<>();

			ps = connection.prepareStatement(SQL_GET_ALL_USERS_FACULTIES);
			ps.setLong(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Faculty faculty = new Faculty();
				faculty.setId((rs.getInt(ID)));
				faculty.setName(rs.getString(NAME));
				faculty.setTeacher(rs.getInt(TEACHER_ID));
				faculty.setStartDate(rs.getDate(S_DATE));

				eDate = rs.getDate(E_DATE);

				faculty.setEndDate(new java.sql.Date(eDate.getTime()));
				faculty.setStatus(date.before(eDate) ? DURRING : FINISHED);

				faculties.add(faculty);
				faculty = null;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				returnConnection(connection);
				ps.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return faculties;
	}

	@Override
	public boolean signUpFaculty(Faculty faculty) throws DaoException {
		logger.info("FacultyDaoImpl.signUpFaculty(" + faculty + ")");

		boolean flag = false;
		Connection connection = null;
		PreparedStatement ps = null;
		int facultyId = 0;
		ResultSet rs = null;

		try {

			connection = takeConnection();

			ps = connection.prepareStatement(SQL_SIGNUP_FACULTY);
			ps.setString(1, faculty.getName());
			ps.setDate(2, faculty.getStartDate());
			ps.setDate(3, faculty.getEndDate());
			ps.setInt(4, faculty.getTeacher());

			if (ps.executeUpdate() > 0) {
				flag = true;
			}

			ps.close();

			ps = connection.prepareStatement(SQL_GET_ID_FACULTY);
			ps.setString(1, faculty.getName());

			rs = ps.executeQuery();

			if (rs.next()) {
				facultyId = rs.getInt(FACULTY_ID);
			}

			ps.close();
			rs.close();

			ps = connection.prepareStatement(SQL_INSERT_IN_USERS_TABLE);
			ps.setInt(1, facultyId);
			ps.setInt(2, faculty.getTeacher());

			if (ps.executeUpdate() > 0) {
				flag = true;
			}

		} catch (ConnectionPoolException | SQLException e) {
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
		return flag;
	}

	@Override
	public List<Student> getFacultyUsers(int id) throws DaoException {
		logger.info("FacultyDaoImpl.getFacultyUsers(" + id + ")");

		List<Student> users = null;
		PreparedStatement ps = null;
		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = takeConnection();
			users = new ArrayList<>();

			ps = connection.prepareStatement(SQL_SHOW_USERS_OF_FACULTY);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				Student user = new Student();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSurname(rs.getString(3));
				user.setMail(rs.getString(4));
				user.setLogin(CONFIDENTIAL);
				user.setPassword(CONFIDENTIAL);

				users.add(user);
				user = null;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				ps.close();
				rs.close();
				returnConnection(connection);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return users;
	}

	@Override
	public boolean delete(int id) throws DaoException {
		logger.info("FacultyDaoImpl.delete(" + id + ")");

		boolean result = false;
		PreparedStatement ps = null;
		Connection connection = null;

		try {

			connection = takeConnection();

			ps = connection.prepareStatement(SQL_DELETE_FACULTY);
			ps.setInt(1, id);

			if (ps.executeUpdate() > 0) {
				result = true;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				ps.close();
				returnConnection(connection);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return result;
	}

	@Override
	public boolean update(Faculty faculty) throws DaoException {
		logger.info("FacultyDaoImpl.update(" + faculty + ")");

		boolean result = false;
		PreparedStatement ps = null;
		Connection connection = null;

		try {
			connection = takeConnection();

			ps = connection.prepareStatement(SQL_UPDATE_FACULTY);
			ps.setString(1, faculty.getName());
			ps.setDate(2, faculty.getStartDate());
			ps.setDate(3, faculty.getEndDate());
			ps.setInt(4, faculty.getId());

			if (ps.executeUpdate() > 0) {
				result = true;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				ps.close();
				returnConnection(connection);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return result;
	}

	@Override
	public boolean cancelSignUp(int idFaculty, int idUser) throws DaoException {
		logger.info("FacultyDaoImpl.cancelSignUp(" + idFaculty + " " + idUser + ")");

		boolean result = false;
		PreparedStatement ps = null;
		Connection connection = null;

		try {

			connection = takeConnection();

			ps = connection.prepareStatement(SQL_DELETE_USER_FROM_FACULTY);
			ps.setInt(1, idFaculty);
			ps.setInt(2, idUser);

			if (ps.executeUpdate() > 0) {
				result = true;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				ps.close();
				returnConnection(connection);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return result;
	}

	@Override
	public Faculty getFacultyInfo(int id) throws DaoException {
		Faculty faculty = null;
		PreparedStatement ps = null;
		Connection connection = null;
		Date date = new Date();
		Date eDate;

		try {
			connection = takeConnection();

			ps = connection.prepareStatement(SQL_FACULTIY_INFO);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				faculty = new Faculty();
				faculty.setId((rs.getInt(1)));
				faculty.setName(rs.getString(2));
				faculty.setTeacher(rs.getInt(5));
				faculty.setStartDate(rs.getDate(3));

				eDate = rs.getDate(4);

				faculty.setEndDate(new java.sql.Date(eDate.getTime()));
				faculty.setStatus(date.before(eDate) ? DURRING : FINISHED);
				faculty.setLink(rs.getString(6));
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		}

		return faculty;
	}

}
