package by.radzionau.imdb.service.impl;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.dao.GenreDao;
import by.radzionau.imdb.model.dao.MovieDao;
import by.radzionau.imdb.model.dao.impl.GenreDaoImpl;
import by.radzionau.imdb.model.dao.impl.MovieDaoImpl;
import by.radzionau.imdb.model.domain.Genre;
import by.radzionau.imdb.model.domain.Movie;
import by.radzionau.imdb.model.domain.MovieType;
import by.radzionau.imdb.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//todo MovieServiceImpl validation

public class MovieServiceImpl implements MovieService {
    private static final Logger logger = LogManager.getLogger();
    private final MovieDao movieDao = MovieDaoImpl.getInstance();
    private final GenreDao genreDao = GenreDaoImpl.getInstance();

    private MovieServiceImpl() {

    }

    private static final class MovieServiceInstanceHolder {
        private static final MovieServiceImpl INSTANCE = new MovieServiceImpl();
    }

    public static MovieService getInstance() {
        return MovieServiceImpl.MovieServiceInstanceHolder.INSTANCE;
    }

    @Override
    public void addMovie(Movie movie) throws ServiceException {
        try {
            movieDao.add(movie);
        } catch (DaoException e) {
            logger.error("Can't handle addMovie request at MovieService", e);
            throw new ServiceException("Can't handle addMovie request at MovieService", e);
        }
    }

    @Override
    public void update(Movie movie) throws ServiceException {
        try {
            movieDao.update(movie);
        } catch (DaoException e) {
            logger.error("Can't handle update request at MovieService", e);
            throw new ServiceException("Can't handle update request at MovieService", e);
        }
    }

    @Override
    public void deleteMovie(Movie movie) throws ServiceException {
        try {
            movieDao.delete(movie);
        } catch (DaoException e) {
            logger.error("Can't handle deleteMovie request at MovieService", e);
            throw new ServiceException("Can't handle deleteMovie request at MovieService", e);
        }
    }

    @Override
    public Movie getMovieById(Long movieId) throws ServiceException {
        try {
            Optional<Movie> optionalMovie = movieDao.findMovieById(movieId);
            if (optionalMovie.isPresent()) {
                return optionalMovie.get();
            } else {
                throw new ServiceException("Can't handle getMovieById request at MovieService");
            }
        } catch (DaoException e) {
            logger.error("Can't handle getMovieById request at MovieService", e);
            throw new ServiceException("Can't handle getMovieById request at MovieService", e);
        }
    }

    @Override
    public List<Movie> findMoviesByTitle(String title) throws ServiceException {
        List<Movie> movies = new ArrayList<>();
        try {
            movies = movieDao.findMoviesByTitle(title);
        } catch (DaoException e) {
            logger.error("Can't handle findMoviesByTitle request at MovieService", e);
            throw new ServiceException("Can't handle findMoviesByTitle request at MovieService", e);
        }
        return movies;
    }

    @Override
    public List<Movie> findMoviesByYear(int year) throws ServiceException {
        List<Movie> movies = new ArrayList<>();
        try {
            movies = movieDao.findMoviesByYear(year);
        } catch (DaoException e) {
            logger.error("Can't handle findMoviesByYear request at MovieService", e);
            throw new ServiceException("Can't handle findMoviesByYear request at MovieService", e);
        }
        return movies;
    }

    @Override
    public List<Movie> findMoviesByGenre(Genre genre) throws ServiceException {
        List<Movie> movies = new ArrayList<>();
        try {
            movies = movieDao.findMoviesByGenre(genre);
        } catch (DaoException e) {
            logger.error("Can't handle findMoviesByGenre request at MovieService", e);
            throw new ServiceException("Can't handle findMoviesByGenre request at MovieService", e);
        }
        return movies;
    }

    @Override
    public List<Movie> findMoviesByMovieType(MovieType movieType) throws ServiceException {
        List<Movie> movies = new ArrayList<>();
        try {
            movies = movieDao.findMoviesByMovieType(movieType);
        } catch (DaoException e) {
            logger.error("Can't handle findMoviesByMovieType request at MovieService", e);
            throw new ServiceException("Can't handle findMoviesByMovieType request at MovieService", e);
        }
        return movies;
    }

    @Override
    public Double findMovieScoreByMovieId(Long movieId) throws ServiceException {
        try {
            Optional<Double> optionalScore = movieDao.findMovieScoreByMovieId(movieId);
            if (optionalScore.isPresent()) {
                return optionalScore.get();
            } else {
                throw new ServiceException("Can't handle findMovieScoreByMovieId request at MovieService");
            }
        } catch (DaoException e) {
            logger.error("Can't handle findMovieScoreByMovieId request at MovieService", e);
            throw new ServiceException("Can't handle findMovieScoreByMovieId request at MovieService", e);
        }
    }

    @Override
    public Double findMovieScore(Movie movie) throws ServiceException {
        return findMovieScoreByMovieId(movie.getMovieId());
    }

    @Override
    public List<Genre> findGenresOfMovie(Movie movie) throws ServiceException {
        List<Genre> genres = new ArrayList<>();
        try {
            genres = genreDao.findGenresOfMovieByMovieId(movie.getMovieId());
        } catch (DaoException e) {
            logger.error("Can't handle findGenresOfMovie request at MovieService", e);
            throw new ServiceException("Can't handle findGenresOfMovie request at MovieService", e);
        }
        return genres;
    }
}
