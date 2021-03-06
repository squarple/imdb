package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.controller.command.RequestUtil;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import by.radzionau.imdb.util.ImageInputStreamUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class EditMovieCommand.
 */
public class EditMovieCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditMovieCommand.class);
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            Long movieId = requestUtil.getParameterAsLong(request, RequestParameter.MOVIE_ID);
            Movie movie = movieService.findMovieById(movieId);
            String newTitle = requestUtil.getParameterAsString(request, RequestParameter.MOVIE_TITLE);
            String newLogline = requestUtil.getParameterAsString(request, RequestParameter.MOVIE_LOGLINE);
            int newReleaseYear = requestUtil.getParameterAsInt(request, RequestParameter.MOVIE_RELEASE_YEAR);
            movie.setTitle(newTitle);
            movie.setLogline(newLogline);
            movie.setReleaseYear(newReleaseYear);
            movieService.update(movie);
            request.setAttribute(RequestAttribute.MOVIE_COVER, ImageInputStreamUtil.getInstance().addDescriptionToCoverImage(movie.getCover()));
            request.setAttribute(RequestAttribute.MOVIE, movie);
            router = new Router(PagePath.GET_MOVIE_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at EditMovieCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
