package by.radzionau.imdb.domain;

public class Genre {
    private Long genreId;
    private String name;

    public Genre() {

    }

    public Genre(Long genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    public Genre(String name) {
        this.genreId = 0L; //fixme инициализация id, если его нету, как надо делать???
        this.name = name;
    }

    public Long getGenreId() {
        return genreId;
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
}
