package by.radzionau.imdb.model.validator;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.service.GenreService;
import by.radzionau.imdb.model.service.impl.GenreServiceImpl;

public class GenreValidator implements Validator {
    private static final GenreValidator INSTANCE = new GenreValidator();

    private GenreValidator() {

    }

    public static GenreValidator getInstance() {
        return INSTANCE;
    }

    public boolean isGenrePresence(String genreName) {
        GenreService genreService = GenreServiceImpl.getInstance();
        try {
            genreService.findGenreByName(genreName);
            return true;
        } catch (ServiceException e) {
            return false;
        }
    }
}
