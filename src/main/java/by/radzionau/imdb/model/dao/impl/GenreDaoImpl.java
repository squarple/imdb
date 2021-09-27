package by.radzionau.imdb.model.dao.impl;

import by.radzionau.imdb.model.dao.GenreDao;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.exception.ConnectionPoolException;
import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDaoImpl implements GenreDao {
    private static final Logger logger = LogManager.getLogger(GenreDaoImpl.class);
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();

    private static final String SQL_INSERT_GENRE =
            "INSERT INTO genre (name) " +
                    "VALUES (?)";
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
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Genre createGenre(ResultSet resultSet) throws SQLException {
        return Genre.builder()
                .setGenreId(resultSet.getLong(1))
                .setName(resultSet.getString(2))
                .build();
    }
}
