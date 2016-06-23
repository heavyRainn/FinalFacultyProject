package by.epam.t7.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.entity.Feedback;
import by.epam.t7.exception.CommandException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.service.FeedbackService;

public class SignUpFeedbackCommand implements Command {

	private final String STUDENT_ID = "idStudent";
	private final String FACULTY_ID = "idFaculty";
	private final String MARK = "mark";
	private final String DESCRIPTION = "description";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;

		boolean result;
		
		Feedback feedback = createFeedback(Integer.valueOf(request.getParameter(STUDENT_ID).trim()),
				Integer.parseInt(request.getSession().getAttribute(FACULTY_ID).toString()),
				Integer.valueOf(request.getParameter(MARK).trim()), request.getParameter(DESCRIPTION).trim());

		try {
			result = FeedbackService.getInstance().signUpFeedback(feedback);
			if(result){
				page = PageName.TEACHER_PAGE;
			}else{
				page = PageName.ERROR_PAGE;
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		return page;
	}

	private Feedback createFeedback(Integer valueOf, Integer valueOf2, Integer valueOf3, String trim) {
		
		Feedback feedback = new Feedback();
		
		feedback.setStudentId(valueOf);
		feedback.setTrainingId(valueOf2);
		feedback.setMark(valueOf3);
		feedback.setDescription(trim);
		
		return feedback;
	}

}
