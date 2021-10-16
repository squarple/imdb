package by.radzionau.imdb.model.service;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Feedback;
import by.radzionau.imdb.model.entity.FeedbackStatus;
import by.radzionau.imdb.model.entity.Movie;

import java.util.List;

/**
 * The interface FeedbackService provides methods to manage reviews.
 */
public interface FeedbackService {
    /**
     * Add feedback. Throws ServiceException if feedback is null or if writing to data source throws an exception.
     *
     * @param feedback the feedback to add
     * @throws ServiceException if feedback is null or if writing to data source throws an exception
     */
    void addFeedback(Feedback feedback) throws ServiceException;

    /**
     * Update feedback status. Throws ServiceException if feedback is null or if feedbackStatus is null or if writing to data source throws an exception.
     *
     * @param feedback       the feedback to update
     * @param feedbackStatus the new feedback status
     * @return the updated feedback
     * @throws ServiceException if feedback is null or if feedbackStatus is null or if writing to data source throws an exception
     */
    Feedback updateFeedbackStatus(Feedback feedback, FeedbackStatus feedbackStatus) throws ServiceException;

    /**
     * Delete feedback. Throws ServiceException if feedback is null or if updating of data source throws an exception.
     *
     * @param feedback the feedback to delete
     * @throws ServiceException if feedback is null or if writing to data source throws an exception
     */
    void deleteFeedback(Feedback feedback) throws ServiceException;

    /**
     * Find feedback by id of the feedback. Throws ServiceException if feedback id is null or if feedback is not presented at data source or if reading from data source throws an exception.
     *
     * @param feedbackId the feedback id
     * @return the found feedback
     * @throws ServiceException if feedback id is null or if feedback is not presented at data source or if reading from data source throws an exception
     */
    Feedback findFeedbackById(Long feedbackId) throws ServiceException;

    /**
     * Find feedbacks by movie id. Throws ServiceException if movie id is null or if reading from data source throws an exception.
     *
     * @param movieId the movie id
     * @return the list of found feedbacks
     * @throws ServiceException if reading from data source throws an exception
     */
    List<Feedback> findFeedbacksByMovieId(Long movieId) throws ServiceException;

    /**
     * Find feedbacks by movie. Throws ServiceException if movie id is null or if reading from data source throws an exception.
     *
     * @param movie the movie
     * @return the list
     * @throws ServiceException if reading from data source throws an exception
     */
    List<Feedback> findFeedbacksByMovie(Movie movie) throws ServiceException;

    /**
     * Find feedbacks by status of feedback. Throws ServiceException if feedbackStatus is null or if reading from data source throws an exception.
     *
     * @param feedbackStatus the feedback status
     * @return the list
     * @throws ServiceException if feedbackStatus is null or if reading from data source throws an exception
     */
    List<Feedback> findFeedbacksByStatus(FeedbackStatus feedbackStatus) throws ServiceException;
}
