package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.RequestParameter;
import by.radzionau.imdb.controller.command.Router;
import by.radzionau.imdb.controller.command.Router.RouterType;
import by.radzionau.imdb.controller.command.util.RequestUtil;
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
    private static final RequestUtil requestUtil = RequestUtil.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            String genreName = requestUtil.getString(request, RequestParameter.MOVIE_GENRE);
            if (genreName.isEmpty()) {
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
}
