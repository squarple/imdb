package test.radzionau.imdb.model.validator;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.User;
import by.radzionau.imdb.model.entity.UserRole;
import by.radzionau.imdb.model.entity.UserStatus;
import by.radzionau.imdb.model.service.UserService;
import by.radzionau.imdb.model.validator.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    @Test
    void isLoginPresence_True() throws ServiceException {
        String login = "login";
        User expectedUser = User.builder()
                .setLogin("login")
                .setEmail("email")
                .setName("name")
                .setSurname("surname")
                .setRole(UserRole.USER)
                .setStatus(UserStatus.ACTIVATED)
                .build();

        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userService.findUserByLogin(login)).thenReturn(expectedUser);

        User actualUser = userService.findUserByLogin(login);

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void isLoginPresence_False() throws ServiceException {
        String login = "login";

        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userService.findUserByLogin(login)).thenThrow(ServiceException.class);

        Assertions.assertThrows(ServiceException.class, () -> userService.findUserByLogin(login));
    }
}
