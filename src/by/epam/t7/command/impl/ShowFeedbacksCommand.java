package by.epam.t7.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.entity.Feedback;
import by.epam.t7.exception.CommandException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.service.FeedbackService;

public class ShowFeedbacksCommand implements Command {

	private final String ID_FACULTY = "idFaculty";
	private final String FEEDBACKS = "facultyFeedbacks";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;

		List<Feedback> feedbacks;
		int id = Integer.valueOf(request.getParameter(ID_FACULTY));

		try {

			feedbacks = FeedbackService.getInstance().getAllFacultyFeedbacks(id);
			request.getSession().setAttribute(FEEDBACKS, feedbacks);

			page = PageName.DELETE_FEEDBACK_FINAL;

		} catch (ServiceException e) {
			throw new CommandException(e);
		}

		return page;
	}

}
