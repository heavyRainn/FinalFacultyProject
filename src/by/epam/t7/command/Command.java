package by.epam.t7.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.exception.CommandException;

public interface Command {

	String execute(HttpServletRequest request) throws CommandException;

}
