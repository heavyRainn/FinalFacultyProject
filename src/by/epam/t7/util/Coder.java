package by.epam.t7.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * This is utility class that works with passwords
 */
public class Coder {

	/**
	 * This method of password hashes
	 * 
	 * @param pw
	 *            password from form
	 * @return hashed password
	 */
	public static String getHash(String pw) {
		return DigestUtils.sha256Hex(pw);
	}
}
