package by.epam.t7.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.entity.Faculty;
import by.epam.t7.exception.CommandException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.service.FacultyService;

public class LookInfoAboutFacultyCommand implements Command {

	private final String ID = "idFaculty";
	private final String FACULTY = "faculty";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;

		int id;

		id = Integer.valueOf(request.getParameter(ID));

		try {
			Faculty faculty = FacultyService.getInstance().getFacultyInfo(id);

			if (!faculty.equals(null)) {
				request.getSession().setAttribute(FACULTY, faculty);
				page = PageName.FACULTY_PAGE;
			} else {
				page = PageName.ERROR_PAGE;
			}

		} catch (ServiceException e) {
			throw new CommandException(e);
		}

		return page;
	}

}
