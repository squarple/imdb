package by.radzionau.imdb.model.validator;

public class MovieValidator implements Validator {
    private static final MovieValidator INSTANCE = new MovieValidator();

    private MovieValidator() {

    }

    public static MovieValidator getInstance() {
        return INSTANCE;
    }
}
