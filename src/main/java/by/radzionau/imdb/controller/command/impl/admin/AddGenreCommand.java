package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.service.GenreService;
import by.radzionau.imdb.model.service.impl.GenreServiceImpl;
import by.radzionau.imdb.model.validator.GenreValidator;
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
            router = new Router(PagePath.ADD_GENRE_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        return router;
    }
}
