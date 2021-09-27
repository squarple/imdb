package by.radzionau.imdb.controller.command;

import by.radzionau.imdb.controller.command.impl.admin.*;
import by.radzionau.imdb.controller.command.impl.general.*;
import by.radzionau.imdb.controller.command.impl.moveto.admin.MoveToEditMoviePageCommand;
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
        commands.put(CommandType.GET_MOVIE_LIST, new GetMovieListCommand());
        commands.put(CommandType.GET_MOVIE, new GetMovieCommand());

        commands.put(CommandType.MOVE_TO, new MoveToPageCommand());

        commands.put(CommandType.SEARCH_MOVIES, new SearchMoviesCommand());
    }

    private void addAdminCommands() {
        commands.put(CommandType.GET_USERS, new GetUsersCommand());
        commands.put(CommandType.CHANGE_USER_ROLE, new ChangeUserRoleCommand());
        commands.put(CommandType.CHANGE_USER_STATUS, new ChangeUserStatusCommand());
        commands.put(CommandType.MOVE_TO_EDIT_MOVIE_PAGE, new MoveToEditMoviePageCommand());
        commands.put(CommandType.EDIT_MOVIE, new EditMovieCommand());
        commands.put(CommandType.DELETE_MOVIE, new DeleteMovieCommand());
    }
}
