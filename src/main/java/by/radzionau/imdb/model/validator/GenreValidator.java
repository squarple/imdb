package by.radzionau.imdb.model.validator;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.service.GenreService;
import by.radzionau.imdb.model.service.impl.GenreServiceImpl;

/**
 * The class Genre validator.
 */
public final class GenreValidator implements Validator{
    private static final GenreValidator INSTANCE = new GenreValidator();

    private GenreValidator() {

    }

    /**
     * Gets instance.
     *
     * @return the instance of genre validator
     */
    public static GenreValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValid(Object object) {
        if (object == null || object.getClass() != Genre.class)
            return false;
        Genre genre = (Genre) object;
        return genre.getName() != null
                && !genre.getName().isEmpty();
    }
}
