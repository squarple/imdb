package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.RequestParameter;
import by.radzionau.imdb.controller.command.Router;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.service.GenreService;
import by.radzionau.imdb.model.service.impl.GenreServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class AddGenreCommand.
 */
public class AddGenreCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final GenreService genreService = GenreServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            String genreName = request.getParameter(RequestParameter.MOVIE_GENRE);
            Genre genre = Genre.builder()
                    .setName(genreName)
                    .build();
            genreService.addGenre(genre);

            router = new Router(PagePath.MAIN_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at AddGenreCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.FORWARD);
        }
        return router;
    }
}
