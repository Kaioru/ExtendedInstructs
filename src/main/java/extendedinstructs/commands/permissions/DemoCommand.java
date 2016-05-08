package extendedinstructs.commands.permissions;

import com.github.kaioru.instructability.Defaults;
import com.github.kaioru.instructability.discord4j.Discord4JCommand;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.MessageBuilder;

import java.util.LinkedList;

public class DemoCommand extends Discord4JCommand {
	@Override
	public String getName() {
		return "demo";
	}

	@Override
	public String getDesc() {
		return Defaults.DESCRIPTION;
	}

	@Override
	public void execute(LinkedList<String> args, MessageReceivedEvent event, MessageBuilder msg) throws Exception {
		msg.appendContent("Hello world!")
				.build();
	}
}
