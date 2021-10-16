package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.User;
import by.radzionau.imdb.model.entity.UserStatus;
import by.radzionau.imdb.model.service.UserService;
import by.radzionau.imdb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class EmailVerificationCommand.
 */
public class EmailVerificationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        try {
            User user = (User) request.getSession().getAttribute(SessionAttribute.USER);

            int password = Integer.parseInt(request.getParameter(RequestParameter.PASSWORD));

            if (user != null && isPasswordCorrect(user, password)) {
                user = userService.updateStatus(user, UserStatus.ACTIVATED);

                setSessionAttributes(request, user);

                router = new Router(PagePath.MAIN_PAGE.getAddress(), Router.RouterType.FORWARD);
            } else {
                router = new Router(PagePath.VERIFY_EMAIL_PAGE.getAddress(), Router.RouterType.FORWARD);
            }
        } catch (NumberFormatException e) {
            logger.error("Wrong request parameter", e);

            router = new Router(PagePath.VERIFY_EMAIL_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at EmailVerificationCommand", e);
            router = new Router(PagePath.VERIFY_EMAIL_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        return router;
    }

    private void setSessionAttributes(HttpServletRequest request, User user) {
        request.getSession().removeAttribute(SessionAttribute.USER);
        request.getSession().setAttribute(SessionAttribute.USER, user);

        request.getSession().setAttribute(SessionAttribute.LOGIN, user.getLogin());
        request.getSession().setAttribute(SessionAttribute.ROLE, user.getRole());
    }

    private boolean isPasswordCorrect(User user, int password) {
        return password == Math.abs(user.hashCode());
    }
}
