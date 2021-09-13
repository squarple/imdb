package by.radzionau.imdb.model.domain;

import java.time.LocalDateTime;

public class Feedback {
    private Long feedbackId = -1L;
    private LocalDateTime feedbackDate;
    private int score;
    private String content;
    private Long movieId;
    private Long userId;
    private FeedbackStatus feedbackStatus;

    public Feedback() {

    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public LocalDateTime getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(LocalDateTime feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public FeedbackStatus getFeedbackStatus() {
        return feedbackStatus;
    }

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Feedback feedback;

        public Builder() {
            feedback = new Feedback();
        }

        public Builder setFeedbackId(Long feedbackId) {
            feedback.setFeedbackId(feedbackId);
            return this;
        }

        public Builder setFeedbackDate(LocalDateTime feedbackDate) {
            feedback.setFeedbackDate(feedbackDate);
            return this;
        }

        public Builder setScore(int score) {
            feedback.setScore(score);
            return this;
        }

        public Builder setContent(String content) {
            feedback.setContent(content);
            return this;
        }

        public Builder setMovieId(Long movieId) {
            feedback.setMovieId(movieId);
            return this;
        }

        public Builder setUserId(Long userId) {
            feedback.setUserId(userId);
            return this;
        }

        public Builder setFeedbackStatus(FeedbackStatus feedbackStatus) {
            feedback.setFeedbackStatus(feedbackStatus);
            return this;
        }

        public Feedback build() {
            return feedback;
        }
    }
}
