package by.radzionau.imdb.util.mail;

import by.radzionau.imdb.model.entity.User;
import by.radzionau.imdb.util.PropertyLoader;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * The class EmailSenderUtil.
 */
public class EmailSenderUtil {
    private Properties properties;
    private static final String MESSAGE_SUBJECT = "IMDb verification";
    private static final String MESSAGE_CONTENT = "To verify your account, please use the following password: \n";
    private static final String MESSAGE_CONTENT_TYPE = "text/html";

    private static final class EmailSenderUtilInstanceHolder {
        private static final EmailSenderUtil INSTANCE = new EmailSenderUtil();
    }

    private EmailSenderUtil() {

    }

    /**
     * Gets instance.
     *
     * @return the instance of email sender
     */
    public static EmailSenderUtil getInstance() {
        return EmailSenderUtil.EmailSenderUtilInstanceHolder.INSTANCE;
    }

    /**
     * Send authentication message to user. Throws {@link MessagingException} if sending of email failed. Throws {@link IOException} if an error occurred when reading from the input stream.
     *
     * @param user the user
     * @throws MessagingException if sending of email failed
     * @throws IOException        if an error occurred when reading from the input stream
     */
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
        message.setSubject(MESSAGE_SUBJECT);
        message.setContent(MESSAGE_CONTENT + getAuthenticationPassword(user), MESSAGE_CONTENT_TYPE);
        return message;
    }

    private int getAuthenticationPassword(User user) {
        return Math.abs(user.hashCode());
    }
}
