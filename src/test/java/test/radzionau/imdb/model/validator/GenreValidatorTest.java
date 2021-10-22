package test.radzionau.imdb.model.validator;

import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.validator.GenreValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GenreValidatorTest {
    @Test
    void isValid_True() {
        Genre genre = Genre.builder()
                .setName("genre")
                .build();
        Assertions.assertTrue(GenreValidator.getInstance().isValid(genre));
    }

    @Test
    void isValid_False() {
        Genre genre = Genre.builder()
                .setName("")
                .build();
        Assertions.assertFalse(GenreValidator.getInstance().isValid(genre));
    }
}
