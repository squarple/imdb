package by.radzionau.imdb.model.entity;

/**
 * The enum User status entity.
 */
public enum UserStatus {
    /**
     * Non activated user status.
     */
    NON_ACTIVATED(1),
    /**
     * Activated user status.
     */
    ACTIVATED(2),
    /**
     * Banned user status.
     */
    BANNED(3);

    private int id;

    UserStatus(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id of user status
     */
    public int getId() {
        return id;
    }
}
