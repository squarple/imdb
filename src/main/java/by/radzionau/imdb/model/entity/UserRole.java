package by.radzionau.imdb.model.entity;

/**
 * The enum User role entity.
 */
public enum UserRole {
    /**
     * User role.
     */
    USER(1),
    /**
     * Admin role.
     */
    ADMIN(2);

    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id of user role
     */
    public int getId() {
        return id;
    }
}
