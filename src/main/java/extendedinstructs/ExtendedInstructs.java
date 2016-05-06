package extendedinstructs;

import extendedinstructs.commands.permissions.PermissionCommand;
import instructability.Instructables;
import instructability.command.CommandRegistry;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

public class ExtendedInstructs implements IModule {

	private static ExtendedInstructs instance;

	@Override
	public boolean enable(IDiscordClient client) {
		ExtendedInstructs.instance = this;

		CommandRegistry reg = Instructables.getRegistry();
		reg.registerCommand(new PermissionCommand());
		return true;
	}

	@Override
	public void disable() {
	}

	@Override
	public String getName() {
		return getClass().getPackage().getImplementationTitle();
	}

	@Override
	public String getAuthor() {
		return "Kaioru";
	}

	@Override
	public String getVersion() {
		return getClass().getPackage().getImplementationVersion();
	}

	@Override
	public String getMinimumDiscord4JVersion() {
		return "2.4.0";
	}

	public static ExtendedInstructs getInstance() {
		return instance;
	}

}
