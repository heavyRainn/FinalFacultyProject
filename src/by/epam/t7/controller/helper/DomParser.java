package by.epam.t7.controller.helper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.t7.command.Command;
import by.epam.t7.exception.DaoException;

/**
 * Parses xml file with commands and convert it into map.
 */
public class DomParser {

	private volatile static DomParser instance = null;

	private static final String ROOT_ELEMENT_NAME = "command";

	private static final String ELEMENT_COMMAND = "value";
	private static final String ELEMENT_IMPL = "impl";

	public static DomParser getInstance() {
		if (instance == null) {
			synchronized (DomParser.class) {
				if (instance == null) {
					instance = new DomParser();
				}
			}
		}
		return instance;
	}

	/**
	 * Returns final result of the reading xml file.
	 * 
	 * @param xmlpath
	 * @return Map<String, Command> of commands
	 * @throws DaoException
	 */
	public Map<String, Command> parse(String xmlpath) throws DaoException {
		Map<String, Command> mp = convertNodeList(readXMLDom(xmlpath));
		return mp;
	}

	/**
	 * Reads file into node list.
	 * 
	 * @param filePath
	 * @return NodeList of the xml file
	 * @throws DaoException
	 */
	private NodeList readXMLDom(String filePath) throws DaoException {

		NodeList nList = null;

		try {
			File inputFile = new File(filePath);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(inputFile);

			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName(ROOT_ELEMENT_NAME);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new DaoException(e);
		}
		return nList;
	}

	/**
	 * Converts node list to the map.
	 * 
	 * @param nList
	 * @return map
	 */
	private Map<String, Command> convertNodeList(NodeList nList) {
		Map<String, Command> cmd = new HashMap<>();
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				String elem1 = eElement.getElementsByTagName(ELEMENT_COMMAND).item(0).getTextContent();
				String elem2 = eElement.getElementsByTagName(ELEMENT_IMPL).item(0).getTextContent();
				Command commandClass = null;
				try {
					Class<?> clazz = Class.forName(elem2);
					commandClass = (Command) clazz.newInstance();

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					System.out.println(e.getMessage());
				}
				cmd.put(elem1, commandClass);
			}
		}
		return cmd;
	}

}
