package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.User;
import by.radzionau.imdb.model.domain.UserStatus;
import by.radzionau.imdb.service.UserService;
import by.radzionau.imdb.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailVerificationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(RequestAttribute.USER);

        int password = Integer.parseInt(request.getParameter(RequestParameter.PASSWORD));

        Router router;

        if (password == Math.abs(user.hashCode())) {
            router = new Router(PagePath.MAIN_PAGE, Router.RouterType.FORWARD);

            try {
                user = userService.updateStatus(user, UserStatus.ACTIVATED);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            request.getSession().removeAttribute(RequestAttribute.USER);
            request.getSession().setAttribute(RequestAttribute.USER, user);
            request.getSession().setAttribute(RequestAttribute.LOGIN, user.getLogin());
            request.getSession().setAttribute(RequestAttribute.ROLE, user.getRole());
        } else {
            router = new Router(PagePath.VERIFY_EMAIL_PAGE, Router.RouterType.FORWARD);
        }

        return router;
    }
}
