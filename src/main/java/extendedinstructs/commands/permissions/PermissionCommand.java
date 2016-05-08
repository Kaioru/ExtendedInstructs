package extendedinstructs.commands.permissions;

import extendedinstructs.commands.HelpableCommand;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.MessageBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

public class PermissionCommand extends HelpableCommand {

	public PermissionCommand() {
		try {
			registerCommands(new PermissionEditCommands());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
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
	public void execute(LinkedList<String> linkedList, MessageReceivedEvent messageReceivedEvent, MessageBuilder messageBuilder) throws Exception {

	}

}
