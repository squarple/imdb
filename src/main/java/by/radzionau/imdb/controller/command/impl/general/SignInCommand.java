package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.User;
import by.radzionau.imdb.model.service.impl.UserServiceImpl;
import by.radzionau.imdb.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService service = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        setPageFromAttribute(request);

        try {
            User user = service.signIn(login, password);
            //todo is activated???
            setSessionAttributes(request, user);

            setPageToAttribute(request, PagePath.MAIN_PAGE);
            router = new Router(PagePath.MAIN_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at SignInCommand", e);

            setPageToAttribute(request, PagePath.LOGIN_PAGE);
            router = new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
        }
        return router;
    }

    private void setSessionAttributes(HttpServletRequest request, User user) {
        request.getSession().setAttribute(RequestAttribute.USER, user);
        request.getSession().setAttribute(RequestAttribute.LOGIN, user.getLogin());
        request.getSession().setAttribute(RequestAttribute.ROLE, user.getRole());
    }
}
