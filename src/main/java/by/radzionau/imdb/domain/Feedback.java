package by.radzionau.imdb.domain;

import java.sql.Timestamp;

public class Feedback {
    private Long feedbackId;
    private Timestamp feedbackDate; //todo какой хранить формат???
    private int score;
    private String content;
    private Long movieId;
    private Long userId;
    private FeedbackStatus feedbackStatus;

    public Feedback() {

    }

    public Feedback(Long feedbackId, Timestamp feedbackDate, int score, String content, Long movieId, Long userId, FeedbackStatus feedbackStatus) {
        this.feedbackId = feedbackId;
        this.feedbackDate = feedbackDate;
        this.score = score;
        this.content = content;
        this.movieId = movieId;
        this.userId = userId;
        this.feedbackStatus = feedbackStatus;
    }

    public Feedback(Timestamp feedbackDate, int score, String content, Long movieId, Long userId, FeedbackStatus feedbackStatus) {
        this.feedbackId = 0L; //fixme инициализация id, если его нету, как надо делать???
        this.feedbackDate = feedbackDate;
        this.score = score;
        this.content = content;
        this.movieId = movieId;
        this.userId = userId;
        this.feedbackStatus = feedbackStatus;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public Timestamp getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Timestamp feedbackDate) {
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
}
