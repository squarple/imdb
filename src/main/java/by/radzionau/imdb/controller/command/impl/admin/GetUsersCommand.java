package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.User;
import by.radzionau.imdb.model.service.UserService;
import by.radzionau.imdb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class GetUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        setPageFromAttribute(request);
        try {
            List<User> users = userService.findAll();
            removeCurrentUserFromList(request, users);
            request.setAttribute(RequestAttribute.USERS_LIST, users);

            setPageToAttribute(request, PagePath.GET_USERS_PAGE);
            router = new Router(PagePath.GET_USERS_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at GetUsersCommand", e);

            PagePath pageTo = PagePath.valueOf(request.getParameter(RequestParameter.PAGE_TO).toUpperCase());
            setPageToAttribute(request, pageTo);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }

        return router;
    }

    private void removeCurrentUserFromList(HttpServletRequest request, List<User> users) {
        User currentUser = (User) request.getSession().getAttribute(RequestAttribute.USER);
        users.remove(currentUser);
    }
}
