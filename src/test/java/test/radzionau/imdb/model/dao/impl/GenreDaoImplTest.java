package test.radzionau.imdb.model.dao.impl;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.dao.GenreDao;
import by.radzionau.imdb.model.entity.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class GenreDaoImplTest {
    private GenreDao genreDao;

    @BeforeEach
    void init() {
        genreDao = Mockito.mock(GenreDao.class);
    }

    @Test
    void add_Positive() {
        Genre genre = new Genre();
        try {
            Mockito.when(genreDao.add(genre)).thenReturn(1);
            Assertions.assertEquals(1, genreDao.add(genre));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void add_Negative() {
        Genre genre = new Genre();
        try {
            Mockito.when(genreDao.add(genre)).thenReturn(0);
            Assertions.assertNotEquals(1, genreDao.add(genre));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findAll_Positive() {
        List<Genre> genreList = new ArrayList<>();
        try {
            Mockito.when(genreDao.findAll()).thenReturn(genreList);
            Assertions.assertEquals(genreList, genreDao.findAll());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findGenresOfMovieByMovieId_Positive() {
        Long movieId = 1L;
        List<Genre> genreList = new ArrayList<>();
        try {
            Mockito.when(genreDao.findGenresOfMovieByMovieId(movieId)).thenReturn(genreList);
            Assertions.assertEquals(genreList, genreDao.findGenresOfMovieByMovieId(movieId));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findGenreByName_Positive() {
        String genreName = "genre";
        Genre genre = new Genre();
        try {
            Mockito.when(genreDao.findGenreByName(genreName)).thenReturn(Optional.of(genre));
            Optional<Genre> optionalGenre = genreDao.findGenreByName(genreName);
            Assertions.assertTrue(optionalGenre.isPresent());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findGenreByName_Negative() {
        String genreName = "genre";
        try {
            Mockito.when(genreDao.findGenreByName(genreName)).thenReturn(Optional.empty());
            Optional<Genre> optionalGenre = genreDao.findGenreByName(genreName);
            Assertions.assertFalse(optionalGenre.isPresent());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void addGenreForMovieByMovieId_Positive() {
        Long movieId = 1L;
        Genre genre = new Genre();
        GenreDao genreDao = Mockito.mock(GenreDao.class);
        try {
            Mockito.when(genreDao.addGenreForMovieByMovieId(movieId, genre)).thenReturn(1);
            Assertions.assertEquals(1, genreDao.addGenreForMovieByMovieId(movieId, genre));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void addGenreForMovieByMovieId_Negative() {
        Long movieId = 1L;
        Genre genre = new Genre();
        GenreDao genreDao = Mockito.mock(GenreDao.class);
        try {
            Mockito.when(genreDao.addGenreForMovieByMovieId(movieId, genre)).thenReturn(0);
            Assertions.assertNotEquals(1, genreDao.addGenreForMovieByMovieId(movieId, genre));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }
}
