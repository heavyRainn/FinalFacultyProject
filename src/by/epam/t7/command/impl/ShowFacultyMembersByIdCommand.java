package by.epam.t7.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.entity.Student;
import by.epam.t7.exception.CommandException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.service.FacultyService;

public class ShowFacultyMembersByIdCommand implements Command {

	private final String ID_FACULTY = "idFaculty";
	private final String USERS = "facultyUsers";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;

		List<Student> users;
		int id = Integer.valueOf(request.getParameter(ID_FACULTY));

		try {
			users = FacultyService.getInstance().getFacultyUsers(id);

			request.getSession().setAttribute(USERS, users);
			request.getSession().setAttribute(ID_FACULTY, id);

			page = PageName.SIGN_UP_FEEDBACK_FINAL;

		} catch (ServiceException e) {
			throw new CommandException(e);
		}

		return page;
	}

}
