package by.epam.t7.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.entity.User;
import by.epam.t7.exception.CommandException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.service.FacultyService;

public class CancelSignUpCommand implements Command {

	private final String LOGIN = "login";
	private final String ID = "idFaculty";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;

		User user;
		int idFaculty;
		int idUser;
		boolean result;

		try {
			user = (User) request.getSession().getAttribute(LOGIN);
			idUser = user.getId();
			idFaculty = Integer.valueOf(request.getParameter(ID));

			result = FacultyService.getInstance().cancelSignUp(idFaculty, idUser);

			if (result) {
				page = PageName.USER_PAGE;
			} else {
				page = PageName.ERROR_PAGE;
			}

		} catch (ServiceException e) {
			throw new CommandException(e);
		}

		return page;
	}

}
