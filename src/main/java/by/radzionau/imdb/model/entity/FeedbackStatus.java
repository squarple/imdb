package by.radzionau.imdb.model.entity;

public enum FeedbackStatus {
    UNDER_CONSIDERATION(1),
    APPROVED(2);

    private final int id;

    FeedbackStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
