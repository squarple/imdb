package test.radzionau.imdb.model.validator;

import by.radzionau.imdb.model.entity.Genre;
import by.radzionau.imdb.model.validator.GenreValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

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

    @Test
    void isGenrePresence_True() {
        String genreName = "genre";

        GenreValidator genreValidator = Mockito.mock(GenreValidator.class);
        when(genreValidator.isGenrePresence(genreName)).thenReturn(true);

        boolean actual = genreValidator.isGenrePresence(genreName);
        Assertions.assertTrue(actual);
    }

    @Test
    void isGenrePresence_False() {
        String genreName = "genre";

        GenreValidator genreValidator = Mockito.mock(GenreValidator.class);
        when(genreValidator.isGenrePresence(genreName)).thenReturn(false);

        boolean actual = genreValidator.isGenrePresence(genreName);
        Assertions.assertFalse(actual);
    }
}
