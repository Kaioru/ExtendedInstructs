package extendedinstructs.commands.permissions;

import instructability.Instructables;
import instructability.command.AnnotatedCommand;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.MessageBuilder;

import java.util.LinkedList;
import java.util.Optional;

public class PermissionEditCommands {

	@AnnotatedCommand(name = "add")
	public void cmdPermissionAdd(MessageReceivedEvent event, MessageBuilder msg, LinkedList<String> args) throws Exception {
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
					.getForGuild(event.getMessage().getGuild().getID())
					.getUserPermissions(u.getID())
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
						.getForGuild(event.getMessage().getGuild().getID())
						.getRolePermissions(r.getID())
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

	@AnnotatedCommand(name = "remove")
	public void cmdPermissionRemove(MessageReceivedEvent event, MessageBuilder msg, LinkedList<String> args) throws Exception {
		String query = args.removeFirst();
		String perms = args.removeFirst();

		Optional<IUser> user = event.getMessage()
				.getGuild()
				.getUsers()
				.stream()
				.filter(u -> u.getName().equals(query))
				.findFirst();

		if (user.isPresent()) {
			IUser u = user.get();

			Instructables.getPermissionRegistry()
					.getForGuild(event.getMessage().getGuild().getID())
					.getUserPermissions(u.getID())
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
					.filter(r -> r.getName().equals(query))
					.findFirst();

			if (role.isPresent()) {
				IRole r = role.get();

				Instructables.getPermissionRegistry()
						.getForGuild(event.getMessage().getGuild().getID())
						.getRolePermissions(r.getID())
						.remove(perms);
				msg.appendContent(String.format(
						"Successfully removed '%s' permissions to role '%s'",
						perms,
						query
				));
				msg.build();
			}
		}
	}

}
