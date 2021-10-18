package test.radzionau.imdb.model.validator;

import by.radzionau.imdb.model.entity.Feedback;
import by.radzionau.imdb.model.entity.FeedbackStatus;
import by.radzionau.imdb.model.validator.FeedbackValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class FeedbackValidatorTest {
    @Test
    void isValid_True() {
        Feedback feedback = Feedback.builder()
                .setFeedbackId(1L)
                .setFeedbackDate(LocalDateTime.now())
                .setScore(10)
                .setContent("content")
                .setMovieId(1L)
                .setUserId(1L)
                .setFeedbackStatus(FeedbackStatus.APPROVED)
                .build();

        Assertions.assertTrue(FeedbackValidator.getInstance().isValid(feedback));
    }

    @Test
    void isValid_False() {
        Feedback feedback = Feedback.builder()
                .setFeedbackId(1L)
                .setFeedbackDate(null)
                .setScore(10)
                .setContent("content")
                .setMovieId(1L)
                .setUserId(1L)
                .setFeedbackStatus(FeedbackStatus.APPROVED)
                .build();

        Assertions.assertFalse(FeedbackValidator.getInstance().isValid(feedback));
    }
}
