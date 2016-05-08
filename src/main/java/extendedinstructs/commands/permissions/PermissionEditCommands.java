package extendedinstructs.commands.permissions;

import com.github.kaioru.instructability.Instructables;
import com.github.kaioru.instructability.discord4j.Discord4JAnnotatedCommand;
import com.github.kaioru.instructability.discord4j.Discord4JCommandBuilder;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.MessageBuilder;

import java.util.LinkedList;
import java.util.Optional;

public class PermissionEditCommands {

	@Discord4JAnnotatedCommand(
			name = "demo",
			desc = "This is a demo!"
	)
	public void cmdDemo(LinkedList<String> args, MessageReceivedEvent event, MessageBuilder msg) throws Exception {
		msg.appendContent("Hello world!")
				.build();
	}

	@Discord4JAnnotatedCommand(
			name = "check",
			desc = "Checks whether you have the specified permission"
	)
	public void cmdPermissionCheck(LinkedList<String> args, MessageReceivedEvent event, MessageBuilder msg) throws Exception {
		String query = args.removeFirst();

		new Discord4JCommandBuilder("internal-check")
				.permission(query)
				.build((e, m, a) -> {
					msg.appendContent(String.format("Permissions verification for '%s' succeeded.", query));
					msg.build();
				})
				.execute(args, event, msg);
	}

	@Discord4JAnnotatedCommand(
			name = "add",
			desc = "Gives User/Role a specified permission"
	)
	public void cmdPermissionAdd(LinkedList<String> args, MessageReceivedEvent event, MessageBuilder msg) throws Exception {
		String query = args.removeFirst();
		String perms = args.removeFirst();

		Optional<IUser> user = event.getMessage()
				.getGuild()
				.getUsers()
				.stream()
				.filter(u -> u.getName().equalsIgnoreCase(query))
				.findFirst();

		if (user.isPresent()) {
			IUser u = user.get();

			Instructables.getPermissionRegistry()
					.getPermissions(event.getMessage().getGuild().getID() + ":" + u.getID())
					.add(perms);
			msg.appendContent(String.format(
					"Successfully added '%s' permissions to user '%s'",
					perms,
					query
			));
			msg.build();
		} else {
			Optional<IRole> role = event.getMessage()
					.getGuild()
					.getRoles()
					.stream()
					.filter(r -> r.getName().equalsIgnoreCase(query))
					.findFirst();

			if (role.isPresent()) {
				IRole r = role.get();

				Instructables.getPermissionRegistry()
						.getPermissions(event.getMessage().getGuild().getID() + ":" + r.getID())
						.add(perms);
				msg.appendContent(String.format(
						"Successfully added '%s' permissions to role '%s'",
						perms,
						query
				));
				msg.build();
			} else {
				msg.appendContent(String.format("Unable to find '%s'", query));
				msg.build();
			}
		}
	}

	@Discord4JAnnotatedCommand(
			name = "remove",
			desc = "Takes from User/Role a specified permission"
	)
	public void cmdPermissionRemove(LinkedList<String> args, MessageReceivedEvent event, MessageBuilder msg) throws Exception {
		String query = args.removeFirst();
		String perms = args.removeFirst();

		Optional<IUser> user = event.getMessage()
				.getGuild()
				.getUsers()
				.stream()
				.filter(u -> u.getName().equalsIgnoreCase(query))
				.findFirst();

		if (user.isPresent()) {
			IUser u = user.get();

			Instructables.getPermissionRegistry()
					.getPermissions(event.getMessage().getGuild().getID() + ":" + u.getID())
					.remove(perms);
			msg.appendContent(String.format(
					"Successfully removed '%s' permissions to user '%s'",
					perms,
					query
			));
			msg.build();
		} else {
			Optional<IRole> role = event.getMessage()
					.getGuild()
					.getRoles()
					.stream()
					.filter(r -> r.getName().equalsIgnoreCase(query))
					.findFirst();

			if (role.isPresent()) {
				IRole r = role.get();

				Instructables.getPermissionRegistry()
						.getPermissions(event.getMessage().getGuild().getID() + ":" + r.getID())
						.remove(perms);
				msg.appendContent(String.format(
						"Successfully removed '%s' permissions to role '%s'",
						perms,
						query
				));
				msg.build();
			} else {
				msg.appendContent(String.format("Unable to find '%s'", query));
				msg.build();
			}
		}
	}

}
