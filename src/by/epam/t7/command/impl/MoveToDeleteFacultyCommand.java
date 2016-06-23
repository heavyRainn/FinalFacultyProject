package by.epam.t7.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.entity.Faculty;
import by.epam.t7.entity.User;
import by.epam.t7.exception.CommandException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.service.FacultyService;

public class MoveToDeleteFacultyCommand implements Command {

	private final String LOGIN = "login";
	private final String FACULTIES = "teachersFaculties";
	private final String FEEDBACKS = "facultyFeedbacks";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		List<Faculty> faculties;
		User user;
		int id;

		try {
			user = (User) request.getSession().getAttribute(LOGIN);
			id = user.getId();

			faculties = FacultyService.getInstance().getAllUsersFaculties(id);

			request.getSession().setAttribute(FACULTIES, faculties);
			request.getSession().setAttribute(FEEDBACKS, null);

		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return PageName.DELETE_FACULTY;
	}

}
