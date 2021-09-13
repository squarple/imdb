package by.radzionau.imdb.model.domain;

public class Genre {
    private Long genreId = -1L;
    private String name;

    public Genre() {

    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Genre genre;

        public Builder() {
            genre = new Genre();
        }

        public Builder setGenreId(Long genreId) {
            genre.setGenreId(genreId);
            return this;
        }

        public Builder setName(String name) {
            genre.setName(name);
            return this;
        }

        public Genre build() {
            return genre;
        }
    }
}
