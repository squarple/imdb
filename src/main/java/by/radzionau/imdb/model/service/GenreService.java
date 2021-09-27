package by.radzionau.imdb.model.service;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Genre;

import java.util.List;

public interface GenreService {
    void addGenre(Genre genre) throws ServiceException;

    List<Genre> findAll() throws ServiceException;

    List<Genre> findGenresOfMovieByMovieId(Long movieId) throws ServiceException;

    Genre findGenreByName(String name) throws ServiceException;
}
