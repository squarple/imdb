package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.RequestParameter;
import by.radzionau.imdb.controller.command.Router;
import by.radzionau.imdb.controller.command.Router.RouterType;
import by.radzionau.imdb.controller.command.RequestUtil;
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
    private static final Logger logger = LogManager.getLogger(AddGenreCommand.class);
    private static final GenreService genreService = GenreServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            String genreName = requestUtil.getParameterAsString(request, RequestParameter.MOVIE_GENRE);
            if (genreName.isEmpty() || isGenrePresence(genreName)) {
                return new Router(PagePath.ADD_GENRE_PAGE.getAddress(), RouterType.FORWARD);
            }
            Genre genre = Genre.builder()
                    .setName(genreName)
                    .build();
            genreService.addGenre(genre);
            router = new Router(PagePath.INDEX_PAGE.getAddress(), RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at AddGenreCommand", e);
            router = new Router(PagePath.ADD_GENRE_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        return router;
    }

    public boolean isGenrePresence(String genreName) {
        try {
            genreService.findGenreByName(genreName);
            return true;
        } catch (ServiceException e) {
            return false;
        }
    }
}
