package by.radzionau.imdb.model.service.impl;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.dao.FeedbackDao;
import by.radzionau.imdb.model.dao.impl.FeedbackDaoImpl;
import by.radzionau.imdb.model.entity.Feedback;
import by.radzionau.imdb.model.entity.FeedbackStatus;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.service.FeedbackService;
import by.radzionau.imdb.model.validator.FeedbackValidator;
import by.radzionau.imdb.model.validator.MovieValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The implementation of FeedbackService interface.
 */
public class FeedbackServiceImpl implements FeedbackService {
    private static final Logger logger = LogManager.getLogger();
    private final FeedbackDao feedbackDao = FeedbackDaoImpl.getInstance();

    private static final class FeedbackServiceInstanceHolder {
        private static final FeedbackServiceImpl INSTANCE = new FeedbackServiceImpl();
    }

    /**
     * Gets instance of feedback service.
     *
     * @return the instance of feedback service
     */
    public static FeedbackService getInstance() {
        return FeedbackServiceImpl.FeedbackServiceInstanceHolder.INSTANCE;
    }

    @Override
    public void addFeedback(Feedback feedback) throws ServiceException {
        if (!FeedbackValidator.getInstance().isValid(feedback)) {
            logger.error("Feedback doesn't present");
            throw new ServiceException("Feedback doesn't present");
        }
        try {
            feedbackDao.add(feedback);
        } catch (DaoException e) {
            logger.error("Can't handle addFeedback request at FeedbackService", e);
            throw new ServiceException("Can't handle addFeedback request at FeedbackService", e);
        }
    }

    @Override
    public Feedback updateFeedbackStatus(Feedback feedback, FeedbackStatus feedbackStatus) throws ServiceException {
        if (!FeedbackValidator.getInstance().isValid(feedback)) {
            logger.error("Feedback doesn't present");
            throw new ServiceException("Feedback doesn't present");
        }
        if (feedbackStatus == null) {
            logger.error("Feedback status doesn't present");
            throw new ServiceException("Feedback status doesn't present");
        }
        Feedback updatedFeedback = buildFeedback(feedback.getFeedbackId(), feedback.getFeedbackDate(), feedback.getScore(), feedback.getContent(), feedback.getMovieId(), feedback.getUserId(), feedbackStatus);
        try {
            feedbackDao.updateFeedbackStatus(updatedFeedback.getFeedbackId(), feedbackStatus);
        } catch (DaoException e) {
            logger.error("Can't handle updateFeedbackStatus request at FeedbackService", e);
            throw new ServiceException("Can't handle updateFeedbackStatus request at FeedbackService", e);
        }
        return updatedFeedback;
    }

    @Override
    public void deleteFeedback(Feedback feedback) throws ServiceException {
        if (!FeedbackValidator.getInstance().isValid(feedback)) {
            logger.error("Invalid feedback");
            throw new ServiceException("Invalid feedback");
        }
        try {
            feedbackDao.delete(feedback);
        } catch (DaoException e) {
            logger.error("Can't handle deleteFeedback request at FeedbackService", e);
            throw new ServiceException("Can't handle deleteFeedback request at FeedbackService", e);
        }
    }

    @Override
    public Feedback findFeedbackById(Long feedbackId) throws ServiceException {
        if (feedbackId == null) {
            logger.error("Feedback id doesn't present");
            throw new ServiceException("Feedback id doesn't present");
        }
        try {
            Optional<Feedback> optionalFeedback = feedbackDao.findFeedbackById(feedbackId);
            if (optionalFeedback.isPresent()) {
                return optionalFeedback.get();
            } else {
                throw new ServiceException("Can't handle findFeedbackById request at FeedbackService");
            }
        } catch (DaoException e) {
            logger.error("Can't handle findFeedbackById request at FeedbackService", e);
            throw new ServiceException("Can't handle findFeedbackById request at FeedbackService", e);
        }
    }

    @Override
    public List<Feedback> findFeedbacksByMovieId(Long movieId) throws ServiceException {
        if (movieId == null) {
            logger.error("Movie id doesn't present");
            throw new ServiceException("Movie id doesn't present");
        }
        List<Feedback> feedbacks;
        try {
            feedbacks = feedbackDao.findFeedbacksByMovieId(movieId);
        } catch (DaoException e) {
            logger.error("Can't handle findFeedbacksByMovieId request at FeedbackService", e);
            throw new ServiceException("Can't handle findFeedbacksByMovieId request at FeedbackService", e);
        }
        return feedbacks;
    }

    @Override
    public List<Feedback> findFeedbacksByMovie(Movie movie) throws ServiceException {
        if (!MovieValidator.getInstance().isValid(movie)) {
            logger.error("Invalid movie");
            throw new ServiceException("Invalid movie");
        }
        return findFeedbacksByMovieId(movie.getMovieId());
    }

    @Override
    public List<Feedback> findFeedbacksByStatus(FeedbackStatus feedbackStatus) throws ServiceException {
        if (feedbackStatus == null) {
            logger.error("Feedback status doesn't present");
            throw new ServiceException("Feedback status doesn't present");
        }
        List<Feedback> feedbacks;
        try {
            feedbacks = feedbackDao.findFeedbacksByStatus(feedbackStatus);
        } catch (DaoException e) {
            logger.error("Can't handle findFeedbacksByStatus request at FeedbackService", e);
            throw new ServiceException("Can't handle findFeedbacksByStatus request at FeedbackService", e);
        }
        return feedbacks;
    }

    private Feedback buildFeedback(Long feedbackId, LocalDateTime feedbackDate, int score, String content, Long movieId, Long userId, FeedbackStatus feedbackStatus) {
        return Feedback.builder()
                .setFeedbackId(feedbackId)
                .setFeedbackDate(feedbackDate)
                .setScore(score)
                .setContent(content)
                .setMovieId(movieId)
                .setUserId(userId)
                .setFeedbackStatus(feedbackStatus)
                .build();
    }
}
