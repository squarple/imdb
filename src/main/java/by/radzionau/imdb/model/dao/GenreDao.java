package by.radzionau.imdb.model.dao;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.entity.Genre;

import java.util.List;
import java.util.Optional;

/**
 * Interface describes the behavior of {@link Genre} entity
 *
 * @author Anton Radzionau
 */
public interface GenreDao {
    /**
     * Add genre. Throws DaoException if writing to data source throws exception.
     *
     * @param genre the genre to add
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int add(Genre genre) throws DaoException;

    /**
     * Find all genres. Throws DaoException if reading from data source throws exception.
     *
     * @return the list of genres
     * @throws DaoException if reading from data source throws exception
     */
    List<Genre> findAll() throws DaoException;

    /**
     * Find genres of movie by movie id. Throws DaoException if reading from data source throws exception.
     *
     * @param movieId the movie id
     * @return the list of genres
     * @throws DaoException if reading from data source throws exception
     */
    List<Genre> findGenresOfMovieByMovieId(Long movieId) throws DaoException;

    /**
     * Find genre by name. Throws DaoException if reading from data source throws exception.
     *
     * @param name the name
     * @return the optional of genre
     * @throws DaoException if reading from data source throws exception
     */
    Optional<Genre> findGenreByName(String name) throws DaoException;

    /**
     * Add genre for movie by movie id. Throws DaoException if reading from data source throws exception.
     *
     * @param movieId the movie id
     * @param genre   the genre
     * @return the count of updated rows
     * @throws DaoException if reading from data source throws exception
     */
    int addGenreForMovieByMovieId(Long movieId, Genre genre) throws DaoException;
}
