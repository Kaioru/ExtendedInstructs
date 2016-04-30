package extendedinstructs;

import extendedinstructs.command.GeneralCommands;
import instructabilty.Instructables;
import instructabilty.command.CommandRegistry;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

public class ExtendedInstructs implements IModule {

	private static ExtendedInstructs instance;

	@Override
	public boolean enable(IDiscordClient client) {
		ExtendedInstructs.instance = this;

		CommandRegistry reg = Instructables.getRegistry();
		reg.registerCommand(GeneralCommands.getModulesCommand());
		return true;
	}

	@Override
	public void disable() {
	}

	@Override
	public String getName() {
		return "ExtendedInstructs";
	}

	@Override
	public String getAuthor() {
		return "Kaioru";
	}

	@Override
	public String getVersion() {
		return "0.0.1";
	}

	@Override
	public String getMinimumDiscord4JVersion() {
		return "2.4.6";
	}

	public static ExtendedInstructs getInstance() {
		return instance;
	}

}
