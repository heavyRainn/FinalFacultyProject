package by.epam.t7.service;

import by.epam.t7.dao.StudentDao;
import by.epam.t7.dao.TeacherDao;
import by.epam.t7.dao.factory.MysqlDaoFactory;
import by.epam.t7.entity.Student;
import by.epam.t7.entity.Teacher;
import by.epam.t7.entity.User;
import by.epam.t7.exception.DaoException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.util.Validator;

public final class UserService {

	private volatile static UserService instance = null;

	public static UserService getInstance() {
		if (instance == null) {
			synchronized (UserService.class) {
				if (instance == null) {
					instance = new UserService();
				}
			}
		}
		return instance;
	}

	public User checkLogin(String login, String password) throws ServiceException {
		StudentDao userDao;
		User user = null;
		if (!Validator.loginValidator(login, password)) {
			return null;
		}
		try {
			userDao = (StudentDao) MysqlDaoFactory.getInstance().getDao(Student.class);
			user = userDao.findClientByLoginAndPassword(login.trim(), password.trim());
			if (!Validator.loginValidator(login, password)) {
				return null;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	public boolean signUp(Student user) throws ServiceException {
		StudentDao userDao;
		boolean isSuccess;
		try {
			if (!Validator.singUpValidator(user.getLogin(), user.getPassword(), user.getMail(), user.getName(),
					user.getSurname())) {
				return false;
			}
			userDao = (StudentDao) MysqlDaoFactory.getInstance().getDao(Student.class);
			isSuccess = userDao.signUp(user);
			if (isSuccess) {
				return true;
			} else {
				return false;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public boolean signUp(Teacher user) throws ServiceException {
		TeacherDao userDao;
		boolean isSuccess;
		try {
			if (!Validator.singUpValidator(user.getLogin(), user.getPassword(), user.getMail(), user.getName(),
					user.getSurname())) {
				return false;
			}
			userDao = (TeacherDao) MysqlDaoFactory.getInstance().getDao(Teacher.class);
			isSuccess = userDao.signUp(user);
			if (isSuccess) {
				return true;
			} else {
				return false;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public String checkRole(int id) throws ServiceException {
		StudentDao userDao;
		String role = null;
		try {
			userDao = (StudentDao) MysqlDaoFactory.getInstance().getDao(Student.class);
			role = userDao.checkRoleById(String.valueOf(id));
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return role;
	}
}
