package by.radzionau.imdb.model.dao;

import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.entity.MovieType;
import by.radzionau.imdb.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MovieDao {
    int add(Movie movie) throws DaoException;

    int update(Movie movie) throws DaoException;

    void delete(Movie movie) throws DaoException;

    Optional<Movie> findMovieById(Long movieId) throws DaoException;

    List<Movie> findMoviesByTitle(String title) throws DaoException;

    List<Movie> findMoviesByYear(int year) throws DaoException;

    List<Movie> findMoviesByGenre(Genre genre) throws DaoException;

    List<Movie> findMoviesByMovieType(MovieType movieType) throws DaoException;

    Optional<Double> findMovieScoreByMovieId(Long movieId) throws DaoException;
}
