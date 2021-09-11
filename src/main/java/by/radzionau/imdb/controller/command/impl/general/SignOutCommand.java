package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        deleteSession(request);
        createNewSession(request);

        setPageFromAttribute(request);
        setPageToAttribute(request, PagePath.MAIN_PAGE);

        return new Router(PagePath.MAIN_PAGE, Router.RouterType.REDIRECT);
    }

    private void deleteSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    private void createNewSession(HttpServletRequest request) {
        request.getSession();
    }
}
