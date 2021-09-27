package by.radzionau.imdb.model.dao.impl;

import by.radzionau.imdb.model.dao.MovieDao;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.entity.MovieType;
import by.radzionau.imdb.exception.ConnectionPoolException;
import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl implements MovieDao {
    private static final Logger logger = LogManager.getLogger(MovieDaoImpl.class);
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();

    private static final String SQL_INSERT_MOVIE =
            "INSERT INTO movie (title, logline, release_year, cover, movie_type_id) " +
                    "VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE_MOVIE =
            "UPDATE movie SET title=?, logline=?, release_year=?, cover=?, movie_type_id=? " +
                    "WHERE movie_id=?";
    private static final String SQL_DELETE_MOVIE =
            "DELETE FROM movie " +
                    "WHERE movie_id=?";
    private static final String SQL_SELECT_MOVIE_BY_ID =
            "SELECT movie_id, title, logline, release_year, cover, movie_type.name AS movie_type " +
                    "FROM movie " +
                    "JOIN movie_type ON movie.movie_type_id=movie_type.movie_type_id " +
                    "WHERE movie_id=?";
    private static final String SQL_SELECT_MOVIES_BY_TITLE =
            "SELECT movie_id, title, logline, release_year, cover, movie_type.name AS movie_type " +
                    "FROM movie " +
                    "JOIN movie_type ON movie.movie_type_id=movie_type.movie_type_id " +
                    "WHERE title LIKE ?";
    private static final String SQL_SELECT_MOVIES_BY_YEAR =
            "SELECT movie_id, title, logline, release_year, cover, movie_type.name AS movie_type " +
                    "FROM movie " +
                    "JOIN movie_type ON movie.movie_type_id=movie_type.movie_type_id " +
                    "WHERE release_year=?";
    private static final String SQL_SELECT_MOVIES_BY_GENRE =
            "SELECT movie_id, title, logline, release_year, cover, movie_type.name AS type " +
                    "FROM movie " +
                    "JOIN movie_type ON movie.movie_type_id=movie_type.movie_type_id " +
                    "WHERE movie_id IN" +
                    "(SELECT movie_id " +
                    "FROM movie_genres " +
                    "WHERE genre_id=?)";
    private static final String SQL_SELECT_MOVIES_BY_MOVIE_TYPE =
            "SELECT movie_id, title, logline, release_year, cover, movie_type.name AS movie_type " +
                    "FROM movie " +
                    "JOIN movie_type ON movie.movie_type_id=movie_type.movie_type_id " +
                    "WHERE movie.movie_type_id=?";
    private static final String SQL_CALCULATE_MOVIE_SCORE_BY_MOVIE_ID = //todo need check
            "SELECT AVG(score) AS avg_score " +
                    "FROM feedback " +
                    "WHERE movie_id=?";

    private MovieDaoImpl() {

    }

    private static final class MySqlMovieDaoInstanceHolder {
        private static final MovieDao INSTANCE = new MovieDaoImpl();
    }

    public static MovieDao getInstance() {
        return MovieDaoImpl.MySqlMovieDaoInstanceHolder.INSTANCE;
    }

    @Override
    public int add(Movie movie) throws DaoException {  //todo convert InputStream to Blob
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_MOVIE, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getLogline());
            statement.setInt(3, movie.getReleaseYear());
            statement.setBlob(4, movie.getCover());
            statement.setLong(5, movie.getMovieType().getId());
            int rowsUpdate = statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    Long key = resultSet.getLong(1);
                    movie.setMovieId(key);
                }
            }
            return rowsUpdate;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a movie", e);
            throw new DaoException("Error while adding a movie", e);
        }
    }

    @Override
    public int update(Movie movie) throws DaoException {   //todo convert InputStream to Blob
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MOVIE)
        ) {
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getLogline());
            statement.setInt(3, movie.getReleaseYear());
            statement.setBlob(4, movie.getCover());
            statement.setLong(5, movie.getMovieType().getId());
            statement.setLong(6, movie.getMovieId());
            int rowsUpdate = statement.executeUpdate();
            return rowsUpdate;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a movie", e);
            throw new DaoException("Error while updating a movie", e);
        }
    }

    @Override
    public void delete(Movie movie) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MOVIE)
        ) {
            statement.setLong(1, movie.getMovieId());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a movie", e);
            throw new DaoException("Error while updating a movie", e);
        }
    }

    @Override
    public Optional<Movie> findMovieById(Long movieId) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MOVIE_BY_ID)
        ) {
            statement.setLong(1, movieId);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Movie movie = createMovie(resultSet);
                    return Optional.of(movie);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> findMoviesByTitle(String title) throws DaoException {
        List<Movie> movies = new ArrayList<>();
        title = "%" + title + "%";
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MOVIES_BY_TITLE)
        ) {
            statement.setString(1, title);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    movies.add(createMovie(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }
        return movies;
    }

    @Override
    public List<Movie> findMoviesByYear(int year) throws DaoException {
        List<Movie> movies = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MOVIES_BY_YEAR)
        ) {
            statement.setInt(1, year);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    movies.add(createMovie(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }
        return movies;
    }

    @Override
    public List<Movie> findMoviesByGenre(Genre genre) throws DaoException {
        List<Movie> movies = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MOVIES_BY_GENRE)
        ) {
            statement.setLong(1, genre.getGenreId());
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    movies.add(createMovie(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }
        return movies;
    }

    @Override
    public List<Movie> findMoviesByMovieType(MovieType movieType) throws DaoException {
        List<Movie> movies = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MOVIES_BY_MOVIE_TYPE)
        ) {
            statement.setLong(1, movieType.getId());
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    movies.add(createMovie(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }
        return movies;
    }

    @Override
    public Optional<Double> findMovieScoreByMovieId(Long movieId) throws DaoException { //need check
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_CALCULATE_MOVIE_SCORE_BY_MOVIE_ID)
        ) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Double score = resultSet.getDouble(1);
                return Optional.of(score);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }

        return Optional.empty();
    }

    private Movie createMovie(ResultSet resultSet) throws SQLException {
        return Movie.builder()
                .setMovieId(resultSet.getLong(1))
                .setTitle(resultSet.getString(2))
                .setLogline(resultSet.getString(3))
                .setReleaseYear(resultSet.getInt(4))
                .setCover(resultSet.getBlob(5).getBinaryStream())
                .setMovieType(MovieType.valueOf(resultSet.getString(6).toUpperCase()))
                .build();
    }
}
