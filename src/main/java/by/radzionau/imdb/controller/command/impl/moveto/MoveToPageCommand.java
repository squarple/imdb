package by.radzionau.imdb.controller.command.impl.moveto;

import by.radzionau.imdb.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoveToPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        setPageFromAttribute(request);

        PagePath pageTo;
        try {
            pageTo = PagePath.valueOf(request.getParameter(RequestParameter.PAGE_TO).toUpperCase());
        } catch (IllegalArgumentException e) {
            pageTo = PagePath.MAIN_PAGE;
        }
        setPageToAttribute(request, pageTo);

        return new Router(pageTo, Router.RouterType.FORWARD);
    }
}
