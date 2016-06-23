package by.epam.t7.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.entity.Faculty;
import by.epam.t7.exception.CommandException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.service.FacultyService;

public class MoveToUserPageRegistrationCommand implements Command {

	private final String FACULTIES = "allFaculties";
	private final String INDEX_FACULTY = "index";
	private final String INDEX_LENGTH = "length";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		try {
			int index = 0;
			int length = 5;

			request.getSession().setAttribute(INDEX_FACULTY, index);
			request.getSession().setAttribute(INDEX_LENGTH, length);

			List<Faculty> faculties = FacultyService.getInstance().getAll(index, length);

			request.getSession().setAttribute(FACULTIES, faculties);
			
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return PageName.USER_PAGE_REGISTRATION;
	}

}
