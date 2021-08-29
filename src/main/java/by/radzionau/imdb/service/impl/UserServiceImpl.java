package by.radzionau.imdb.service.impl;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.dao.UserDao;
import by.radzionau.imdb.model.dao.impl.UserDaoImpl;
import by.radzionau.imdb.model.domain.User;
import by.radzionau.imdb.model.domain.UserRole;
import by.radzionau.imdb.model.domain.UserStatus;
import by.radzionau.imdb.service.UserService;
import by.radzionau.imdb.utils.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {

    }

    private static final class UserServiceInstanceHolder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserService getInstance() {
        return UserServiceInstanceHolder.INSTANCE;
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        //todo validation
        Optional<User> user;
        try {
            Optional<String> hashedPassword = userDao.findUserPasswordByLogin(login);
            if (hashedPassword.isPresent()) {
                user = userDao.findUserByLogin(login);
                if (user.isPresent() && PasswordEncryptor.checkPassword(password, hashedPassword.get())) {
                    return user.get();
                }
            }
        } catch (DaoException e) {
            logger.error("Can't handle signIn request at UserService", e);
            throw new ServiceException("Can't handle signIn request at UserService", e);
        }
        throw new ServiceException("Invalid login or password");
    }

    @Override
    public User signUp(String login, String password, String mail, String firstName, String surname) throws ServiceException {
        //todo validation
        User user = new User(login, mail, firstName, surname, UserRole.USER, UserStatus.NON_ACTIVATED);
        String hashedPassword = PasswordEncryptor.encryptPassword(password);
        try {
            userDao.add(user, hashedPassword);
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
