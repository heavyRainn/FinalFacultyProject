package by.epam.t7.dao;

import java.util.List;

import by.epam.t7.entity.Student;
import by.epam.t7.entity.User;
import by.epam.t7.exception.DaoException;

/**
 * This interface is the basic interface to StudentDaoImpl.It has methods for a
 * work with Students.
 */
public interface StudentDao extends Dao {

	/**
	 * Gets the list of the all students that are registered.
	 * 
	 * @return list of students
	 * @throws DaoException
	 */
	public List<Student> getAll() throws DaoException;

	/**
	 * Searching the user with the same login and password.If it will find him
	 * then method returns this Student
	 * 
	 * @param login
	 *            entered in form
	 * @param password
	 *            entered in form
	 * @return student witch was found
	 * @throws DaoException
	 */
	public Student findClientByLoginAndPassword(String login, String password) throws DaoException;

	/**
	 * Checks is the student with the same login in database.
	 * 
	 * @param login
	 *            of the student
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
	public boolean signUp(User stu) throws DaoException;

	/**
	 * Checks the role of the user
	 * 
	 * @param id
	 *            of the user
	 * @return name of the role
	 * @throws DaoException
	 */
	public String checkRoleById(String id) throws DaoException;
}
