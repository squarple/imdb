package test.radzionau.imdb.model.dao.impl;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.dao.MovieDao;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.entity.MovieType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class MovieDaoImplTest {
    private MovieDao movieDao;

    @BeforeEach
    void init() {
        movieDao = Mockito.mock(MovieDao.class);
    }

    @Test
    void add_Positive() {
        Movie movie = new Movie();
        try {
            Mockito.when(movieDao.add(movie)).thenReturn(1);
            Assertions.assertEquals(1, movieDao.add(movie));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void add_Negative() {
        Movie movie = new Movie();
        try {
            Mockito.when(movieDao.add(movie)).thenReturn(0);
            Assertions.assertNotEquals(1, movieDao.add(movie));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void update_Positive() {
        Movie movie = new Movie();
        try {
            Mockito.when(movieDao.update(movie)).thenReturn(1);
            Assertions.assertEquals(1, movieDao.update(movie));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void update_Negative() {
        Movie movie = new Movie();
        try {
            Mockito.when(movieDao.update(movie)).thenReturn(0);
            Assertions.assertNotEquals(1, movieDao.update(movie));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void delete_Positive() {
        Movie movie = new Movie();
        try {
            Mockito.when(movieDao.delete(movie)).thenReturn(1);
            Assertions.assertEquals(1, movieDao.delete(movie));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void delete_Negative() {
        Movie movie = new Movie();
        try {
            Mockito.when(movieDao.delete(movie)).thenReturn(0);
            Assertions.assertNotEquals(1, movieDao.update(movie));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findMovieById_Positive() {
        Long movieId = 1L;
        Movie movie = new Movie();
        try {
            Mockito.when(movieDao.findMovieById(movieId)).thenReturn(Optional.of(movie));
            Optional<Movie> optionalMovie = movieDao.findMovieById(movieId);
            Assertions.assertTrue(optionalMovie.isPresent());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findMovieById_Negative() {
        Long movieId = 1L;
        try {
            Mockito.when(movieDao.findMovieById(movieId)).thenReturn(Optional.empty());
            Optional<Movie> optionalMovie = movieDao.findMovieById(movieId);
            Assertions.assertFalse(optionalMovie.isPresent());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findMoviesByTitle_Positive() {
        String title = "title";
        List<Movie> movieList = new ArrayList<>();
        try {
            Mockito.when(movieDao.findMoviesByTitle(title)).thenReturn(movieList);
            Assertions.assertEquals(movieList, movieDao.findMoviesByTitle(title));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findMoviesByMovieType_Positive() {
        MovieType movieType = MovieType.FILM;
        List<Movie> movieList = new ArrayList<>();
        try {
            Mockito.when(movieDao.findMoviesByMovieType(movieType)).thenReturn(movieList);
            Assertions.assertEquals(movieList, movieDao.findMoviesByMovieType(movieType));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findMovieScoreByMovieId_Positive() {
        Long movieId = 1L;
        Double score = 5.6d;
        try {
            Mockito.when(movieDao.findMovieScoreByMovieId(movieId)).thenReturn(Optional.of(score));
            Optional<Double> optionalDouble = movieDao.findMovieScoreByMovieId(movieId);
            Assertions.assertTrue(optionalDouble.isPresent());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findMovieScoreByMovieId_Negative() {
        Long movieId = 1L;
        try {
            Mockito.when(movieDao.findMovieScoreByMovieId(movieId)).thenReturn(Optional.empty());
            Optional<Double> optionalDouble = movieDao.findMovieScoreByMovieId(movieId);
            Assertions.assertFalse(optionalDouble.isPresent());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }
}
