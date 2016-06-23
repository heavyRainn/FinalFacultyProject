package by.epam.t7.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.entity.Feedback;
import by.epam.t7.entity.User;
import by.epam.t7.exception.CommandException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.service.FeedbackService;

public class MoveToUserFeedbacksCommand implements Command {

	private final String LOGIN = "login";
	private final String FEEDBACKS = "myFeedbacks";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		List<Feedback> feedbacks;
		User user;
		int id;

		try {
			user = (User) request.getSession().getAttribute(LOGIN);
			id = user.getId();

			feedbacks = FeedbackService.getInstance().getAllUsersFeedbacks(id);
			request.getSession().setAttribute(FEEDBACKS, feedbacks);

		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return PageName.USER_FEEDBACKS;
	}

}
