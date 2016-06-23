package by.epam.t7.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.entity.User;
import by.epam.t7.exception.CommandException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.service.UserService;
import by.epam.t7.util.Coder;

public class LoginCommand implements Command {

	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String ROLE = "role";
	private static final String TEACHER = "teacher";
	private static final String STUDENT = "student";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		{
			String role = null;
			User user = null;

			try {
				user = UserService.getInstance().checkLogin(request.getParameter(LOGIN),
						Coder.getHash(request.getParameter(PASSWORD)));

				if (user != null) {
					role = UserService.getInstance().checkRole(user.getId());
					request.getSession(true).setAttribute(LOGIN, user);
					request.getSession().setAttribute(ROLE, role);
					if (role.equals(STUDENT)) {
						page = PageName.USER_PAGE;
					} else if (role.equals(TEACHER)) {
						page = PageName.TEACHER_PAGE;
					}
				} else {
					page = PageName.INDEX_PAGE;
				}

			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		return page;
	}

}
