package by.radzionau.imdb.util.mail;

import by.radzionau.imdb.model.domain.User;
import by.radzionau.imdb.util.PropertyLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class EmailSenderUtil {
    private static final Logger logger = LogManager.getLogger();
    private Properties properties;

    private static final class EmailSenderUtilInstanceHolder {
        private static final EmailSenderUtil INSTANCE = new EmailSenderUtil();
    }

    public static EmailSenderUtil getInstance() {
        return EmailSenderUtil.EmailSenderUtilInstanceHolder.INSTANCE;
    }

    private EmailSenderUtil() {

    }

    public void sendAuthenticationMessage(User user) throws MessagingException, IOException {
        if (properties == null) {
            setProperties();
        }

        String username = properties.getProperty("mail.username");
        String password = properties.getProperty("mail.password");
        String from = properties.getProperty("mail.username");
        String to = user.getEmail();

        Session session = getSession(properties, username, password);

        Message message = buildMessage(session, user, from, to);
        Transport.send(message);

    }

    private void setProperties() throws IOException {
        properties = PropertyLoader.loadProperty("email/email.properties");
    }

    private Session getSession(Properties properties, String username, String password) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private Message buildMessage(Session session, User user, String from, String to) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("IMDb verification");
        message.setContent("To verify your email address, please use the following Password:\n" + getPassword(user), "text/html");
        return message;
    }

    private int getPassword(User user) {
        return Math.abs(user.hashCode());
    }
}