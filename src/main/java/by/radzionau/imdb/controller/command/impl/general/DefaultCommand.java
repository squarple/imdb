package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The class DefaultCommand.
 */
public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        String pageTo = getPageFrom(request);
        return new Router(pageTo, Router.RouterType.FORWARD);
    }
}
