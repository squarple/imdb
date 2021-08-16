package by.radzionau.imdb.dao;

import by.radzionau.imdb.domain.User;
import by.radzionau.imdb.domain.UserRole;
import by.radzionau.imdb.domain.UserStatus;
import by.radzionau.imdb.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void add(User user, String password) throws DaoException; //fixme правильная ли реализация?

    void update(User user) throws DaoException;

    //void delete(User user) throws DaoException; //todo удалять или давать роль BANNED ???

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    List<User> findUsersByStatus(UserStatus userStatus) throws DaoException;

    List<User> findUsersByRole(UserRole userRole) throws DaoException;
}
