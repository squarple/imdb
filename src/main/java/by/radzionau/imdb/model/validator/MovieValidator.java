package by.radzionau.imdb.model.validator;

import by.radzionau.imdb.model.entity.Movie;

/**
 * The class Movie validator.
 */
public class MovieValidator implements Validator {
    private static final MovieValidator INSTANCE = new MovieValidator();

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
        if (object == null)
            return false;
        if (object instanceof Movie) {
            Movie movie = (Movie) object;
            if (movie.getTitle() == null || movie.getTitle().isEmpty())
                return false;
            if (movie.getLogline() == null || movie.getLogline().isEmpty())
                return false;
            if (movie.getMovieType() == null)
                return false;
        } else {
            return false;
        }
        return true;
    }
}
