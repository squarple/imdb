package by.radzionau.imdb.dao;

import by.radzionau.imdb.domain.Feedback;
import by.radzionau.imdb.domain.FeedbackStatus;
import by.radzionau.imdb.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface FeedbackDao {
    int add(Feedback feedback) throws DaoException;

    int updateFeedbackStatus(Long feedbackId, FeedbackStatus feedbackStatus) throws DaoException;

    int delete(Feedback feedback) throws DaoException;

    Optional<Feedback> findFeedbackById(Long feedbackId) throws DaoException;

    List<Feedback> findFeedbacksByMovieId(Long movieId) throws DaoException;

    List<Feedback> findFeedbacksByStatus(FeedbackStatus feedbackStatus) throws DaoException;
}
