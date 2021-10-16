package by.radzionau.imdb.model.service;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Genre;

import java.util.List;

/**
 * The interface GenreService provides methods to manage genres of movies.
 */
public interface GenreService {
    /**
     * Add genre. Throws ServiceException if genre is null or if genre is presented already or if writing to data source throws the exception.
     *
     * @param genre the genre to add
     * @throws ServiceException if genre is null or if genre is presented already or if writing to data source throws the exception
     */
    void addGenre(Genre genre) throws ServiceException;

    /**
     * Find all genres. Throws ServiceException if reading from data source throws the exception.
     *
     * @return the list of genres
     * @throws ServiceException if reading from data source throws the exception
     */
    List<Genre> findAll() throws ServiceException;

    /**
     * Find genres of movie by movie id. Throws ServiceException if movie id is null or if reading from data source throws the exception.
     *
     * @param movieId the movie id
     * @return the list of genres
     * @throws ServiceException if movie id is null or if reading from data source throws the exception
     */
    List<Genre> findGenresOfMovieByMovieId(Long movieId) throws ServiceException;

    /**
     * Find genre by name of genre. Throws ServiceException if genre name is null or empty or if genre does not exist or if reading from data source throws the exception.
     *
     * @param name the name of genre
     * @return the genre
     * @throws ServiceException if genre name is null or empty or if genre does not exist or if reading from data source throws the exception
     */
    Genre findGenreByName(String name) throws ServiceException;

    /**
     * Add genre for movie by movie id. Throws ServiceException if movie id is null or if genre is null or if writing to data source throws the exception.
     *
     * @param movieId the movie id
     * @param genre   the genre
     * @throws ServiceException if movie id is null or if genre is null or if writing to data source throws the exception
     */
    void addGenreForMovieByMovieId(Long movieId, Genre genre) throws ServiceException;
}
