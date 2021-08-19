package by.radzionau.imdb.dao;

import by.radzionau.imdb.domain.Genre;
import by.radzionau.imdb.exception.DaoException;

import java.util.List;

public interface GenreDao {
    int add(Genre genre) throws DaoException;

    List<Genre> findAll() throws DaoException;

    List<Genre> findGenresOfMovieByMovieId(Long movieId) throws DaoException;
}
