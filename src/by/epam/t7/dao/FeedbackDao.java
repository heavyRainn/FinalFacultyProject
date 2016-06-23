package by.epam.t7.dao;

import java.util.List;

import by.epam.t7.entity.Feedback;
import by.epam.t7.exception.DaoException;

/**
 * This interface is the basic interface to FeedbackDaoImpl.It has methods for a
 * work with Feedback.
 */
public interface FeedbackDao extends Dao {

	/**
	 * Creates feedback in the database.Returns <tt>true</tt> if this operation
	 * finished succesfully.
	 * 
	 * @param feedback
	 *            that must be writed into database.
	 * @return <tt>false</tt> if this operation finished wrong.
	 * @throws DaoException
	 */
	public boolean signUpFeedback(Feedback feedback) throws DaoException;

	/**
	 * Gets the list of feedbacks of the concrete user.
	 * 
	 * @param id
	 *            of the user
	 * @return list of feedbacks
	 * @throws DaoException
	 */
	public List<Feedback> getAllUsersFeedbacks(int id) throws DaoException;

	/**
	 * Gets the list of feedbacks of the concrete faculty.
	 * 
	 * @param id
	 *            of the faculty
	 * @return list of feedbacks
	 * @throws DaoException
	 */
	public List<Feedback> getAllFacultyFeedbacks(int id) throws DaoException;

	/**
	 * Deletes feedback from the database.Returns <tt>true</tt> if this
	 * operation finished succesfully.
	 * 
	 * @param id
	 *            of the feedback
	 * @return <tt>false</tt> if this operation finished wrong
	 * @throws DaoException
	 */
	public boolean delete(int id) throws DaoException;
}
