package by.radzionau.imdb.controller.command.impl.redirect;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.RequestParameter;
import by.radzionau.imdb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RedirectToPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String pageTo = request.getParameter(RequestParameter.PAGE_TO);
        return new Router(PagePath.valueOf(pageTo.toUpperCase()), Router.RouterType.FORWARD);
    }
}
