package by.radzionau.imdb.controller.command;

import by.radzionau.imdb.controller.command.impl.general.SignInCommand;
import by.radzionau.imdb.controller.command.impl.general.SignOutCommand;
import by.radzionau.imdb.controller.command.impl.general.SignUpCommand;
import by.radzionau.imdb.controller.command.impl.redirect.RedirectToPageCommand;

import java.util.EnumMap;

public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    private CommandProvider() {
        commands.put(CommandType.SIGN_IN, new SignInCommand());
        commands.put(CommandType.SIGN_UP, new SignUpCommand());
        commands.put(CommandType.SIGN_OUT, new SignOutCommand());
        commands.put(CommandType.REDIRECT, new RedirectToPageCommand());
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
}
