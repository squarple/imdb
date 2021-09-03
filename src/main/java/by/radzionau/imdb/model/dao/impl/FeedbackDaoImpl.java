package by.radzionau.imdb.model.dao.impl;

import by.radzionau.imdb.model.dao.FeedbackDao;
import by.radzionau.imdb.model.domain.Feedback;
import by.radzionau.imdb.model.domain.FeedbackStatus;
import by.radzionau.imdb.exception.ConnectionPoolException;
import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FeedbackDaoImpl implements FeedbackDao {
    private static final Logger logger = LogManager.getLogger(FeedbackDaoImpl.class);
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();

    private static final String SQL_INSERT_FEEDBACK =
            "INSERT INTO feedback (feedback_date, score, content, movie_id, usr_id, feedback_status_id) " +
                    "VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE_FEEDBACK_STATUS =
            "UPDATE feedback SET feedback_status_id=? " +
                    "WHERE feedback_id=?";
    private static final String SQL_DELETE_FEEDBACK =
            "DELETE FROM feedback " +
                    "WHERE feedback_status_id=?";
    private static final String SQL_SELECT_FEEDBACK_BY_ID =
            "SELECT feedback_id, score, content, movie_id, usr_id, feedback_status.name AS feedback_status " +
                    "FROM feedback " +
                    "JOIN feedback_status on feedback.feedback_status_id = feedback_status.feedback_status_id " +
                    "WHERE feedback_id=?";
    private static final String SQL_FIND_FEEDBACKS_BY_MOVIE_ID =
            "SELECT feedback_id, score, content, movie_id, usr_id, feedback_status.name AS feedback_status " +
                    "FROM feedback " +
                    "JOIN feedback_status on feedback.feedback_status_id = feedback_status.feedback_status_id " +
                    "WHERE movie_id=?";
    private static final String SQL_FIND_FEEDBACKS_BY_STATUS =
            "SELECT feedback_id, score, content, movie_id, usr_id, feedback_status.name AS feedback_status " +
                    "FROM feedback " +
                    "JOIN feedback_status on feedback.feedback_status_id = feedback_status.feedback_status_id " +
                    "WHERE feedback.feedback_status_id=?";

    private FeedbackDaoImpl() {

    }

    private static final class MySqlFeedbackDaoInstanceHolder {
        private static final FeedbackDao INSTANCE = new FeedbackDaoImpl();
    }

    public static FeedbackDao getInstance() {
        return FeedbackDaoImpl.MySqlFeedbackDaoInstanceHolder.INSTANCE;
    }

    @Override
    public int add(Feedback feedback) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_FEEDBACK, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setLong(1, feedback.getFeedbackId());
            int rowsUpdate = statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    Long key = resultSet.getLong(1);
                    feedback.setFeedbackId(key);
                }
            }
            return rowsUpdate;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a feedback={}", feedback);
            throw new DaoException("Error while adding a feedback=" + feedback, e);
        }
    }

    @Override
    public int updateFeedbackStatus(Long feedbackId, FeedbackStatus feedbackStatus) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_FEEDBACK_STATUS)
        ) {
            statement.setLong(1, feedbackStatus.getId());
            statement.setLong(2, feedbackId);
            int rowsUpdate = statement.executeUpdate();
            return rowsUpdate;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a feedback", e);
            throw new DaoException("Error while updating a feedback", e);
        }
    }

    @Override
    public int delete(Feedback feedback) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FEEDBACK)
        ) {
            statement.setTimestamp(1, Timestamp.valueOf(feedback.getFeedbackDate()));
            statement.setInt(2, feedback.getScore());
            statement.setString(3, feedback.getContent());
            statement.setLong(4, feedback.getMovieId());
            statement.setLong(5, feedback.getUserId());
            statement.setLong(6, feedback.getFeedbackStatus().getId());

            int rowsUpdate = statement.executeUpdate();
            return rowsUpdate;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while deleting a feedback={}", feedback);
            throw new DaoException("Error while deleting a feedback=" + feedback, e);
        }
    }

    @Override
    public Optional<Feedback> findFeedbackById(Long feedbackId) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_FEEDBACK_BY_ID)
        ) {
            statement.setLong(1, feedbackId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(createFeedback(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a feedback");
            throw new DaoException("Error while adding a feedback", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Feedback> findFeedbacksByMovieId(Long movieId) throws DaoException {
        List<Feedback> feedbacks = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_FEEDBACKS_BY_MOVIE_ID)
        ) {
            statement.setLong(1, movieId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    feedbacks.add(createFeedback(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movies");
            throw new DaoException("Error while selecting a movies", e);
        }
        return feedbacks;
    }

    @Override
    public List<Feedback> findFeedbacksByStatus(FeedbackStatus feedbackStatus) throws DaoException {
        List<Feedback> feedbacks = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_FEEDBACKS_BY_STATUS)
        ) {
            statement.setLong(1, feedbackStatus.getId());
            try(ResultSet resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    feedbacks.add(createFeedback(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movies");
            throw new DaoException("Error while selecting a movies", e);
        }
        return feedbacks;
    }

    private Feedback createFeedback(ResultSet resultSet) throws SQLException {
        return Feedback.builder()
                .setFeedbackId(resultSet.getLong(1))
                .setFeedbackDate(resultSet.getTimestamp(2).toLocalDateTime())
                .setScore(resultSet.getInt(3))
                .setContent(resultSet.getString(4))
                .setMovieId(resultSet.getLong(5))
                .setUserId(resultSet.getLong(6))
                .setFeedbackStatus(FeedbackStatus.valueOf(resultSet.getString(7).toUpperCase()))
                .build();
    }
}
