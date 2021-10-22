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
    void add_Positive() throws DaoException {
        Genre genre = new Genre();
        Mockito.when(genreDao.add(genre)).thenReturn(1);
        Assertions.assertEquals(1, genreDao.add(genre));
    }

    @Test
    void add_Negative() throws DaoException {
        Genre genre = new Genre();
        Mockito.when(genreDao.add(genre)).thenReturn(0);
        Assertions.assertNotEquals(1, genreDao.add(genre));
    }

    @Test
    void findAll_Positive() throws DaoException {
        List<Genre> genreList = new ArrayList<>();
        Mockito.when(genreDao.findAll()).thenReturn(genreList);
        Assertions.assertEquals(genreList, genreDao.findAll());
    }

    @Test
    void findGenresOfMovieByMovieId_Positive() throws DaoException {
        Long movieId = 1L;
        List<Genre> genreList = new ArrayList<>();
        Mockito.when(genreDao.findGenresOfMovieByMovieId(movieId)).thenReturn(genreList);
        Assertions.assertEquals(genreList, genreDao.findGenresOfMovieByMovieId(movieId));
    }

    @Test
    void findGenreByName_Positive() throws DaoException {
        String genreName = "genre";
        Genre genre = new Genre();
        Mockito.when(genreDao.findGenreByName(genreName)).thenReturn(Optional.of(genre));
        Optional<Genre> optionalGenre = genreDao.findGenreByName(genreName);
        Assertions.assertTrue(optionalGenre.isPresent());
    }

    @Test
    void findGenreByName_Negative() throws DaoException {
        String genreName = "genre";
        Mockito.when(genreDao.findGenreByName(genreName)).thenReturn(Optional.empty());
        Optional<Genre> optionalGenre = genreDao.findGenreByName(genreName);
        Assertions.assertFalse(optionalGenre.isPresent());
    }

    @Test
    void addGenreForMovieByMovieId_Positive() throws DaoException {
        Long movieId = 1L;
        Genre genre = new Genre();
        GenreDao genreDao = Mockito.mock(GenreDao.class);
        Mockito.when(genreDao.addGenreForMovieByMovieId(movieId, genre)).thenReturn(1);
        Assertions.assertEquals(1, genreDao.addGenreForMovieByMovieId(movieId, genre));
    }

    @Test
    void addGenreForMovieByMovieId_Negative() throws DaoException {
        Long movieId = 1L;
        Genre genre = new Genre();
        GenreDao genreDao = Mockito.mock(GenreDao.class);
        Mockito.when(genreDao.addGenreForMovieByMovieId(movieId, genre)).thenReturn(0);
        Assertions.assertNotEquals(1, genreDao.addGenreForMovieByMovieId(movieId, genre));
    }
}
