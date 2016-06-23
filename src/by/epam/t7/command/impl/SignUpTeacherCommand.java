package by.epam.t7.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.entity.Teacher;
import by.epam.t7.exception.CommandException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.service.UserService;
import by.epam.t7.util.Coder;

public class SignUpTeacherCommand implements Command {

	private final String URL = "url";
	private final String LOGIN = "login";
	private final String PASSWORD = "password";
	private final String MAIL = "mail";
	private final String NAME = "name";
	private final String SURNAME = "surname";
	private final String ROLE = "role";
	private final String TEACHER = "teacher";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;

		Teacher user = createTeacher(request.getParameter(LOGIN).trim(), request.getParameter(PASSWORD).trim(),
				request.getParameter(MAIL).trim(), request.getParameter(NAME).trim(),
				request.getParameter(SURNAME).trim());

		boolean isSuccess;

		try {
			isSuccess = UserService.getInstance().signUp(user);
			if (isSuccess) {
				request.getSession(true).setAttribute(LOGIN, user);
				request.getSession().setAttribute(URL, page);
				request.getSession().setAttribute(ROLE, TEACHER);
				page = PageName.TEACHER_PAGE;
			} else {
				page = PageName.ERROR_PAGE;
			}

		} catch (ServiceException e) {
			throw new CommandException(e);
		}

		return page;
	}

	private Teacher createTeacher(String login, String password, String mail, String name, String surname) {

		Teacher user = new Teacher();

		user.setMail(mail);
		user.setLogin(login);
		user.setPassword(Coder.getHash(password));
		user.setName(name);
		user.setSurname(surname);

		return user;

	}
}
