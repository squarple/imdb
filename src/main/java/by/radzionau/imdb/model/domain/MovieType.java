package by.radzionau.imdb.model.domain;

public enum MovieType {
    FILM(1),
    SERIAL(2);

    private final int id;

    MovieType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
