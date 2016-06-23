package by.epam.t7.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.exception.CommandException;

public class ChangeLocalCommand implements Command {

	private final String URL = "url";
	private final String LOCALE = "language";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;

		String language = request.getParameter(LOCALE);
		request.getSession().setAttribute(LOCALE, language);

		page = (String) request.getSession().getAttribute(URL);

		return page;
	}

}
