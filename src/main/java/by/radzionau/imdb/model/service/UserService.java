package by.radzionau.imdb.model.service;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.User;
import by.radzionau.imdb.model.entity.UserRole;
import by.radzionau.imdb.model.entity.UserStatus;

import java.util.List;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;

    User signUp(String login, String password, String repeatedPassword, String mail, String firstName, String surname) throws ServiceException;

    User updateStatus(User user, UserStatus userStatus) throws ServiceException;

    User updateRole(User user, UserRole userRole) throws ServiceException;

    List<User> findUsersByStatus(UserStatus userStatus) throws ServiceException;

    List<User> findUsersByRole(UserRole userRole) throws ServiceException;

    User findUserByLogin(String login) throws ServiceException;

    List<User> findAll() throws ServiceException;
}
