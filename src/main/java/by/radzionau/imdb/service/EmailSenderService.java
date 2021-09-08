package by.radzionau.imdb.service;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.User;

public interface EmailSenderService {
    void sendAuthenticationMessage(User user) throws ServiceException;
}
