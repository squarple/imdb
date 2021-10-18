package test.radzionau.imdb.model.dao.impl;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.dao.FeedbackDao;
import by.radzionau.imdb.model.entity.Feedback;
import by.radzionau.imdb.model.entity.FeedbackStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class FeedbackDaoImplTest {
    @Test
    void add_Positive() throws DaoException {
        Feedback feedback = new Feedback();

        FeedbackDao feedbackDao = Mockito.mock(FeedbackDao.class);
        Mockito.when(feedbackDao.add(feedback)).thenReturn(1);

        Assertions.assertEquals(1, feedbackDao.add(feedback));
    }

    @Test
    void add_Negative() throws DaoException {
        Feedback feedback = new Feedback();

        FeedbackDao feedbackDao = Mockito.mock(FeedbackDao.class);
        Mockito.when(feedbackDao.add(feedback)).thenReturn(0);

        Assertions.assertNotEquals(1, feedbackDao.add(feedback));
    }

    @Test
    void updateFeedbackStatus_Positive() throws DaoException {
        Long feedbackId = 1L;
        FeedbackStatus feedbackStatus = FeedbackStatus.APPROVED;

        FeedbackDao feedbackDao = Mockito.mock(FeedbackDao.class);
        Mockito.when(feedbackDao.updateFeedbackStatus(feedbackId, feedbackStatus)).thenReturn(1);

        Assertions.assertEquals(1, feedbackDao.updateFeedbackStatus(feedbackId, feedbackStatus));
    }

    @Test
    void updateFeedbackStatus_Negative() throws DaoException {
        Long feedbackId = 1L;
        FeedbackStatus feedbackStatus = FeedbackStatus.APPROVED;

        FeedbackDao feedbackDao = Mockito.mock(FeedbackDao.class);
        Mockito.when(feedbackDao.updateFeedbackStatus(feedbackId, feedbackStatus)).thenReturn(0);

        Assertions.assertNotEquals(1, feedbackDao.updateFeedbackStatus(feedbackId, feedbackStatus));
    }

    @Test
    void delete_Positive() throws DaoException {
        Feedback feedback = new Feedback();

        FeedbackDao feedbackDao = Mockito.mock(FeedbackDao.class);
        Mockito.when(feedbackDao.delete(feedback)).thenReturn(1);

        Assertions.assertEquals(1, feedbackDao.delete(feedback));
    }

    @Test
    void delete_Negative() throws DaoException {
        Feedback feedback = new Feedback();

        FeedbackDao feedbackDao = Mockito.mock(FeedbackDao.class);
        Mockito.when(feedbackDao.delete(feedback)).thenReturn(0);

        Assertions.assertNotEquals(1, feedbackDao.delete(feedback));
    }

    @Test
    void findFeedbackById_Positive() throws DaoException {
        Long feedbackId = 1L;
        Feedback feedback = new Feedback();

        FeedbackDao feedbackDao = Mockito.mock(FeedbackDao.class);
        Mockito.when(feedbackDao.findFeedbackById(feedbackId)).thenReturn(Optional.of(feedback));

        Optional<Feedback> optionalFeedback = feedbackDao.findFeedbackById(feedbackId);

        Assertions.assertTrue(optionalFeedback.isPresent());
    }

    @Test
    void findFeedbackById_Negative() throws DaoException {
        Long feedbackId = 1L;

        FeedbackDao feedbackDao = Mockito.mock(FeedbackDao.class);
        Mockito.when(feedbackDao.findFeedbackById(feedbackId)).thenReturn(Optional.empty());

        Optional<Feedback> optionalFeedback = feedbackDao.findFeedbackById(feedbackId);

        Assertions.assertFalse(optionalFeedback.isPresent());
    }

    @Test
    void findFeedbacksByMovieId_Positive() throws DaoException {
        Long movieId = 1L;
        List<Feedback> feedbackList = new ArrayList<>();

        FeedbackDao feedbackDao = Mockito.mock(FeedbackDao.class);
        Mockito.when(feedbackDao.findFeedbacksByMovieId(movieId)).thenReturn(feedbackList);

        Assertions.assertEquals(feedbackList, feedbackDao.findFeedbacksByMovieId(movieId));
    }

    @Test
    void findFeedbacksByStatus_Positive() throws DaoException {
        FeedbackStatus feedbackStatus = FeedbackStatus.APPROVED;
        List<Feedback> feedbackList = new ArrayList<>();

        FeedbackDao feedbackDao = Mockito.mock(FeedbackDao.class);
        Mockito.when(feedbackDao.findFeedbacksByStatus(feedbackStatus)).thenReturn(feedbackList);

        Assertions.assertEquals(feedbackList, feedbackDao.findFeedbacksByStatus(feedbackStatus));

    }
}
