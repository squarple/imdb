package test.radzionau.imdb.model.validator;

import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.entity.MovieType;
import by.radzionau.imdb.model.validator.MovieValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MovieValidatorTest {
    @Test
    void isValid_True() {
        Movie movie = Movie.builder()
                .setTitle("title")
                .setLogline("logline")
                .setMovieType(MovieType.FILM)
                .build();
        Assertions.assertTrue(MovieValidator.getInstance().isValid(movie));
    }

    @Test
    void isValid_False() {
        Movie movie = Movie.builder()
                .setTitle(null)
                .setLogline("logline")
                .setMovieType(MovieType.FILM)
                .build();
        Assertions.assertFalse(MovieValidator.getInstance().isValid(movie));
    }
}
