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

import java.io.InputStream;
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
                throw new ServiceException("Can't handle deleteMovieById request at MovieService");
            }
        } catch (DaoException e) {
            logger.error("Can't handle deleteMovieById request at MovieService", e);
            throw new ServiceException("Can't handle deleteMovieById request at MovieService", e);
        }
    }

    @Override
    public List<Movie> searchMoviesByTitle(String title) throws ServiceException {
        List<Movie> movies = new ArrayList<>();
        try {
            movies = movieDao.findMoviesByTitle(title);
        } catch (DaoException e) {
            logger.error("Can't handle searchMoviesByTitle request at MovieService", e);
            throw new ServiceException("Can't handle searchMoviesByTitle request at MovieService", e);
        }
        return movies;
    }

    @Override
    public List<Movie> searchMoviesByYear(int year) throws ServiceException {
        List<Movie> movies = new ArrayList<>();
        try {
            movieDao.findMoviesByYear(year);
        } catch (DaoException e) {
            logger.error("Can't handle searchMoviesByYear request at MovieService", e);
            throw new ServiceException("Can't handle searchMoviesByYear request at MovieService", e);
        }
        return movies;
    }

    @Override
    public List<Movie> searchMoviesByGenre(Genre genre) throws ServiceException {
        List<Movie> movies = new ArrayList<>();
        try {
            movieDao.findMoviesByGenre(genre);
        } catch (DaoException e) {
            logger.error("Can't handle searchMoviesByGenre request at MovieService", e);
            throw new ServiceException("Can't handle searchMoviesByGenre request at MovieService", e);
        }
        return movies;
    }

    @Override
    public List<Movie> searchMoviesByMovieType(MovieType movieType) throws ServiceException {
        List<Movie> movies = new ArrayList<>();
        try {
            movieDao.findMoviesByMovieType(movieType);
        } catch (DaoException e) {
            logger.error("Can't handle searchMoviesByMovieType request at MovieService", e);
            throw new ServiceException("Can't handle searchMoviesByMovieType request at MovieService", e);
        }
        return movies;
    }

    @Override
    public Double getMovieScoreByMovieId(Long movieId) throws ServiceException {
        try {
            Optional<Double> optionalScore = movieDao.findMovieScoreByMovieId(movieId);
            if (optionalScore.isPresent()) {
                return optionalScore.get();
            } else {
                throw new ServiceException("Can't handle getMovieScoreByMovieId request at MovieService");
            }
        } catch (DaoException e) {
            logger.error("Can't handle getMovieScoreByMovieId request at MovieService", e);
            throw new ServiceException("Can't handle getMovieScoreByMovieId request at MovieService", e);
        }
    }

    @Override
    public Double getMovieScore(Movie movie) throws ServiceException {
        return getMovieScoreByMovieId(movie.getMovieId());
    }

    @Override
    public List<Genre> getGenresOfMovie(Movie movie) throws ServiceException {
        List<Genre> genres = new ArrayList<>();
        try {
            genres = genreDao.findGenresOfMovieByMovieId(movie.getMovieId());
        } catch (DaoException e) {
            logger.error("Can't handle getGenresOfMovie request at MovieService", e);
            throw new ServiceException("Can't handle getGenresOfMovie request at MovieService", e);
        }
        return genres;
    }
}
