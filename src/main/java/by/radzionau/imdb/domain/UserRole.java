package by.radzionau.imdb.domain;

public enum UserRole {
    USER(1),
    ADMIN(2);

    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
