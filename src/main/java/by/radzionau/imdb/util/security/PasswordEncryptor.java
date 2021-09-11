package by.radzionau.imdb.util.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {
    private PasswordEncryptor() {

    }

    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
