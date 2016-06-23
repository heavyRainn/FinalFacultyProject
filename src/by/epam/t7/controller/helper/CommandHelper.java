package by.epam.t7.controller.helper;

import java.util.Map;

import by.epam.t7.command.Command;
import by.epam.t7.command.impl.UnknownCommand;

/**
 * Contains all commands and gives it's by name.
 */
public final class CommandHelper {

	/**
	 * Map with all commands
	 */
	private Map<String, Command> commands;

	public CommandHelper() {
		commands = XmlHelper.getInstance().parse();
	}

	/**
	 * Returns command by the name.
	 * 
	 * @param commandName
	 * @return command
	 */
	public Command getCommand(String commandName) {

		Command command = null;

		commandName = commandName.replace('-', '_').toUpperCase().trim();

		command = commands.get(commandName);

		if (command == null) {
			command = new UnknownCommand();
		}

		return command;
	}

}
