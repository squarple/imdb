package by.radzionau.imdb.controller.command.impl.opr;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.Router;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.*;
import by.radzionau.imdb.model.service.FeedbackService;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.UserService;
import by.radzionau.imdb.model.service.impl.FeedbackServiceImpl;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import by.radzionau.imdb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MethodOfWeighingAssessmentsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MethodOfWeighingAssessmentsCommand.class);
    private static final MovieService movieService = MovieServiceImpl.getInstance();
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final FeedbackService feedbackService = FeedbackServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try{
            List<Movie> movieList = movieService.findMoviesByMovieType(MovieType.FILM).stream().limit(10).collect(Collectors.toList());
            List<User> userList = userService.findAll().stream().filter(user -> user.getRole().equals(UserRole.USER)).collect(Collectors.toList());
            List<Feedback> feedbackList = feedbackService.findFeedbacksByStatus(FeedbackStatus.APPROVED);
            Map<User, List<Double>> userScoresMap = getUserScoresMap(userList, movieList, feedbackList);



            router = new Router(PagePath.TOP_RESULT_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at MethodOfWeighingAssessmentsCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }

    private Map<User, List<Double>> getUserScoresMap(List<User> userList, List<Movie> movieList, List<Feedback> feedbackList) {
        Map<User, List<Double>> userScoresMap = new HashMap<>();
        for (User user : userList) {
            List<Double> userScoresList = new ArrayList<>();
            for (Movie movie : movieList) {
                userScoresList.add((double) feedbackList
                        .stream()
                        .filter(f -> f.getUserId().equals(user.getUserId()))
                        .filter(f -> f.getMovieId().equals(movie.getMovieId()))
                        .findFirst()
                        .orElseGet(() -> Feedback.builder().setScore(0).build())
                        .getScore());
            }
            userScoresMap.put(user, userScoresList);
        }
        return userScoresMap;
    }
}
