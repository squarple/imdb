package by.radzionau.imdb.controller.command;

import by.radzionau.imdb.controller.command.impl.admin.*;
import by.radzionau.imdb.controller.command.impl.general.*;
import by.radzionau.imdb.controller.command.impl.moveto.admin.MoveToAddGenrePageCommand;
import by.radzionau.imdb.controller.command.impl.moveto.admin.MoveToAddMovieCoverPageCommand;
import by.radzionau.imdb.controller.command.impl.moveto.admin.MoveToAddMoviePageCommand;
import by.radzionau.imdb.controller.command.impl.moveto.admin.MoveToEditMoviePageCommand;
import by.radzionau.imdb.controller.command.impl.moveto.general.MoveToLoginPageCommand;
import by.radzionau.imdb.controller.command.impl.moveto.general.MoveToMainPageCommand;
import by.radzionau.imdb.controller.command.impl.moveto.general.MoveToSignupPageCommand;
import by.radzionau.imdb.controller.command.impl.moveto.user.MoveToAddFeedbackPageCommand;
import by.radzionau.imdb.controller.command.impl.moveto.opr.MoveToTopMethodsCommand;
import by.radzionau.imdb.controller.command.impl.opr.GetTopPairedComparisons;
import by.radzionau.imdb.controller.command.impl.opr.MethodOfPairedComparisonsCommand;
import by.radzionau.imdb.controller.command.impl.opr.MethodOfWeighingAssessmentsCommand;
import by.radzionau.imdb.controller.command.impl.user.AddFeedbackCommand;

import java.util.EnumMap;

/**
 * The class CommandProvider. It helps to find a command with specified name.
 */
public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    private CommandProvider() {
        addGeneralCommands();
        addUserCommands();
        addAdminCommands();
        addOprCommands();
    }

    /**
     * Gets instance.
     *
     * @return the instance of command provider
     */
    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    /**
     * Gets command.
     *
     * @param commandName the command name
     * @return the command with specified name
     */
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
        commands.put(CommandType.DEFAULT, new DefaultCommand());
        commands.put(CommandType.GET_MOVIE, new GetMovieCommand());
        commands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandType.SEARCH_MOVIES, new SearchMoviesCommand());
        commands.put(CommandType.GET_MOVIE_LIST, new GetMovieListCommand());
        commands.put(CommandType.MOVE_TO_MAIN_PAGE, new MoveToMainPageCommand());
        commands.put(CommandType.MOVE_TO_LOGIN_PAGE, new MoveToLoginPageCommand());
        commands.put(CommandType.MOVE_TO_SIGNUP_PAGE, new MoveToSignupPageCommand());
        commands.put(CommandType.EMAIL_VERIFICATION, new EmailVerificationCommand());
    }

    private void addAdminCommands() {
        commands.put(CommandType.ADD_GENRE, new AddGenreCommand());
        commands.put(CommandType.GET_USERS, new GetUsersCommand());
        commands.put(CommandType.ADD_MOVIE, new AddMovieCommand());
        commands.put(CommandType.EDIT_MOVIE, new EditMovieCommand());
        commands.put(CommandType.DELETE_MOVIE, new DeleteMovieCommand());
        commands.put(CommandType.GET_FEEDBACKS, new GetFeedbacksCommand());
        commands.put(CommandType.ADD_MOVIE_COVER, new AddMovieCoverCommand());
        commands.put(CommandType.CHANGE_USER_ROLE, new ChangeUserRoleCommand());
        commands.put(CommandType.CHANGE_USER_STATUS, new ChangeUserStatusCommand());
        commands.put(CommandType.MOVE_TO_ADD_MOVIE_PAGE, new MoveToAddMoviePageCommand());
        commands.put(CommandType.MOVE_TO_ADD_GENRE_PAGE, new MoveToAddGenrePageCommand());
        commands.put(CommandType.CHANGE_FEEDBACK_STATUS, new ChangeFeedbackStatusCommand());
        commands.put(CommandType.MOVE_TO_EDIT_MOVIE_PAGE, new MoveToEditMoviePageCommand());
        commands.put(CommandType.MOVE_TO_ADD_MOVIE_COVER_PAGE, new MoveToAddMovieCoverPageCommand());
    }

    private void addUserCommands() {
        commands.put(CommandType.SIGN_OUT, new SignOutCommand());
        commands.put(CommandType.ADD_FEEDBACK, new AddFeedbackCommand());
        commands.put(CommandType.MOVE_TO_ADD_FEEDBACK_PAGE, new MoveToAddFeedbackPageCommand());
    }

    private void addOprCommands() {
        commands.put(CommandType.MOVE_TO_TOP_METHODS, new MoveToTopMethodsCommand());
        commands.put(CommandType.METHOD_OF_PAIRED_COMPARISONS, new MethodOfPairedComparisonsCommand());
        commands.put(CommandType.METHOD_OF_WEIGHING_ASSESSMENTS, new MethodOfWeighingAssessmentsCommand());
        commands.put(CommandType.GET_TOP_PAIRED_COMPARISONS, new GetTopPairedComparisons());
    }
}
