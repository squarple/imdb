package by.radzionau.imdb.model.validator;

import by.radzionau.imdb.model.entity.Feedback;

/**
 * The class Feedback validator.
 */
public class FeedbackValidator implements Validator {
    private static final FeedbackValidator INSTANCE = new FeedbackValidator();

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
        if (object == null)
            return false;
        if (object instanceof Feedback) {
            Feedback feedback = (Feedback) object;
            if (feedback.getFeedbackDate() == null)
                return false;
            if (feedback.getMovieId() == null)
                return false;
            if (feedback.getUserId() == null)
                return false;
            if (feedback.getFeedbackStatus() == null)
                return false;
        } else {
            return false;
        }
        return true;
    }
}
