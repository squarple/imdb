package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.User;
import by.radzionau.imdb.model.domain.UserStatus;
import by.radzionau.imdb.model.service.UserService;
import by.radzionau.imdb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailVerificationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        setPageFromAttribute(request);

        try {
            User user = (User) request.getSession().getAttribute(RequestAttribute.USER);

            int password = Integer.parseInt(request.getParameter(RequestParameter.PASSWORD));

            if (user != null && isPasswordCorrect(user, password)) {
                user = userService.updateStatus(user, UserStatus.ACTIVATED);

                setSessionAttributes(request, user);

                setPageToAttribute(request, PagePath.MAIN_PAGE);
                router = new Router(PagePath.MAIN_PAGE, Router.RouterType.FORWARD);
            } else {
                setPageToAttribute(request, PagePath.VERIFY_EMAIL_PAGE);
                router = new Router(PagePath.VERIFY_EMAIL_PAGE, Router.RouterType.FORWARD);
            }
        } catch (NumberFormatException e) {
            logger.error("Wrong request parameter", e);

            setPageToAttribute(request, PagePath.VERIFY_EMAIL_PAGE);
            router = new Router(PagePath.VERIFY_EMAIL_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at EmailVerificationCommand", e);

            setPageToAttribute(request, PagePath.VERIFY_EMAIL_PAGE);
            router = new Router(PagePath.VERIFY_EMAIL_PAGE, Router.RouterType.FORWARD);
        }
        return router;
    }

    private void setSessionAttributes(HttpServletRequest request, User user) {
        request.getSession().removeAttribute(RequestAttribute.USER);
        request.getSession().setAttribute(RequestAttribute.USER, user);

        request.getSession().setAttribute(RequestAttribute.LOGIN, user.getLogin());
        request.getSession().setAttribute(RequestAttribute.ROLE, user.getRole());
    }

    private boolean isPasswordCorrect(User user, int password) {
        return password == Math.abs(user.hashCode());
    }
}
