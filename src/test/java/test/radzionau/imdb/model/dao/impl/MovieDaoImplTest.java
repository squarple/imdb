package test.radzionau.imdb.model.dao.impl;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.dao.MovieDao;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.entity.MovieType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class MovieDaoImplTest {
    @Test
    void add_Positive() throws DaoException {
        Movie movie = new Movie();

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.add(movie)).thenReturn(1);

        Assertions.assertEquals(1, movieDao.add(movie));
    }

    @Test
    void add_Negative() throws DaoException {
        Movie movie = new Movie();

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.add(movie)).thenReturn(0);

        Assertions.assertNotEquals(1, movieDao.add(movie));
    }

    @Test
    void update_Positive() throws DaoException {
        Movie movie = new Movie();

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.update(movie)).thenReturn(1);

        Assertions.assertEquals(1, movieDao.update(movie));
    }

    @Test
    void update_Negative() throws DaoException {
        Movie movie = new Movie();

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.update(movie)).thenReturn(0);

        Assertions.assertNotEquals(1, movieDao.update(movie));
    }

    @Test
    void delete_Positive() throws DaoException {
        Movie movie = new Movie();

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.delete(movie)).thenReturn(1);

        Assertions.assertEquals(1, movieDao.delete(movie));
    }

    @Test
    void delete_Negative() throws DaoException {
        Movie movie = new Movie();

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.delete(movie)).thenReturn(0);

        Assertions.assertNotEquals(1, movieDao.update(movie));
    }

    @Test
    void findMovieById_Positive() throws DaoException {
        Long movieId = 1L;
        Movie movie = new Movie();

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.findMovieById(movieId)).thenReturn(Optional.of(movie));

        Optional<Movie> optionalMovie = movieDao.findMovieById(movieId);

        Assertions.assertTrue(optionalMovie.isPresent());
    }

    @Test
    void findMovieById_Negative() throws DaoException {
        Long movieId = 1L;

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.findMovieById(movieId)).thenReturn(Optional.empty());

        Optional<Movie> optionalMovie = movieDao.findMovieById(movieId);

        Assertions.assertFalse(optionalMovie.isPresent());
    }

    @Test
    void findMoviesByTitle_Positive() throws DaoException {
        String title = "title";
        List<Movie> movieList = new ArrayList<>();

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.findMoviesByTitle(title)).thenReturn(movieList);

        Assertions.assertEquals(movieList, movieDao.findMoviesByTitle(title));
    }

    @Test
    void findMoviesByYear_Positive() throws DaoException {
        int year = 2016;
        List<Movie> movieList = new ArrayList<>();

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.findMoviesByYear(year)).thenReturn(movieList);

        Assertions.assertEquals(movieList, movieDao.findMoviesByYear(year));
    }

    @Test
    void findMoviesByGenre_Positive() throws DaoException {
        Genre genre = new Genre();
        List<Movie> movieList = new ArrayList<>();

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.findMoviesByGenre(genre)).thenReturn(movieList);

        Assertions.assertEquals(movieList, movieDao.findMoviesByGenre(genre));
    }

    @Test
    void findMoviesByMovieType_Positive() throws DaoException {
        MovieType movieType = MovieType.FILM;
        List<Movie> movieList = new ArrayList<>();

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.findMoviesByMovieType(movieType)).thenReturn(movieList);

        Assertions.assertEquals(movieList, movieDao.findMoviesByMovieType(movieType));
    }

    @Test
    void findMovieScoreByMovieId_Positive() throws DaoException {
        Long movieId = 1L;
        Double score = 5.6d;

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.findMovieScoreByMovieId(movieId)).thenReturn(Optional.of(score));

        Optional<Double> optionalDouble = movieDao.findMovieScoreByMovieId(movieId);

        Assertions.assertTrue(optionalDouble.isPresent());
    }

    @Test
    void findMovieScoreByMovieId_Negative() throws DaoException {
        Long movieId = 1L;

        MovieDao movieDao = Mockito.mock(MovieDao.class);
        Mockito.when(movieDao.findMovieScoreByMovieId(movieId)).thenReturn(Optional.empty());

        Optional<Double> optionalDouble = movieDao.findMovieScoreByMovieId(movieId);

        Assertions.assertFalse(optionalDouble.isPresent());
    }
}
