package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.controller.command.util.RequestUtil;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.*;
import by.radzionau.imdb.model.service.FeedbackService;
import by.radzionau.imdb.model.service.GenreService;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.FeedbackServiceImpl;
import by.radzionau.imdb.model.service.impl.GenreServiceImpl;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The class GetMovieCommand.
 */
public class GetMovieCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GetMovieCommand.class);
    private static final MovieService movieService = MovieServiceImpl.getInstance();
    private static final GenreService genreService = GenreServiceImpl.getInstance();
    private static final FeedbackService feedbackService = FeedbackServiceImpl.getInstance();
    private static final RequestUtil requestUtil = RequestUtil.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            Long movieId = requestUtil.getLong(request, RequestParameter.MOVIE_ID);
            Movie movie = movieService.findMovieById(movieId);
            List<Genre> genresList = genreService.findGenresOfMovieByMovieId(movieId);
            Double score = movieService.findMovieScore(movie);
            request.setAttribute(RequestAttribute.MOVIE_SCORE, score);
            request.setAttribute(RequestAttribute.MOVIE, movie);
            request.setAttribute(RequestAttribute.GENRES_LIST, genresList);
            request.setAttribute(RequestAttribute.MOVIE_COVER, addDescriptionToCoverImage(movie.getCover()));

            User currentUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
            if (currentUser != null && currentUser.getRole() == UserRole.USER) {
                List<Feedback> feedbackList = feedbackService.findFeedbacksByMovieId(movieId)
                        .stream()
                        .filter(feedback -> feedback.getFeedbackStatus().equals(FeedbackStatus.APPROVED))
                        .collect(Collectors.toList());
                request.setAttribute(RequestAttribute.FEEDBACK_LIST, feedbackList);
                Optional<Feedback> feedbackOfCurrentUser = feedbackList.stream().filter(feedback -> feedback.getUserId().equals(currentUser.getUserId())).findFirst();
                boolean isUserAlreadyMadeFeedback = feedbackOfCurrentUser.isPresent();
                request.setAttribute(RequestAttribute.IS_USER_ALREADY_MADE_REVIEW, isUserAlreadyMadeFeedback);
            }
            router = new Router(PagePath.GET_MOVIE_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at GetMovieListCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.FORWARD);
        }
        return router;
    }

    private String addDescriptionToCoverImage(String cover) {
        return "data:image/jpeg;base64," + cover;
    }
}
