package by.epam.t7.dao.factory;

import java.util.HashMap;
import java.util.Map;

import by.epam.t7.dao.Dao;
import by.epam.t7.dao.FacultyDao;
import by.epam.t7.dao.FeedbackDao;
import by.epam.t7.dao.TeacherDao;
import by.epam.t7.dao.impl.FacultyDaoImpl;
import by.epam.t7.dao.impl.FeedbackDaoImpl;
import by.epam.t7.dao.impl.StudentDaoImpl;
import by.epam.t7.dao.impl.TeacherDaoImpl;
import by.epam.t7.entity.Faculty;
import by.epam.t7.entity.Feedback;
import by.epam.t7.entity.Student;
import by.epam.t7.entity.Teacher;
import by.epam.t7.exception.DaoException;

/**
 * Class that implements DaoFactory interface.It must returns concrete dao by
 * the class name.
 */
public class MysqlDaoFactory implements DaoFactory {

	/**
	 * This class is a singleton, it's the only instance of connection pool,
	 * available via getInstance() method.
	 */
	private volatile static MysqlDaoFactory instance = null;

	/**
	 * Collection that contains all daos.
	 */
	private Map<Class<?>, DaoCreator> creators;

	public static MysqlDaoFactory getInstance() {
		if (instance == null) {
			synchronized (MysqlDaoFactory.class) {
				if (instance == null) {
					instance = new MysqlDaoFactory();
				}
			}
		}
		return instance;
	}

	/**
	 * Initialization of the collection of daos.
	 */
	private MysqlDaoFactory() {
		creators = new HashMap<>();

		creators.put(Student.class, new DaoCreator() {
			public StudentDaoImpl create() {
				return StudentDaoImpl.getInstance();
			}
		});
		creators.put(Teacher.class, new DaoCreator() {
			public TeacherDao create() {
				return TeacherDaoImpl.getInstance();
			}
		});
		creators.put(Faculty.class, new DaoCreator() {
			public FacultyDao create() {
				return FacultyDaoImpl.getInstance();
			}
		});
		creators.put(Feedback.class, new DaoCreator() {
			public FeedbackDao create() {
				return FeedbackDaoImpl.getInstance();
			}
		});
	}

	/**
	 * Returns concrete dao by the class name.
	 */
	@Override
	public Dao getDao(Class<?> daoClass) throws DaoException {
		DaoCreator creator = creators.get(daoClass);
		if (creator == null) {
			throw new DaoException("Dao object " + daoClass + " not found");
		}
		return creator.create();
	}

}
