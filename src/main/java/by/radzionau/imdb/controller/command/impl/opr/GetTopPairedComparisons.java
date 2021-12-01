package by.radzionau.imdb.controller.command.impl.opr;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.Router;
import by.radzionau.imdb.controller.command.SessionAttribute;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.opr.Pair;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetTopPairedComparisons implements Command {
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

            request.setAttribute("norm_prices", normPrices);
            router = new Router(PagePath.TOP_RESULT_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (Exception e) {
            //todo
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

        double[] norm_prices = new double[prices.length];
        int prices_sum = Arrays.stream(prices).sum();
        for (int i = 0; i < norm_prices.length; i++) {
            norm_prices[i] = ((double)prices[i]) / prices_sum;
        }

        return norm_prices;
    }

}
