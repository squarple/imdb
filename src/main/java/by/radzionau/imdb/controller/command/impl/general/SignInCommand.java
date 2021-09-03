package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.User;
import by.radzionau.imdb.service.UserService;
import by.radzionau.imdb.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String username = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        UserService service = UserServiceImpl.getInstance();
        try {
            User user = service.signIn(username, password);

            request.getSession().setAttribute(RequestAttribute.USER, user);
            request.getSession().setAttribute(RequestAttribute.ROLE, user.getRole());

            //todo login and password -> cookie

            router = new Router(PagePath.MAIN_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at SignInCommand", e);
            router = new Router(PagePath.LOGIN_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
