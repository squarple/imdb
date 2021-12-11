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
import by.radzionau.imdb.util.ImageInputStreamUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class ResultsOfWeighingAssessmentsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ResultsOfWeighingAssessmentsCommand.class);
    private static final MovieService movieService = MovieServiceImpl.getInstance();
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final FeedbackService feedbackService = FeedbackServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try{
            List<Movie> movieList = movieService.findMoviesByMovieType(MovieType.FILM).stream()
                    .limit(10)
                    .collect(Collectors.toList());
            for (Movie movie : movieList) {
                movie.setCover(ImageInputStreamUtil.getInstance().addDescriptionToCoverImage(movie.getCover()));
            }
            List<User> userList = userService.findAll().stream()
                    .filter(user -> user.getRole().equals(UserRole.USER))
                    .collect(Collectors.toList());
            List<Feedback> feedbackList = feedbackService.findFeedbacksByStatus(FeedbackStatus.APPROVED);
            Map<User, Map<Movie, Double>> userScoresMap = getUserScoresMap(userList, movieList, feedbackList);
            Map<User, Double> userCompetencyMap = getUserCompetency(userList);
            Map<Movie, Double> movieWeightMap = getMovieWeights(movieList, userList, userScoresMap, userCompetencyMap);

            Map<Double, Movie> resultMap = new TreeMap<>(Comparator.reverseOrder());
            movieWeightMap.keySet().forEach(e -> resultMap.put(movieWeightMap.get(e), e));

            //todo вынести название of attribute!!!!!!!
            request.setAttribute("movie_weight_map", resultMap);

            router = new Router(PagePath.RESULTS_OF_WEIGHING_ASSESSMENTS.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at ResultsOfWeighingAssessmentsCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }

    private Map<Movie, Double> getMovieWeights(List<Movie> movieList, List<User> userList, Map<User, Map<Movie, Double>> userScoresMap, Map<User, Double> userCompetencyMap) {
        Map<Movie, Double> movieWeightMap = new HashMap<>();
        for (Movie movie : movieList) {
            double movieWeight = 0d;
            for (User user : userList) {
                movieWeight += userScoresMap.get(user).get(movie) * userCompetencyMap.get(user);
            }
            movieWeightMap.put(movie, movieWeight);
        }

        return movieWeightMap;
    }

    private Map<User, Double> getUserCompetency(List<User> userList) {
        Map<User, Double> userCompetencyMap = new HashMap<>();
        userList.forEach(e -> userCompetencyMap.put(e, userService.getUserWeight(e.getUserId()).doubleValue()));
        Double sum = userCompetencyMap.values().stream().mapToDouble(e -> e).sum();
        for (User user : userList) {
            Double newValue = userCompetencyMap.get(user) / sum;
            userCompetencyMap.put(user, newValue);
        }
        return userCompetencyMap;
    }

    private Map<User, Map<Movie, Double>> getUserScoresMap(List<User> userList, List<Movie> movieList, List<Feedback> feedbackList) {
        Map<User, Map<Movie, Double>> userScoresMap = new HashMap<>();
        for (User user : userList) {
            Map<Movie, Double> userMovieScoresMap = new HashMap<>();
            for (Movie movie : movieList) {
                userMovieScoresMap.put(movie, (double) feedbackList
                        .stream()
                        .filter(f -> f.getUserId().equals(user.getUserId()))
                        .filter(f -> f.getMovieId().equals(movie.getMovieId()))
                        .findFirst()
                        .orElseGet(() -> Feedback.builder().setScore(0).build())
                        .getScore());
            }
            userScoresMap.put(user, userMovieScoresMap);
        }

        for (Map<Movie, Double> valueMap : userScoresMap.values()) {
            Double sum = valueMap.values().stream().mapToDouble(e -> e).sum();
            for (Movie movie : movieList) {
                Double newValue = valueMap.get(movie) / sum;
                valueMap.put(movie, newValue);
            }
        }
        return userScoresMap;
    }
}
