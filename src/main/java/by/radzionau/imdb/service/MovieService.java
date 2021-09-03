package by.radzionau.imdb.service;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.Genre;
import by.radzionau.imdb.model.domain.Movie;
import by.radzionau.imdb.model.domain.MovieType;

import java.util.List;

public interface MovieService {
    void addMovie(Movie movie) throws ServiceException;

    void update(Movie movie) throws ServiceException;

    void deleteMovie(Movie movie) throws ServiceException;

    Movie getMovieById(Long movieId) throws ServiceException;

    List<Movie> searchMoviesByTitle(String title) throws ServiceException;

    List<Movie> searchMoviesByYear(int year) throws ServiceException;

    List<Movie> searchMoviesByGenre(Genre genre) throws ServiceException;

    List<Movie> searchMoviesByMovieType(MovieType movieType) throws ServiceException;

    Double getMovieScoreByMovieId(Long movieId) throws ServiceException;

    Double getMovieScore(Movie movie) throws ServiceException;

    List<Genre> getGenresOfMovie(Movie movie) throws ServiceException;
}
