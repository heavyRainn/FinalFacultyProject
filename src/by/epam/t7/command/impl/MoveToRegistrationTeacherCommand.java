package by.epam.t7.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.exception.CommandException;

public class MoveToRegistrationTeacherCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		return PageName.SIGN_UP_TEACHER;
	}

}
