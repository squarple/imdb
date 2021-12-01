package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.entity.MovieType;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import by.radzionau.imdb.util.ImageInputStreamUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The class GetMovieListCommand.
 */
public class GetMovieListCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GetMovieListCommand.class);
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            MovieType movieType = requestUtil.getParameterAsMovieType(request);
            List<Movie> movies = movieService.findMoviesByMovieType(movieType);
            List<String> movieCoversList = new ArrayList<>();
            List<Double> movieRatingList = new ArrayList<>();
            for (Movie movie : movies) {
                movieCoversList.add(ImageInputStreamUtil.getInstance().addDescriptionToCoverImage(movie.getCover()));
                movieRatingList.add(movieService.findMovieScore(movie));
            }
            request.setAttribute(RequestAttribute.MOVIES_LIST, movies);
            request.setAttribute(RequestAttribute.MOVIE_COVERS_LIST, movieCoversList);
            request.setAttribute(RequestAttribute.MOVIE_RATING_LIST, movieRatingList);
            router = new Router(PagePath.GET_MOVIE_LIST_PAGE.getAddress(), Router.RouterType.FORWARD);

        } catch (ServiceException e) {
            logger.error("Error at GetMovieListCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
