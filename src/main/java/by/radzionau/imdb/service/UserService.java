package by.radzionau.imdb.service;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.User;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;

    User signUp(String login, String password, String mail, String firstName, String surname) throws ServiceException;
}
