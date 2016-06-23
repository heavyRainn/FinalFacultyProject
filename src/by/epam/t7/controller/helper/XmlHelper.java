package by.epam.t7.controller.helper;

import java.util.Map;

import org.apache.log4j.Logger;

import by.epam.t7.command.Command;
import by.epam.t7.exception.DaoException;

/**
 * Helps to initialize map with commands
 */
public class XmlHelper {

	private static final Logger logger = Logger.getLogger(XmlHelper.class);
	private static final String xmlPath = "A:\\workspase eclipse\\FinalFacultyProject\\src\\resources\\cmd.xml";

	private volatile static XmlHelper instance = null;

	public static XmlHelper getInstance() {
		if (instance == null) {
			synchronized (XmlHelper.class) {
				if (instance == null) {
					instance = new XmlHelper();
				}
			}
		}
		return instance;
	}

	public Map<String, Command> parse() {
		Map<String, Command> map = null;
		try {
			map = DomParser.getInstance().parse(xmlPath);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
		return map;
	}

}
