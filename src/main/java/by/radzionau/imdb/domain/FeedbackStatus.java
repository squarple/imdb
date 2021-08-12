package by.radzionau.imdb.domain;

public enum FeedbackStatus {
    UNDER_CONSIDERATION(1),
    APPROVED(2),
    BLOCKED(3);

    private final int id;

    FeedbackStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
