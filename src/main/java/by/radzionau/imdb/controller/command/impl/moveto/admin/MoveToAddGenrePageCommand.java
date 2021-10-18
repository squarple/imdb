package by.radzionau.imdb.controller.command.impl.moveto.admin;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The class MoveToAddGenrePageCommand.
 */
public class MoveToAddGenrePageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.ADD_GENRE_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}
