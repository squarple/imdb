package by.radzionau.imdb.model.entity;

/**
 * The Genre entity.
 */
public class Genre {
    private Long genreId = -1L;
    private String name;

    /**
     * Gets genre id.
     *
     * @return the genre id
     */
    public Long getGenreId() {
        return genreId;
    }

    /**
     * Sets genre id.
     *
     * @param genreId the genre id
     */
    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Genre genre = (Genre) o;

        return genreId.equals(genre.genreId)
                && name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        int result = genreId.hashCode();
        result = result * 31 + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Genre{")
                .append("genreId=").append(genreId)
                .append(", name=").append(name)
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
     * The Builder for Genre.
     */
    public static class Builder {
        private final Genre genre;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            genre = new Genre();
        }

        /**
         * Sets genre id.
         *
         * @param genreId the genre id
         * @return the genre id
         */
        public Builder setGenreId(Long genreId) {
            genre.setGenreId(genreId);
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public Builder setName(String name) {
            genre.setName(name);
            return this;
        }

        /**
         * Build genre.
         *
         * @return the genre
         */
        public Genre build() {
            return genre;
        }
    }
}
