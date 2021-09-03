package by.radzionau.imdb.service;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.User;
import by.radzionau.imdb.model.domain.UserRole;
import by.radzionau.imdb.model.domain.UserStatus;

import java.util.List;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;

    User signUp(String login, String password, String repeatedPassword, String mail, String firstName, String surname) throws ServiceException;

    User updateStatus(User user, UserStatus userStatus) throws ServiceException;

    List<User> getUsersByStatus(UserStatus userStatus) throws ServiceException;

    List<User> getUsersByRole(UserRole userRole) throws ServiceException;
}
