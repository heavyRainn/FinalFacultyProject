package by.epam.t7.dao.factory;

import by.epam.t7.dao.Dao;

import by.epam.t7.exception.DaoException;

/**
 * Interface for the creating concrete dao by the class name.
 */
public interface DaoFactory {

	/**
	 * Inner interface for the anonymous class.
	 */
	public interface DaoCreator {
		public Dao create();
	}

	/**
	 * Gets dao from Map by the class name.
	 * 
	 * @param daoClass
	 * @return concrete object of dao
	 * @throws DaoException
	 */
	public Dao getDao(Class<?> daoClass) throws DaoException;
}
