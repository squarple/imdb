package by.radzionau.imdb.model.domain;

import java.io.InputStream;

public class Movie {
    private Long movieId = -1L;
    private String title;
    private String logline;
    private int releaseYear;
    private InputStream cover;
    private MovieType movieType;

    public Movie() {

    }

    public Movie(Long movieId, String title, String logline, int releaseYear, InputStream cover, MovieType movieType) {
        this.movieId = movieId;
        this.title = title;
        this.logline = logline;
        this.releaseYear = releaseYear;
        this.cover = cover;
        this.movieType = movieType;
    }

    public Movie(String title, String logline, int releaseYear, InputStream cover, MovieType movieType) {
        this.title = title;
        this.logline = logline;
        this.releaseYear = releaseYear;
        this.cover = cover;
        this.movieType = movieType;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogline() {
        return logline;
    }

    public void setLogline(String logline) {
        this.logline = logline;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public InputStream getCover() {
        return cover;
    }

    public void setCover(InputStream cover) {
        this.cover = cover;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Movie movie = (Movie) o;

        return movieId.equals(movie.movieId)
                && title.equals(movie.title)
                && logline.equals(movie.logline)
                && releaseYear == movie.releaseYear
                && cover.equals(movie.cover)
                && movieType.equals(movie.movieType);
    }

    @Override
    public int hashCode() {
        int result = movieId.hashCode();
        result = result * 31 + title.hashCode();
        result = result * 31 + logline.hashCode();
        result = result * 31 + releaseYear;
        result = result * 31 + cover.hashCode();
        result = result * 31 + movieType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Movie{")
                .append(", movieId=").append(movieId)
                .append(", title=").append(title)
                .append(", logline=").append(logline)
                .append(", releaseYear=").append(releaseYear)
                .append(", cover=").append(cover)
                .append(", movieType=").append(movieType)
                .append('}');
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Movie movie;

        public Builder() {
            movie = new Movie();
        }

        public Builder setMovieId(Long movieId) {
            movie.setMovieId(movieId);
            return this;
        }

        public Builder setTitle(String title) {
            movie.setTitle(title);
            return this;
        }

        public Builder setLogline(String logline) {
            movie.setLogline(logline);
            return this;
        }

        public Builder setReleaseYear(int year) {
            movie.setReleaseYear(year);
            return this;
        }

        public Builder setCover(InputStream cover) {
            movie.setCover(cover);
            return this;
        }

        public Builder setMovieType(MovieType movieType) {
            movie.setMovieType(movieType);
            return this;
        }

        public Movie build() {
            return movie;
        }
    }
}
