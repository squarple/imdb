package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class EditMovieCommand.
 */
public class EditMovieCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            Long movieId = Long.valueOf(request.getParameter(RequestParameter.MOVIE_ID));
            Movie movie = movieService.findMovieById(movieId);
            String newTitle = request.getParameter(RequestParameter.MOVIE_TITLE);
            String newLogline = request.getParameter(RequestParameter.MOVIE_LOGLINE);
            int newReleaseYear = Integer.parseInt(request.getParameter(RequestParameter.MOVIE_RELEASE_YEAR));

            movie.setTitle(newTitle);
            movie.setLogline(newLogline);
            movie.setReleaseYear(newReleaseYear);

            movieService.update(movie);

            request.setAttribute(RequestAttribute.MOVIE_COVER, addDescriptionToCoverImage(movie.getCover()));
            request.setAttribute(RequestAttribute.MOVIE, movie);
            router = new Router(PagePath.GET_MOVIE_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at EditMovieCommand", e);

            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.FORWARD);
        }
        return router;
    }

    private String addDescriptionToCoverImage(String cover) {
        return "data:image/jpeg;base64," + cover;
    }
}
