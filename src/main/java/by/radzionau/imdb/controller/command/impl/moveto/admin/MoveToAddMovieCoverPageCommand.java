package by.radzionau.imdb.controller.command.impl.moveto.admin;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.controller.command.RequestUtil;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class MoveToAddMovieCoverPageCommand.
 */
public class MoveToAddMovieCoverPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToAddMovieCoverPageCommand.class);
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            Long movieId = requestUtil.getParameterAsLong(request, RequestParameter.MOVIE_ID);
            Movie movie = movieService.findMovieById(movieId);
            request.setAttribute(RequestAttribute.MOVIE, movie);
            router = new Router(PagePath.ADD_MOVIE_COVER_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at MoveToAddMovieCoverPageCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
