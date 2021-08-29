package by.radzionau.imdb.model.domain;

public enum UserStatus {
    NON_ACTIVATED(1),
    ACTIVATED(2),
    BANNED(3);

    private int id;

    UserStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
