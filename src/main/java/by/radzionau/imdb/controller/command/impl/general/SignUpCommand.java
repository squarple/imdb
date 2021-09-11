package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.User;
import by.radzionau.imdb.model.service.UserService;
import by.radzionau.imdb.model.service.impl.UserServiceImpl;
import by.radzionau.imdb.util.mail.EmailSenderUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final EmailSenderUtil emailSenderUtil = EmailSenderUtil.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        setPageFromAttribute(request);

        //Map<String, String> signupParameters = getSignupParameters(request);

        try {
            User user = signUpUser(request);

            emailSenderUtil.sendAuthenticationMessage(user);

            request.getSession().setAttribute(RequestAttribute.USER, user);

            setPageToAttribute(request, PagePath.VERIFY_EMAIL_PAGE);
            router = new Router(PagePath.VERIFY_EMAIL_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at SignUpCommand", e);

            setPageToAttribute(request, PagePath.SIGNUP_PAGE);
            router = new Router(PagePath.SIGNUP_PAGE, Router.RouterType.FORWARD);
        } catch (MessagingException | IOException e) {
            logger.error("Error at SignUpCommand", e);

            setPageToAttribute(request, PagePath.SIGNUP_PAGE);
            router = new Router(PagePath.SIGNUP_PAGE, Router.RouterType.FORWARD);
        }
        return router;
    }

    private User signUpUser(HttpServletRequest request) throws ServiceException {
        return userService.signUp(
                request.getParameter(RequestParameter.LOGIN),
                request.getParameter(RequestParameter.PASSWORD),
                request.getParameter(RequestParameter.REPEATED_PASSWORD),
                request.getParameter(RequestParameter.EMAIL),
                request.getParameter(RequestParameter.FIRST_NAME),
                request.getParameter(RequestParameter.SURNAME)
        );
    }

    private Map<String, String> getSignupParameters(HttpServletRequest request) {
        Map<String, String> signUpParameters = new HashMap<>();
        signUpParameters.put(RequestParameter.LOGIN, request.getParameter(RequestParameter.LOGIN));
        signUpParameters.put(RequestParameter.PASSWORD, request.getParameter(RequestParameter.PASSWORD));
        signUpParameters.put(RequestParameter.REPEATED_PASSWORD, request.getParameter(RequestParameter.REPEATED_PASSWORD));
        signUpParameters.put(RequestParameter.EMAIL, request.getParameter(RequestParameter.EMAIL));
        signUpParameters.put(RequestParameter.FIRST_NAME, request.getParameter(RequestParameter.FIRST_NAME));
        signUpParameters.put(RequestParameter.SURNAME, request.getParameter(RequestParameter.SURNAME));
        return signUpParameters;
    }

    private void setSignupParameters(HttpServletRequest request, Map<String, String> signupParameters) {
        //signupParameters.forEach(request::setAttribute);

        for (Map.Entry<String, String> entry : signupParameters.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }

}
