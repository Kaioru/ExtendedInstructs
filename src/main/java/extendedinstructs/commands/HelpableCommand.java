package extendedinstructs.commands;

import instructability.command.SimpleCommand;

public abstract class HelpableCommand extends SimpleCommand {

	public HelpableCommand() {
		addHelperCommands();
	}

}
