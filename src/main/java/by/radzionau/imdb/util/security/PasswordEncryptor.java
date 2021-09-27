package by.radzionau.imdb.util.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {
    private static final PasswordEncryptor INSTANCE = new PasswordEncryptor();
    private PasswordEncryptor() {

    }

    public static PasswordEncryptor getInstance() {
        return INSTANCE;
    }

    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
