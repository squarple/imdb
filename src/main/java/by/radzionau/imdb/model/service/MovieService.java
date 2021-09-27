package by.radzionau.imdb.model.service;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.entity.MovieType;

import java.util.List;

public interface MovieService {
    void addMovie(Movie movie) throws ServiceException;

    void update(Movie movie) throws ServiceException;

    void deleteMovie(Movie movie) throws ServiceException;

    Movie getMovieById(Long movieId) throws ServiceException;

    List<Movie> findMoviesByTitle(String title) throws ServiceException;

    List<Movie> findMoviesByYear(int year) throws ServiceException;

    List<Movie> findMoviesByGenre(Genre genre) throws ServiceException;

    List<Movie> findMoviesByMovieType(MovieType movieType) throws ServiceException;

    Double findMovieScoreByMovieId(Long movieId) throws ServiceException;

    Double findMovieScore(Movie movie) throws ServiceException;

    List<Genre> findGenresOfMovie(Movie movie) throws ServiceException;
}
