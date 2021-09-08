package by.radzionau.imdb.service.impl;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.domain.User;
import by.radzionau.imdb.service.EmailSenderService;
import by.radzionau.imdb.util.PropertyLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class EmailSenderServiceImpl implements EmailSenderService {
    private static final Logger logger = LogManager.getLogger();
    private Properties properties;

    private static final class EmailSenderServiceInstanceHolder {
        private static final EmailSenderServiceImpl INSTANCE = new EmailSenderServiceImpl();
    }

    public static EmailSenderService getInstance() {
        return EmailSenderServiceImpl.EmailSenderServiceInstanceHolder.INSTANCE;
    }

    private EmailSenderServiceImpl() {

    }

    public void sendAuthenticationMessage(User user) throws ServiceException {
        if (properties == null) {
            setProperties();
        }

        String username = properties.getProperty("mail.username");
        String password = properties.getProperty("mail.password");
        String from = properties.getProperty("mail.username");
        String to = user.getEmail();

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("IMDb verification");
            message.setContent("To verify your email address, please use the following Password:\n" + Math.abs(user.hashCode()), "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Can't handle sendEmail request at EmailService", e);
            throw new ServiceException("Can't handle sendEmail request at EmailService", e);
        }
    }

    private void setProperties() throws ServiceException {
        try {
            properties = PropertyLoader.loadProperty("email/email.properties");
        } catch (IOException e) {
            logger.error("Unable to load resources", e);
            throw new ServiceException("Unable to load resources", e);
        }
    }

}
