package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.controller.command.RequestUtil;
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
 * The class DeleteMovieCommand.
 */
public class DeleteMovieCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteMovieCommand.class);
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            Long movieId = requestUtil.getParameterAsLong(request, RequestParameter.MOVIE_ID);
            Movie movie = movieService.findMovieById(movieId);
            movieService.deleteMovie(movie);
            MovieType movieType = movie.getMovieType();
            List<Movie> movieList = movieService.findMoviesByMovieType(movieType);
            List<String> movieCoversList = new ArrayList<>();
            for (Movie currentMovie : movieList) {
                movieCoversList.add(ImageInputStreamUtil.getInstance().addDescriptionToCoverImage(currentMovie.getCover()));
            }
            request.setAttribute(RequestAttribute.MOVIE_COVERS_LIST, movieCoversList);
            request.setAttribute(RequestAttribute.MOVIES_LIST, movieList);
            router = new Router(PagePath.GET_MOVIE_LIST_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at DeleteMovieCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
