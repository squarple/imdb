package by.radzionau.imdb.model.dao;

import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    int add(Genre genre) throws DaoException;

    List<Genre> findAll() throws DaoException;

    List<Genre> findGenresOfMovieByMovieId(Long movieId) throws DaoException;

    Optional<Genre> findGenreByName(String name) throws DaoException;
}
