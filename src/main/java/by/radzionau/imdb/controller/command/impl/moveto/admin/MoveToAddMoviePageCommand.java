package by.radzionau.imdb.controller.command.impl.moveto.admin;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.RequestAttribute;
import by.radzionau.imdb.controller.command.Router;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.entity.MovieType;
import by.radzionau.imdb.model.service.GenreService;
import by.radzionau.imdb.model.service.impl.GenreServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class MoveToAddMoviePageCommand.
 */
public class MoveToAddMoviePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final GenreService genreService = GenreServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            List<Genre> genreList = genreService.findAll();
            request.setAttribute(RequestAttribute.GENRES_LIST, genreList);
            request.setAttribute(RequestAttribute.MOVIE_TYPES_LIST, MovieType.values());
            router = new Router(PagePath.ADD_MOVIE_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at MoveToAddMoviePageCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.FORWARD);
        }

        return router;
    }
}
