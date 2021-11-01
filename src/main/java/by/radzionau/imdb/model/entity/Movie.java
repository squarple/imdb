package by.radzionau.imdb.model.entity;

/**
 * The Movie entity.
 */
public class Movie {
    private Long movieId = -1L;
    private String title;
    private String logline;
    private int releaseYear;
    private String cover;
    private MovieType movieType;

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
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets logline.
     *
     * @return the logline
     */
    public String getLogline() {
        return logline;
    }

    /**
     * Sets logline.
     *
     * @param logline the logline
     */
    public void setLogline(String logline) {
        this.logline = logline;
    }

    /**
     * Gets release year.
     *
     * @return the release year
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets release year.
     *
     * @param releaseYear the release year
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets cover.
     *
     * @return the cover
     */
    public String getCover() {
        return cover;
    }

    /**
     * Sets cover.
     *
     * @param cover the cover
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * Gets movie type.
     *
     * @return the movie type
     */
    public MovieType getMovieType() {
        return movieType;
    }

    /**
     * Sets movie type.
     *
     * @param movieType the movie type
     */
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

    /**
     * Gets builder.
     *
     * @return the builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The Builder for Movie.
     */
    public static class Builder {
        private final Movie movie;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            movie = new Movie();
        }

        /**
         * Sets movie id.
         *
         * @param movieId the movie id
         * @return the movie id
         */
        public Builder setMovieId(Long movieId) {
            movie.setMovieId(movieId);
            return this;
        }

        /**
         * Sets title.
         *
         * @param title the title
         * @return the title
         */
        public Builder setTitle(String title) {
            movie.setTitle(title);
            return this;
        }

        /**
         * Sets logline.
         *
         * @param logline the logline
         * @return the logline
         */
        public Builder setLogline(String logline) {
            movie.setLogline(logline);
            return this;
        }

        /**
         * Sets release year.
         *
         * @param year the year
         * @return the release year
         */
        public Builder setReleaseYear(int year) {
            movie.setReleaseYear(year);
            return this;
        }

        /**
         * Sets cover.
         *
         * @param cover the cover
         * @return the cover
         */
        public Builder setCover(String cover) {
            movie.setCover(cover);
            return this;
        }

        /**
         * Sets movie type.
         *
         * @param movieType the movie type
         * @return the movie type
         */
        public Builder setMovieType(MovieType movieType) {
            movie.setMovieType(movieType);
            return this;
        }

        /**
         * Build movie.
         *
         * @return the movie
         */
        public Movie build() {
            return movie;
        }
    }
}
