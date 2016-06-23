package by.epam.t7.dao;

import java.util.List;

import by.epam.t7.entity.Faculty;
import by.epam.t7.entity.Student;
import by.epam.t7.exception.DaoException;

/**
 * This interface is the basic interface to FacultyDaoImpl.It has methods for a
 * work with Faculty.
 */
public interface FacultyDao extends Dao {

	/**
	 * Gets all faculties thar stores in database.
	 * 
	 * @param index
	 *            of the first row
	 * @param length
	 *            of the count of rows
	 * @return the list of faculties
	 * @throws DaoException
	 */
	public List<Faculty> getAll(int index, int length) throws DaoException;

	/**
	 * Registrate the student on the faculty.Returns <tt>true</tt> if this
	 * operation finished succesfully.
	 * 
	 * @param id
	 *            of the faculty
	 * @param id2
	 *            of the user
	 * @return <tt>false</tt> if this operation finished wrong.
	 * @throws DaoException
	 */
	public boolean signUp(int id, int id2) throws DaoException;

	/**
	 * Gets all faculties of the concrete user.
	 * 
	 * @param user_id
	 *            whoes faculties we want to get
	 * @return the list of faculties of the user
	 * @throws DaoException
	 */
	public List<Faculty> getAllUsersFaculties(int user_id) throws DaoException;

	/**
	 * Create this faculty in the database.Returns true if operation finished
	 * with success
	 * 
	 * @param faculty
	 *            Faculty that must be created
	 * @return false if this operation finished wrong
	 * @throws DaoException
	 */
	public boolean signUpFaculty(Faculty faculty) throws DaoException;

	/**
	 * Returns list of users which are rigestered on this faculty
	 * 
	 * @param id
	 *            Id of the faculty
	 * @return list of users
	 * @throws DaoException
	 */
	public List<Student> getFacultyUsers(int id) throws DaoException;

	/**
	 * Delete this faculty in the database.Returns true if operation finished
	 * with success
	 * 
	 * @param id
	 *            Id of the faculty
	 * @return <tt>false</tt> if this operation finished wrong.
	 * @throws DaoException
	 */
	public boolean delete(int id) throws DaoException;

	/**
	 * Update this faculty in the database.Returns true if operation finished
	 * with success
	 * 
	 * @param faculty
	 *            Faculty that must be updated
	 * @return <tt>false</tt> if this operation finished wrong.
	 * @throws DaoException
	 */
	public boolean update(Faculty faculty) throws DaoException;

	/**
	 * Deletes student from the faculty.
	 * 
	 * @param idFaculty
	 * @param idUser
	 * @return true if the re
	 * @throws DaoException
	 */
	public boolean cancelSignUp(int idFaculty, int idUser) throws DaoException;

	/**
	 * Gets description of the faculty.
	 * 
	 * @param id
	 *            of the faculty
	 * @return object of faculty
	 */
	public Faculty getFacultyInfo(int id) throws DaoException;
}
