package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.*;
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
    private static final Logger logger = LogManager.getLogger();
    private static final ImageInputStreamUtil inputStreamUtil = ImageInputStreamUtil.getInstance();
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String cover = null;
        try {
            cover = getMovieCover(request);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            //todo
        }

        try {
            Long movieId = Long.valueOf(request.getParameter(RequestParameter.MOVIE_ID));
            Movie movie = movieService.findMovieById(movieId);
            movie.setCover(cover/*Stream*/);
            movieService.update(movie);

            request.setAttribute(RequestAttribute.MOVIE, movie);
            request.setAttribute(RequestAttribute.MOVIE_COVER, addDescriptionToCoverImage(movie.getCover()));
            router = new Router(PagePath.GET_MOVIE_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at AddMovieCoverCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.FORWARD);
        }
        return router;
    }

    private String getMovieCover(HttpServletRequest request) throws ServletException, IOException {
        Optional<Part> part = request.getParts().stream()
                .filter(p -> p.getName().equals(RequestParameter.MOVIE_COVER)).findFirst();
        if (part.isPresent()) {
            return inputStreamUtil.getImageInputStreamAsString(part.get().getInputStream());
        } else {
            return "";
        }
    }

    private String addDescriptionToCoverImage(String cover) {
        return "data:image/jpeg;base64," + cover;
    }
}
