package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.entity.MovieType;
import by.radzionau.imdb.model.service.GenreService;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.GenreServiceImpl;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class AddMovieCommand.
 */
public class AddMovieCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final MovieService movieService = MovieServiceImpl.getInstance();
    private static final GenreService genreService = GenreServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            String title = request.getParameter(RequestParameter.MOVIE_TITLE);
            String logline = request.getParameter(RequestParameter.MOVIE_TITLE);
            String genreName = request.getParameter(RequestParameter.MOVIE_GENRE);
            int releaseYear = Integer.parseInt(request.getParameter(RequestParameter.MOVIE_RELEASE_YEAR));
            MovieType movieType = MovieType.valueOf(request.getParameter(RequestParameter.MOVIE_TYPE).toUpperCase());

            Genre genre = genreService.findGenreByName(genreName);

            Movie movie = Movie.builder()
                    .setTitle(title)
                    .setLogline(logline)
                    .setReleaseYear(releaseYear)
                    .setCover(null)
                    .setMovieType(movieType)
                    .build();

            movieService.addMovie(movie);

            genreService.addGenreForMovieByMovieId(movie.getMovieId(), genre);

            request.setAttribute(RequestAttribute.MOVIE, movie);
            router = new Router(PagePath.ADD_MOVIE_COVER_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at AddMovieCommand", e);

            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.FORWARD);
        }
        return router;
    }
}
