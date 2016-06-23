package by.epam.t7.exception;

public class DaoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2768520820248976282L;

	public DaoException(Throwable exception) {
		super(exception);
	}

	public DaoException(String string) {
		super(string);
	}
}
