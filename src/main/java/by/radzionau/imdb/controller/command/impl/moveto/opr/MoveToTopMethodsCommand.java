package by.radzionau.imdb.controller.command.impl.moveto.opr;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class MoveToTopMethodsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.TOP_METHODS_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}
