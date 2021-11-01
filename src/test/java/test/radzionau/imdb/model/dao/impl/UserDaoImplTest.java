package test.radzionau.imdb.model.dao.impl;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.dao.UserDao;
import by.radzionau.imdb.model.entity.User;
import by.radzionau.imdb.model.entity.UserRole;
import by.radzionau.imdb.model.entity.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class UserDaoImplTest {
    private UserDao userDao;

    @BeforeEach
    void init() {
        userDao = Mockito.mock(UserDao.class);
    }

    @Test
    void add_Positive() {
        User user = new User();
        String hashedPassword = "passwd";
        try {
            Mockito.when(userDao.add(user, hashedPassword)).thenReturn(1);
            int actualResult = userDao.add(user, hashedPassword);
            Assertions.assertEquals(1, actualResult);
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void add_Negative() {
        User user = new User();
        String hashedPassword = "passwd";
        try {
            Mockito.when(userDao.add(user, hashedPassword)).thenReturn(0);
            int actualResult = userDao.add(user, hashedPassword);
            Assertions.assertEquals(0, actualResult);
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void update_Positive() {
        User user = new User();
        try {
            Mockito.when(userDao.update(user)).thenReturn(1);
            int actualResult = userDao.update(user);
            Assertions.assertEquals(1, actualResult);
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void update_Negative() {
        User user = new User();
        try {
            Mockito.when(userDao.update(user)).thenReturn(0);
            int actualResult = userDao.update(user);
            Assertions.assertEquals(0, actualResult);
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findUserByLogin_Positive() {
        User user = new User();
        String login = "login";
        try {
            Mockito.when(userDao.findUserByLogin(login)).thenReturn(Optional.of(user));
            Optional<User> actualUser = userDao.findUserByLogin(login);
            Assertions.assertTrue(actualUser.isPresent());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findUserByLogin_Negative() {
        String login = "login";
        try {
            Mockito.when(userDao.findUserByLogin(login)).thenReturn(Optional.empty());
            Optional<User> actualUser = userDao.findUserByLogin(login);
            Assertions.assertFalse(actualUser.isPresent());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findUserPasswordByLogin_Positive() {
        String login = "login";
        String password = "password";
        try {
            Mockito.when(userDao.findUserPasswordByLogin(login)).thenReturn(Optional.of(password));
            Optional<String> actualPassword = userDao.findUserPasswordByLogin(login);
            Assertions.assertTrue(actualPassword.isPresent());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findUserPasswordByLogin_Negative() {
        String login = "login";
        try {
            Mockito.when(userDao.findUserPasswordByLogin(login)).thenReturn(Optional.empty());
            Optional<String> actualUser = userDao.findUserPasswordByLogin(login);
            Assertions.assertFalse(actualUser.isPresent());
        } catch (DaoException e) {
            Assertions.fail();
        }
    }

    @Test
    void findAll_Positive() {
        List<User> userList = new ArrayList<>();
        try {
            Mockito.when(userDao.findAll()).thenReturn(userList);
            List<User> actualUserList = userDao.findAll();
            Assertions.assertEquals(userList, actualUserList);
        } catch (DaoException e) {
            Assertions.fail();
        }
    }
}
