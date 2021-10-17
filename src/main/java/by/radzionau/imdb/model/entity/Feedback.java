package by.radzionau.imdb.model.entity;

import java.time.LocalDateTime;

/**
 * The Feedback entity.
 */
public class Feedback {
    private Long feedbackId = -1L;
    private LocalDateTime feedbackDate;
    private int score;
    private String content;
    private Long movieId;
    private Long userId;
    private FeedbackStatus feedbackStatus;

    /**
     * Gets feedback id.
     *
     * @return the feedback id
     */
    public Long getFeedbackId() {
        return feedbackId;
    }

    /**
     * Sets feedback id.
     *
     * @param feedbackId the feedback id
     */
    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     * Gets feedback date.
     *
     * @return the feedback date
     */
    public LocalDateTime getFeedbackDate() {
        return feedbackDate;
    }

    /**
     * Sets feedback date.
     *
     * @param feedbackDate the feedback date
     */
    public void setFeedbackDate(LocalDateTime feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets movie id.
     *
     * @return the movie id
     */
    public Long getMovieId() {
        return movieId;
    }

    /**
     * Sets movie id.
     *
     * @param movieId the movie id
     */
    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets feedback status.
     *
     * @return the feedback status
     */
    public FeedbackStatus getFeedbackStatus() {
        return feedbackStatus;
    }

    /**
     * Sets feedback status.
     *
     * @param feedbackStatus the feedback status
     */
    public void setFeedbackStatus(FeedbackStatus feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Feedback feedback = (Feedback) o;

        return feedbackId.equals(feedback.feedbackId)
                && feedbackDate.equals(feedback.feedbackDate)
                && score == feedback.score
                && content.equals(feedback.content)
                && movieId.equals(feedback.movieId)
                && userId.equals(feedback.userId)
                && feedbackStatus.equals(feedback.feedbackStatus);
    }

    @Override
    public int hashCode() {
        int result = feedbackId.hashCode();
        result = result * 31 + feedbackDate.hashCode();
        result = result * 31 + Integer.hashCode(score);
        result = result * 31 + content.hashCode();
        result = result * 31 + movieId.hashCode();
        result = result * 31 + userId.hashCode();
        result = result * 31 + feedbackStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Feedback{")
                .append("feedbackId=").append(feedbackId)
                .append(", feedbackDate=").append(feedbackDate)
                .append(", score=").append(score)
                .append(", content=").append(content)
                .append(", movieId=").append(movieId)
                .append(", userId=").append(userId)
                .append(", feedbackStatus=").append(feedbackStatus)
                .append('}');
        return sb.toString();
    }

    /**
     * Gets builder.
     *
     * @return the builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The Builder for Feedback.
     */
    public static class Builder {
        private final Feedback feedback;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            feedback = new Feedback();
        }

        /**
         * Sets feedback id.
         *
         * @param feedbackId the feedback id
         * @return the feedback id
         */
        public Builder setFeedbackId(Long feedbackId) {
            feedback.setFeedbackId(feedbackId);
            return this;
        }

        /**
         * Sets feedback date.
         *
         * @param feedbackDate the feedback date
         * @return the feedback date
         */
        public Builder setFeedbackDate(LocalDateTime feedbackDate) {
            feedback.setFeedbackDate(feedbackDate);
            return this;
        }

        /**
         * Sets score.
         *
         * @param score the score
         * @return the score
         */
        public Builder setScore(int score) {
            feedback.setScore(score);
            return this;
        }

        /**
         * Sets content.
         *
         * @param content the content
         * @return the content
         */
        public Builder setContent(String content) {
            feedback.setContent(content);
            return this;
        }

        /**
         * Sets movie id.
         *
         * @param movieId the movie id
         * @return the movie id
         */
        public Builder setMovieId(Long movieId) {
            feedback.setMovieId(movieId);
            return this;
        }

        /**
         * Sets user id.
         *
         * @param userId the user id
         * @return the user id
         */
        public Builder setUserId(Long userId) {
            feedback.setUserId(userId);
            return this;
        }

        /**
         * Sets feedback status.
         *
         * @param feedbackStatus the feedback status
         * @return the feedback status
         */
        public Builder setFeedbackStatus(FeedbackStatus feedbackStatus) {
            feedback.setFeedbackStatus(feedbackStatus);
            return this;
        }

        /**
         * Build feedback.
         *
         * @return the feedback
         */
        public Feedback build() {
            return feedback;
        }
    }
}
