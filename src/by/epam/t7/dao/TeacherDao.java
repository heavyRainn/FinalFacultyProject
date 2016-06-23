package by.epam.t7.dao;

import java.util.List;

import by.epam.t7.entity.Teacher;
import by.epam.t7.entity.User;
import by.epam.t7.exception.DaoException;

/**
 * This interface is the basic interface to TeacherDaoImpl.It has methods for a
 * work with Teachers.
 */
public interface TeacherDao extends Dao {

	/**
	 * Gets the list of the all teacherss that are registered.
	 * 
	 * @return list of teachers
	 * @throws DaoException
	 */
	public List<Teacher> getAll() throws DaoException;

	/**
	 * Registrate the teacher on the faculty.Returns true if this operation
	 * finished succesfully.
	 * 
	 * @param id
	 *            of the faculty
	 * @param id2
	 *            of the user
	 * @return <tt>false</tt> if this operation finished wrong.
	 * @throws DaoException
	 */
	public Teacher findClientByLoginAndPassword(String login, String password) throws DaoException;

	/**
	 * Checks is the teacher with the same login in database.
	 * 
	 * @param login
	 *            of the teacher
	 * @return true if exists
	 * @throws DaoException
	 */
	public boolean isExist(String login) throws DaoException;

	/**
	 * Create this user in the database.Returns true if operation finished with
	 * success
	 * 
	 * @param stu
	 *            user that must be created
	 * @return false if this operation finished wrong
	 * @throws DaoException
	 */
	public boolean signUp(User teach) throws DaoException;
}
