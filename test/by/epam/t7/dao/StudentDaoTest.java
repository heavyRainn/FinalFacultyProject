package by.epam.t7.dao;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.t7.dao.StudentDao;
import by.epam.t7.dao.factory.MysqlDaoFactory;
import by.epam.t7.dao.pool.ConnectionPool;
import by.epam.t7.entity.Student;
import by.epam.t7.exception.DaoException;

public class StudentDaoTest {

	private static final Logger LOG = Logger.getLogger(StudentDaoTest.class);
	private Student student;

	@BeforeClass
	public static void initializePool() {
		ConnectionPool.getInstance().initConnectionPool();
	}

	@Before
	public void createStudent() {
		student = new Student();

		student.setName("Johnie");
		student.setSurname("Delano");
		student.setMail("johnie_delano@mail.ru");
		student.setLogin("jd");
		student.setPassword("123");
	}

	@Test
	public void testSignUp() {
		LOG.info("testSignUp()");

		try {
			StudentDao studentDao = (StudentDao) MysqlDaoFactory.getInstance().getDao(Student.class);
			studentDao.signUp(student);

			Student newStudent = studentDao.findClientByLoginAndPassword("jd", "123");

			Assert.assertEquals(student.getName(), newStudent.getName());
			Assert.assertEquals(student.getSurname(), newStudent.getSurname());
			Assert.assertEquals(student.getMail(), newStudent.getMail());

		} catch (DaoException e) {
			LOG.error("DAOException", e);
		}
	}

	@Test
	public void testFindClientByLoginAndPassword() {
		LOG.info("testFindClientByLoginAndPassword()");

		try {
			StudentDao studentDao = (StudentDao) MysqlDaoFactory.getInstance().getDao(Student.class);

			Student newStudent = studentDao.findClientByLoginAndPassword("jd", "123");

			Assert.assertEquals(student.getName(), newStudent.getName());
			Assert.assertEquals(student.getSurname(), newStudent.getSurname());
			Assert.assertEquals(student.getMail(), newStudent.getMail());

		} catch (DaoException e) {
			LOG.error("DAOException", e);
		}
	}

	@Test
	public void testIsExist() {
		LOG.info("testIsExist()");

		try {
			StudentDao studentDao = (StudentDao) MysqlDaoFactory.getInstance().getDao(Student.class);
			boolean flag = studentDao.isExist("jd");

			Assert.assertTrue(flag);
		} catch (DaoException e) {
			LOG.error("DAOException", e);
		}
	}

	@AfterClass
	public static void destroyPool() {
		ConnectionPool.getInstance().stop();
	}
}
