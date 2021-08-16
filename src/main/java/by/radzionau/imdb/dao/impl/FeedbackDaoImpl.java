package by.radzionau.imdb.dao.impl;

import by.radzionau.imdb.dao.FeedbackDao;
import by.radzionau.imdb.domain.Feedback;
import by.radzionau.imdb.domain.FeedbackStatus;
import by.radzionau.imdb.domain.Movie;
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
import java.util.Optional;

public class FeedbackDaoImpl implements FeedbackDao {
    private static final Logger logger = LogManager.getLogger(FeedbackDaoImpl.class);
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();

    private static final String SQL_INSERT_FEEDBACK =
            "INSERT INTO feedback (feedback_date, score, content, movie_id, usr_id, feedback_status_id) " +
                    "VALUES (?,?,?,?,?,?)";
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
                    "WHERE movie_id=?";

    @Override
    public void add(Feedback feedback) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_FEEDBACK)
        ) {
            statement.setTimestamp(1, feedback.getFeedbackDate());
            statement.setInt(2, feedback.getScore());
            statement.setString(3, feedback.getContent());
            statement.setLong(4, feedback.getMovieId());
            statement.setLong(5, feedback.getUserId());
            statement.setLong(6, feedback.getFeedbackStatus().getId());
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a feedback={}", feedback);
            throw new DaoException("Error while adding a user=" + feedback, e);
        }
    }

    @Override
    public Optional<Feedback> findFeedbackById(Long feedbackId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_FEEDBACK_BY_ID)
        ) {
            statement.setLong(1, feedbackId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Feedback(
                        resultSet.getLong(1),
                        resultSet.getTimestamp(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getLong(5),
                        resultSet.getLong(6),
                        FeedbackStatus.valueOf(resultSet.getString(7).toUpperCase())
                ));
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
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_FEEDBACKS_BY_MOVIE_ID)
        ) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                feedbacks.add(new Feedback(
                        resultSet.getLong(1),
                        resultSet.getTimestamp(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getLong(5),
                        resultSet.getLong(5),
                        FeedbackStatus.valueOf(resultSet.getString(5).toUpperCase())
                ));
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
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_FEEDBACKS_BY_STATUS)
        ) {
            statement.setLong(1, feedbackStatus.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                feedbacks.add(new Feedback(
                        resultSet.getLong(1),
                        resultSet.getTimestamp(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getLong(5),
                        resultSet.getLong(5),
                        FeedbackStatus.valueOf(resultSet.getString(5).toUpperCase())
                ));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movies");
            throw new DaoException("Error while selecting a movies", e);
        }

        return feedbacks;
    }
}
