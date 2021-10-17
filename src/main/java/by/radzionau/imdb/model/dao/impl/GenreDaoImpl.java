package by.radzionau.imdb.model.dao.impl;

import by.radzionau.imdb.exception.ConnectionPoolException;
import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.dao.GenreDao;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The implementation of GenreDao interface.
 */
public class GenreDaoImpl implements GenreDao {
    private static final Logger logger = LogManager.getLogger(GenreDaoImpl.class);
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();

    private static final String SQL_INSERT_GENRE =
            "INSERT INTO genre (name) " +
                    "VALUES (?)";
    private static final String SQL_INSERT_GENRE_FOR_MOVIE =
            "INSERT INTO movie_genres (movie_id, genre_id) " +
                    "VALUES (?,?)";
    private static final String SQL_SELECT_ALL_GENRES =
            "SELECT genre_id, name " +
                    "FROM genre";
    private static final String SQL_SELECT_GENRES_OF_MOVIE_BY_MOVIE_ID =
            "SELECT genre_id, name " +
                    "FROM genre " +
                    "WHERE genre_id IN " +
                        "(SELECT movie_genres.genre_id " +
                        "FROM movie_genres " +
                        "WHERE movie_genres.movie_id = ?)";
    private static final String SQL_SELECT_GENRE_BY_NAME =
            "SELECT genre_id, name " +
                    "FROM genre " +
                    "WHERE name = ?";

    private GenreDaoImpl() {

    }

    private static final class MySqlGenreDaoInstanceHolder {
        private static final GenreDao INSTANCE = new GenreDaoImpl();
    }

    /**
     * Gets instance.
     *
     * @return the instance of genre dao
     */
    public static GenreDao getInstance() {
        return GenreDaoImpl.MySqlGenreDaoInstanceHolder.INSTANCE;
    }

    @Override
    public int add(Genre genre) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_GENRE, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, genre.getName());
            int rowsUpdate = statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    Long key = resultSet.getLong(1);
                    genre.setGenreId(key);
                }
            }
            return rowsUpdate;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a genre={}", genre, e);
            throw new DaoException("Error while adding a genre=" + genre, e);
        }
    }

    @Override
    public List<Genre> findAll() throws DaoException {
        List<Genre> genres = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_GENRES);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                genres.add(createGenre(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a genres=", e);
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
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    movies.add(createGenre(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a genre", e);
            throw new DaoException("Error while selecting a genre", e);
        }
        return movies;
    }

    @Override
    public Optional<Genre> findGenreByName(String name) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_GENRE_BY_NAME)
        ) {
            statement.setString(1, name);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(createGenre(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while finding a genre by name", e);
            throw new DaoException("Error while finding a genre by name", e);
        }
        return Optional.empty();
    }

    @Override
    public int addGenreForMovieByMovieId(Long movieId, Genre genre) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_GENRE_FOR_MOVIE);
        ) {
            statement.setLong(1, movieId);
            statement.setLong(2, genre.getGenreId());
            return statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Error while adding a genre", e);
            throw new DaoException("Error while adding a genre", e);
        }
    }

    private Genre createGenre(ResultSet resultSet) throws SQLException {
        return Genre.builder()
                .setGenreId(resultSet.getLong(1))
                .setName(resultSet.getString(2))
                .build();
    }
}
