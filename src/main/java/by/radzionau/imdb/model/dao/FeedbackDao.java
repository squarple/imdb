package by.radzionau.imdb.model.dao;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.entity.Feedback;
import by.radzionau.imdb.model.entity.FeedbackStatus;

import java.util.List;
import java.util.Optional;

/**
 * Interface describes the behavior of {@link Feedback} entity
 *
 * @author Anton Radzionau
 */
public interface FeedbackDao {
    /**
     * Add feedback. Throws DaoException if writing to data source throws exception.
     *
     * @param feedback the feedback to add
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int add(Feedback feedback) throws DaoException;

    /**
     * Update feedback status of feedback by feedback id. Throws DaoException if writing to data source throws exception.
     *
     * @param feedbackId     the feedback id
     * @param feedbackStatus the feedback status
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int updateFeedbackStatus(Long feedbackId, FeedbackStatus feedbackStatus) throws DaoException;

    /**
     * Delete feedback. Throws DaoException if writing to data source throws exception.
     *
     * @param feedback the feedback to delete
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int delete(Feedback feedback) throws DaoException;

    /**
     * Find feedback by feedback id. Throws DaoException if reading from data source throws exception.
     *
     * @param feedbackId the feedback id
     * @return the optional of feedback
     * @throws DaoException if writing to data source throws exception
     */
    Optional<Feedback> findFeedbackById(Long feedbackId) throws DaoException;

    /**
     * Find feedbacks by movie id. Throws DaoException if reading from data source throws exception.
     *
     * @param movieId the movie id
     * @return the list of feedbacks
     * @throws DaoException if writing to data source throws exception
     */
    List<Feedback> findFeedbacksByMovieId(Long movieId) throws DaoException;

    /**
     * Find feedbacks by status. Throws DaoException if reading from data source throws exception.
     *
     * @param feedbackStatus the feedback status
     * @return the list of feedbacks
     * @throws DaoException if writing to data source throws exception
     */
    List<Feedback> findFeedbacksByStatus(FeedbackStatus feedbackStatus) throws DaoException;
}
