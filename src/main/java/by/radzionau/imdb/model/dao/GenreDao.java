package by.radzionau.imdb.model.dao;

import by.radzionau.imdb.model.domain.Genre;
import by.radzionau.imdb.exception.DaoException;

import java.util.List;

public interface GenreDao {
    int add(Genre genre) throws DaoException;

    List<Genre> findAll() throws DaoException;

    List<Genre> findGenresOfMovieByMovieId(Long movieId) throws DaoException;
}
