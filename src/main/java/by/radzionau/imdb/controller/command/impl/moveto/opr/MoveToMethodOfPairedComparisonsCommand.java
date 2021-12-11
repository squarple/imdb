package by.radzionau.imdb.controller.command.impl.moveto.opr;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.entity.MovieType;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import by.radzionau.imdb.opr.Pair;
import by.radzionau.imdb.opr.PairUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class MoveToMethodOfPairedComparisonsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToMethodOfPairedComparisonsCommand.class);
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            List<Movie> movieList = movieService.findMoviesByMovieType(MovieType.FILM)
                    .stream().limit(5).collect(Collectors.toList());
            List<Pair> pairList = PairUtil.getPairs(movieList);
            request.setAttribute(RequestAttribute.MOVIES_LIST, movieList);
            request.setAttribute(RequestAttribute.PAIR_LIST, pairList);

            request.getSession().setAttribute(SessionAttribute.MOVIES_LIST, movieList);
            request.getSession().setAttribute(SessionAttribute.PAIR_LIST, pairList);

            router = new Router(PagePath.PAIRED_COMPARISONS_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at ResultsOfPairedComparisonsCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
