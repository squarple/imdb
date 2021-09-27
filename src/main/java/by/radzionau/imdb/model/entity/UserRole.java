package by.radzionau.imdb.model.entity;

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
