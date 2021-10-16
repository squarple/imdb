package by.radzionau.imdb.controller.command.impl.moveto.general;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.RequestParameter;
import by.radzionau.imdb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The class MoveToCommand.
 */
public class MoveToCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        String pageTo = request.getParameter(RequestParameter.PAGE_TO);

        try {
            if (pageTo == null) {
                pageTo = getPageFrom(request);
            } else {
                pageTo = PagePath.valueOf(pageTo.toUpperCase()).getAddress();
            }
        } catch (IllegalArgumentException e) {
            pageTo = getPageFrom(request);
        }

        return new Router(pageTo, Router.RouterType.FORWARD);
    }
}
