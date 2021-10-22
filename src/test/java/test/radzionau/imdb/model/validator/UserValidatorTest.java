package test.radzionau.imdb.model.validator;

import by.radzionau.imdb.model.entity.User;
import by.radzionau.imdb.model.entity.UserRole;
import by.radzionau.imdb.model.entity.UserStatus;
import by.radzionau.imdb.model.validator.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserValidatorTest {
    @Test
    void isValid_True() {
        User user = User.builder()
                .setLogin("login")
                .setEmail("email")
                .setName("name")
                .setSurname("surname")
                .setRole(UserRole.USER)
                .setStatus(UserStatus.ACTIVATED)
                .build();
        boolean actual = UserValidator.getInstance().isValid(user);
        Assertions.assertTrue(actual);
    }

    @Test
    void isValid_False() {
        User user = User.builder()
                .setLogin("")
                .setEmail("email")
                .setName("name")
                .setSurname("surname")
                .setRole(UserRole.USER)
                .setStatus(UserStatus.ACTIVATED)
                .build();
        boolean actual = UserValidator.getInstance().isValid(user);
        Assertions.assertFalse(actual);
    }

    @Test
    void isEmailValid_True() {
        String email = "antony@gmail.com";
        boolean actual = UserValidator.getInstance().isEmailValid(email);
        Assertions.assertTrue(actual);
    }

    @Test
    void isEmailValid_False() {
        String email = "antony@gmail.";
        boolean actual = UserValidator.getInstance().isEmailValid(email);
        Assertions.assertFalse(actual);
    }
}
