package by.radzionau.imdb.model.service.impl;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.dao.UserDao;
import by.radzionau.imdb.model.dao.impl.UserDaoImpl;
import by.radzionau.imdb.model.entity.User;
import by.radzionau.imdb.model.entity.UserRole;
import by.radzionau.imdb.model.entity.UserStatus;
import by.radzionau.imdb.model.service.UserService;
import by.radzionau.imdb.model.validator.UserValidator;
import by.radzionau.imdb.util.security.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The implementation of UserService interface.
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {

    }

    private static final class UserServiceInstanceHolder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    /**
     * Gets instance of user service.
     *
     * @return the instance of user service
     */
    public static UserService getInstance() {
        return UserServiceInstanceHolder.INSTANCE;
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        if (isStringValid(login) && isStringValid(password)) {
            Optional<User> user;
            try {
                Optional<String> hashedPassword = userDao.findUserPasswordByLogin(login);
                if (hashedPassword.isPresent()) {
                    user = userDao.findUserByLogin(login);
                    if (user.isPresent() && PasswordEncryptor.getInstance().checkPassword(password, hashedPassword.get())) {
                        return user.get();
                    }
                }
            } catch (DaoException e) {
                logger.error("Can't handle signIn request at UserService", e);
                throw new ServiceException("Can't handle signIn request at UserService", e);
            }
        }
        logger.error("Invalid login or password");
        throw new ServiceException("Invalid login or password");
    }

    @Override
    public User signUp(String login, String password, String repeatedPassword, String mail, String firstName, String surname) throws ServiceException {
        if (!isStringValid(login, password, repeatedPassword, mail, firstName, surname)) {
            logger.error("Invalid parameters");
            throw new ServiceException("Invalid parameters");
        }

        User user = User.builder()
                .setLogin(login)
                .setEmail(mail)
                .setName(firstName)
                .setSurname(surname)
                .setRole(UserRole.USER)
                .setStatus(UserStatus.NON_ACTIVATED)
                .build();

        String hashedPassword = PasswordEncryptor.getInstance().encryptPassword(password);
        try {
            userDao.add(user, hashedPassword);
            return user;
        } catch (DaoException e) {
            logger.error("Can't handle signUp request at UserService", e);
            throw new ServiceException("Can't handle signUp request at UserService", e);
        }
    }

    @Override
    public User updateStatus(User user, UserStatus userStatus) throws ServiceException {
        if (!UserValidator.getInstance().isValid(user)) {
            logger.error("Invalid user");
            throw new ServiceException("Invalid user");
        }
        if (userStatus == null) {
            logger.error("User status doesn't present");
            throw new ServiceException("User status doesn't present");
        }

        User updatedUser = buildUser(user.getUserId(), user.getLogin(), user.getEmail(),
                user.getName(), user.getSurname(), user.getRole(), userStatus);
        try {
            userDao.update(updatedUser);
        } catch (DaoException e) {
            logger.error("Can't handle updateStatus request at UserService", e);
            throw new ServiceException("Can't handle updateStatus request at UserService", e);
        }
        return updatedUser;
    }

    @Override
    public User updateRole(User user, UserRole userRole) throws ServiceException {
        if (!UserValidator.getInstance().isValid(user)) {
            logger.error("Invalid user");
            throw new ServiceException("Invalid user");
        }
        if (userRole == null) {
            logger.error("User role doesn't present");
            throw new ServiceException("User role doesn't present");
        }

        User updatedUser = buildUser(user.getUserId(), user.getLogin(), user.getEmail(),
                user.getName(), user.getSurname(), userRole, user.getStatus());
        try {
            userDao.update(updatedUser);
        } catch (DaoException e) {
            logger.error("Can't handle updateRole request at UserService", e);
            throw new ServiceException("Can't handle updateRole request at UserService", e);
        }
        return updatedUser;
    }

    @Override
    public User findUserByLogin(String login) throws ServiceException {
        if (!isStringValid(login)) {
            logger.error("Login doesn't present");
            throw new ServiceException("Login doesn't present");
        }
        try {
            Optional<User> optionalUser = userDao.findUserByLogin(login);
            if (optionalUser.isPresent()) {
                return optionalUser.get();
            } else {
                logger.error("User with login={} doesn't exist", login);
                throw new ServiceException("User with login=" + login + " doesn't exist");
            }
        } catch (DaoException e) {
            logger.error("User  with login {} doesn't exist", login, e);
            throw new ServiceException("User with login " + login + " doesn't exist");
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            logger.error("Can't handle findAll request at UserService", e);
            throw new ServiceException("Can't handle findAll request at UserService", e);
        }
        return users;
    }

    private User buildUser(Long userId, String login, String email, String name, String surname, UserRole role, UserStatus userStatus) {
        return User.builder()
                .setUserId(userId)
                .setLogin(login)
                .setEmail(email)
                .setName(name)
                .setSurname(surname)
                .setRole(role)
                .setStatus(userStatus)
                .build();
    }

    private boolean isStringValid(String... params) {
        for (String param : params) {
            if (param != null && !param.isEmpty())
                return true;
        }
        return false;
    }
}
