package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.Movie;
import by.radzionau.imdb.model.domain.MovieType;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetMoviesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        setPageFromAttribute(request);
        try {
            MovieType movieType = MovieType.valueOf(request.getParameter(RequestParameter.MOVIE_TYPE).toUpperCase());
            List<Movie> movies = movieService.findMoviesByMovieType(movieType);
            request.setAttribute(RequestAttribute.MOVIES_LIST, movies);

            setPageToAttribute(request, PagePath.GET_MOVIES_PAGE);
            router = new Router(PagePath.GET_MOVIES_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at GetMoviesCommand", e);

            PagePath pageTo = PagePath.valueOf(request.getParameter(RequestParameter.PAGE_TO));
            setPageToAttribute(request, pageTo);
            router = new Router(pageTo, Router.RouterType.FORWARD);
        }

        return router;
    }
}
