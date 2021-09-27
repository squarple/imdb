package by.radzionau.imdb.model.validator;

public class FeedbackValidator implements Validator {
    private static final FeedbackValidator INSTANCE = new FeedbackValidator();
    private FeedbackValidator() {

    }

    public static FeedbackValidator getInstance() {
        return INSTANCE;
    }
}
