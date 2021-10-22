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
    void add_Positive() throws DaoException {
        User user = new User();
        String hashedPassword = "passwd";
        Mockito.when(userDao.add(user, hashedPassword)).thenReturn(1);
        int actualResult = userDao.add(user, hashedPassword);
        Assertions.assertEquals(1, actualResult);
    }

    @Test
    void add_Negative() throws DaoException {
        User user = new User();
        String hashedPassword = "passwd";
        Mockito.when(userDao.add(user, hashedPassword)).thenReturn(0);
        int actualResult = userDao.add(user, hashedPassword);
        Assertions.assertEquals(0, actualResult);
    }

    @Test
    void update_Positive() throws DaoException {
        User user = new User();
        Mockito.when(userDao.update(user)).thenReturn(1);
        int actualResult = userDao.update(user);
        Assertions.assertEquals(1, actualResult);
    }

    @Test
    void update_Negative() throws DaoException {
        User user = new User();
        Mockito.when(userDao.update(user)).thenReturn(0);
        int actualResult = userDao.update(user);
        Assertions.assertEquals(0, actualResult);
    }

    @Test
    void findUserByLogin_Positive() throws DaoException {
        User user = new User();
        String login = "login";
        Mockito.when(userDao.findUserByLogin(login)).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.findUserByLogin(login);
        Assertions.assertTrue(actualUser.isPresent());
    }

    @Test
    void findUserByLogin_Negative() throws DaoException {
        String login = "login";
        Mockito.when(userDao.findUserByLogin(login)).thenReturn(Optional.empty());
        Optional<User> actualUser = userDao.findUserByLogin(login);
        Assertions.assertFalse(actualUser.isPresent());
    }

    @Test
    void findUserPasswordByLogin_Positive() throws DaoException {
        String login = "login";
        String password = "password";
        Mockito.when(userDao.findUserPasswordByLogin(login)).thenReturn(Optional.of(password));
        Optional<String> actualPassword = userDao.findUserPasswordByLogin(login);
        Assertions.assertTrue(actualPassword.isPresent());
    }

    @Test
    void findUserPasswordByLogin_Negative() throws DaoException {
        String login = "login";
        Mockito.when(userDao.findUserPasswordByLogin(login)).thenReturn(Optional.empty());
        Optional<String> actualUser = userDao.findUserPasswordByLogin(login);
        Assertions.assertFalse(actualUser.isPresent());
    }

    @Test
    void findUsersByStatus_Positive() throws DaoException {
        List<User> userList = new ArrayList<>();
        UserStatus userStatus = UserStatus.ACTIVATED;
        Mockito.when(userDao.findUsersByStatus(userStatus)).thenReturn(userList);
        List<User> actualUserList = userDao.findUsersByStatus(userStatus);
        Assertions.assertEquals(userList, actualUserList);
    }

    @Test
    void findUsersByRole_Positive() throws DaoException {
        List<User> userList = new ArrayList<>();
        UserRole userRole = UserRole.USER;
        Mockito.when(userDao.findUsersByRole(userRole)).thenReturn(userList);
        List<User> actualUserList = userDao.findUsersByRole(userRole);
        Assertions.assertEquals(userList, actualUserList);
    }

    @Test
    void findAll_Positive() throws DaoException {
        List<User> userList = new ArrayList<>();
        Mockito.when(userDao.findAll()).thenReturn(userList);
        List<User> actualUserList = userDao.findAll();
        Assertions.assertEquals(userList, actualUserList);
    }
}
