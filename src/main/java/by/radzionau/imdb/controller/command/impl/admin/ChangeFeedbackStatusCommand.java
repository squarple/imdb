package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Feedback;
import by.radzionau.imdb.model.entity.FeedbackStatus;
import by.radzionau.imdb.model.service.FeedbackService;
import by.radzionau.imdb.model.service.impl.FeedbackServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class ChangeFeedbackStatusCommand.
 */
public class ChangeFeedbackStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final FeedbackService feedbackService = FeedbackServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            Long feedbackId = Long.valueOf(request.getParameter(RequestParameter.FEEDBACK_ID));
            Feedback feedback = feedbackService.findFeedbackById(feedbackId);
            //fixme validation
            FeedbackStatus newFeedbackStatus = FeedbackStatus.valueOf(request.getParameter(RequestParameter.FEEDBACK_STATUS).toUpperCase());
            feedbackService.updateFeedbackStatus(feedback, newFeedbackStatus);

            List<Feedback> feedbackList = feedbackService.findFeedbacksByStatus(FeedbackStatus.UNDER_CONSIDERATION);
            request.setAttribute(RequestAttribute.FEEDBACK_LIST, feedbackList);
            router = new Router(PagePath.GET_FEEDBACKS_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at ChangeFeedbackStatusCommand", e);

            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.FORWARD);
        }
        return router;
    }
}
