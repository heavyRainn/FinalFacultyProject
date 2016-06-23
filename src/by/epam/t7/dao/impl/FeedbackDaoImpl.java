package by.epam.t7.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.t7.dao.FeedbackDao;
import by.epam.t7.entity.Feedback;
import by.epam.t7.exception.ConnectionPoolException;
import by.epam.t7.exception.DaoException;

public class FeedbackDaoImpl implements FeedbackDao {

	private static final Logger logger = Logger.getLogger(FeedbackDaoImpl.class);

	private volatile static FeedbackDaoImpl instance = null;

	private final static String SQL_FIND_ALL_FEEDBACKS = "SELECT feedback_id,faculty_id,user_id,mark,"
			+ "description FROM facultyfinal.feedback WHERE user_id = (?)";
	private final static String SQL_SIGNUP_FEEDBACK = "INSERT INTO facultyfinal.feedback(user_id,mark,"
			+ "description,faculty_id,feedback_id) VALUES(?,?,?,?,null)";
	private final static String SQL_FIND_ALL_FACULTY_FEEDBACKS = "SELECT feedback_id,faculty_id,user_id,mark,"
			+ "description FROM facultyfinal.feedback WHERE faculty_id = (?)";
	private final static String SQL_DELETE_FEEDBACK = "DELETE FROM facultyfinal.feedback"
			+ " WHERE facultyfinal.feedback.feedback_id = (?)";

	public static FeedbackDaoImpl getInstance() {
		if (instance == null) {
			synchronized (FeedbackDaoImpl.class) {
				if (instance == null) {
					instance = new FeedbackDaoImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public boolean signUpFeedback(Feedback feedback) throws DaoException {
		logger.info("FacultyDaoImpl.signUpFeedback(" + feedback + ")");

		boolean result = false;

		Connection connection = null;
		PreparedStatement prStatement = null;

		try {
			connection = takeConnection();

			prStatement = connection.prepareStatement(SQL_SIGNUP_FEEDBACK);
			prStatement.setInt(1, feedback.getStudentId());
			prStatement.setInt(2, feedback.getMark());
			prStatement.setString(3, feedback.getDescription());
			prStatement.setInt(4, feedback.getTrainingId());

			if (prStatement.executeUpdate() > 0) {
				result = true;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				prStatement.close();
				returnConnection(connection);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return result;

	}

	@Override
	public List<Feedback> getAllUsersFeedbacks(int id) throws DaoException {
		logger.info("FacultyDaoImpl.getAllUsersFeedbacks(" + id + ")");

		List<Feedback> feedbacks = null;
		PreparedStatement ps = null;
		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = takeConnection();

			feedbacks = new ArrayList<>();
			ps = connection.prepareStatement(SQL_FIND_ALL_FEEDBACKS);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				Feedback feedback = new Feedback();

				feedback.setId(rs.getInt(1));
				feedback.setTrainingId(rs.getInt(2));
				feedback.setStudentId(rs.getInt(3));
				feedback.setMark(rs.getInt(4));
				feedback.setDescription(rs.getString(5));

				feedbacks.add(feedback);
				feedback = null;
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
		return feedbacks;
	}

	@Override
	public List<Feedback> getAllFacultyFeedbacks(int id) throws DaoException {
		logger.info("FacultyDaoImpl.getAllFacultyFeedbacks(" + id + ")");

		List<Feedback> feedbacks = null;
		PreparedStatement ps = null;
		Connection connection = null;
		ResultSet rs = null;

		try {
			connection = takeConnection();
			feedbacks = new ArrayList<>();

			ps = connection.prepareStatement(SQL_FIND_ALL_FACULTY_FEEDBACKS);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				Feedback feedback = new Feedback();

				feedback.setId(rs.getInt(1));
				feedback.setTrainingId(rs.getInt(2));
				feedback.setStudentId(rs.getInt(3));
				feedback.setMark(rs.getInt(4));
				feedback.setDescription(rs.getString(5));

				feedbacks.add(feedback);
				feedback = null;
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
		return feedbacks;
	}

	@Override
	public boolean delete(int id) throws DaoException {
		logger.info("FacultyDaoImpl.delete(" + id + ")");

		boolean result = false;

		Connection connection = null;
		PreparedStatement prStatement = null;

		try {
			connection = takeConnection();

			prStatement = connection.prepareStatement(SQL_DELETE_FEEDBACK);
			prStatement.setInt(1, id);

			if (prStatement.executeUpdate() > 0) {
				result = true;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				prStatement.close();
				returnConnection(connection);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return result;
	}
}
