package by.radzionau.imdb.service.impl;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.dao.GenreDao;
import by.radzionau.imdb.model.dao.impl.GenreDaoImpl;
import by.radzionau.imdb.model.domain.Genre;
import by.radzionau.imdb.service.GenreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GenreServiceImpl implements GenreService {
    private static final Logger logger = LogManager.getLogger();
    private final GenreDao genreDao = GenreDaoImpl.getInstance();

    private GenreServiceImpl() {

    }

    private static final class GenreServiceInstanceHolder {
        private static final GenreServiceImpl INSTANCE = new GenreServiceImpl();
    }

    public static GenreService getInstance() {
        return GenreServiceImpl.GenreServiceInstanceHolder.INSTANCE;
    }

    @Override
    public void addGenre(Genre genre) throws ServiceException {
        try {
            genreDao.add(genre);
        } catch (DaoException e) {
            logger.error("Can't handle addGenre request at GenreService", e);
            throw new ServiceException("Can't handle addGenre request at GenreService", e);
        }
    }

    @Override
    public List<Genre> findAll() throws ServiceException {
        List<Genre> genres = new ArrayList<>();
        try {
            genres = genreDao.findAll();
        } catch (DaoException e) {
            logger.error("Can't handle findAll request at GenreService", e);
            throw new ServiceException("Can't handle findAll request at GenreService", e);
        }
        return genres;
    }

    @Override
    public List<Genre> findGenresOfMovieByMovieId(Long movieId) throws ServiceException {
        List<Genre> genres = new ArrayList<>();
        try {
            genres = genreDao.findGenresOfMovieByMovieId(movieId);
        } catch (DaoException e) {
            logger.error("Can't handle findGenresOfMovieByMovieId request at GenreService", e);
            throw new ServiceException("Can't handle findGenresOfMovieByMovieId request at GenreService", e);
        }
        return genres;
    }
}
