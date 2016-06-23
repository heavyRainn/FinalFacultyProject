package by.epam.t7.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.t7.command.Command;
import by.epam.t7.controller.helper.CommandHelper;
import by.epam.t7.exception.CommandException;

/**
 * Processes all incoming requests by redirecting them to the corresponding
 * command.
 */
public class Controller extends HttpServlet {

	private static final Logger logger = Logger.getLogger(Controller.class);
	private static final long serialVersionUID = 1L;
	private static final String COMMAND_NAME = "command";
	private final CommandHelper commandHelper = new CommandHelper();

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		super.service(arg0, arg1);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		Command command = null;
		String page = null;
		try {
			command = commandHelper.getCommand(request.getParameter(COMMAND_NAME));
			page = command.execute(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);

			if (dispatcher != null) {
				dispatcher.forward(request, response);
			} else {
				page = command.execute(request);
				dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
			}
		} catch (CommandException | ServletException | IOException e) {
			logger.error(e.getMessage());
			page = PageName.ERROR_PAGE;
		}
	}

}
