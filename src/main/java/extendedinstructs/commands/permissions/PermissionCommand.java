package extendedinstructs.commands.permissions;

import extendedinstructs.commands.HelpableCommand;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.MessageBuilder;

import java.util.LinkedList;

public class PermissionCommand extends HelpableCommand {

	public PermissionCommand() {
		registerCommands(new PermissionEditCommands());
	}

	@Override
	public String getName() {
		return "permissions";
	}

	@Override
	public String getDesc() {
		return "All permissions-related commands";
	}

	@Override
	public void onExecute(MessageReceivedEvent event, MessageBuilder msg, LinkedList<String> args) throws Exception {

	}

}
