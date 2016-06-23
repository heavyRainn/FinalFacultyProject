package by.epam.t7.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator involved validation of input data
 */
public class Validator {

	private volatile static Validator instance = null;

	private final static String DOMEN = "[a-z][a-z[0-9]\u005F\u002E\u002D]*[a-z||0-9]";
	private final static String PREFIX = "([a-z]){2,4}";
	private final static String REGEX_FOR_NAME = "[a-zA-Z ]{3,30}";
	private final static String REGEX_FOR_MAIL = DOMEN + "@" + DOMEN + "\u002E" + PREFIX;

	public static Validator getInstance() {
		if (instance == null) {
			synchronized (Validator.class) {
				if (instance == null) {
					instance = new Validator();
				}
			}
		}
		return instance;
	}

	/**
	 * Checks login and password on information is correct
	 * 
	 * @param login
	 *            from the form
	 * @param password
	 *            from the form
	 * @return true if data is correct
	 */
	public static boolean loginValidator(String login, String password) {

		if (login.isEmpty() && validate(login, REGEX_FOR_NAME)) {
			return false;
		}
		if (password.isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * Vlidate the data from the registration form
	 * 
	 * @param login
	 * @param password
	 * @param mail
	 * @param name
	 * @param surname
	 * @return true if the data is correct
	 */
	public static boolean singUpValidator(String login, String password, String mail, String name, String surname) {

		if (login.isEmpty() && validate(login, REGEX_FOR_NAME)) {
			return false;
		}
		if (password.isEmpty()) {
			return false;
		}
		if (mail.isEmpty() && validate(mail, REGEX_FOR_MAIL)) {
			return false;
		}
		if (name.isEmpty()) {
			return false;
		}
		if (surname.isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * Checks the string on the entered regex
	 * 
	 * @param name
	 *            string that we will validate
	 * @param regex
	 *            by witch we will validate
	 * @return
	 */
	private static boolean validate(String name, String regex) {

		boolean flag = false;

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);

		if (matcher.matches()) {
			flag = true;
		}

		return flag;
	}

}
