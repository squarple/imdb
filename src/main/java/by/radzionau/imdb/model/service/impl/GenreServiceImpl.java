package by.radzionau.imdb.model.service.impl;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.dao.GenreDao;
import by.radzionau.imdb.model.dao.impl.GenreDaoImpl;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.service.GenreService;
import by.radzionau.imdb.model.validator.GenreValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The implementation of GenreService interface.
 */
public class GenreServiceImpl implements GenreService {
    private static final Logger logger = LogManager.getLogger(GenreServiceImpl.class);
    private final GenreDao genreDao = GenreDaoImpl.getInstance();

    private GenreServiceImpl() {

    }

    private static final class GenreServiceInstanceHolder {
        private static final GenreServiceImpl INSTANCE = new GenreServiceImpl();
    }

    /**
     * Gets instance of genre service.
     *
     * @return the instance of genre service
     */
    public static GenreService getInstance() {
        return GenreServiceImpl.GenreServiceInstanceHolder.INSTANCE;
    }

    @Override
    public void addGenre(Genre genre) throws ServiceException {
        if (!GenreValidator.getInstance().isValid(genre)) {
            logger.error("Invalid genre");
            throw new ServiceException("Invalid genre");
        }
        if (GenreValidator.getInstance().isGenrePresence(genre.getName())) {
            logger.error("Genre already presence");
            throw new ServiceException("Genre already presence");
        }
        try {
            genreDao.add(genre);
        } catch (DaoException e) {
            logger.error("Can't handle addGenre request at GenreService", e);
            throw new ServiceException("Can't handle addGenre request at GenreService", e);
        }
    }

    @Override
    public List<Genre> findAll() throws ServiceException {
        List<Genre> genres;
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
        if (movieId == null) {
            logger.error("MovieId doesn't present");
            throw new ServiceException("MovieId doesn't present");
        }
        List<Genre> genres;
        try {
            genres = genreDao.findGenresOfMovieByMovieId(movieId);
        } catch (DaoException e) {
            logger.error("Can't handle findGenresOfMovieByMovieId request at GenreService", e);
            throw new ServiceException("Can't handle findGenresOfMovieByMovieId request at GenreService", e);
        }
        return genres;
    }

    @Override
    public Genre findGenreByName(String name) throws ServiceException {
        if (name == null || name.isEmpty()) {
            logger.error("Name doesn't present");
            throw new ServiceException("Name doesn't present");
        }
        try {
            Optional<Genre> optionalGenre = genreDao.findGenreByName(name);
            if (optionalGenre.isPresent()) {
                return optionalGenre.get();
            } else {
                logger.error("Genre with name={} not found", name);
                throw new ServiceException("Genre with name=" + name + " not found");
            }
        } catch (DaoException e) {
            logger.error("Can't handle findGenreByName request at GenreService", e);
            throw new ServiceException("Can't handle findGenreByName request at GenreService", e);
        }
    }

    @Override
    public void addGenreForMovieByMovieId(Long movieId, Genre genre) throws ServiceException {
        if (!GenreValidator.getInstance().isValid(genre)) {
            logger.error("Invalid genre");
            throw new ServiceException("Invalid genre");
        }
        if (movieId == null) {
            logger.error("MovieId doesn't present");
            throw new ServiceException("MovieId doesn't present");
        }
        try {
            genreDao.addGenreForMovieByMovieId(movieId, genre);
        } catch (DaoException e) {
            logger.error("Can't handle addGenreForMovieByMovieId request at GenreService", e);
            throw new ServiceException("Can't handle addGenreForMovieByMovieId request at GenreService", e);
        }
    }
}
