package by.epam.t7.service;

import java.util.List;
import by.epam.t7.dao.FacultyDao;
import by.epam.t7.dao.factory.MysqlDaoFactory;
import by.epam.t7.entity.Faculty;
import by.epam.t7.entity.Student;
import by.epam.t7.entity.User;
import by.epam.t7.exception.DaoException;
import by.epam.t7.exception.ServiceException;

public class FacultyService {

	private volatile static FacultyService instance = null;

	public static FacultyService getInstance() {
		if (instance == null) {
			synchronized (FacultyService.class) {
				if (instance == null) {
					instance = new FacultyService();
				}
			}
		}
		return instance;
	}

	public List<Faculty> getAll(int index, int length) throws ServiceException {
		List<Faculty> faculties = null;
		FacultyDao facultyDao;
		try {
			facultyDao = (FacultyDao) MysqlDaoFactory.getInstance().getDao(Faculty.class);
			faculties = facultyDao.getAll(index, length);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return faculties;

	}

	public boolean signUp(int id, User user) throws ServiceException {
		boolean result = false;
		FacultyDao facultyDao;
		try {
			facultyDao = (FacultyDao) MysqlDaoFactory.getInstance().getDao(Faculty.class);
			result = facultyDao.signUp(id, user.getId());
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public List<Faculty> getAllUsersFaculties(int user_id) throws ServiceException {
		List<Faculty> faculties;
		FacultyDao facultyDao;
		try {
			facultyDao = (FacultyDao) MysqlDaoFactory.getInstance().getDao(Faculty.class);
			faculties = facultyDao.getAllUsersFaculties(user_id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return faculties;
	}

	public boolean signUpFaculty(Faculty faculty) throws ServiceException {
		boolean result = false;
		FacultyDao facultyDao;
		try {
			facultyDao = (FacultyDao) MysqlDaoFactory.getInstance().getDao(Faculty.class);
			result = facultyDao.signUpFaculty(faculty);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public List<Student> getFacultyUsers(int id) throws ServiceException {
		List<Student> users;
		FacultyDao facultyDao;
		try {
			facultyDao = (FacultyDao) MysqlDaoFactory.getInstance().getDao(Faculty.class);
			users = facultyDao.getFacultyUsers(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return users;
	}

	public boolean delete(int id) throws ServiceException {
		boolean result = false;
		FacultyDao facultyDao;
		try {
			facultyDao = (FacultyDao) MysqlDaoFactory.getInstance().getDao(Faculty.class);
			result = facultyDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

		return result;
	}

	public boolean update(Faculty faculty) throws ServiceException {
		boolean result = false;
		FacultyDao facultyDao;
		try {
			facultyDao = (FacultyDao) MysqlDaoFactory.getInstance().getDao(Faculty.class);
			result = facultyDao.update(faculty);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public boolean cancelSignUp(int idFaculty, int idUser) throws ServiceException {
		boolean result = false;
		FacultyDao facultyDao;
		try {
			facultyDao = (FacultyDao) MysqlDaoFactory.getInstance().getDao(Faculty.class);
			result = facultyDao.cancelSignUp(idFaculty,idUser);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public Faculty getFacultyInfo(int id) throws ServiceException {
		FacultyDao facultyDao;
		Faculty faculty;
		try {
			facultyDao = (FacultyDao) MysqlDaoFactory.getInstance().getDao(Faculty.class);
			faculty = facultyDao.getFacultyInfo(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return faculty;
	}
}
