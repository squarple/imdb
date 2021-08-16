package by.radzionau.imdb.dao.impl;

import by.radzionau.imdb.dao.UserDao;
import by.radzionau.imdb.domain.User;
import by.radzionau.imdb.domain.UserRole;
import by.radzionau.imdb.domain.UserStatus;
import by.radzionau.imdb.exception.ConnectionPoolException;
import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();

    private static final String SQL_INSERT_USER =
            "INSERT INTO usr (login, password, mail, first_name, surname, usr_role_id, usr_status_id) " +
                    "VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_USER =
            "UPDATE usr SET login=?, mail=?, first_name=?, surname=?, usr_role_id=?, usr_status_id=? " +
                    "WHERE usr_id=?";
    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD =
            "SELECT usr_id, login, mail, first_name, surname, usr_role.name AS role_name, usr_status.name AS user_status " +
                    "FROM usr " +
                    "JOIN usr_role ON usr_role.usr_role_id = usr.usr_role_id " +
                    "JOIN usr_status ON usr_status.usr_status_id = usr.usr_status_id " +
                    "WHERE login=? AND password=?";
    private static final String SQL_SELECT_USERS_BY_STATUS =
            "SELECT usr_id, login, mail, first_name, surname, usr_role.name AS role_name, usr_status.name AS user_status " +
                    "FROM usr " +
                    "JOIN usr_role ON usr_role.usr_role_id = usr.usr_role_id " +
                    "JOIN usr_status ON usr_status.usr_status_id = usr.usr_status_id " +
                    "WHERE usr_status_id=?";
    private static final String SQL_SELECT_USERS_BY_ROLE =
            "SELECT usr_id, login, mail, first_name, surname, usr_role.name AS role_name, usr_status.name AS user_status " +
                    "FROM usr " +
                    "JOIN usr_role ON usr_role.usr_role_id = usr.usr_role_id " +
                    "JOIN usr_status ON usr_status.usr_status_id = usr.usr_status_id " +
                    "WHERE usr_role_id=?";

    @Override
    public void add(User user, String password) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER)
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, password);   //todo зашифровать!!!
            statement.setString(3, user.getMail());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setLong(6, user.getRole().getId());
            statement.setLong(7, user.getStatus().getId());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a user={}", user);
            throw new DaoException("Error while adding a user=" + user, e);
        }
    }

    @Override
    public void update(User user) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getMail());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setLong(5, user.getRole().getId());
            statement.setLong(6, user.getStatus().getId());
            statement.setLong(7, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a user={}", user);
            throw new DaoException("Error while updating a user=" + user, e);
        }
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD)
        ) {
            statement.setString(1, login);
            statement.setString(2, password);   //todo зашифровать!!!
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        UserRole.valueOf(resultSet.getString(6).toUpperCase()),
                        UserStatus.valueOf(resultSet.getString(7).toUpperCase())
                ));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a user");
            throw new DaoException("Error while selecting a user", e);
        }

        return Optional.empty();
    }

    @Override
    public List<User> findUsersByStatus(UserStatus userStatus) throws DaoException {
        List<User> users = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_BY_STATUS)
        ) {
            statement.setLong(1, userStatus.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        UserRole.valueOf(resultSet.getString(6).toUpperCase()),
                        UserStatus.valueOf(resultSet.getString(7).toUpperCase())
                ));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a users");
            throw new DaoException("Error while selecting a users", e);
        }

        return users;
    }

    @Override
    public List<User> findUsersByRole(UserRole userRole) throws DaoException {
        List<User> users = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_BY_ROLE)
        ) {
            statement.setLong(1, userRole.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        UserRole.valueOf(resultSet.getString(6).toUpperCase()),
                        UserStatus.valueOf(resultSet.getString(7).toUpperCase())
                ));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a users");
            throw new DaoException("Error while selecting a users", e);
        }

        return users;
    }
}
