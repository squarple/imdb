package by.radzionau.imdb.domain;

import java.sql.Blob;
import java.time.Year;

public class Movie {
    private Long movieId;
    private String title;
    private String logline;
    private int releaseYear;
    private Blob cover; //todo какой хранить формат? Blob или что-то другое? (может File?)
    private MovieType movieType;

    public Movie() {

    }

    public Movie(Long movieId, String title, String logline, int releaseYear, Blob cover, MovieType movieType) {
        this.movieId = movieId;
        this.title = title;
        this.logline = logline;
        this.releaseYear = releaseYear;
        this.cover = cover;
        this.movieType = movieType;
    }

    public Movie(String title, String logline, int releaseYear, Blob cover, MovieType movieType) {
        this.movieId = 0L; //fixme инициализация id, если его нету, как надо делать???
        this.title = title;
        this.logline = logline;
        this.releaseYear = releaseYear;
        this.cover = cover;
        this.movieType = movieType;
    }

    public Long getMovieId() {
        return movieId;
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

    public Blob getCover() {
        return cover;
    }

    public void setCover(Blob cover) {
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
}