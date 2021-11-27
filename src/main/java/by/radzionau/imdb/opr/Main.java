package by.radzionau.imdb.opr;

import by.radzionau.imdb.model.entity.Movie;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(getMovie("film 1"));
        movieList.add(getMovie("film 2"));
        movieList.add(getMovie("film 3"));
        movieList.add(getMovie("film 4"));
        movieList.add(getMovie("film 5"));

        List<Pair> pairs = PairUtil.getPairs(movieList);

        HashMap<Pair, Integer> scores = new HashMap<>();
        for (int i = 0; i < pairs.size(); i++) {
            int val = new Random().nextInt();
            val = val > 0 ? 1 : 0;
            scores.put(pairs.get(i), val);
        }

        int[][] matrix = buildMatrix(scores, movieList);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

        int[] prices = new int[movieList.size()];
        for (int i = 0; i < prices.length; i++) {
            int price = 0;
            for (int j = 0; j < matrix.length; j++) {
                price += matrix[i][j];
            }
            prices[i] = price;
        }

        double[] norm_prices = new double[prices.length];
        int prices_sum = Arrays.stream(prices).sum();
        for (int i = 0; i < norm_prices.length; i++) {
            norm_prices[i] = ((double)prices[i]) / prices_sum;
        }

        List<Movie> ratingList = new ArrayList<>();

    }

    public static Movie getMovie(String name) {
        Movie movie = new Movie();
        movie.setTitle(name);
        return movie;
    }

    public static int[][] buildMatrix(HashMap<Pair, Integer> scores, List<Movie> movieList) {
        int n = movieList.size();
        int[][] matrix = new int[n][n];

        for (Pair pair : scores.keySet()) {
            matrix[movieList.indexOf(pair.getMovie1())][movieList.indexOf(pair.getMovie2())] = scores.get(pair);
            matrix[movieList.indexOf(pair.getMovie2())][movieList.indexOf(pair.getMovie1())] = 1 - scores.get(pair);
        }

        return matrix;
    }
}
