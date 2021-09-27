package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditMovieCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        setPageFromAttribute(request);
        try {
            Long movieId = Long.valueOf(request.getParameter(RequestParameter.MOVIE_ID));
            Movie movie = movieService.getMovieById(movieId);
            String newTitle = request.getParameter(RequestParameter.MOVIE_TITLE);
            String newLogline = request.getParameter(RequestParameter.MOVIE_LOGLINE);
            int newReleaseYear = Integer.parseInt(request.getParameter(RequestParameter.MOVIE_RELEASE_YEAR));

            movie.setTitle(newTitle);
            movie.setLogline(newLogline);
            movie.setReleaseYear(newReleaseYear);

            movieService.update(movie);

            request.setAttribute(RequestAttribute.MOVIE, movie);
            setPageToAttribute(request, PagePath.GET_MOVIE_PAGE);
            router = new Router(PagePath.GET_MOVIE_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at EditMovieCommand", e);

            PagePath pageTo = PagePath.valueOf(request.getParameter(RequestParameter.PAGE_FROM));
            setPageToAttribute(request, pageTo);
            router = new Router(pageTo, Router.RouterType.FORWARD);
        }
        return router;
    }
}
