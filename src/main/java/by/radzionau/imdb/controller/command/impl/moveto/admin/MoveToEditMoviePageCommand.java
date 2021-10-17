package by.radzionau.imdb.controller.command.impl.moveto.admin;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.controller.command.util.RequestUtil;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class MoveToEditMoviePageCommand.
 */
public class MoveToEditMoviePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToEditMoviePageCommand.class);
    private static final MovieService movieService = MovieServiceImpl.getInstance();
    private static final RequestUtil requestUtil = RequestUtil.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            Long movieId = requestUtil.getLong(request, RequestParameter.MOVIE_ID);
            Movie movie = movieService.findMovieById(movieId);
            request.setAttribute(RequestAttribute.MOVIE, movie);
            router = new Router(PagePath.EDIT_MOVIE_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at MoveToEditMoviePageCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.FORWARD);
        }
        return router;
    }
}
