package by.epam.t7.command.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import by.epam.t7.command.Command;
import by.epam.t7.controller.PageName;
import by.epam.t7.entity.Faculty;
import by.epam.t7.entity.User;
import by.epam.t7.exception.CommandException;
import by.epam.t7.exception.ServiceException;
import by.epam.t7.service.FacultyService;

public class UpdateFacultyCommand implements Command {

	private final String LOGIN = "login";
	private final String ID = "idFaculty";
	private final String NAME = "name";
	private final String S_DATE = "startDate";
	private final String E_DATE = "endDate";
	private final String DATE_FORMAT = "yyyy-MM-dd";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		String page = null;
		User user;
		int id;
		boolean result;
		int teacherId;

		try {
			id = Integer.valueOf(request.getParameter(ID));

			user = (User) request.getSession().getAttribute(LOGIN);
			teacherId = user.getId();

			Faculty faculty = createFaculty(id, request.getParameter(NAME).trim(), request.getParameter(S_DATE).trim(),
					request.getParameter(E_DATE).trim(), teacherId);

			result = FacultyService.getInstance().update(faculty);

			if (result) {
				page = PageName.TEACHER_PAGE;
			} else {
				page = PageName.ERROR_PAGE;
			}

		} catch (ServiceException | ParseException e) {
			throw new CommandException(e);
		}

		return page;
	}

	private Faculty createFaculty(int id, String parameter, String parameter2, String parameter3, int teacherId)
			throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		Date dateUtil = formatter.parse(goodDateFormat(parameter2));
		java.sql.Date sqlDate = new java.sql.Date(dateUtil.getTime());
		Faculty faculty = new Faculty();
		faculty.setId(id);
		faculty.setName(parameter);
		faculty.setStartDate(sqlDate);

		dateUtil = formatter.parse(goodDateFormat(parameter3));
		sqlDate = new java.sql.Date(dateUtil.getTime());

		faculty.setEndDate(sqlDate);
		faculty.setTeacher(teacherId);

		return faculty;
	}

	private String goodDateFormat(String dateBad) {
		String[] parts = dateBad.split("/");
		String goodDate = parts[2] + "-" + parts[0] + "-" + parts[1];
		System.out.println(goodDate);
		return goodDate;

	}
}
