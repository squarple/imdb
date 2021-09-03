package by.radzionau.imdb.service;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.Genre;

import java.util.List;

public interface GenreService {
    void addGenre(Genre genre) throws ServiceException;

    List<Genre> getAll() throws ServiceException;

    List<Genre> getGenresOfMovieByMovieId(Long movieId) throws ServiceException;
}
