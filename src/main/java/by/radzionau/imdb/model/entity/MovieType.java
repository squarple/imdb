package by.radzionau.imdb.model.entity;

/**
 * The enum Movie entity.
 */
public enum MovieType {
    /**
     * Film movie type.
     */
    FILM(1),
    /**
     * Serial movie type.
     */
    SERIAL(2);

    private final int id;

    MovieType(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id of movie type
     */
    public int getId() {
        return id;
    }

}
