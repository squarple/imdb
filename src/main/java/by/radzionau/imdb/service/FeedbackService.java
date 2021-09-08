package by.radzionau.imdb.service;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.Feedback;
import by.radzionau.imdb.model.domain.FeedbackStatus;
import by.radzionau.imdb.model.domain.Movie;

import java.util.List;

public interface FeedbackService {
    void addFeedback(Feedback feedback) throws ServiceException;

    Feedback updateFeedbackStatus(Feedback feedback, FeedbackStatus feedbackStatus) throws ServiceException;

    void deleteFeedback(Feedback feedback) throws ServiceException;

    Feedback findFeedbackById(Long feedbackId) throws ServiceException;

    List<Feedback> findFeedbacksByMovieId(Long movieId) throws ServiceException;

    List<Feedback> findFeedbacksByMovie(Movie movie) throws ServiceException;

    List<Feedback> findFeedbacksByStatus(FeedbackStatus feedbackStatus) throws ServiceException;
}
