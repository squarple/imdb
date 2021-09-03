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

    Feedback getFeedbackById(Long feedbackId) throws ServiceException;

    List<Feedback> searchFeedbacksByMovieId(Long movieId) throws ServiceException;

    List<Feedback> searchFeedbacksByMovie(Movie movie) throws ServiceException;

    List<Feedback> searchFeedbacksByStatus(FeedbackStatus feedbackStatus) throws ServiceException;

}
