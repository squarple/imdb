package by.radzionau.imdb.dao;

import by.radzionau.imdb.domain.User;
import by.radzionau.imdb.domain.UserRole;
import by.radzionau.imdb.domain.UserStatus;
import by.radzionau.imdb.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    int add(User user, String hashedPassword) throws DaoException;

    int update(User user) throws DaoException;

    Optional<User> findUserByLogin(String login) throws DaoException;

    Optional<String> findUserPasswordByLogin(String login) throws DaoException;

    List<User> findUsersByStatus(UserStatus userStatus) throws DaoException;

    List<User> findUsersByRole(UserRole userRole) throws DaoException;
}
