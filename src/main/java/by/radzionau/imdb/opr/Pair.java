package by.radzionau.imdb.opr;

import by.radzionau.imdb.model.entity.Movie;

public class Pair {
    private Movie movie1;
    private Movie movie2;

    public Pair(Movie movie1, Movie movie2) {
        this.movie1 = movie1;
        this.movie2 = movie2;
    }

    public Movie getMovie1() {
        return movie1;
    }

    public void setMovie1(Movie movie1) {
        this.movie1 = movie1;
    }

    public Movie getMovie2() {
        return movie2;
    }

    public void setMovie2(Movie movie2) {
        this.movie2 = movie2;
    }
}
