package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The class SearchMoviesCommand.
 */
public class SearchMoviesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            String searchQuery = request.getParameter(RequestParameter.SEARCH_QUERY);
            if (searchQuery.isEmpty()) {
                String pageTo = getPageFrom(request);
                return new Router(pageTo, Router.RouterType.REDIRECT);
            }

            List<Movie> movieList = movieService.findMoviesByTitle(searchQuery);

            List<String> movieCoversList = new ArrayList<>();
            List<Double> movieRatingList = new ArrayList<>();
            for (Movie movie : movieList) {
                movieCoversList.add(addDescriptionToCoverImage(movie.getCover()));
                movieRatingList.add(movieService.findMovieScore(movie));
            }

            request.setAttribute(RequestAttribute.MOVIES_LIST, movieList);
            request.setAttribute(RequestAttribute.MOVIE_COVERS_LIST, movieCoversList);
            request.setAttribute(RequestAttribute.MOVIE_RATING_LIST, movieRatingList);

            router = new Router(PagePath.SEARCH_MOVIES_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at SearchMoviesCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.FORWARD);
        }
        return router;
    }

    private String addDescriptionToCoverImage(String cover) {
        return "data:image/jpeg;base64," + cover;
    }
}
