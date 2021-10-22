package test.radzionau.imdb.model.dao.impl;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.dao.FeedbackDao;
import by.radzionau.imdb.model.entity.Feedback;
import by.radzionau.imdb.model.entity.FeedbackStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class FeedbackDaoImplTest {
    private FeedbackDao feedbackDao;

    @BeforeEach
    public void init() {
        feedbackDao = Mockito.mock(FeedbackDao.class);
    }

    @Test
    void add_Positive() {
        Feedback feedback = new Feedback();
        try {
            Mockito.when(feedbackDao.add(feedback)).thenReturn(1);
            Assertions.assertEquals(1, feedbackDao.add(feedback));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void add_Negative() {
        Feedback feedback = new Feedback();
        try {
            Mockito.when(feedbackDao.add(feedback)).thenReturn(0);
            Assertions.assertNotEquals(1, feedbackDao.add(feedback));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void updateFeedbackStatus_Positive() {
        Long feedbackId = 1L;
        FeedbackStatus feedbackStatus = FeedbackStatus.APPROVED;
        try {
            Mockito.when(feedbackDao.updateFeedbackStatus(feedbackId, feedbackStatus)).thenReturn(1);
            Assertions.assertEquals(1, feedbackDao.updateFeedbackStatus(feedbackId, feedbackStatus));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void updateFeedbackStatus_Negative() {
        Long feedbackId = 1L;
        FeedbackStatus feedbackStatus = FeedbackStatus.APPROVED;
        try {
            Mockito.when(feedbackDao.updateFeedbackStatus(feedbackId, feedbackStatus)).thenReturn(0);
            Assertions.assertNotEquals(1, feedbackDao.updateFeedbackStatus(feedbackId, feedbackStatus));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void delete_Positive() {
        Feedback feedback = new Feedback();
        try {
            Mockito.when(feedbackDao.delete(feedback)).thenReturn(1);
            Assertions.assertEquals(1, feedbackDao.delete(feedback));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void delete_Negative() {
        Feedback feedback = new Feedback();
        try {
            Mockito.when(feedbackDao.delete(feedback)).thenReturn(0);
            Assertions.assertNotEquals(1, feedbackDao.delete(feedback));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findFeedbackById_Positive() {
        Long feedbackId = 1L;
        Feedback feedback = new Feedback();
        try {
            Mockito.when(feedbackDao.findFeedbackById(feedbackId)).thenReturn(Optional.of(feedback));
            Optional<Feedback> actualFeedback = feedbackDao.findFeedbackById(feedbackId);
            Assertions.assertTrue(actualFeedback.isPresent());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findFeedbackById_Negative() {
        Long feedbackId = 1L;
        try {
            Mockito.when(feedbackDao.findFeedbackById(feedbackId)).thenReturn(Optional.empty());
            Optional<Feedback> optionalFeedback = feedbackDao.findFeedbackById(feedbackId);
            Assertions.assertFalse(optionalFeedback.isPresent());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findFeedbacksByMovieId_Positive() {
        Long movieId = 1L;
        List<Feedback> feedbackList = new ArrayList<>();
        try {
            Mockito.when(feedbackDao.findFeedbacksByMovieId(movieId)).thenReturn(feedbackList);
            Assertions.assertEquals(feedbackList, feedbackDao.findFeedbacksByMovieId(movieId));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findFeedbacksByStatus_Positive() {
        FeedbackStatus feedbackStatus = FeedbackStatus.APPROVED;
        List<Feedback> feedbackList = new ArrayList<>();
        try {
            Mockito.when(feedbackDao.findFeedbacksByStatus(feedbackStatus)).thenReturn(feedbackList);
            Assertions.assertEquals(feedbackList, feedbackDao.findFeedbacksByStatus(feedbackStatus));
        } catch (DaoException e) {
            Assertions.fail();
        }
    }
}
