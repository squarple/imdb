package by.radzionau.imdb.controller.command;

import by.radzionau.imdb.controller.command.impl.admin.ChangeUserRoleCommand;
import by.radzionau.imdb.controller.command.impl.admin.ChangeUserStatusCommand;
import by.radzionau.imdb.controller.command.impl.admin.GetUsersCommand;
import by.radzionau.imdb.controller.command.impl.general.*;
import by.radzionau.imdb.controller.command.impl.moveto.MoveToPageCommand;

import java.util.EnumMap;

public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    private CommandProvider() {
        addGeneralCommands();
        addAdminCommands();
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandType.DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT;
        }
        return commands.get(commandType);
    }

    private void addGeneralCommands() {
        commands.put(CommandType.SIGN_IN, new SignInCommand());
        commands.put(CommandType.SIGN_UP, new SignUpCommand());
        commands.put(CommandType.SIGN_OUT, new SignOutCommand());
        commands.put(CommandType.EMAIL_VERIFICATION, new EmailVerificationCommand());
        commands.put(CommandType.GET_MOVIES, new GetMoviesCommand());

        commands.put(CommandType.MOVE_TO, new MoveToPageCommand());
    }

    private void addAdminCommands() {
        commands.put(CommandType.GET_USERS, new GetUsersCommand());
        commands.put(CommandType.CHANGE_USER_ROLE, new ChangeUserRoleCommand());
        commands.put(CommandType.CHANGE_USER_STATUS, new ChangeUserStatusCommand());
    }
}
