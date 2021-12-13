package by.radzionau.imdb.controller.command.impl.opr;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.Router;
import by.radzionau.imdb.controller.command.SessionAttribute;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.opr.Pair;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ResultsOfPairedComparisonsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ResultsOfPairedComparisonsCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try{
            List<Movie> movieList = (List<Movie>)request.getSession().getAttribute(SessionAttribute.MOVIES_LIST);
            List<Pair> pairList = (List<Pair>)request.getSession().getAttribute(SessionAttribute.PAIR_LIST);
            List<Integer> scores = getScores(request);
            int n = movieList.size();
            int[][] matrix = new int[n][n];

            for(Pair pair : pairList) {
                matrix[movieList.indexOf(pair.getMovie1())][movieList.indexOf(pair.getMovie2())] = scores.get(pairList.indexOf(pair));
                matrix[movieList.indexOf(pair.getMovie2())][movieList.indexOf(pair.getMovie1())] = 1 - scores.get(pairList.indexOf(pair));
            }

            double[] normPrices = getNormPrices(n, matrix);
            List<WeightMoviePair> weightMoviePairList = new ArrayList<>();
            for (int i = 0; i < normPrices.length; i++) {
                weightMoviePairList.add(new WeightMoviePair(normPrices[i], movieList.get(i)));
            }
            weightMoviePairList.sort((e1,e2) -> e2.getWeight().compareTo(e1.getWeight()));

            request.setAttribute("matrix", matrix);
            request.setAttribute("norm_prices", weightMoviePairList);
            router = new Router(PagePath.RESULTS_OF_PAIRED_COMPARISONS_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (Exception e) {
            logger.error("Error at ResultsOfPairedComparisonsCommand", e);
            router = new Router(null, null);
        }
        return router;
    }

    private List<Integer> getScores(HttpServletRequest request) {
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            scores.add(Integer.parseInt(request.getParameter(Integer.toString(i))));
        }
        return scores;
    }

    private double[] getNormPrices(int n, int[][] matrix) {
        int[] prices = new int[n];
        for (int i = 0; i < prices.length; i++) {
            int price = 0;
            for (int j = 0; j < matrix.length; j++) {
                price += matrix[i][j];
            }
            prices[i] = price;
        }

        double[] normPrices = new double[prices.length];
        int pricesSum = Arrays.stream(prices).sum();
        for (int i = 0; i < normPrices.length; i++) {
            normPrices[i] = ((double)prices[i]) / pricesSum;
        }

        return normPrices;
    }

    public static class WeightMoviePair {
        private Double weight;
        private Movie movie;

        public WeightMoviePair(Double weight, Movie movie) {
            this.weight = weight;
            this.movie = movie;
        }

        public Double getWeight() {
            return weight;
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }

        public Movie getMovie() {
            return movie;
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
        }
    }
}
