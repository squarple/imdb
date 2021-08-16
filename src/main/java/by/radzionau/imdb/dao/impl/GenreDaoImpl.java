package by.radzionau.imdb.dao.impl;

import by.radzionau.imdb.dao.GenreDao;
import by.radzionau.imdb.domain.*;
import by.radzionau.imdb.exception.ConnectionPoolException;
import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl implements GenreDao {
    private static final Logger logger = LogManager.getLogger(GenreDaoImpl.class);
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();

    private static final String SQL_INSERT_GENRE =
            "INSERT INTO genre (name) " +
                    "VALUES (?)";
    private static final String SQL_SELECT_ALL_GENRES = "SELECT genre_id, name " +
            "FROM genre";
    private static final String SQL_SELECT_GENRES_OF_MOVIE_BY_MOVIE_ID =
            "SELECT genre_id, name FROM genre WHERE genre_id = " +
                    "(SELECT genre_id FROM movie_genres WHERE movie_id = ?)";

    @Override
    public void add(Genre genre) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_GENRE)
        ) {
            statement.setString(1, genre.getName());

            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a genre={}", genre);
            throw new DaoException("Error while adding a genre=" + genre, e);
        }
    }

    @Override
    public List<Genre> findAll() throws DaoException {
        List<Genre> genres = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_GENRES)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                genres.add(new Genre(
                        resultSet.getLong(1),
                        resultSet.getString(2)
                ));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a genres=");
            throw new DaoException("Error while adding a genres", e);
        }
        return genres;
    }

    @Override
    public List<Genre> findGenresOfMovieByMovieId(Long movieId) throws DaoException {
        List<Genre> movies = new ArrayList<>();

        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_GENRES_OF_MOVIE_BY_MOVIE_ID)
        ) {

            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                movies.add(new Genre(
                        resultSet.getLong(1),
                        resultSet.getString(2)
                ));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a genre");
            throw new DaoException("Error while selecting a genre", e);
        }

        return movies;
    }
}
