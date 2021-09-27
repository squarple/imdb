package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.service.GenreService;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.GenreServiceImpl;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GetMovieCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final MovieService movieService = MovieServiceImpl.getInstance();
    private static final GenreService genreService = GenreServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        setPageFromAttribute(request);
        try {
            Long movieId = Long.valueOf(request.getParameter(RequestParameter.MOVIE_ID));
            Movie movie = movieService.getMovieById(movieId);//fixme  fix get/find

            List<Genre> genresList = genreService.findGenresOfMovieByMovieId(movieId);
            Double score = movieService.findMovieScore(movie);
            request.setAttribute(RequestAttribute.MOVIE_SCORE, score);
            request.setAttribute(RequestAttribute.MOVIE, movie);
            request.setAttribute(RequestAttribute.GENRES_LIST, genresList);
            setPageToAttribute(request, PagePath.GET_MOVIE_PAGE);
            router = new Router(PagePath.GET_MOVIE_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at GetMovieListCommand", e);

            PagePath pageTo = PagePath.valueOf(request.getParameter(RequestParameter.PAGE_FROM));
            setPageToAttribute(request, pageTo);
            router = new Router(pageTo, Router.RouterType.FORWARD);
        }
        return router;
    }
}
