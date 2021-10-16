package by.radzionau.imdb.model.entity;

/**
 * The enum Feedback status entity.
 */
public enum FeedbackStatus {
    /**
     * Under consideration feedback status.
     */
    UNDER_CONSIDERATION(1),
    /**
     * Approved feedback status.
     */
    APPROVED(2),
    /**
     * Blocked feedback status.
     */
    BLOCKED(3);

    private final int id;

    FeedbackStatus(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id of status
     */
    public int getId() {
        return id;
    }
}
