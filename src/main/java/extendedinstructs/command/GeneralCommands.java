package extendedinstructs.command;

import instructabilty.command.Command;
import instructabilty.command.CommandBuilder;
import sx.blah.discord.util.MessageBuilder.Styles;

public class GeneralCommands {

	public static Command getModulesCommand() {
		return new CommandBuilder("modules")
				.desc("Displays the Bot's modules")
				.build((event, msg, args) -> {
					msg.appendContent("Modules:\r\n\r\n", Styles.BOLD);

					event.getClient()
							.getModuleLoader()
							.getLoadedModules()
							.forEach(mod -> {
								StringBuilder content = new StringBuilder();

								content.append("• ");
								content.append(mod.getName());
								content.append(" ");
								content.append("v" + mod.getVersion());
								content.append(" by ");
								content.append(mod.getAuthor());
								msg.appendContent(content.toString(),
										Styles.INLINE_CODE)
										.appendContent("\r\n");
							});

					msg.build();
				});
	}

}
