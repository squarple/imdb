package by.radzionau.imdb.model.validator;

import by.radzionau.imdb.model.entity.Movie;

/**
 * The class Movie validator.
 */
public final class MovieValidator implements Validator {
    private static final MovieValidator INSTANCE = new MovieValidator();

    private MovieValidator() {

    }

    /**
     * Gets instance.
     *
     * @return the instance of movie validator
     */
    public static MovieValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValid(Object object) {
        if (object == null || object.getClass() != Movie.class)
            return false;
        Movie movie = (Movie) object;
        return movie.getTitle() != null
                && !movie.getTitle().isEmpty()
                && movie.getLogline() != null
                && !movie.getLogline().isEmpty()
                && movie.getMovieType() != null;
    }
}
