package by.radzionau.imdb.opr;

import by.radzionau.imdb.model.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class PairUtil {
    public static List<Pair> getPairs(List<Movie> movieList) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            if (i < movieList.size() - 1) {
                for (int j = i+1; j < movieList.size(); j++) {
                    pairs.add(new Pair(movieList.get(i), movieList.get(j)));
                }
            }
        }
        return pairs;
    }
}
