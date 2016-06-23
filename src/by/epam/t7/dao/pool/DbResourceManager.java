package by.epam.t7.dao.pool;

import java.util.ResourceBundle;

/**
 * Provides access to the database configuration parameters via keys.
 */
public class DbResourceManager {

	private volatile static DbResourceManager instance = null;

	private ResourceBundle bundle = ResourceBundle.getBundle("resources.db");

	public static DbResourceManager getInstance() {
		if (instance == null) {
			synchronized (DbResourceManager.class) {
				if (instance == null) {
					instance = new DbResourceManager();
				}
			}
		}
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}

}
