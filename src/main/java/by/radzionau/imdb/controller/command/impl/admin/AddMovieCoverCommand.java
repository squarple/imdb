package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.controller.command.util.RequestUtil;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import by.radzionau.imdb.util.ImageInputStreamUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

/**
 * The class AddMovieCoverCommand.
 */
public class AddMovieCoverCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddMovieCoverCommand.class);
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        RequestUtil requestUtil = RequestUtil.getInstance();
        Router router;
        try {
            String cover = getMovieCover(request);
            Long movieId = requestUtil.getLong(request, RequestParameter.MOVIE_ID);
            Movie movie = movieService.findMovieById(movieId);
            movie.setCover(cover);
            movieService.update(movie);
            request.setAttribute(RequestAttribute.MOVIE, movie);
            request.setAttribute(RequestAttribute.MOVIE_COVER, addDescriptionToCoverImage(movie.getCover()));
            router = new Router(PagePath.GET_MOVIE_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException | ServletException | IOException e) {
            logger.error("Error at AddMovieCoverCommand", e);
            router = new Router(PagePath.INDEX_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        return router;
    }

    private String getMovieCover(HttpServletRequest request) throws ServletException, IOException {
        Optional<Part> part = request.getParts().stream()
                .filter(p -> p.getName().equals(RequestParameter.MOVIE_COVER)).findFirst();
        if (part.isPresent()) {
            return ImageInputStreamUtil.getInstance().getImageInputStreamAsString(part.get().getInputStream());
        } else {
            return "";
        }
    }

    private String addDescriptionToCoverImage(String cover) {
        return "data:image/jpeg;base64," + cover;
    }
}
