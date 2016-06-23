package by.epam.t7.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.exception.CommandException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.service.FacultyService;

public class DeleteFacultyCommand implements Command {

	private final String ID = "idFaculty";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;

		int id;
		boolean result;

		id = Integer.valueOf(request.getParameter(ID));

		try {
			result = FacultyService.getInstance().delete(id);

			if (result) {
				page = PageName.TEACHER_PAGE;
			} else {
				page = PageName.ERROR_PAGE;
			}

		} catch (ServiceException e) {
			throw new CommandException(e);
		}

		return page;
	}

}
