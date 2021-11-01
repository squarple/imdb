package by.radzionau.imdb.model.validator;

import by.radzionau.imdb.model.entity.Feedback;

/**
 * The class Feedback validator.
 */
public final class FeedbackValidator implements Validator {
    private static final FeedbackValidator INSTANCE = new FeedbackValidator();

    private FeedbackValidator() {

    }

    /**
     * Gets instance.
     *
     * @return the instance of feedback validator
     */
    public static FeedbackValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValid(Object object) {
        if (object == null || object.getClass() != Feedback.class)
            return false;
        Feedback feedback = (Feedback) object;
        return feedback.getFeedbackDate() != null
                && feedback.getMovieId() != null
                && feedback.getUserId() != null
                && feedback.getFeedbackStatus() != null;
    }
}
