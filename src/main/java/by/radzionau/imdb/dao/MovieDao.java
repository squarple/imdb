package by.radzionau.imdb.dao;

import by.radzionau.imdb.domain.Genre;
import by.radzionau.imdb.domain.Movie;
import by.radzionau.imdb.domain.MovieType;
import by.radzionau.imdb.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MovieDao {
    void add(Movie movie) throws DaoException;

    void update(Movie movie) throws DaoException;

    void delete(Movie movie) throws DaoException;

    Optional<Movie> findMovieById(Long movieId) throws DaoException;

    List<Movie> findMoviesByTitle(String title) throws DaoException;

    List<Movie> findMoviesByYear(int year) throws DaoException;

    List<Movie> findMoviesByGenre(Genre genre) throws DaoException;

    List<Movie> findMoviesByMovieType(MovieType movieType) throws DaoException;


}
